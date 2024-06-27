<template>
  <div class="wrapper-landingPage G_sectionWrapper G_unselectable">
    <div class="page-title" ref="pageTitle">
      <h1 class="main">Hi, I'm <span id="name-span">Richard</span>.</h1>
      <h2 class="sub">Software Engineer, front-end developer.</h2>
    </div>
    <div class="absolute-pos-container">
      <div class="scroll-hint-icon" ref="scrollHint"/>
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
        pageTitle.classList.remove('anim-landingSection-title-fade-in');
        pageTitle.classList.add('anim-landingSection-title-fade-out');
        pageTitle.addEventListener('animationend', function callback() {
          pageTitle.style.display = 'none';
          pageTitle.removeEventListener('animationend', callback);
        });
      }
      if (scrollHint) {
        scrollHint.classList.remove('anim-landingSection-scrollHint-fade-in');
        scrollHint.classList.add('anim-landingSection-scrollHint-fade-out');
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
        pageTitle.classList.remove('anim-landingSection-title-fade-out');
        pageTitle.classList.add('anim-landingSection-title-fade-in');
        pageTitle.addEventListener('animationend', function callback() {
          pageTitle.removeEventListener('animationend', callback);
        });
      }
      if (scrollHint) {
        scrollHint.style.display = '';
        scrollHint.classList.remove('anim-landingSection-scrollHint-fade-out');
        scrollHint.classList.add('anim-landingSection-scrollHint-fade-in');
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
      pageTitle.classList.add('anim-landingSection-title-fade-in');
    }
    if (scrollHint) {
      scrollHint.classList.add('anim-landingSection-scrollHint-fade-in');
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
.wrapper-landingPage {
  .page-title {
    opacity: 0;
    position: absolute;
    width: 50%;
    height: fit-content;
    margin: 60vh auto auto 8%;
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
      margin-left: 10px;
      font-size: 1.5em;
      font-weight: 300;
      color: darkgrey;
      transform: translateX(-6px);
    }
    #name-span {
      color: $theme-primary-color;
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
@media screen and (max-width: 1300px) {
  .wrapper-landingPage {
    font-size: 0.8em;
  }
}
@media screen and (max-width: 950px) {
  .wrapper-landingPage {
    font-size: 0.7em;
  }
}
@media screen and (max-width: 675px) {
  .wrapper-landingPage {
    .page-title {
      width: 100%;
      margin-left: auto;
      margin-right: auto;
      text-align: center;
    }
  }
}
</style>
