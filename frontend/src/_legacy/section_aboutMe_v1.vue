<template>
  <div class="component-wrapper">
    <div class="content-wrapper">
      <div class="left">
        <div class="image"></div>
        <div class="toolbox-icons-group-small">
          <div class="toolbox-icon" id="icon-html"></div>
          <div class="toolbox-icon" id="icon-css"></div>
          <div class="toolbox-icon" id="icon-js"></div>
          <div class="toolbox-icon" id="icon-vue"></div>
          <div class="toolbox-icon" id="icon-java"></div>
          <div class="toolbox-icon" id="icon-nodejs"></div>
          <div class="toolbox-icon" id="icon-sql"></div>
          <div class="toolbox-icon" id="icon-python"></div>
        </div>
      </div>
      <div class="right">
        <div class="about-block">
          <h3 class="G_unselectable G_sectionHeader">ABOUT</h3>
          <typewriter class="FunctionTitle"/>

          <div v-if="this.windowW > 500">
            <p class="G_paragraph">
              My journey through IT started out by gaining a certificate regarding the basics of Python, by working on the
              assignments of this course I discovered a strong passion for programming within myself. After this discovery
              I decided to get my Bachelors Degree in Software Engineering at the
              <a target="_blank" href="https://www.amsterdamuas.com/about-auas">
                Amsterdam University of Applied Sciences
              </a>. (AUAS)
            </p>
            <p class="G_paragraph">
              Gaining a certificate in Python made me discover a strong passion for programming within myself, this
              discovery led to decide to study Software Engineering (Bachelor) at the
              <a target="_blank" href="https://www.amsterdamuas.com/about-auas">
                Amsterdam University of Applied Sciences
              </a>. (AUAS)
            </p>
            <p class="G_paragraph">
              At AUAS I initially learned about general computer science topics, then several programming languages and
              associated frameworks, and later full stack web-development by working (Agile) on the full Software
              Development Life Cycle (SDLC) of our projects.
            </p>
            <p class="G_paragraph">
              Through these experiences I found that I'm most comfortable working in the front-end of a product but I also
              have some experience with back-end programming, REST API's & web-sockets, databases, testing, and of course
              deployment.
            </p>
          </div>

          <div v-else>
            <p class="G_paragraph">
              Hey there! I'm Richard, a driven student and aspiring software engineer on a mission to translate code into
              enhanced user experiences. Currently pursuing a Bachelor's in Software Engineering at the
              <a target="_blank" href="https://www.amsterdamuas.com/about-auas">
                Amsterdam University of Applied Sciences.
              </a>
            </p>
            <p class="G_paragraph">
              I'm especially passionate about front-end/web -development but aspire to work Full-stack.
            </p>
            <p class="G_paragraph">
              My journey into programming began with a Python certificate and has evolved to encompass several other
              languages. I've also got experience with most recurrent libraries and associated frameworks such as VueJs
              and Springboot.
            </p>
          </div>

          <p class="G_paragraph">
            <span class="G_theme-primary-color-text">My toolkit of preference includes:</span>
          </p>
          <div class="toolbox-icons-group-big">
            <div class="toolbox-icon" id="icon-html"></div>
            <div class="toolbox-icon" id="icon-css"></div>
            <div class="toolbox-icon" id="icon-js"></div>
            <div class="toolbox-icon" id="icon-vue"></div>
            <div class="toolbox-icon" id="icon-java"></div>
            <div class="toolbox-icon" id="icon-nodejs"></div>
            <div class="toolbox-icon" id="icon-sql"></div>
            <div class="toolbox-icon" id="icon-python"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import typewriter from '../components/typewriter.vue';
export default {
  name: 'section_aboutMe',
  components: {
    typewriter,
  },
  data() {
    return {
      windowW: window.innerWidth
    }
  },
  methods: {
    handleResize() {
      this.windowW = window.innerWidth;
    }
  },
  created() {
    window.addEventListener('resize', this.handleResize);
  }
}
</script>

