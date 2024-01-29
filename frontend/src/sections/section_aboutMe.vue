<template>
  <div class="component-wrapper">
    <div class="content-wrapper">
      <div class="left">
        <div class="image"></div></div>
      <div class="right">
        <div class="about-block">
          <h3 class="G_unselectable G_sectionHeader">ABOUT</h3>
          <FunctionTitle class="FunctionTitle"/>
          <p class="G_paragraph">
            My journey through IT started out by gaining a certificate regarding the basics of Python, by working on the
            assignments of this course I discovered a strong passion for programming within myself. After this discovery
            I decided to get my Bachelors Degree in Software Engineering at the
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
          <p class="G_paragraph">
            <span class="G_theme-primary-color-text">My toolkit of preference includes:</span>
          </p>
          <div class="toolbox-icons-group">
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
import FunctionTitle from '@/components/subcomponent_FunctionTitle.vue';
export default {
  name: 'section_aboutMe',
  components: {
    FunctionTitle,
  },
  methods: {
    fetchData() {
      let ajax = new XMLHttpRequest()
      ajax.open('GET', '/images/timeline')
      ajax.onload = () => {
        this.slides = JSON.parse(ajax.responseText);
      };
      ajax.onerror = () => {
        console.error('Error fetching slideshow data.');
      };
      ajax.send();
    }
  },
  created() {
    this.fetchData();
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
      }
    }
    .right {
      display: flex;
      width: 50%;
      height: 100%;
      .about-block {
        width: 70%;
        margin: auto auto auto 2em !important;
        .FunctionTitle {
          padding: 1em 0 0.2em 0;
        }
        p {
          a {
            text-decoration: none;
            color: white;
          }
        }
        .toolbox-icons-group {
          display: flex;
          flex-direction: row;
          width: 100%;
          height: 100px;
          margin: 20px 0 0 0 !important;
          justify-content: space-evenly;
          .toolbox-icon {
            width: 60px;
            height: 60px;
            margin: auto !important;
            transition: 300ms;
            &:hover {
              transform: translateY(-3px) scale(110%);
            }
          }
          #icon-html {
            background: url("../assets/icons-svg/icon-html.svg") center no-repeat;
            background-size: cover;
          }
          #icon-css {
            background: url("../assets/icons-svg/icon-css.svg") center no-repeat;
            background-size: cover;
          }
          #icon-js {
            background: url("../assets/icons-svg/icon-js.svg") center no-repeat;
            background-size: cover;
          }
          #icon-vue {
            background: url("../assets/icons-svg/icon-vue.svg") center no-repeat;
            background-size: cover;
          }
          #icon-java {
            background: url("../assets/icons-svg/icon-java.svg") center no-repeat;
            background-size: cover;
          }
          #icon-nodejs {
            background: url("../assets/icons-svg/icon-nodejs.svg") center no-repeat;
            background-size: cover;
          }
          #icon-sql {
            background: url("../assets/icons-svg/icon-sql.svg") center no-repeat;
            background-size: cover;
          }
          #icon-python {
            background: url("../assets/icons-svg/icon-python.svg") center no-repeat;
            background-size: cover;
          }
        }
      }
    }
  }
}
</style>
