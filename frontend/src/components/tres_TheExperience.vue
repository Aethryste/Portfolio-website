<script setup lang="ts">

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
