<script lang="ts">
import { ref, Ref, onMounted } from 'vue'
import * as THREE from 'three';
import { Noise } from 'noisejs';

export default {
  setup() {
    // Changeable variables
    const gridSize: number = 20;
    const pillar_size: number = 1;
    const gap_modifier: number = 0.025;
    const material_color: number = 0x3d4040;
    const noiseScale = 4;

    // ThreeJS setup
    const threeJsCanvas = ref<HTMLDivElement | null>(null);
    const renderer = new THREE.WebGLRenderer();
    renderer.setSize(window.innerWidth, window.innerHeight);
    const scene = new THREE.Scene();
    const light1 = new THREE.DirectionalLight(0xe3eeff, 0.5);
    light1.position.set(-5, 10, 1);
    light1.castShadow = true;
    const light2 = new THREE.DirectionalLight(0xe3eeff, 0.5);
    light2.position.set(5, 10, 1);
    light2.castShadow = true;
    scene.add(light1);
    scene.add(light2);
    const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);
    camera.position.set(5, 6, 5);
    camera.lookAt(new THREE.Vector3(0, 0, 0));
    scene.add(camera);

    // FPS tracker
    let frameCount = ref(0);
    let lastFrameTime = ref(performance.now());
    let minFPS = ref(Infinity);
    let maxFPS = ref(0);
    let currentFPS = ref(0);

    // Noise and pillars
    let time = ref(0);
    let noise = new Noise(Math.random());
    const noiseValues: number[][] = Array.from({ length: gridSize }, () => Array(gridSize).fill(0));
    const randomValues: number[] = Array(gridSize * gridSize).fill(0).map(() => Math.random());
    const pillars: Ref<THREE.Mesh[]> = ref([]);
    const gap: number = pillar_size * (1+gap_modifier);

    const pillar_medium_material: Ref<THREE.MeshPhongMaterial> = ref(new THREE.MeshPhongMaterial({ color: material_color }));
    const pillar_medium_geometry: Ref<THREE.BoxGeometry> = ref(new THREE.BoxGeometry(pillar_size, 5, pillar_size));
    const pillar_small_material: Ref<THREE.MeshPhongMaterial> = ref(new THREE.MeshPhongMaterial({ color: material_color }));
    const pillar_small_geometry: Ref<THREE.BoxGeometry> = ref(new THREE.BoxGeometry((pillar_size / 2) / gap, 5, (pillar_size / 2) / gap));
    const pillar_tiny_material: Ref<THREE.MeshPhongMaterial> = ref(new THREE.MeshPhongMaterial({ color: material_color }));
    const pillar_tiny_geometry: Ref<THREE.BoxGeometry> = ref(new THREE.BoxGeometry((pillar_size / 4) / gap / gap, 5, (pillar_size / 4) / gap / gap));

    const prototypePillarMedium = new THREE.Mesh(pillar_medium_geometry.value, pillar_medium_material.value);
    const prototypePillarSmall = new THREE.Mesh(pillar_small_geometry.value, pillar_small_material.value);
    const prototypePillarTiny = new THREE.Mesh(pillar_tiny_geometry.value, pillar_tiny_material.value);

    // Methods
    const createPillar = (x: number, z: number, prototypePillar: THREE.Mesh) => {
      const pillar = prototypePillar.clone();
      pillar.position.set(x, 0, z);
      pillars.value.push(pillar);
      scene.add(pillar);
    };
    const createSplitPillar = (x: number, z: number) => {
      for (let dx = -0.5; dx <= 0.5; dx += 1) {
        for (let dz = -0.5; dz <= 0.5; dz += 1) {
          const splitAgain = Math.random() < 0.3;
          if (splitAgain) {
            for (let dxTiny = -0.5; dxTiny <= 0.5; dxTiny += 1) {
              for (let dzTiny = -0.5; dzTiny <= 0.5; dzTiny += 1) {
                createPillar(
                    x + dx * (pillar_size + gap_modifier) / 2 + dxTiny * (pillar_size - (gap_modifier / 4)) / 4,
                    z + dz * (pillar_size + gap_modifier) / 2 + dzTiny * (pillar_size - (gap_modifier / 4)) / 4,
                    prototypePillarTiny)
              }
            }
          }
          else {
            createPillar(
                x + dx * (pillar_size + gap_modifier) / 2,
                z + dz * (pillar_size + gap_modifier) / 2,
                prototypePillarSmall)
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
          const splitPillar = randomValues[index++] < 0.2;
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
            maxZ: Math.max(acc.maxZ, pillar.position.z),
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
      for (let i = 0; i < gridSize; i++) {
        for (let j = 0; j < gridSize; j++) {
          noiseValues[i][j] = noise.simplex3(i / 10, j / 10, time.value * 0.1);
        }
      }
      pillars.value.forEach((pillar) => {
        const noiseValue = noise.simplex3(
            pillar.position.x / 10,
            pillar.position.z / 10,
            time.value * 0.1
        );
        const targetHeightOffset = noiseValue * noiseScale;
        const currentHeightOffset = pillar.position.y;
        pillar.position.y = lerp(currentHeightOffset, targetHeightOffset, 0.1);
      });
    };
    const lerp = (a: number, b: number, t: number) => {
      return a * (1 - t) + b * t;
    };
    const calculateFPS = () => {
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
      calculateFPS()
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
      cameraPosition: ref([0, 10, 0]) as Ref<number[]>,
      cameraFov: ref(75) as Ref<number>,
      cameraAspect: ref(window.innerWidth / window.innerHeight) as Ref<number>,
      cameraNear: ref(0.1) as Ref<number>,
      cameraFar: ref(1000) as Ref<number>,
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
    Max: {{ maxFPS.toFixed(2) }}
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
  background-color: rgba(0, 0, 0, 0.5);
  padding: 10px;
  z-index: 100;
  opacity: 0.5;
}
</style>