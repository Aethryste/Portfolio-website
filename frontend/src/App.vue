<script setup>
import IndexComp from '../src/pages/index.vue';
import ThreeScene from '../src/components/TresScene_lightweight.vue';
</script>

<template>
  <IndexComp id="IndexComp"/>
  <ThreeScene/>
</template>

<style>
#IndexComp {
  position: absolute;
  margin: 0!important;
  top: 0;
  left: 0;
}
</style>
