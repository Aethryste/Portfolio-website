<template>
  <div class="container">
    <div class="content">
      <div class="top">
        <header class="G_unselectable G_sectionHeader header">\\ Projects</header>
        <header class="G_unselectable G_sectionHeader subheader">Featured</header>
        <div v-if="windowWidth < 400" class="projects-container">
          <projects-tile v-for="project in projects" :key="project.title" :project="project"
                         :width="'120px'"
                         :height="'120px'"
                         :margin="'10px'"
                         :translate="'65px'"
                         :thumbnail="project.thumbnail"
                         class="projects-tile" />
        </div>
        <div v-if="windowWidth < 600 && windowWidth > 399" class="projects-container">
          <projects-tile v-for="project in projects" :key="project.title" :project="project"
                         :width="'150px'"
                         :height="'150px'"
                         :margin="'10px'"
                         :translate="'75px'"
                         :thumbnail="project.thumbnail"
                         class="projects-tile" />
        </div>
        <div v-if="windowWidth > 599" class="projects-container">
          <projects-tile v-for="project in projects" :key="project.title" :project="project"
                         :width="'200px'"
                         :height="'200px'"
                         :margin="'18px'"
                         :translate="'100px'"
                         :thumbnail="project.thumbnail"
                         class="projects-tile" />
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
      windowWidth: window.innerWidth as number,
      elem_top: null as Element | null,
      elem_bottom: null as Element | null,
      elem_projects_container: null as HTMLElement | null,
      elem_project_items: null as HTMLElement | null,
      cube_properties: {
        width: 200 as number,
        height: 200 as number,
        margin: 18 as number
      },
      projects_row_length: 0 as number,
      projects: [
        {
          thumbnail: 'github.svg',
          title: 'Portfolio website',
          url: 'https://github.com/Aethryste/Portfolio-website',
          description: "My web-development portfolio, you're currently viewing it!",
          skills: [
              {
                title: 'Vue',
                color: '#31f586'
              },
              {
                title: 'Vite',
                color: '#a621ff'
              },
              {
                title: 'Typescript',
                color: '#0db6db'
              },
              {
                title: 'SCSS',
                color: '#d64fa9'
              },
              {
                title: 'Java',
                color: '#ff8324'
              },
              {
                title: 'Spring',
                color: '#039956'
              }
          ]
        },
        {
          thumbnail: 'github.svg',
          title: 'Mailing Service',
          url: 'https://github.com/Aethryste/MailingService-template',
          description: 'SMTP mailing-service template for spring backends.',
          skills: [
            {
              title: 'Java',
              color: '#ff8324'
            },
            {
              title: 'JavaMail',
              color: '#ff8324'
            },
            {
              title: 'Spring',
              color: '#039956'
            }
          ]
        },
        {
          thumbnail: 'github.svg',
          title: 'Degiro Notifier',
          url: 'https://github.com/Aethryste/Degiro_notifier',
          description: 'Selenium-based web-scraper with Telegram bot for notifications.',
          skills: [
              {
                title: 'Python',
                color: '#05499c'
              }
          ]
        },
        {
          thumbnail: 'github.svg',
          title: 'X.com clone',
          url: 'https://github.com/Aethryste/x.com_clone',
          description: "This is a new project that i'm currently working on!",
          skills: [
            {
              title: 'Vue',
              color: '#31f586'
            },
            {
              title: 'Vite',
              color: '#a621ff'
            },
            {
              title: 'Typescript',
              color: '#0db6db'
            },
            {
              title: 'SCSS',
              color: '#d64fa9'
            },
            {
              title: 'Java',
              color: '#ff8324'
            },
            {
              title: 'Spring',
              color: '#039956'
            }
          ]
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
      if (this.elem_projects_container && this.elem_project_items) {
        if ("style" in this.elem_projects_container) {
          this.elem_projects_container.style.width = '';
        }
        let currentRowYOffset = this.elem_project_items?.[0].offsetTop;
        let count:number = 0;
        for (let item of this.elem_project_items) {
          if (item.offsetTop !== currentRowYOffset) {
            break;
          }
          count++
        }
        this.projects_row_length = count;
        const containerWidth = (this.projects_row_length * this.cube_properties.width)
            + ((this.cube_properties.margin * 2) * this.projects_row_length);
        if ("style" in this.elem_projects_container) {
          this.elem_projects_container.style.width = `${containerWidth}px`;
        }
      }
    },
  },
  mounted () {
    this.elem_top = document.querySelector('.top') as HTMLElement;
    this.elem_bottom = document.querySelector('.bottom') as HTMLElement;
    this.elem_projects_container = this.$el.querySelector('.projects-container') as HTMLElement;
    this.elem_project_items = this.elem_projects_container.querySelectorAll('.projects-tile') as HTMLElement;
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
        display: flex;
        flex-wrap: wrap;
        width: fit-content;
        max-width: calc(
            (#{$cube-width} * #{$cube-total-in-list}) +
            (#{$cube-margin} * (#{$cube-total-in-list} * 2) )
        );
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
          margin: 10px 0 auto 15px;
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
@media screen and (max-width: 885px) {
  .container .content .bottom .flex-container {
    flex-direction: column;
    width: fit-content;
  }
}
@media screen and (max-width: 600px) {
  .container {
    max-width: 440px;
    margin: auto;
    flex-direction: column;
    .content .bottom {
      .flex-container {
        margin: 0 auto 0 5px;
        width: 92%;
        button {
          width: 335px;
          margin: 10px 0 0 0;
        }
      }
    }
  }
}
@media screen and (max-width: 430px) {
  .container .content {
    min-width: 350px;
    width: fit-content;
    .header, .subheader {
      width: fit-content;
    }
  }
}
@media screen and (max-width: 400px) {
  .container .content {
    min-width: 286px;
    .bottom .flex-container button {
      width: 273px;
    }
  }
}
</style>
