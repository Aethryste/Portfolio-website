<script lang="ts">
import { ref, Ref } from 'vue'
import { BoxGeometry, Mesh, MeshPhongMaterial } from 'three'
import { TresCanvas } from "@tresjs/core";

export default {
  components: { TresCanvas },
  setup() {
    // Changeable variables
    const gridSize: number = 10;
    const pillar_size: number = 1;
    const gap_modifier: number = 0.05;
    const material_color: number = 0x949999;

    // Static
    const pillars: Ref<Mesh[]> = ref([]);
    const gap: number = pillar_size * (1+gap_modifier);

    const pillar_medium_material: Ref<MeshPhongMaterial> = ref(new MeshPhongMaterial({ color: material_color }));
    const pillar_medium_geometry: Ref<BoxGeometry> = ref(new BoxGeometry(pillar_size, 5, pillar_size));
    const pillar_small_material: Ref<MeshPhongMaterial> = ref(new MeshPhongMaterial({ color: material_color }));
    const pillar_small_geometry: Ref<BoxGeometry> = ref(new BoxGeometry((pillar_size - (gap_modifier))/2, 5, (pillar_size - (gap_modifier))/2));

    // Methods
    const createPillar = (x: number, z: number, splitPillar: boolean) => {
      if (splitPillar) {
        const offset: number = 0.5 + (gap_modifier/2)
        for (let dx = -offset; dx <= offset; dx += offset*2) {
          for (let dz = -offset; dz <= offset; dz += offset*2) {
            const pillar = new Mesh(pillar_small_geometry.value, pillar_small_material.value)
            pillar.position.set(x + dx * pillar_size / 2, -4, z + dz * pillar_size / 2)
            pillars.value.push(pillar)
          }
        }
      } else {
        const pillar = new Mesh(pillar_medium_geometry.value, pillar_medium_material.value)
        pillar.position.set(x, -4, z)
        pillars.value.push(pillar)
      }
    }

    const createGrid = (gridSize: number) => {
      for (let i = 0; i < gridSize; i++) {
        for (let j = 0; j < gridSize; j++) {
          const x = i * gap
          const z = j * gap
          const splitPillar = Math.random() < 0.8;
          createPillar(x, z, splitPillar);
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

    createGrid(gridSize);

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
      pillars
    }
  }
}
</script>
<template>
  <TresCanvas preset="realistic" window-size>
    <TresPerspectiveCamera
        :position="[0, 6, 0]"
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