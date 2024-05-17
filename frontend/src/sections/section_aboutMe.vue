<script lang="ts">
import { defineComponent } from 'vue';
import typewriter from "../components/typewriter.vue";
import Skill_item from "../components/skill_item.vue";
import { backendFetch, isMobileDevice } from "../globals";

export default defineComponent({
  name: "section_aboutMe",
  components: {
    Skill_item,
    typewriter,
  },
  props: {
    isActive: Boolean
  },
  computed: {
    filteredSkillCategories() {
      if (this.isMobileDevice) {
        return this.skillCategories.slice(0, -1);
      } else {
        return this.skillCategories;
      }
    }
  },
  data() {
    return {
      windowWidth: 0 as number,
      isMobileDevice: isMobileDevice() as boolean,
      elem_profile_image: null as Element | null,
      elem_profile_details: null as Element | null,
      readMoreActivated: false as boolean,
      longText: "Hey there! I'm Richard, a driven student and aspiring software engineer on a mission to create " +
          "enhanced user experiences through code. Currently pursuing a Bachelor's in Software Engineering at the " +
          "Amsterdam University of Applied Sciences, I'm especially passionate about front-end/web -development but " +
          "aspire to work Full-stack.\n\nMy journey into programming began with a Python certificate and has " +
          "evolved to encompass several other languages including HTML/(S)CSS/Javascript, Java, and MySQL. I've " +
          "also got experience with most recurrent libraries and associated frameworks such as VueJs and Springboot. " +
          "I thrive in collaborative environments where ideas flow freely and collective efforts drive innovation." +
          " I tend to prioritize quality and precision in my work.\n\nCurrently seeking long-term career " +
          "opportunities that encourage personal and professional growth, I'm eager to contribute and " +
          "tackle exciting challenges! " as string,
      profileImagePath: BACKEND_URL+'/ProfileImg.svg' as string,
      skillCategories: [
        {
          title: 'Frontend',
          skills: [
            { name: 'HTML', iconName: 'html', icon: '' },
            { name: 'CSS', iconName: 'css', icon: '' },
            { name: 'JavaScript', iconName: 'js', icon: '' },
            { name: 'SCSS', iconName: 'scss', icon: '' },
            { name: 'VueJs', iconName: 'vue', icon: '' },
            { name: 'Vite', iconName: 'vite', icon: '' },
          ]
        },
        {
          title: 'Backend',
          skills: [
            { name: 'Java', iconName: 'java', icon: '' },
            { name: 'Spring', iconName: 'spring', icon: '' },
            { name: 'Python', iconName: 'python', icon: '' },
            { name: 'MySQL', iconName: 'sql', icon: '' },
          ]
        },
        {
          title: 'Other',
          skills: [
            { name: 'NPM', iconName: 'npm', icon: '' },
            { name: 'GIT', iconName: 'git', icon: '' },
            { name: 'IDEA', iconName: 'idea', icon: '' },
          ]
        }
      ] as Array<{ title: string, skills: Array<{ name: string, icon: string }> }>
    }
  },
  watch: {
    isActive(newVal:boolean, oldVal:boolean) {
      if (newVal !== oldVal) { this.isActiveWatch(newVal); }
    }
  },
  methods: {
    toggleAnimation(element: Element | null, inClass: string, outClass: string, isActive: boolean) {
      if (element) {
        element.classList.remove(isActive ? outClass : inClass);
        element.classList.add(isActive ? inClass : outClass);
      }
    },
    isActiveWatch(value: boolean) {
      this.toggleAnimation(this.elem_profile_image, 'animation-move-in-from-left', 'animation-move-out-to-left', value);
      this.toggleAnimation(this.elem_profile_details, 'animation-move-in-from-right', 'animation-move-out-to-right', value);
    },
    handleResize() {
      this.windowWidth = window.innerWidth;
    },
    toggleReadMore() {
      this.readMoreActivated = !this.readMoreActivated;
    },
    async fetchIcon(filename:string) {
      try {
        const base64SVG = btoa(await backendFetch(`/res/icon/${filename}`));
        return `url('data:image/svg+xml;base64,${base64SVG}')`;
      } catch (error) {
        console.error(`Error fetching icon: ${filename}`, error);
        return '';
      }
    }
  },
  async created() {
    window.addEventListener('resize', this.handleResize);
    this.handleResize();
    for (const category of this.skillCategories) {
      for (const skill of category.skills) {
        skill.icon = await this.fetchIcon(`icon-${skill.iconName}.svg`);
      }
    }
  },
  mounted () {
    this.elem_profile_image = document.querySelector('.profile-image');
    this.elem_profile_details = document.querySelector('.profile-details');
  }
});
</script>

<template>
  <div class="container">
    <div class="profile-image">
      <img :src='profileImagePath' alt="Profile image"/>
    </div>
    <div class="profile-details">
      <div class="about">
        <header class="G_unselectable G_sectionHeader">About</header>
        <typewriter id="typewriter"/>
        <p class="biography G_paragraph" :class="{ 'expanded': readMoreActivated }">
          <span v-if="!readMoreActivated">{{longText.slice(0, 330)}}</span>
          <a class="" v-if="!readMoreActivated" @click="toggleReadMore" href="#">
            Read more...
          </a>
          <span v-if="readMoreActivated" v-html="longText.replace(/\n/g, '<br/>')"></span>
          <a class="" v-if="readMoreActivated" @click="toggleReadMore" href="#">
            Read less...
          </a>
        </p>
        <div v-if="!readMoreActivated || readMoreActivated && windowWidth >= 970" class="skills-container">
          <div v-for="category in filteredSkillCategories" :key="category.title" class="skill-section">
            <h3 v-if="!readMoreActivated">{{ category.title }}</h3>
            <h4 v-if="readMoreActivated && windowWidth > 1260">{{ category.title }}</h4>
            <hr v-if="readMoreActivated && windowWidth < 1260">
            <skill_item
                v-for="skill in category.skills"
                :key="skill.name"
                :icon="skill.icon"
                :minimize="readMoreActivated"
            >
              <template v-slot:title>{{ skill.name }}</template>
            </skill_item>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@import "../styles/theme";
