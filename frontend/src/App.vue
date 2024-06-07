<script setup>
import IndexComp from '../src/pages/index.vue';
import TresScene from '../src/components/TresScene_lightweight.vue';
</script>

<template>
  <IndexComp id="IndexComp"/>
  <TresScene id="canvas"/>
</template>

<style>
#IndexComp {
  position: absolute;
  margin: 0!important;
  top: 0;
  left: 0;
}
</style>
