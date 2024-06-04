<script lang="ts">
import { ref, Ref } from 'vue'
import {BoxGeometry, Mesh, MeshPhongMaterial} from 'three'
import { TresCanvas } from "@tresjs/core";

export default {
  components: { TresCanvas },
  setup() {
    const pillars: Ref<Mesh[]> = ref([])
    const gridSize: number = 10
    const gap: number = 1.025

    const materialColor: number = 0x949999;
    const material: Ref<MeshPhongMaterial> = ref(new MeshPhongMaterial({ color: materialColor }));
    const geometry: Ref<BoxGeometry> = ref(new BoxGeometry(1, 5, 1));

    const createGrid = (gridSize: number) => {
      for (let i = 0; i < gridSize; i++) {
        for (let j = 0; j < gridSize; j++) {
          const pillar = new Mesh(geometry.value, material.value)
          pillar.position.set(i * gap, -4, j * gap)
          pillars.value.push(pillar)
        }
      }
    }

    createGrid(gridSize)

    return {
      cameraPosition: ref([5, 8, 5]) as Ref<number[]>,
      cameraFov: ref(75) as Ref<number>,
      cameraAspect: ref(window.innerWidth / window.innerHeight) as Ref<number>,
      cameraNear: ref(0.1) as Ref<number>,
      cameraFar: ref(1000) as Ref<number>,
      geometry,
      materialColor,
      material,
      pillars
    }
  }
}
</script>

<template>
  <TresCanvas preset="realistic" window-size>
    <TresPerspectiveCamera
        :position="[5, 8, 5]"
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
        :geometry="geometry"
        :material="material"
        :roughness="0.8"
        :position="pillar.position.toArray()"
    />
  </TresCanvas>
</template>
