<script lang="ts">
export default {
  name: "skill_item",
  props: {
    icon: {
      type: String,
      required: true
    },
    minimize: {
      type: Boolean,
      default: false
    }
  }
}
</script>

<template>
  <div class="container G_unselectable" :class="{ 'minimized': minimize }">
    <div class="icon" v-if="!minimize" :style="{ background: `url(${icon}) center no-repeat`}"/>
    <p class="title">
      <slot name="title"></slot>
    </p>
  </div>
</template>

<style scoped lang="scss">
@import "../styles/theme";
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
    color: $theme-primary-color;
  }
  .icon {
    background-size: contain!important;
    opacity: 1;
    min-width: 36px;
    min-height: 36px;
    border-radius: 0.3em;
    margin: auto auto auto 10px;
  }
  .title {
    width: 100%;
    height: fit-content;
    margin: auto auto auto 0.5em;
    color: white;
    font-size: 0.9em;
  }
}
.minimized {
  height: 35px;
  transition: 300ms;
  text-align: center;
  .icon {
    display: none;
  }
}
</style>