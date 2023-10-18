<template>
  <div class="page-wrapper G_unselectable">
    <div class="page-title" ref="pageTitle">
      <h1 class="main">Hi, I'm Richard.</h1>
      <h2 class="sub">Software Engineer, front-end developer.</h2>
      <div class="terminal">
        <TerminalBlinker/>
      </div>
    </div>
  </div>
</template>

<script>
import TerminalBlinker from '@/components/subcomponent_TerminalBlinker'
export default {
  name: 'IndexSectionLanding',
  components: {
    TerminalBlinker
  },
  props: {
    isActive: Boolean
  },
  watch: {
    isActive(newVal, oldVal) {
      if (newVal !== oldVal) {
        this.handleIsTrueChange(newVal);
      }
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
      if (pageTitle) {
        pageTitle.classList.remove('anim-fade-in');
        pageTitle.classList.add('anim-fade-out');
        pageTitle.addEventListener('animationend', function callback() {
          pageTitle.style.display = 'none';
          pageTitle.removeEventListener('animationend', callback);
        });
      }
    },
    header_enable: function () {
      const pageTitle = this.$refs.pageTitle;
      if (pageTitle) {
        pageTitle.style.display = '';
        pageTitle.classList.remove('anim-fade-out');
        pageTitle.classList.add('anim-fade-in');
        pageTitle.addEventListener('animationend', function callback() {
          pageTitle.removeEventListener('animationend', callback);
        });
      }
    }
  },
  mounted () {
    const pageTitle = this.$refs.pageTitle;
    if (pageTitle) {
      pageTitle.classList.add('anim-fade-in');
    }
  }
}
</script>

<style scoped lang="scss">
@import "./src/styles/theme.scss";
.page-wrapper {
  position: relative;
  width: 100vw;
  height: 100vh;
  margin: 0;
  overflow: hidden;
  .page-title {
    opacity: 0;
    position: relative;
    width: 50%;
    height: fit-content;
    margin: 60vh auto auto 8% !important;
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
    .terminal {
      margin: 1.5em auto auto 0 !important;
    }
  }
}
.hidden {
  opacity: 0;
}
</style>
