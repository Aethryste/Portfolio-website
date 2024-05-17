<template>
  <div class="component-wrapper">
    <div class="timeline">
      <swiper
        direction="vertical"
        :navigation="{ nextEl: '.swiper-button-next', prevEl: '.swiper-button-prev' }"
        :speed="800"
        :touchEventsTarget="'none'"
        :simulateTouch="false"
        :allowTouchMove="false"
        ref="mySwiper"
      >
        <swiper-slide
          v-for="slide in slides"
          :key="slide.year"
          :class="{ 'swiper-slide-active': slide.active }"
          :style="{ backgroundImage: generateBackgroundImage(slide) }"
          :data-year="slide.year"
        >
          <div class="swiper-slide-content G_unselectable">
            <div class="slide-description">
              <span class="timeline-year">{{ slide.year }}</span>
              <h4 class="timeline-title">{{ slide.title }}</h4>
              <p class="timeline-text">{{ slide.body }}</p>
            </div>
          </div>
        </swiper-slide>
        <div class="navigation-buttons">
          <div id="swiper-button-prev" @click="prevSlide"></div>
          <div id="navigation-deco-top"></div>
          <div id="year-list">
          <span
            v-for="slide in slides"
            :key="slide.year"
            @click="goToSlide(slide.year)"
          >
            {{ slide.year }}
          </span>
          </div>
          <div id="navigation-deco-bot"></div>
          <div id="swiper-button-next" @click="nextSlide"></div>
        </div>
      </swiper>
    </div>
  </div>
</template>

<script>
import { Swiper, SwiperSlide } from 'vue-awesome-swiper';
import 'swiper/swiper-bundle.css';

export default {
  name: 'section_experience',
  components: {
    Swiper,
    SwiperSlide,
  },
  data() {
    return {
      slides: []
    };
  },
  methods: {
    fetchSlides() {
      let ajax = new XMLHttpRequest()
      ajax.open('GET', BACKEND_URL+'/res/slides')
      ajax.onload = () => {
        this.slides = JSON.parse(ajax.responseText);
      };
      ajax.onerror = () => {
        console.error('Error fetching slideshow data.');
      };
      ajax.send();
    },
    fetchIcons() {
      let ajax = new XMLHttpRequest()
      ajax.open('GET', BACKEND_URL + '/res/icon/icon-arrow.svg');
      ajax.responseType = 'text';
      ajax.onload = () => {
        if (ajax.status === 200) {
          const base64SVG = btoa(ajax.responseText);
          const maskUrl = `url('data:image/svg+xml;base64,${base64SVG}')`;
          document.documentElement.style.setProperty('--btn-mask', maskUrl);
        } else {
          console.error('Error fetching icon:', ajax.statusText);
        }
      };
      ajax.onerror = () => {
        console.error('Error fetching icon.');
      };
      ajax.send();
    },
    generateBackgroundImage(slide) {
      if (slide.content && slide.type) {
        const base64Image = `data:image/${slide.type};base64,${slide.content}`;
        return `url(${base64Image})`;
      } else { return ''; }
    },
    goToSlide(year) {
      const index = this.slides.findIndex(slide => slide.year === year);
      this.$refs.mySwiper.$el.swiper.slideTo(index);
    },
    nextSlide() {
      this.$refs.mySwiper.$el.swiper.slideNext();
    },
    prevSlide() {
      this.$refs.mySwiper.$el.swiper.slidePrev();
    }
  },
  created() {
    this.fetchSlides();
    this.fetchIcons();
  }
}
</script>

