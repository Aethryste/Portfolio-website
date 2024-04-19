<script setup>
import IndexComp from '../src/pages/index.vue';
import devPopUp from './components/dev_popup.vue'

console.log("%cOh, hey there!", "font-size: 2em;")
console.log("%cSo you're more interested in the code behind this website? You should check out its repo at: " +
    "https://github.com/Aethryste/Portfolio-website :)", "font-size: 1em;")
</script>

<template>
  <devPopUp/>
  <IndexComp id="IndexComp"/>
</template>

<style>
#IndexComp {
  position: absolute;
  margin: 0!important;
  top: 0;
  left: 0;
}
</style>