<style scoped lang="scss">
@import "../styles/theme";
.component-wrapper {
  color: white;
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  .content-wrapper {
    display: flex;
    flex-direction: row;
    height: 70%;
    width: 100%;
    margin: auto!important;
    .left {
      display: flex;
      width: 50%;
      height: 100%;
      .image {
        width: 100%;
        height: 80%;
        background: url("../assets/ProfileImg.svg") center no-repeat;
        background-size: cover;
        margin: auto!important;
        box-shadow: rgba(0, 0, 0, 0.3) 0 3px 8px;
      }
    }
    .right {
      display: flex;
      width: 50%;
      height: 100%;
      .about-block {
        width: 70%;
        margin: auto auto auto 2em!important;
        .FunctionTitle {
          margin: -1em 0 -1em 0!important;
        }
        p {
          a {
            text-decoration: none;
            color: white;
          }
        }
      }
    }
  }
}
.toolbox-icons-group {
  &-big {
    display: flex;
    flex-direction: row;
    width: 100%;
    height: fit-content;
    margin: 20px 0 0 0!important;
    justify-content: space-evenly;
    .toolbox-icon {
      width: 60px;
      min-width: 60px;
      height: 60px;
      min-height: 60px;
      margin: 5px auto!important;
      transition: 300ms;
      &:hover {
        transform: translateY(-3px) scale(110%);
      }
    }
  }
  &-small {
    display: none;
    flex-direction: row;
    width: 95%;
    height: fit-content;
    margin: -15% auto auto auto!important;
    justify-content: space-evenly;
    .toolbox-icon {
      width: 50px;
      min-width: 50px;
      height: 50px;
      min-height: 50px;
      margin: 5px auto!important;
      transition: 300ms;
      &:hover {
        transform: translateY(-3px) scale(110%);
      }
    }
  }
}
@media screen and (max-width: 1450px) {
  .toolbox-icons-group-big .toolbox-icon {
    width: 50px;
    min-width: 50px;
    height: 50px;
    min-height: 50px;
  }
}
@media screen and (max-width: 1260px) {
  #icon-html {
    background: url("../assets/icons-svg/icon-htmlcssjs.svg") center no-repeat;
    background-size: cover;
  }
  #icon-css, #icon-js {
    display: none;
  }
  .toolbox-icons-group-big .toolbox-icon {
    width: 60px;
    min-width: 60px;
    height: 60px;
    min-height: 60px;
  }
}
@media screen and (max-width: 1190px) {
  .G_theme-primary-color-text {
    display: none;
  }
}
@media screen and (max-width: 1100px) {
  .toolbox-icons-group-big .toolbox-icon {
    width: 50px;
    min-width: 50px;
    height: 50px;
    min-height: 50px;
  }
}
@media screen and (max-width: 1000px) {
  .toolbox-icons-group {
    &-big {
      display: none;
    }
    &-small {
      display: flex;
    }
  }
  .component-wrapper .content-wrapper .left {
    flex-wrap: wrap;
    align-items: flex-start;
    .image {
      height: 60%;
      margin-top: 18%!important;
    }
  }
}
@media screen and (max-width: 950px) {
  .component-wrapper .content-wrapper {
    flex-direction: column;
    height: 100%;
    .left {
      margin: auto auto 0 auto!important;
      width: 80%;
      height: fit-content;
      flex-direction: column;
      .image {
        height: 300px;
        width: 100%;
        margin: auto!important;
      }
      .toolbox-icons-group-small {
        margin: auto auto 0 auto!important;
        width: 100%;
        padding-top: 10px;
        padding-bottom: 10px;
      }
    }
    .right {
      margin: 0 auto auto auto!important;
      width: 80%;
      height: fit-content;
      .about-block {
        width: 100%;
        margin: auto!important;
      }
    }
  }
}
@media screen and (max-width: 700px) {
  .component-wrapper .content-wrapper .left .image {
    max-height: 250px;
  }
}
@media screen and (max-width: 600px) {
  .component-wrapper .content-wrapper {
    max-width: 90%;
    margin: auto!important;
    .left .image {
      background-size: 140%;
    }
  }
}
@media screen and (max-width: 500px) {
  .component-wrapper .content-wrapper {
    .left {
      .image {
        max-height: 180px;
      }
      .toolbox-icons-group-small {
        border-bottom: 1px solid rgba(255,255,255,0.3);
        margin-bottom: 1em!important;
        .toolbox-icon {
          width: 40px;
          min-width: 40px;
          height: 40px;
          min-height: 40px;
        }
      }
    }
    .right .about-block .G_sectionHeader {
      font-size: 1.1em;
    }
  }
}
@media screen and (max-width: 400px) {
  .component-wrapper .content-wrapper .left .image {
    background-size: 200%;
    background-position: 50% 40%;
  }
  .G_sectionHeader {
    font-size: 0.8em!important;
    margin: 0 0 0 0!important;
  }
}
</style>
