<template>
  <div class="container">
    <div class="content">
      <div class="top">
        <header class="G_unselectable G_sectionHeader header">\\ Projects</header>
        <header class="G_unselectable G_sectionHeader subheader">Featured</header>
        <projects_list/>
      </div>
      <div class="spacing"/>
      <div class="bottom">
        <header class="G_unselectable G_sectionHeader subheader">More</header>
        <button class="font_inter" @click="redirect('https://github.com/Aethryste?tab=repositories', true)">SEE MY WORK</button>
        <button class="font_inter" @click="redirect('https://codepen.io/Aethryste', true)">CHECK OUT MY SKETCHES</button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Projects_list from "../components/projects_list.vue";

export default {
  name: 'section_projects',
  components: {
    Projects_list
  },
  props: {
    isActive: Boolean
  },
  data() {
    return {
      elem_top: null as Element | null,
      elem_bottom: null as Element | null
    }
  },
  watch: {
    isActive(newVal:boolean, oldVal:boolean) {
      if (newVal !== oldVal) { this.isActiveWatch(newVal); }
    }
  },
  methods: {
    redirect(path: string, newTab: any) {
      if (newTab) {
        window.open(
          path, '_blank'
        );
      }
    },
    toggleAnimation(element: Element | null, inClass: string, outClass: string, isActive: boolean) {
      if (element) {
        element.classList.remove(isActive ? outClass : inClass);
        element.classList.add(isActive ? inClass : outClass);
      }
    },
    isActiveWatch(value: boolean) {
      this.toggleAnimation(this.elem_top, 'animation-projects-to-right', 'animation-projects-to-left', value);
      this.toggleAnimation(this.elem_bottom, 'animation-projects-to-top', 'animation-projects-to-bottom', value);
    },
  },
  mounted () {
    this.elem_top = document.querySelector('.top');
    this.elem_bottom = document.querySelector('.bottom');
  }
}
</script>

<style scoped lang="scss">
@import "src/styles/theme";
.container {
  position: relative;
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  .content {
    width: fit-content;
    height: 70%;
    margin: auto;
    .top {
      transform: translateX(-200%);
      transition: 800ms;
      .header {
        margin: 2em 0 5px 15px;
      }
      .subheader {
        margin: 0 0 0 15px;
        font-size: 1.8em;
        color: white;
        letter-spacing: 5px;
      }
    }
    .spacing {
      width: 100%;
      height: 5%;
    }
    .bottom {
      transform: translateY(500%);
      display: flex;
      flex-direction: column;
      max-height: 30%;
      .subheader {
        margin: 0 0 0 15px;
        font-size: 1.8em;
        color: white;
        letter-spacing: 5px;
      }
      button {
        @extend .font_inter;
        width: 300px;
        height: 4em;
        padding: 1em;
        text-align: center;
        margin: 1em 0 0 15px;
        background-color: rgba(0,0,0,0);
        border: 2px solid white;
        color: whitesmoke;
        cursor: pointer;
        letter-spacing: 2px;
        font-weight: bold;
        transition: 300ms;
        &:hover {
          border: 2px solid $theme-primary-color;
          color: $theme-primary-color;
        }
      }
    }
  }
}
</style>
