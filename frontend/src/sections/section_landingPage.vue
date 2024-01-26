<template>
  <div class="page-wrapper">

    <div class="front-layer G_unselectable">
      <div class="page-title" ref="pageTitle">
        <h1 class="main">Hi, I'm Richard.</h1>
        <h2 class="sub">Software Engineer, front-end developer.</h2>
      </div>
    </div>

<!--    <div class="back-layer G_unselectable">-->
<!--      <frontpage_threeJs id="threeJs"/>-->
<!--    </div>-->

  </div>
</template>

<script>
import frontpage_threeJs from '@/components/frontpage_threejs.vue'
// https://play.tresjs.org/#eNqdVV1z4jYU/SsaZzp5KNgmJGnrZjsU9mO20+xmFt7W+yDsG6xEljSSIDAZ/nuvJDB2Ns2SBQ+D7sfR0dG914/RTIP5W6l4tYQoi65MoZmyxIBdKsKpWLzJI2vy6K9csFpJbckjGVPDimlFS/lwTVWPTL98GE8kl3qqaAE98knOpAB0KSYWZEtutazJqa00wOmfLRy39YSKFTVN0Mii7c4khdTd2M96zuxECqslfyacgbDSuIxcFFIYSxacvCGPuSCk4EC155eR05Pfz96OJxenPecx/gwmI1YvwVsoVxXNyC3lJhhCCJ5ltlGQPT26i5BLq5ZIbX/+7KkeLsgeBMm6+qB7i7SvkqA86owLC7Xi1AKuCLlq6bTqz5ko8U4W3N8JwY/334A2CgrLVjChNWhKMiUNs0wKjP76R4+451sekWSf1pW0MTu0azBVF6B/1iP4pA4h09LSvSPtkWtqq/jmY/DuYPa08aAfQNZg9YZkVC+Myxn0yCC+6JFhm09r65mUAkFBM8pJ4ZTErN3NtU+Q7BNe5o4U3eM2K6ix/XCn3W3Hcv0MT0dy//Mqrufv3fcnuKLGTurvlZyqCvRBy6OZvB+P0+H4h0zeMu2qRwrK/2WLyn6nIJI695fPhAWBrg06BvFZV9TdLmGPULNouEpaBY1LYzfc/a1szbH+57LchFatUXkmMpJiRxCiaFn6hgnLChyxjAzS9BdveGClrZr1Nhcn2FIB6AexhMxpcb/QcinKvpcqIydp6vZBGOxFTzDqRWH+9Guq4jsjBU5ID5/vHDgYs7Chs7WHl3PkUWWtMlmSFKXA/BI4W+lYgE2EqpN2+GgYX8aDpGTGJs6M0XnkJ0cHOIy512CHDIT/LU4P8Dtzdxf7APReUXEM9yZ2dB6nDfG9Ma6Zy2lju+F/DK6LGyHiZYp8fwVTt2XAd9TSwNH6tsJHgzS+QEQcnrCO6666uzisYA3lK4BDwkvQGNgvoWZHgO5D3eHP48uEs3kb0iFiaW6xJq3BN9wtWzypyELWinHQn5Xr2m5lUs7lwz/e1rzqfE4Fxf0z9juzDpRvsFhAr1Duw1Vij4IN7nfTT7DG/42zluWS7y7nf5xfwEi+dBxD2Bh7EGm34jzbj77BsP1n5t3aT5zdoTpELb6VvfXrfhw2tRYM37xwPt5fx+QFlQ4nG8bDRvDtfzRV+38=
export default {
  name: 'section_landingPage',
  components: {
    // frontpage_threeJs
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
@import "src/styles/theme";
.page-wrapper {
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
      //border: 1px solid orange;
    }
  }
}
.hidden {
  opacity: 0;
}
</style>
