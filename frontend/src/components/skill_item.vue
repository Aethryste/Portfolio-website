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
  },
  watch: {
    icon(newIcon) {
      const iconElement = this.$refs.icon as HTMLElement;
      iconElement.style.background = newIcon;
    }
  },
  mounted() {
    const iconElement = this.$refs.icon as HTMLElement;
    iconElement.style.background = this.icon;
  }
}
</script>

<template>
  <div class="container G_unselectable" :class="{ 'minimized': minimize }">
    <div ref="icon" class="icon" v-show="!minimize"/>
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
          rgba(255,255,255,0.2),
          rgba(200,200,200,0.1)
  );
  opacity: 1;
  transition: 300ms;
  backdrop-filter: blur(5px);
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
@media screen and (max-width: 1420px) {
  .container {
    height: 50px;
    width: 150px;
  }
  .minimized {
    height: 35px;
  }
}
@media screen and (max-width: 1280px) {
  .minimized {
    height: 32px;
    font-size: 0.8em;
  }
}
@media screen and (max-width: 1120px) {
  .container {
    width: 130px;
    .icon {
      transform: scale(80%);
    }
  }
}
@media screen and (max-width: 1060px) {
  .container {
    width: 125px;
    .icon {
      transform: scale(74%);
    }
  }
}
</style>