<style scoped lang="scss">
@import "../styles/theme";
.component-wrapper {
  width: 100dvw;
  height: 100dvh;
  overflow: hidden;
  .timeline {
    width: 100%;
    height: 100%;
    .swiper {
      height: 100%;
      .navigation-buttons {
        z-index: 10;
        position: absolute;
        height: 95%;
        right: 130px;
        top: 50%;
        transform: translate(-50%,-50%);
        display: flex;
        flex-direction: column;
        #year-list {
          position: relative;
          width: fit-content;
          height: fit-content;
          display: flex;
          flex-direction: column;
          span {
            margin: auto!important;
            padding: 5px;
            font-size: 1.1em;
            font-weight: bold;
            color: $theme-primary-color;
            text-shadow: 3px 4px 7px rgba(0,0,0,0.8);
            opacity: 0.8;
            cursor: pointer;
            transition: 0.3s;
            &:hover {
              color: $general-text-color;
              opacity: 1;
            }
          }
        }
        #swiper-button-prev, #swiper-button-next, #navigation-deco-top, #navigation-deco-bot {
          position: relative;
          display: flex;
          width: 30px;
        }
        #swiper-button-prev, #swiper-button-next {
          height: 30px;
          margin: auto!important;
          mask: var(--btn-mask);
          mask-size: cover;
          background-color: $theme-primary-color;
          cursor: pointer;
          transform: scaleY(70%);
          filter: none;
          transition: 300ms;
          &:hover {
            background-color: white;
          }
        }
        #swiper-button-next {
          rotate: 180deg;
        }
        #navigation-deco-top, #navigation-deco-bot {
          mask: url("../assets/brushStroke.svg");
          mask-size: cover;
          mask-repeat: no-repeat;
          mask-position: center;
          background-color: $theme-primary-color;
          height: 15%;
          transform: scaleX(50%);
        }
        #navigation-deco-top {
          margin: auto auto 10px auto!important;
        }
        #navigation-deco-bot {
          rotate: 180deg;
          margin: 10px auto auto auto!important;
        }
      }
    }
    .swiper-slide {
      margin: auto!important;
      height: 100%;
      position: relative;
      overflow: hidden;
      background-size: cover;
      background-repeat: no-repeat;
      background-position: center center;
      &::after {
        content: "";
        position: absolute;
        z-index: 1;
        right: -115%;
        bottom: -10%;
        width: 100%;
        height: 100%;
        background-color: rgba(black, .7);
        box-shadow: -230px 0 150px 60vw rgba(black, .7);
        border-radius: 100%;
      }
      &-content {
        position: absolute;
        right: 10px;
        top: 50%;
        width: 470px;
        height: fit-content;
        transform: translate(-50%,-50%);
        text-align: right;
        z-index: 15;
      }
      .timeline-year, .timeline-title, .timeline-text {
        transform: translate3d(20px, 0, 0);
        opacity: 0;
      }
      .timeline-year {
        display: block;
        color: $theme-primary-color;
        font-style: italic;
        font-weight: 100;
        font-size: 42px;
      }
      .timeline-title {
        margin: 0 0 -5px;
        font-weight: 300;
        font-size: 34px;
      }
      .timeline-text {
        font-size: 12px;
        line-height: 1.5;
      }
      &-active {
        .timeline-year, .timeline-title, .timeline-text {
          opacity: 1;
          transform: translate3d(0, 0, 0);
          transition: 1100ms ease .4s;
        }
      }
    }
  }
}
@media screen and (max-width: 1200px) {
  .component-wrapper .timeline .swiper {
    .swiper-slide-content {
      transform: translate(-30%,-50%);
    }
    .navigation-buttons {
      right: 50px;
    }
  }
}
@media screen and (max-width: 700px) {
  .component-wrapper .timeline .swiper {
    .swiper-slide-content {
      transform: translate(0,0);
      top: 0;
      left: 0;
      position: relative;
      margin: calc(50% - 100px) auto auto auto!important;
      text-align: center;
      width: 280px;
      height: 200px;
    }
    .navigation-buttons {
      background: rgba(255,255,255,0);
      box-shadow: 0 0 10px 5px rgb(30,30,30);
      border-radius: 10px;
      //backdrop-filter: blur(8px);
      flex-direction: column;
      height: fit-content;
      width: 100px;
      transform: translate(0,0);
      left: 0;
      bottom: 50px;
      margin: auto auto 10% calc(50% - 50px)!important;
      justify-content: center;
      #year-list {
        flex-direction: column;
        margin: auto!important;
        span {
          border-bottom: 1px solid $theme-primary-color;
          &:nth-last-child(1) {
            border-bottom: none;
          }
        }
      }
      #navigation-deco-top, #navigation-deco-bot {
        opacity: 0;
      }
    }
  }
}
</style>
