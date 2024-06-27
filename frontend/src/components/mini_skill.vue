<script lang="ts">
import {defineComponent} from "vue";

export default defineComponent({
  props: {
    skill: {
      type: Object,
      required: true
    }
  }
});
</script>

<template>
  <div class="component-wrapper" :style="{ border: '2px solid '+ skill.color }">
    {{ skill.title }}
  </div>
</template>

<style scoped lang="scss">
.component-wrapper {
  border-radius: 5px;
  width: 68px;
  height: fit-content;
  padding: 5px;
  margin: 2px;
  font-size: 0.8em;
  font-family: "Roboto Light", sans-serif;
  letter-spacing: 1px;
  text-shadow:
      0 4px 3px rgba(0,0,0,0.4),
      0 8px 13px rgba(0,0,0,0.1),
      0 18px 23px rgba(0,0,0,0.1);
}
@media screen and (max-width: 600px) {
  .component-wrapper {
    font-size: 0.7em;
    letter-spacing: 0;
    width: fit-content;
  }
}
</style>
