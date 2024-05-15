<script lang="ts">
import typewriter from "../components/typewriter.vue";
import Skill_item from "../components/skill_item.vue";
import { isMobileDevice } from "../globals";

export default {
  name: "section_aboutMe",
  components: {
    Skill_item,
    typewriter,
  },
  props: {
    isActive: Boolean
  },
  data() {
    return {
      windowWidth: 0,
      isMobileDevice: isMobileDevice(),
      elem_profile_image: null,
      elem_profile_details: null,
      readMoreActivated: false,
      longText: "Hey there! I'm Richard, a driven student and aspiring software engineer on a mission to create " +
          "enhanced user experiences through code. Currently pursuing a Bachelor's in Software Engineering at the " +
          "Amsterdam University of Applied Sciences, I'm especially passionate about front-end/web -development but " +
          "aspire to work Full-stack.\n\nMy journey into programming began with a Python certificate and has " +
          "evolved to encompass several other languages including HTML/(S)CSS/Javascript, Java, and MySQL. I've " +
          "also got experience with most recurrent libraries and associated frameworks such as VueJs and Springboot. " +
          "I thrive in collaborative environments where ideas flow freely and collective efforts drive innovation." +
          " I tend to prioritize quality and precision in my work.\n\nCurrently seeking long-term career " +
          "opportunities that encourage personal and professional growth, I'm eager to contribute and " +
          "tackle exciting challenges! ",
      icons: {
        html: "icon-html.svg",
        css: "icon-css.svg",
        js: "icon-js.svg",
        scss: "icon-scss.svg",
        vue: "icon-vue.svg",
        vite: "icon-vite.svg",
        java: "icon-java.svg",
        spring: "icon-spring.svg",
        python: "icon-python.svg",
        sql: "icon-sql.svg",
        npm: "icon-npm.svg",
        git: "icon-git.svg",
        idea: "icon-idea.svg",
      },
      profileImagePath: BACKEND_URL+'/ProfileImg.svg'
    }
  },
  watch: {
    isActive(newVal, oldVal) {
      if (newVal !== oldVal) { this.isActiveWatch(newVal); }
    }
  },
  methods: {
    isActiveWatch(value) {
      if (value) {
        if (this.elem_profile_image.classList.contains('animation-move-out-to-left')) {
          this.elem_profile_image.classList.remove('animation-move-out-to-left');
        }
        this.elem_profile_image.classList.add('animation-move-in-from-left');

        if (this.elem_profile_details.classList.contains('animation-move-out-to-right')) {
          this.elem_profile_details.classList.remove('animation-move-out-to-right');
        }
        this.elem_profile_details.classList.add('animation-move-in-from-right');
      }
      else {
        if (this.elem_profile_image.classList.contains('animation-move-in-from-left')) {
          this.elem_profile_image.classList.remove('animation-move-in-from-left');
        }
        this.elem_profile_image.classList.add('animation-move-out-to-left');

        if (this.elem_profile_details.classList.contains('animation-move-in-from-right')) {
          this.elem_profile_details.classList.remove('animation-move-in-from-right');
        }
        this.elem_profile_details.classList.add('animation-move-out-to-right');
      }
    },
    handleResize() {
      this.windowWidth = window.innerWidth;
    },
    activateReadMore() {
      this.readMoreActivated = true;
    },
    activateReadLess() {
      this.readMoreActivated = false;
    }
  },
  created() {
    window.addEventListener('resize', this.handleResize);
    this.handleResize();
  },
  mounted () {
    this.elem_profile_image = document.querySelector('.profile-image');
    this.elem_profile_details = document.querySelector('.profile-details');
  }
}
</script>

