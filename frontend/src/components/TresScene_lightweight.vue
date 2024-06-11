<script lang="ts">
import { ref, Ref, onMounted } from 'vue'
import { isMobileDevice } from '../utils/generalUtils.ts';
import * as THREE from 'three';
import { Noise } from 'noisejs';

export default {
  setup() {
    //TODO: Grid checker, disable or remove in prod.
    const DEV_gridCheck: boolean = false;

    const gridSize: number = isMobileDevice() ? 10 : 18;
    const pillar_size: number = 1;
    const gap_modifier: number = 0.025;
    const gap: number = pillar_size * (gap_modifier + 1);
    const material_color: number = 0x3d4040;
    const splitChanceSmall: number = 0.4;
    const splitChanceTiny: number = 0.1;
    const noiseScale: number = 2.5;
    const noise = new Noise(0.36);
    const noiseValues: number[][] = Array.from({ length: gridSize }, () => Array(gridSize).fill(0));
    const randomValues: number[] = Array(gridSize * gridSize).fill(0).map(() => Math.random());
    let time = ref(0);

    //TODO: FPS tracker, disable or remove in prod.
    let frameCount = ref(0);
    let lastFrameTime = ref(performance.now());
    let minFPS = ref(Infinity);
    let maxFPS = ref(0);
    let currentFPS = ref(0);

    // ThreeJS setup
    const threeJsCanvas = ref<HTMLDivElement | null>(null);
    const renderer = new THREE.WebGLRenderer();
    renderer.setPixelRatio(window.devicePixelRatio);
    renderer.setSize(window.innerWidth, window.innerHeight);
    if (isMobileDevice()) {
      renderer.shadowMap.enabled = false;
    } else {
      renderer.shadowMap.enabled = true;
      renderer.shadowMap.type = THREE.PCFSoftShadowMap;
    }
    const scene = new THREE.Scene();
    if (isMobileDevice()) {
      scene.fog = new THREE.FogExp2(0x000000, 0.15);
    } else {
      scene.fog = new THREE.FogExp2(0x000000, 0.1);
    }
    const bottomLight = new THREE.PointLight(0xe63702, 50, 800, 1.5);
    bottomLight.position.set(0, -5, 0);
    bottomLight.lookAt(5,0,5);
    bottomLight.castShadow = true;
    scene.add(bottomLight);
    const topLight = new THREE.PointLight(0xc1f2f5, 5, 50, 1);
    topLight.position.set(-15, 10, -15);
    topLight.lookAt(0,0,0);
    topLight.castShadow = true;
    scene.add(topLight);

    let camera: THREE.OrthographicCamera | THREE.PerspectiveCamera | null = null;
    if (DEV_gridCheck) {
      camera = new THREE.OrthographicCamera(
          window.innerWidth / - 120,
          window.innerWidth / 120,
          window.innerHeight / 120,
          window.innerHeight / - 120,
          1, 100 );
    } else {
      camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 50);
    }

    if (isMobileDevice()) {
      camera.position.set(5, 4, 4.2);
      camera.lookAt(new THREE.Vector3(-1, 0, 0));
    } else {
      camera.position.set(5.2, 4.1, 5.2);
      camera.lookAt(new THREE.Vector3(0, 0, 0));
    }
    scene.add(camera);

    // Scene geometry
    const pillars: Ref<THREE.Mesh[]> = ref([]);
    const pillarMaterial = new THREE.MeshStandardMaterial({
      color: material_color,
      roughness: 0.5,
      metalness: 0.1,
      fog: true
    });
    const pillar_medium_material: Ref<THREE.MeshStandardMaterial> = ref(pillarMaterial);
    const pillar_medium_geometry: Ref<THREE.BoxGeometry> = ref(new THREE.BoxGeometry(pillar_size, 5, pillar_size));
    const pillar_small_material: Ref<THREE.MeshStandardMaterial> = ref(pillarMaterial);
    const pillar_small_geometry: Ref<THREE.BoxGeometry> = ref(new THREE.BoxGeometry((pillar_size / 2) / gap, 5, (pillar_size / 2) / gap));
    const pillar_tiny_material: Ref<THREE.MeshStandardMaterial> = ref(pillarMaterial);
    const pillar_tiny_geometry: Ref<THREE.BoxGeometry> = ref(new THREE.BoxGeometry((pillar_size / 4) / gap / gap, 5, (pillar_size / 4) / gap / gap));
    const prototypePillarMedium = new THREE.Mesh(pillar_medium_geometry.value, pillar_medium_material.value);
    const prototypePillarSmall = new THREE.Mesh(pillar_small_geometry.value, pillar_small_material.value);
    const prototypePillarTiny = new THREE.Mesh(pillar_tiny_geometry.value, pillar_tiny_material.value);

    if (DEV_gridCheck) {
      camera.position.set(0, 11, 0);
      camera.lookAt(new THREE.Vector3(0, 0, 0));

      const DEV_light = new THREE.PointLight(0xc1f2f5, 5, 50, 0);
      DEV_light.position.set(0, 10, 0);
      DEV_light.lookAt(0,0,0);
      DEV_light.castShadow = true;
      scene.add(DEV_light);

      scene.fog = new THREE.FogExp2(0x000000, 0);
    }

    // Methods
    const createPillar = (x: number, z: number, prototypePillar: THREE.Mesh) => {
      const pillar = prototypePillar.clone();
      pillar.position.set(x, 0, z);
      pillars.value.push(pillar);
      scene.add(pillar);
    };
    const createSplitPillar = (x: number, z: number) => {
      const baseX = x;
      const baseZ = z;
      for (let dx = -0.5; dx <= 0.5; dx++) {
        for (let dz = -0.5; dz <= 0.5; dz++) {
          const splitAgain = Math.random() < splitChanceTiny;
          if (splitAgain) {
            for (let dxTiny = -0.5; dxTiny <= 0.5; dxTiny++) {
              for (let dzTiny = -0.5; dzTiny <= 0.5; dzTiny++) {
                createPillar(
                    baseX + dx * (pillar_size + gap_modifier) / 2 + dxTiny * (pillar_size + gap_modifier) / 4,
                    baseZ + dz * (pillar_size + gap_modifier) / 2 + dzTiny * (pillar_size + gap_modifier) / 4,
                    prototypePillarTiny
                );
              }
            }
          } else {
            createPillar(
                baseX + dx * (pillar_size + gap_modifier) / 2,
                baseZ + dz * (pillar_size + gap_modifier) / 2,
                prototypePillarSmall
            );
          }
        }
      }
    };
    const createGrid = (gridSize: number) => {
      let index = 0;
      for (let i = 0; i < gridSize; i++) {
        for (let j = 0; j < gridSize; j++) {
          const x = i * gap;
          const z = j * gap;
          const splitPillar = randomValues[index++] < splitChanceSmall;
          if (splitPillar) {
            createSplitPillar(x, z);
          } else {
            createPillar(x, z, prototypePillarMedium);
          }
        }
      }
      getCenter();
    };
    const getCenter = () => {
      const { minX, minZ, maxX, maxZ } = pillars.value.reduce(
          (acc, pillar) => ({
            minX: Math.min(acc.minX, pillar.position.x),
            minZ: Math.min(acc.minZ, pillar.position.z),
            maxX: Math.max(acc.maxX, pillar.position.x),
            maxZ: Math.max(acc.maxZ, pillar.position.z)
          }),
          { minX: Infinity, minZ: Infinity, maxX: -Infinity, maxZ: -Infinity }
      )
      const centerX = (minX + maxX) / 2
      const centerZ = (minZ + maxZ) / 2
      pillars.value.forEach(pillar => {
        pillar.position.x -= centerX
        pillar.position.z -= centerZ
      })
    };
    const animateWaves = () => {
      time.value += 0.01;
      if (frameCount.value % 4 === 0) {
        for (let i = 0; i < gridSize; i++) {
          for (let j = 0; j < gridSize; j++) {
            noiseValues[i][j] = noise.simplex3(i / 10, j / 10, time.value * 0.1);
          }
        }
      }
      pillars.value.forEach((pillar) => {
        const x = pillar.position.x / 10;
        const z = pillar.position.z / 10;
        const noiseValue = noise.simplex3(x, z, time.value * 0.1);
        const targetHeightOffset = noiseValue * noiseScale;
        const currentHeightOffset = pillar.position.y;
        pillar.position.y = lerp(currentHeightOffset, targetHeightOffset, 0.1);
      });
      frameCount.value++;
    };
    const lerp = (a: number, b: number, t: number) => {
      return a * (1 - t) + b * t;
    };
    const calculatePerformance = () => {
      frameCount.value++;
      const currentTime = performance.now();
      const deltaTime = currentTime - lastFrameTime.value;
      if (deltaTime >= 1000) {
        currentFPS.value = (frameCount.value * 1000) / deltaTime;
        minFPS.value = Math.min(minFPS.value, currentFPS.value);
        maxFPS.value = Math.max(maxFPS.value, currentFPS.value);
        frameCount.value = 0;
        lastFrameTime.value = currentTime;
      }
    };
    const animate = () => {
      calculatePerformance()
      animateWaves();
      renderer.render(scene, camera);
      requestAnimationFrame(animate)
    };
    onMounted(() => {
      if (threeJsCanvas.value) {
        threeJsCanvas.value.appendChild(renderer.domElement);
      }
      createGrid(gridSize);
      animate();
    });
    return {
      pillar_small_geometry,
      pillar_small_material,
      pillar_medium_geometry,
      pillar_medium_material,
      pillars,
      threeJsCanvas,
      currentFPS,
      minFPS,
      maxFPS
    };
  }
}
</script>
<template>
  <div id="fps-counter">
    FPS: {{ currentFPS.toFixed(2) }}<br>
    Min: {{ minFPS.toFixed(2) }}<br>
    Max: {{ maxFPS.toFixed(2) }}<br>
    Pillars: {{ pillars.length }}
  </div>
  <div id="threeJsCanvas" ref="threeJsCanvas"></div>
</template>
<style>
#fps-counter {
  font-family: "Roboto Light", sans-serif;
  position: absolute;
  top: 0;
  left: 0;
  color: white;
  font-size: 0.8em;
  padding: 10px;
  z-index: 100;
  opacity: 0.2;
}
#threeJsCanvas {
  position: absolute;
  top: 0;
  left: 0;
  max-width: 100dvw;
  max-height: 100dvh;
  margin: 0;
  z-index: -10;
}
</style>
