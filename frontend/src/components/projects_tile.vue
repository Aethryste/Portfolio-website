<script lang="ts">
import { defineComponent, PropType, ref } from 'vue';
import { redirect } from "../utils/generalUtils.ts";
import mini_skill from "../components/mini_skill.vue";

interface Project {
  thumbnail: string;
  title: string;
  url: string;
  description: string;
  skills: string[];
}

export default defineComponent({
  components: {
    mini_skill
  },
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
    const handleRedirect = (url:string) => {
      redirect(url, true);
    };
    return {
      transformStyle,
      handleMouseEnter,
      handleMouseLeave,
      handleRedirect
    };
  },
});
</script>

<template>
  <div class="cube-container" @mouseenter="handleMouseEnter" @mouseleave="handleMouseLeave" @click="handleRedirect(project.url)">
    <div
        class="cube"
        :style="{ width: width, height: height, margin: margin, transform: transformStyle }"
    >
      <div
          class="face front"
          :style="{ width: width, height: height, transform: 'translateZ(' + translate + ')' }"
      >
        <h3 class="G_unselectable">{{ project.title }}</h3>
        <img :src="'src/assets/' + project.thumbnail" :alt="project.title">
      </div>
      <div
          class="face right"
          :style="{ width: width, height: height, transform: 'rotateY(90deg) translateZ(' + translate + ')' }"
      >
        <p class="description">{{ project.description }}</p>
        <div class="mini-skill-container">
          <div class="mini-skill"></div>
          <mini_skill v-for="skill in project.skills" :key="skill" :skill="skill"/>
        </div>
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
  cursor: pointer;
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
    border: 2px solid white;
    backface-visibility: hidden;
    transform-style: flat;
    text-align: center;
    overflow: hidden;
  }
  .front {
    background: linear-gradient(30deg, rgb(50, 50, 50), rgb(100, 100, 100));

    h3 {
      position: absolute;
      top: 8px;
      left: 8px;
      width: calc(100% - 18px);
      height: 1.2em;
      margin: 0 auto auto 0;
      z-index: 5;
      font-family: "Roboto Light", sans-serif;
      font-weight: 100;
      text-align: start;
      text-transform: uppercase;
      letter-spacing: 3px;
      word-spacing: 100dvw;
    }
    img {
      width: 100%;
      filter: brightness(120%);
      z-index: -5;
    }
  }
  .right {
    .description {
      letter-spacing: 1px;
      width: fit-content;
      max-width: calc(100% - 20px);
      height: fit-content;
      max-height: 50%;
      position: relative;
      margin: 10px auto auto auto;
    }
    .mini-skill-container {
      display: flex;
      flex-direction: column;
      flex-wrap: wrap;
      position: relative;
      width: fit-content;
      max-width: calc(100% - 18px);
      height: fit-content;
      max-height: 50%;
      margin: 10px auto;
      z-index: 5;
    }
  }
}
@media screen and (max-width: 600px) {
  .cube-container .right {
    .description {
      font-size: 0.7em;
      height: fit-content;
      margin: 5px auto 0 auto;
    }
    .mini-skill-container {
      justify-content: center;
      flex-flow: column wrap;
      flex-direction: row;
      flex-wrap: wrap;
      margin: 2px auto auto auto;
      height: fit-content;
    }
  }
}
@media screen and (max-width: 400px) {
  .cube-container .cube {
    .front h3 {
      font-size: 1em;
      letter-spacing: 2px;
    }
    .right .mini-skill-container {
      display: none;
    }
  }
}
</style>
