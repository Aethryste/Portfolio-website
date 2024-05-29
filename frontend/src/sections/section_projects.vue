<template>
  <div class="container">
    <div class="content">
      <div class="top">
        <header class="G_unselectable G_sectionHeader header">\\ Projects</header>
        <header class="G_unselectable G_sectionHeader subheader">Featured</header>
        <div class="projects-container">
          <projects-tile v-for="project in projects" :key="project.title" :project="project" class="projects-tile" />
        </div>
      </div>
      <div class="bottom">
        <header class="G_unselectable G_sectionHeader subheader">More</header>
        <div class="flex-container">
          <button class="font_inter" @click="handleRedirect('https://github.com/Aethryste?tab=repositories', true)">SEE MY WORK</button>
          <button class="font_inter" @click="handleRedirect('https://codepen.io/Aethryste', true)">CHECK OUT MY SKETCHES</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import ProjectsTile from "../components/projects_tile.vue";
import { redirect } from "../utils/generalUtils.ts";

export default {
  name: 'section_projects',
  components: {
    ProjectsTile
  },
  props: {
    isActive: Boolean
  },
  data() {
    return {
      elem_top: null as Element | null,
      elem_bottom: null as Element | null,
      cube_properties: {
        width: 200 as number,
        height: 200 as number,
        margin: 18 as number
      },
      projects_row_length: 0,
      projects: [
        {
          passiveImg: '../assets/project_portfolio_front.png',
          activeImg: 'right.jpg',
          title: 'Portfolio website',
          description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vestibulum metus a lorem convallis eleifend. Vivamus tempus in metus et rutrum.',
          tools: ['Vue', 'Vite', 'TypeScript', 'SCSS'],
        },
        {
          passiveImg: 'front.jpg',
          activeImg: 'right.jpg',
          title: 'Mailing Service',
          description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vestibulum metus a lorem convallis eleifend. Vivamus tempus in metus et rutrum.',
          tools: ['Java', 'Spring', 'JavaMail'],
        },
        {
          passiveImg: 'front.jpg',
          activeImg: 'right.jpg',
          title: 'None1',
          description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vestibulum metus a lorem convallis eleifend. Vivamus tempus in metus et rutrum.',
          tools: [],
        },
        {
          passiveImg: 'front.jpg',
          activeImg: 'right.jpg',
          title: 'None2',
          description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vestibulum metus a lorem convallis eleifend. Vivamus tempus in metus et rutrum.',
          tools: [],
        },
      ]
    }
  },
  watch: {
    isActive(newVal:boolean, oldVal:boolean) {
      if (newVal !== oldVal) { this.isActiveWatch(newVal); }
    }
  },
  methods: {
    handleRedirect(path: string, newTab: any) {
      redirect(path, newTab);
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
    adjustWidth() {
      const container = this.$el.querySelector('.projects-container') as HTMLElement;
      const items = container.querySelectorAll('.projects-tile') as HTMLElement;
      container.style.width = '';

      let currentRowYOffset = items[0].offsetTop;
      let count:number = 0;
      for (let item of items) {
        if (item.offsetTop !== currentRowYOffset) {
          break;
        }
        count++
      }
      this.projects_row_length = count;

      const cubeWidth = this.cube_properties.width;
      const cubeMargin = this.cube_properties.margin;
      const containerWidth = (this.projects_row_length * cubeWidth) + ((cubeMargin * 2) * this.projects_row_length);
      container.style.width = `${containerWidth}px`;
    },
  },
  mounted () {
    this.elem_top = document.querySelector('.top');
    this.elem_bottom = document.querySelector('.bottom');
    this.$watch('windowWidth', this.adjustWidth);
    this.adjustWidth();
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
    border: 1px solid red;
    width: fit-content;
    max-width: 80%;
    height: fit-content;
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
      .projects-container {
        border: 1px solid orange;
        display: flex;
        flex-wrap: wrap;
        width: fit-content;
        max-width: calc(($cube-width*$cube-total-in-list) + ($cube-margin*($cube-total-in-list*2)));
        height: 100%;
        margin: auto;
      }
    }
    .bottom {
      transform: translateY(500%);
      display: flex;
      flex-direction: column;
      max-height: 30%;
      .subheader {
        margin: 3% 0 0 15px;
        font-size: 1.8em;
        color: white;
        letter-spacing: 5px;
      }
      .flex-container {
        border: 1px solid orange;
        display: flex;
        flex-wrap: wrap;
        width: fit-content;
        height: fit-content;
        margin: auto auto 3% 0;
        button {
          @extend .font_inter;
          width: 300px;
          height: 4em;
          text-align: center;
          margin: auto 0 auto 15px;
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
}
@media screen and (max-width: 910px) {
  .container .content .bottom .flex-container {
    flex-direction: column;
    button {
      width: 250px;
      margin-bottom: 10px;
    }
  }
}
</style>