<template>
  <div class="container">

    <div class="profile-image">
      <img :src='this.profileImagePath' alt="Profile image"/>
    </div>

    <div class="profile-details">
      <div class="about">
        <header class="G_unselectable G_sectionHeader">About</header>
        <typewriter id="typewriter"/>
        <p class="biography G_paragraph" :class="{ 'expanded': readMoreActivated }">
          <span v-if="!readMoreActivated">{{longText.slice(0, 330)}}</span>
          <a class="" v-if="!readMoreActivated" @click="activateReadMore" href="#">
            Read more...
          </a>
          <span v-if="readMoreActivated" v-html="longText.replace(/\n/g, '<br/>')"></span>
          <a class="" v-if="readMoreActivated" @click="activateReadLess" href="#">
            Read less...
          </a>
        </p>
        <div v-if="!this.readMoreActivated || this.readMoreActivated && windowWidth >= 970" class="skills-container">
          <h3 v-if="!this.readMoreActivated">Frontend</h3>
          <h4 v-if="this.readMoreActivated && windowWidth > 1260">Frontend</h4>
          <hr v-if="this.readMoreActivated && windowWidth < 1260">
          <div class="skill-section">
            <skill_item :icon="icons.html" :minimize="this.readMoreActivated">
              <template v-slot:title>HTML</template>
            </skill_item>
            <skill_item :icon="icons.css" :minimize="this.readMoreActivated">
              <template v-slot:title>CSS</template>
            </skill_item>
            <skill_item :icon="icons.js" :minimize="this.readMoreActivated">
              <template v-slot:title>JavaScript</template>
            </skill_item>
            <skill_item :icon="icons.scss" :minimize="this.readMoreActivated">
              <template v-slot:title>SCSS</template>
            </skill_item>
            <skill_item :icon="icons.vue" :minimize="this.readMoreActivated">
              <template v-slot:title>VueJs</template>
            </skill_item>
            <skill_item :icon="icons.vite" :minimize="this.readMoreActivated">
              <template v-slot:title>Vite</template>
            </skill_item>
          </div>
          <h3 v-if="!this.readMoreActivated">Backend</h3>
          <h4 v-if="this.readMoreActivated && windowWidth > 1260">Backend</h4>
          <hr v-if="this.readMoreActivated && windowWidth < 1260">
          <div class="skill-section">
            <skill_item :icon="icons.java" :minimize="this.readMoreActivated">
              <template v-slot:title>Java</template>
            </skill_item>
            <skill_item :icon="icons.spring" :minimize="this.readMoreActivated">
              <template v-slot:title>Spring</template>
            </skill_item>
            <skill_item :icon="icons.python" :minimize="this.readMoreActivated">
              <template v-slot:title>Python</template>
            </skill_item>
            <skill_item :icon="icons.sql" :minimize="this.readMoreActivated">
              <template v-slot:title>MySQL</template>
            </skill_item>
          </div>
          <h3 v-if="!this.isMobileDevice && !this.readMoreActivated">Tools</h3>
          <h4 v-if="!this.isMobileDevice && this.readMoreActivated && windowWidth > 1260">Tools</h4>
          <hr v-if="!this.isMobileDevice && this.readMoreActivated && windowWidth < 1260 && windowWidth > 1136">
          <div v-if="!this.isMobileDevice && !this.readMoreActivated || !this.isMobileDevice && this.readMoreActivated && windowWidth > 1136" class="skill-section">
            <skill_item :icon="icons.npm" :minimize="this.readMoreActivated">
              <template v-slot:title>NPM</template>
            </skill_item>
            <skill_item :icon="icons.git" :minimize="this.readMoreActivated">
              <template v-slot:title>GIT</template>
            </skill_item>
            <skill_item :icon="icons.idea" :minimize="this.readMoreActivated">
              <template v-slot:title>IDEA</template>
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
      }
      h3 {
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
      margin: 8% auto auto auto;
      width: 80%;
      height: 25%;
    }
    .profile-details {
      margin: auto;
      width: 80%;
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
    }
  }
}
@media screen and (max-width: 510px) {
  .container {
    .profile-image {
      height: 15%;
      margin: auto auto 0 auto;
    }
  }
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
