<template>
  <div class="page-wrapper">
    <div id="frontpage-deco">
      <div class="page-title anim-fade-in">
        <h1 class="main">Hi, I'm Richard.</h1>
        <h2 class="sub">Software Engineer, front-end developer.</h2>
      </div>
      <div class="scroll-hint anim-fade-in">
        <p>Learn more about what I do</p>
        <div class="icon-down"></div>
      </div>
    </div>
    <div class="page-divider">
      <div class="base">
        <div class="elements">
          <h2 class="title">ABOUT</h2>
          <div class="element">
            element-1
          </div>
          <div class="element">
            element-2
          </div>
          <div class="element">
            element-3
          </div>
<!--          <comp_aboutMe/>-->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'view_AboutMe',
  data() {
    return {
      scroll: 0
    };
  },
  watch: {
    scroll(newValue) {
      if (newValue > 0) {
        console.log('scroll at ',newValue)
      }
      if (newValue > 250) {
        this.transition_1_disable();
      }
      if (newValue < 250) {
        this.transition_1_enable();
      }
    }
  },
  methods: {
    updateScroll() {
      this.scroll = window.scrollY;
    },
    transition_1_enable: function () {
      let title = document.querySelector(".page-title");
      title.classList.remove('anim-fade-out');
      title.classList.add('anim-fade-in');
      let hint = document.querySelector(".scroll-hint");
      hint.classList.remove('anim-fade-out');
      hint.classList.add('anim-fade-in');
      let divider = document.querySelector(".page-divider");

      // To prevent animation bug.
      if (divider.classList.contains('anim-translate_down')) {
        divider.classList.remove('anim-translate_down');
        divider.classList.add('anim-translate_up');
      }
    },
    transition_1_disable: function () {
      let title = document.querySelector(".page-title");
      title.classList.remove('anim-fade-in');
      title.classList.add('anim-fade-out');
      let hint = document.querySelector(".scroll-hint");
      hint.classList.remove('anim-fade-in');
      hint.classList.add('anim-fade-out');
      let divider = document.querySelector(".page-divider");
      divider.classList.remove('anim-translate_up');
      divider.classList.add('anim-translate_down');
    }
  },
  mounted() {
    window.addEventListener('scroll', this.updateScroll);
  }
}
</script>

<style scoped lang="scss">
@import "./src/styles/theme.scss";
.page-wrapper {
  width: 100%;
  height: 100%;
  margin: 0;
  .page-title {
    margin: 25% auto auto auto;
    position: absolute;
    font-family: 'montserrat', sans-serif;
    font-weight: 100;
    text-align: center;
    width: 50%;
    height: fit-content;
    .main {
      font-size: 4em;
      margin-bottom: -0.6%;
    }
    .sub {
      font-size: 1.5em;
      font-weight: 300;
      color: darkgrey;
    }
  }
  .scroll-hint {
    position: absolute;
    width: fit-content;
    height: fit-content;
    left:0;
    right:0;
    bottom: 20px;
    margin-left: auto;
    margin-right: auto;
    font-weight: lighter;
    font-size: 0.8em;
    letter-spacing: 3px;
    justify-content: center;
    text-align: center;
    opacity: 40%;
    .icon-down {
      width: 50px;
      height: 50px;
      margin: -20px auto auto auto;
      background: url("../assets/icons/icon_arrow_down_white.png") no-repeat center;
      -webkit-background-size: cover;
      -moz-background-size: cover;
      -o-background-size: cover;
      background-size: cover;
    }
  }
  .page-divider {
    overflow: hidden;
    transform: translateY(120vh);
    position: absolute;
    left: 0;
    width: 100%;
    height: fit-content;
    .base {
      background: ghostwhite;
      display: flex;
      height: 100rem;
      width: 100%;
      margin: 10rem 0 auto 0;
      position: relative;
      &:before {
        border-bottom: 120px solid ghostwhite;
        border-left: 65rem solid transparent;
        border-right: 65rem solid transparent;
        content: "";
        height: 0;
        left: -1rem;
        position: absolute;
        top: -119px;
        width: 0;
      }
      .elements {
        position: relative;
        width: 75%;
        height: fit-content;
        margin: 0 auto auto auto;
        border: orange 1px solid;
        .title {
          color: dimgrey;
          font-size: 3em;
          font-weight: 100;
          letter-spacing: 2px;
          text-align: center;
          margin: 1em;
          text-decoration: underline;
          text-underline-offset: 0.2em;
          //&:after {
          //  content: "";
          //  position:absolute;
          //  bottom:-2px
          //  left:0;
          //  width:50px;
          //  border-bottom:1px solid dimgrey;
          //}
        }
        .element {
          width: 80%;
          height: 200px;
          border: orange 1px solid;
          margin: 1em auto;
        }
      }
    }
  }
}
.hidden {
  opacity: 0;
}
</style>
