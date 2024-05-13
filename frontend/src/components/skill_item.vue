<script lang="ts">
export default {
  name: "skill_item",
  props: {
    icon: {
      type: String,
      required: true
    }
  }
}
</script>

<template>
  <div class="container">
    <div class="icon" :style="{ backgroundImage: `url(${icon})` }"/>
    <p class="title">
      <slot name="title"></slot>
    </p>
  </div>
</template>

<style scoped lang="scss">
.container {
  font-family: "Roboto Light", sans-serif;
  border-radius: 0.3em;
  display: block;
  width: 200px;
  height: 60px;
  margin: 5px;
  background: linear-gradient(
          190deg,
          rgba(255,255,255,0.05),
          rgba(200,200,200,0.05)
  );
  opacity: 1;
  transition: 300ms;
  &:hover {
    opacity: 1;
  }
  .icon {
    opacity: 1;
    min-width: 36px;
    min-height: 36px;
    border: 1px solid white;
    border-radius: 0.3em;
    margin: auto auto auto 10px;
  }
  .title {
    width: 100%;
    height: fit-content;
    margin: auto auto auto 0.5em;
    color: white;
  }
}
</style>