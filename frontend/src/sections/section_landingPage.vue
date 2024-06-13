<template>
  <div class="component-wrapper">
    <div class="front-layer G_unselectable">
      <div class="page-title" ref="pageTitle">
        <h1 class="main">Hi, I'm <span id="richard">Richard</span>.</h1>
        <h2 class="sub">Software Engineer, front-end developer.</h2>
      </div>
      <div class="absolute-pos-container">
        <div class="scroll-hint-icon" ref="scrollHint"/>
      </div>
    </div>
  </div>
</template>

<script>
import lottie from 'lottie-web';
import animationData from '../assets/anim-scroll.json';

export default {
  name: 'section_landingPage',
  props: {
    isActive: Boolean
  },
  data() {
    return {
      defaultOptions: {
        animationData: animationData
      }
    };
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
    lottie.loadAnimation({
      container: this.$refs.scrollHint,
      renderer: 'svg',
      loop: true,
      autoplay: true,
      animationData: animationData
    });
  }
}
</script>

<style scoped lang="scss">
@import "src/styles/theme";
#richard {
  color: $theme-primary-color;
}
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
    .absolute-pos-container {
      position: absolute;
      width: 100dvw;
      height: 100dvh;
      .scroll-hint-icon {
        position: absolute;
        bottom: 5dvh;
        left: calc(50% - 30px);
        width: 60px;
        height: 60px;
        opacity: 0;
      }
    }
  }
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
