<script lang="ts">
import typewriter from "../components/typewriter.vue";
import Skill_item from "../components/skill_item.vue";
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
          "tackle exciting challenges! "
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
    activateReadMore() {
      this.readMoreActivated = true;
    },
    activateReadLess() {
      this.readMoreActivated = false;
    }
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
      <img src="../assets/ProfileImg.svg" alt="Profile image"/>
    </div>

<!--    <div class="arrow"/>-->

    <div class="profile-details">
      <div class="about">
        <header class="G_unselectable G_sectionHeader">About</header>
        <typewriter id="typewriter"/>
        <p class="biography G_paragraph">
          <span v-if="!readMoreActivated">{{longText.slice(0, 330)}}</span>
          <a class="" v-if="!readMoreActivated" @click="activateReadMore" href="#">
            Read more...
          </a>
          <span v-if="readMoreActivated" v-html="longText.replace(/\n/g, '<br/>')"></span>
          <a class="" v-if="readMoreActivated" @click="activateReadLess" href="#">
            Read less...
          </a>
          <div class="skills-container G_unselectable">
            <h3>Frontend</h3>
            <div class="skill-section">
              <skill_item icon="">
                <template v-slot:title>HTML</template>
              </skill_item>
              <skill_item icon="">
                <template v-slot:title>CSS</template>
              </skill_item>
              <skill_item icon="">
                <template v-slot:title>JavaScript</template>
              </skill_item>
              <skill_item icon="">
                <template v-slot:title>SCSS</template>
              </skill_item>
              <skill_item icon="">
                <template v-slot:title>VueJs</template>
              </skill_item>
              <skill_item icon="">
                <template v-slot:title>Vite</template>
              </skill_item>
            </div>
            <h3>Backend</h3>
            <div class="skill-section">
              <skill_item icon="">
                <template v-slot:title>Java</template>
              </skill_item>
              <skill_item icon="">
                <template v-slot:title>Spring</template>
              </skill_item>
              <skill_item icon="">
                <template v-slot:title>Python</template>
              </skill_item>
              <skill_item icon="">
                <template v-slot:title>MySQL</template>
              </skill_item>
            </div>
            <h3>Tools</h3>
            <div class="skill-section">
              <skill_item icon="">
                <template v-slot:title>NPM</template>
              </skill_item>
              <skill_item icon="">
                <template v-slot:title>GIT</template>
              </skill_item>
              <skill_item icon="">
                <template v-slot:title>IDEA</template>
              </skill_item>
            </div>
          </div>
        </p>
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
    transform: translateX(-100%);
    display: flex;
    width: 50%;
    height: 70%;
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
      opacity: 0.1;
    }
  }
  .arrow {
    position: absolute;
    width: 400px;
    height: 200px;
    border: 1px solid red;
    top: 20%;
    left: 40%;
  }
  .profile-details {
    transform: translateX(100%);
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
      a {
        text-decoration: none;
        color: $theme-primary-color;
      }
      .skills-container {
        margin: 1em auto auto auto;
        width: 100%;
        height: 100%;
        h3 {
          color: $theme-primary-color;
          font-size: 1.1em;
          font-weight: 400;
          margin-top: 0.2em;
          margin-bottom: 0;
          opacity: 0.5;
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
</style>