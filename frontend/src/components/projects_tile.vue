<script lang="ts">
import { defineComponent, PropType, ref } from 'vue';

interface Project {
  thumbnail: string;
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
    width: {
      type: String,
      required: true,
    },
    height: {
      type: String,
      required: true,
    },
    margin: {
      type: String,
      required: true,
    },
    translate: {
      type: String,
      required: true,
    }
  },
  setup() {
    const isMouseInside = ref(false);
    const transformStyle = ref('perspective(1200px) rotateY(0deg)');
    const handleMouseEnter = () => {
      if (!isMouseInside.value) {
        transformStyle.value = 'perspective(1200px) rotateY(-90deg)';
        isMouseInside.value = true;
      }
    };
    const handleMouseLeave = () => {
      if (isMouseInside.value) {
        transformStyle.value = 'perspective(1200px) rotateY(0deg)';
        isMouseInside.value = false;
      }
    };
    return {
      transformStyle,
      handleMouseEnter,
      handleMouseLeave
    };
  },
});
</script>

<template>
  <div class="cube-container" @mouseenter="handleMouseEnter" @mouseleave="handleMouseLeave">
    <div
        class="cube"
        :style="{ width: width, height: height, margin: margin, transform: transformStyle }"
    >
      <div
          class="face front"
          :style="{ width: width, height: height, transform: 'translateZ(' + translate + ')' }"
      >
        <img :src="'src/assets/' + project.thumbnail" :alt="project.title">
      </div>
      <div
          class="face right"
          :style="{ width: width, height: height, transform: 'rotateY(90deg) translateZ(' + translate + ')' }"
      >
        <h3 class="title G_sectionHeader">{{ project.title }}</h3>
        <p class="description">{{ project.description }}</p>
        <div class="git-icon"></div>
        <div class="mini-icons-list"></div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@import "../styles/theme";

.cube-container {
  position: relative;
  display: inline-block;
  width: fit-content;
  height: fit-content;
  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 1;
    background: transparent;
  }
}
.cube {
  position: relative;
  transform-style: preserve-3d;
  transition: transform 0.4s;
  transform: perspective(1200px) rotateY(0deg);
  z-index: 0;
  .face {
    position: absolute;
    background: linear-gradient(30deg, rgb(50, 50, 50), rgb(100, 100, 100));
    backface-visibility: hidden;
    transform-style: flat;
    text-align: center;
    overflow: hidden;
    .title {
      margin: 0.5em auto -0.5em auto;
    }
    .description {
      font-family: "Roboto Light", sans-serif;
      font-size: 0.7em;
      color: white;
    }
    .git-icon {
      border: 1px solid orange;
      width: 40px;
      height: 40px;
      margin: auto;
    }
    img {
      width: 100%;
    }
  }
}
</style>