#more {display: none;}
.container {
  display: flex;
  font-family: "Roboto Light", sans-serif;
  .profile-image {
    transform: translateX(-125%);
    display: flex;
    width: 50%;
    height: 640px;
    margin: auto auto auto 0;
    overflow: hidden;
    img {
      width: 100%;
      height: 100%;
      transform: scale(1.3);
      -webkit-transform: scale(1.3);
      -moz-transform: scale(1.3);
      -o-transform: scale(1.3);
      -ms-transform: scale(1.3);
      object-fit: cover;
      object-position: center;
    }
  }
  .profile-details {
    transform: translateX(125%);
    position: relative;
    display: flex;
    flex-direction: column;
    width: 50%;
    height: 70%;
    margin: auto auto auto 0;
    .about {
      width: calc(100%  - 5em);
      height: calc(100%  - 2em);
      margin: auto auto auto 1em;
      .G_sectionHeader {
        text-transform: uppercase;
      }
      #typewriter {
        margin: -1em 0 -1em 0!important;
      }
    }
    .biography {
      transition: max-height 0.5s linear;
      overflow: hidden;
      max-height: 20%;
      &.expanded {
        max-height: 100%;
        transition: max-height 0.5s linear;
      }
      a {
        text-decoration: none;
        color: $theme-primary-color;
      }
    }
    .skills-container {
      margin: 1em auto auto auto;
      width: 100%;
      height: fit-content;
      hr {
        opacity: 0.1;
        margin: 0;
        width: 100%;
      }
      h3 {
        width: 100%;
        text-transform: uppercase;
        color: lightgrey;
        font-weight: 500;
        font-size: 0.9em;
        margin-top: 0.2em;
        margin-bottom: 0;
        border-bottom: 1px solid rgba(255,255,255,0.2);
        opacity: 0.3;
      }
      h4 {
        width: 100%;
        text-transform: uppercase;
        color: lightgrey;
        font-size: 0.7em;
        font-weight: 500;
        margin-top: 0.2em;
        margin-bottom: 0;
        border-bottom: 1px solid rgba(255,255,255,0.2);
        opacity: 0.3;
      }
      .skill-section {
        width: 100%;
        height: fit-content;
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        .skill-item {
          border-radius: 0.3em;
          display: block;
          width: 200px;
          height: 60px;
          margin: 5px;
          background: linear-gradient(
                  190deg,
                  rgb(255,255,255),
                  rgb(200,200,200)
          );
        }
      }
    }
  }
}
.animation-move-in-from-left {
  animation: move-in-from-left 500ms forwards 400ms;
}
@keyframes move-in-from-left {
  0% {
    transform: translateX(-100%);
  }
  100% {
    transform: translateX(0%);
  }
}

.animation-move-in-from-right {
  animation: move-in-from-right 500ms forwards 400ms;
}
@keyframes move-in-from-right {
  0% {
    transform: translateX(100%);
  }
  100% {
    transform: translateX(0%);
  }
}

.animation-move-out-to-left {
  animation: move-out-to-left 400ms forwards;
}
@keyframes move-out-to-left {
  0% {
    transform: translateX(0%);
  }
  100% {
    transform: translateX(-100%);
  }
}

.animation-move-out-to-right {
  animation: move-out-to-right 400ms forwards;
}
@keyframes move-out-to-right {
  0% {
    transform: translateX(0%);
  }
  100% {
    transform: translateX(100%);
  }
}

@media screen and (max-width: 1420px) {
  .container .profile-image img {
    transform: scale(1);
  }
}
@media screen and (max-width: 970px) {
  .container .profile-details .skills-container {
    h3 {
      font-size: 0.7em;
    }
    .skill-section {
      .container {
        height: 40px;
      }
    }
  }
}
@media screen and (max-width: 940px) {
  .container .profile-details .biography {
    max-height: 30%;
  }
}
@media screen and (max-width: 840px) {
  .container {
    flex-direction: column;
    justify-content: space-evenly;
    .profile-image {
      margin: auto auto 0 auto;
      width: 80%;
      height: 25%;
    }
    .profile-details {
      margin: 0 auto auto auto;
      width: 80%;
      height: fit-content;
    }
    .about {
      margin: auto!important;
      width: 100%!important;
      .skills-container {
        h3 {
          font-size: 0.9em;
        }
        .skill-section .container {
          flex-direction: row;
        }
      }
      .biography {
        max-height: 37%;
      }
    }
  }
}
@media screen and (max-width: 510px) {
  .container .profile-details {
    margin: 0 auto auto auto;
    .about .skills-container {
      h3 {
        display: none;
      }
      .skill-section {
        justify-content: center;
      }
    }
  }
}
</style>
