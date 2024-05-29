<script lang="ts">
import { defineComponent, PropType } from 'vue';

interface Project {
  passiveImg: string;
  activeImg: string;
  title: string;
  description: string;
  tools: string[];
}

export default defineComponent({
  props: {
    project: {
      type: Object as PropType<Project>,
      required: true,
    },
  },
  methods: {
    rotate() {
      this.$el.style.transform = 'perspective(1200px) rotateY(-90deg)';
    },
    reset() {
      this.$el.style.transform = 'perspective(2300px) rotateY(0deg)';
    },
  },
});
</script>

<template>
  <div class="cube" @mouseover="rotate" @mouseout="reset">
    <div class="face front">
      <img src="../assets/project_portfolio_front.png" :alt="project.title">
    </div>
    <div class="face right">
      <h3 class="title G_sectionHeader">{{ project.title }}</h3>
      <p class="description">{{ project.description }}</p>
      <div class="git-icon"/>
      <div class="mini-icons-list"></div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@import "../styles/theme";

.cube {
  position: relative;
  width: $cube-width;
  height: $cube-height;
  transform-style: preserve-3d;
  transition: transform $rotation-speed;
  margin: $cube-margin;
  transform: perspective(1200px) rotateY(0deg);
  &:hover {
    transform: perspective(1200px) rotateY(-90deg);
  }
  &.no-perspective {
    transform: perspective(2300px) rotateY(-90deg);
  }
  .face {
    position: absolute;
    width: $cube-width;
    height: $cube-height;
    background: linear-gradient(
            30deg,
            rgb(50,50,50),
            rgb(100,100,100)
    );
    backface-visibility: hidden;
    transform-style: flat;
    text-align: center;
    .description {
      font-family: "Roboto Light", sans-serif;
      font-size: 0.8em;
      color: white;
    }
    img {
      width: 100%;
    }
  }
  .front {
    transform: translateZ(100px);
  }
  .right {
    transform: rotateY(90deg) translateZ(100px);
  }
}
</style>