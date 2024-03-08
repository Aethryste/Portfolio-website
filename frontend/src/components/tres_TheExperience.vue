<script setup lang="ts">
import {useRenderLoop} from "@tresjs/core";
import {ref} from "vue";

const { onLoop } = useRenderLoop()
const meshRef = ref('meshRef')

onLoop(({ delta, elapsed }) => {
  if (meshRef.value) {
    // meshRef.value.rotation.y += delta
    // meshRef.value.rotation.z = elapsed * 0.2
  }
})
</script>
<template>
    <TresCanvas>
      <TresPerspectiveCamera/>
      <TresScene>
        <TresMesh ref="meshRef">
          <TresTorusGeometry :args="[1, 0.5, 16, 32]" />
          <TresMeshBasicMaterial color="orange" />
          <TresAmbientLight :intensity="1" />
        </TresMesh>
      </TresScene>
      <TresAxesHelper/>
    </TresCanvas>
</template>

<style scoped lang="scss">

</style>
