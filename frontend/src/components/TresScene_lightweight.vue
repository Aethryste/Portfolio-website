<script lang="ts">
import { ref, Ref } from 'vue'
import { BoxGeometry, Mesh, MeshPhongMaterial } from 'three'
import { TresCanvas } from "@tresjs/core";
import { Noise } from 'noisejs';

export default {
  components: { TresCanvas },
  setup() {
    // Changeable variables
    const gridSize: number = 20;
    const pillar_size: number = 1;
    const gap_modifier: number = 0.025;
    const material_color: number = 0x3d4040;
    const noiseScale = 4;

    // Static
    // FPS Counter
    let frameCount = ref(0);
    let lastFrameTime = ref(performance.now());
    let minFPS = ref(Infinity);
    let maxFPS = ref(0);
    let currentFPS = ref(0);

    // Noise and pillars
    let time = ref(0);
    let noise = new Noise(Math.random());
    const pillars: Ref<Mesh[]> = ref([]);
    const gap: number = pillar_size * (1+gap_modifier);

    const pillar_medium_material: Ref<MeshPhongMaterial> = ref(new MeshPhongMaterial({ color: material_color }));
    const pillar_medium_geometry: Ref<BoxGeometry> = ref(new BoxGeometry(
        pillar_size,
        5,
        pillar_size));

    const pillar_small_material: Ref<MeshPhongMaterial> = ref(new MeshPhongMaterial({ color: material_color }));
    const pillar_small_geometry: Ref<BoxGeometry> = ref(new BoxGeometry(
        (pillar_size / 2) / gap,
        5,
        (pillar_size / 2) / gap
        )
    );

    const pillar_tiny_material: Ref<MeshPhongMaterial> = ref(new MeshPhongMaterial({ color: material_color }));
    const pillar_tiny_geometry: Ref<BoxGeometry> = ref(new BoxGeometry(
        (pillar_size / 4) / gap / gap,
        5,
        (pillar_size / 4) / gap / gap
        )
    );

    // Create prototype pillars
    const prototypePillarMedium = new Mesh(pillar_medium_geometry.value, pillar_medium_material.value);
    const prototypePillarSmall = new Mesh(pillar_small_geometry.value, pillar_small_material.value);
    const prototypePillarTiny = new Mesh(pillar_tiny_geometry.value, pillar_tiny_material.value);

    // Methods
    const createPillar = (x: number, z: number, prototypePillar: Mesh) => {
      const pillar = prototypePillar.clone();
      pillar.position.set(x, 0, z);
      pillars.value.push(pillar);
    }

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
    }

    const createGrid = (gridSize: number) => {
      for (let i = 0; i < gridSize; i++) {
        for (let j = 0; j < gridSize; j++) {
          const x = i * gap;
          const z = j * gap;
          const splitPillar = Math.random() < 0.2;
          if (splitPillar) {
            createSplitPillar(x, z);
          } else {
            createPillar(x, z, prototypePillarMedium);
          }
        }
      }
      getCenter();
    }

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
    }

    const animateWaves = () => {
      time.value += 0.01;
      pillars.value.forEach((pillar, index) => {
        const noiseValue = noise.simplex3(
            pillar.position.x / 10,
            pillar.position.z / 10,
            time.value * 0.1
        );
        const targetHeightOffset = noiseValue * noiseScale;
        const currentHeightOffset = pillar.position.y;
        pillar.position.y = lerp(currentHeightOffset, targetHeightOffset, 0.1);
      });
    }

    const lerp = (a: number, b: number, t: number) => {
      return a * (1 - t) + b * t;
    }

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
    }

    const animate = () => {
      calculateFPS()
      animateWaves();
      requestAnimationFrame(animate)
    }

    createGrid(gridSize);
    animate()

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
      currentFPS,
      minFPS,
      maxFPS
    }
  }
}
</script>
<template>
  <div id="fps-counter">
    FPS: {{ currentFPS.toFixed(2) }}<br>
    Min: {{ minFPS.toFixed(2) }}<br>
    Max: {{ maxFPS.toFixed(2) }}
  </div>
  <TresCanvas preset="realistic" window-size>
    <TresPerspectiveCamera
        :position="[5, 6, 5]"
        :fov="cameraFov"
        :aspect="cameraAspect"
        :near="cameraNear"
        :far="cameraFar"
        :look-at="[0,0,0]"
    />
    <TresDirectionalLight :intensity="0.5" :color="0xe3eeff" :position="[-5, 10, 1]" :castShadow="true" />
    <TresDirectionalLight :intensity="0.5" :color="0xe3eeff" :position="[5, 10, 1]" :castShadow="true" />
    <TresMesh
        v-for="(pillar, index) in pillars"
        :key="index"
        :geometry="pillar.geometry"
        :material="pillar.material"
        :roughness="0.8"
        :position="pillar.position.toArray()"
    />
  </TresCanvas>
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