<template>
  <div class="component-wrapper">
    <div class="content-wrapper">
      <div class="left"> <!-- TODO: Add image to About section. -->
        <div class="image"></div>
<!--        <p style="color:black; padding: 1em;">Placeholder for image/profile picture.</p>-->
      </div>
      <div class="right">
        <div class="about-block">
          <h3 class="G_unselectable G_sectionHeader">ABOUT</h3>
          <FunctionTitle class="FunctionTitle"/>
          <p class="G_paragraph"> <!-- TODO: Clean-up html structure. -->
            My journey in IT started out with a simple certificate in the basics of Python,
            here I discovered my burning passion for programming.
            After this discovery I decided to get my Bachelors Degree in Software Engineering at the <a target="_blank" href="https://www.amsterdamuas.com/about-auas">Amsterdam University of Applied Sciences</a>. (AUAS)
            <br>
            <br>
            At AUAS I learned to work with several programming languages and associated frameworks by working on
            the full Software Development Life Cycle (SDLC) of my projects. In other words, all the way from concept to final deployment.
            <br>
            <br>
            I'm most comfortable working in the front-end of a product but also have experience with back-end programming, API's, databases, testing, and deployment.
            <br>
            <br>
            <span style="color: #3D7DF5">My toolkit of preference includes:</span> <!-- TODO: Remove inline styling. -->
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
        background: url("../assets/ProfileImg.svg") center no-repeat; // PLACEHOLDER
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
