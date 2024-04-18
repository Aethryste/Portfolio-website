<template>
  <div class="component-wrapper">
    <div class="front-layer G_unselectable">
      <div class="page-title" ref="pageTitle">
        <h1 class="main">Hi, I'm Richard.</h1>
        <h2 class="sub">Software Engineer, front-end developer.</h2>
      </div>
      <div class="scroll-hint-bounce anim-bounce-vertical">
        <div class="scroll-hint" ref="scrollHint"></div>
      </div>
    </div>
    <div class="back-layer G_unselectable">
      <TresScene v-bind:isActive="isActive" id="canvas"/>
    </div>
  </div>
</template>

<script>
import TresScene from '../components/tres_scene.vue';
export default {
  name: 'section_landingPage',
  components: {
    TresScene
  },
  props: {
    isActive: Boolean
  },
  watch: {
    isActive(newVal, oldVal) {
      if (newVal !== oldVal) { this.handleIsTrueChange(newVal); }
    }
  },
  methods: {
    handleIsTrueChange(value) {
      if (value) {
        this.header_enable()
      } else {
        this.header_disable()
      }
    },
    header_disable: function () {
      const pageTitle = this.$refs.pageTitle;
      const scrollHint = this.$refs.scrollHint;
      if (pageTitle) {
        pageTitle.classList.remove('anim-pageTitle-fade-in');
        pageTitle.classList.add('anim-pageTitle-fade-out');
        pageTitle.addEventListener('animationend', function callback() {
          pageTitle.style.display = 'none';
          pageTitle.removeEventListener('animationend', callback);
        });
      }
      if (scrollHint) {
        scrollHint.classList.remove('anim-scrollHint-fade-in');
        scrollHint.classList.add('anim-scrollHint-fade-out');
        scrollHint.addEventListener('animationend', function callback() {
          scrollHint.style.display = 'none';
          scrollHint.removeEventListener('animationend', callback);
        });
      }
    },
    header_enable: function () {
      const pageTitle = this.$refs.pageTitle;
      const scrollHint = this.$refs.scrollHint;
      if (pageTitle) {
        pageTitle.style.display = '';
        pageTitle.classList.remove('anim-pageTitle-fade-out');
        pageTitle.classList.add('anim-pageTitle-fade-in');
        pageTitle.addEventListener('animationend', function callback() {
          pageTitle.removeEventListener('animationend', callback);
        });
      }
      if (scrollHint) {
        scrollHint.style.display = '';
        scrollHint.classList.remove('anim-scrollHint-fade-out');
        scrollHint.classList.add('anim-scrollHint-fade-in');
        scrollHint.addEventListener('animationend', function callback() {
          scrollHint.removeEventListener('animationend', callback);
        });
      }
    }
  },
  mounted () {
    const pageTitle = this.$refs.pageTitle;
    const scrollHint = this.$refs.scrollHint;
    if (pageTitle) {
      pageTitle.classList.add('anim-pageTitle-fade-in');
    }
    if (scrollHint) {
      scrollHint.classList.add('anim-scrollHint-fade-in');
    }
  }
}
</script>

<style scoped lang="scss">
@import "src/styles/theme";
.component-wrapper {
  position: relative;
  width: 100vw;
  height: 100vh;
  margin: 0;
  overflow: hidden;
  .front-layer {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 10;
    .page-title {
      opacity: 0;
      position: absolute;
      width: 50%;
      height: fit-content;
      margin: 60vh auto auto 8% !important;
      z-index: 10;
      top: 0;
      left: 0;
      font-family: 'montserrat', sans-serif;
      font-weight: 100;
      text-align: left;
      .main {
        font-size: 4em;
        margin-bottom: -0.6%;
      }
      .sub {
        margin-left: 10px!important;
        font-size: 1.5em;
        font-weight: 300;
        color: darkgrey;
        transform: translateX(-6px);
      }
    }
    .scroll-hint-bounce {
      position: absolute;
      width: 40px;
      height: 40px;
      bottom: 15px;
      left: calc(50% - 20px);
      transform: translateY(0);
      .scroll-hint {
        width: 40px;
        height: 40px;
        mask: url("../assets/icons-svg/icon-scroll.svg");
        mask-position: center;
        mask-size: contain;
        background-color: ghostwhite;
        opacity: 0;
      }
    }
  }
  .back-layer {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: -10;
    #threeJs {
      position: absolute;
      z-index: -10;
      top: 0;
      left: 0;
      opacity: 1;
    }
  }
}
#canvas {
  position: absolute!important;
  width: 100%!important;
  height: 100%!important;
}
.hidden {
  opacity: 0;
}
@media screen and (max-width: 1300px) {
  .component-wrapper {
    .front-layer {
      font-size: 0.8em;
    }
  }
}
@media screen and (max-width: 950px) {
  .component-wrapper {
    .front-layer {
      font-size: 0.7em;
    }
  }
}
@media screen and (max-width: 675px) {
  .component-wrapper {
    .front-layer {
      .page-title {
        width: 100%;
        margin-left: auto!important;
        margin-right: auto!important;
        text-align: center;
      }
    }
  }
}
</style>
