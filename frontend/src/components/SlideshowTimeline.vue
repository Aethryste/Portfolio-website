<template>
  <div class="container">
    <div class="timeline">
      <swiper
        direction="vertical"
        :navigation="{ nextEl: '.swiper-button-next', prevEl: '.swiper-button-prev' }"
        :speed="800"
        ref="mySwiper"
      >
        <swiper-slide
          v-for="slide in slides"
          :key="slide.year"
          :class="{ 'swiper-slide-active': slide.active }"
          :style="{ backgroundImage: generateBackgroundImage(slide) }"
          :data-year="slide.year"
        >
          <div class="swiper-slide-content">
            <span class="timeline-year">{{ slide.year }}</span>
            <h4 class="timeline-title">{{ slide.title }}</h4>
            <p class="timeline-text">{{ slide.body }}</p>
          </div>
        </swiper-slide>

        <div class="navigation-buttons">
          <div class="swiper-button-prev" @click="prevSlide"></div>
          <div class="year-list">
          <span
            v-for="slide in slides"
            :key="slide.year"
            @click="goToSlide(slide.year)"
          >
            {{ slide.year }}
          </span>
          </div>
          <div class="swiper-button-next" @click="nextSlide"></div>
        </div>

      </swiper>
    </div>
  </div>
</template>

<script>
import { Swiper, SwiperSlide } from 'vue-awesome-swiper'
import 'swiper/swiper-bundle.css'

export default {
  name: 'SlideshowTimeline',
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
    this.fetchData();
  }
}
</script>

<style scoped lang="scss">
// Colors
$white: #fff;
$black: #000;
$primary: #d4a024;
$gray: #616161;

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  margin: 0!important;
  .timeline {
    width: 100%;
    height: 100%;
    .swiper {
      width: 100%;
      height: 100%;
      .navigation-buttons {
        .year-list {
          position: absolute;
          top: 50%;
          right: 12.6%;
          transform: translate(-50%, -50%);
          display: flex;
          flex-direction: column;
          z-index: 10;
          span {
            cursor: pointer;
            padding: 5px 10px;
            margin: 5px 0;
            background-color: rgba($black, 0.6);
            color: $white;
            transition: background-color 0.3s;
            &:hover {
              background-color: $primary;
            }
          }
        }
        .swiper-button-prev {
          top: 10%;
        }
        .swiper-button-next {
          bottom: 10%;
        }
      }
    }
    .swiper-container {}
    .swiper-wrapper {}
    .swiper-slide {
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
        background-color: rgba($black, .7);
        box-shadow: -230px 0 150px 60vw rgba($black, .7);
        border-radius: 100%;
      }
      &-content {
        position: absolute;
        text-align: center;
        width: 80%;
        max-width: 310px;
        right: 50%;
        top: 13%;
        transform: translate(50%, 0);
        font-size: 12px;
        z-index: 2;
      }
      &-active {
        .timeline-year, .timeline-title, .timeline-text {
          transition: 1100ms ease .4s!important;
        }
      }
      .timeline-year {
        display: block;
        font-style: italic;
        font-size: 42px;
        margin-bottom: 50px;
        transform: translate3d(20px, 0, 0);
        color: $primary;
        font-weight: 300;
        opacity: 0;
        //transition: .2s ease .4s;
      }
      .timeline-title {
        font-weight: 800;
        font-size: 34px;
        margin: 0 0 30px;
        opacity: 0;
        transform: translate3d(20px, 0, 0);
        transition: .2s ease .5s;
      }
      .timeline-text {
        line-height: 1.5;
        opacity: 0;
        transform: translate3d(20px, 0, 0);
        transition: .2s ease .6s;
      }
      &-active {
        .timeline-year {
          opacity: 1;
          transform: translate3d(0, 0, 0);
          transition: .4s ease 1.6s;
        }
        .timeline-title {
          opacity: 1;
          transform: translate3d(0, 0, 0);
          transition: .4s ease 1.7s;
        }
        .timeline-text {
          opacity: 1;
          transform: translate3d(0, 0, 0);
          transition: .4s ease 1.8s;
        }
      }
    }
    .swiper-button-next, .swiper-button-prev {
      background-size: 20px 20px;
      top: 15%;
      width: 20px;
      height: 20px;
      margin-top: 0;
      z-index: 2;
      transition: .2s;
      color: transparent;
    }
    .swiper-button-prev {
      left: 8%;
      background-image: url("data:image/svg+xml;charset=utf-8,%3Csvg%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20viewBox%3D'0%200%2027%2044'%3E%3Cpath%20d%3D'M0%2C22L22%2C0l2.1%2C2.1L4.2%2C22l19.9%2C19.9L22%2C44L0%2C22L0%2C22L0%2C22z'%20fill%3D'%23d4a024'%2F%3E%3C%2Fsvg%3E");
      &:hover {
        transform: translateX(-3px);
      }
    }
    .swiper-button-next {
      right: 8%;
      background-image: url("data:image/svg+xml;charset=utf-8,%3Csvg%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20viewBox%3D'0%200%2027%2044'%3E%3Cpath%20d%3D'M27%2C22L27%2C22L5%2C44l-2.1-2.1L22.8%2C22L2.9%2C2.1L5%2C0L27%2C22L27%2C22z'%20fill%3D'%23d4a024'%2F%3E%3C%2Fsvg%3E");
      &:hover {
        transform: translateX(3px);
      }
    }
    @media screen and (min-width: 768px) {
      .swiper-slide {
        &::after {
          right: -30%;
          bottom: -8%;
          width: 240px;
          height: 50%;
          box-shadow: -230px 0 150px 50vw rgba($black, .7);
        }
        &-content {
          right: 20%!important;
          top: 50%;
          transform: translateY(-50%);
          width: 510px;
          max-width: 100%;
          font-size: 11px;
          text-align: right;
        }
        .timeline-year {
          margin-bottom: 0;
          font-size: 32px;
        }
        .timeline-title {
          font-size: 3em;
          margin: 0.1em 0 0.4em 0 !important;
        }
        .timeline-text {
          font-size: 1.1em;
        }
      }
      .swiper-pagination {
        display: flex;
        border: 1px solid blue;
      }
      .swiper-button-prev {
        top: 15%;
        left: auto;
        right: 15%;
        transform: rotate(90deg) translate(0, 10px);
        &:hover {
          transform: rotate(90deg) translate(-3px, 10px);
        }
      }
      .swiper-button-next {
        top: auto;
        bottom: 15%;
        right: 15%;
        transform: rotate(90deg) translate(0, 10px);
        &:hover {
          transform: rotate(90deg) translate(3px, 10px);
        }
      }
    }
    @media screen and (min-width: 1024px) {
      .swiper-slide {
        &::after {
          right: -20%;
          bottom: -12%;
          width: 240px;
          height: 50%;
          box-shadow: -230px 0 150px 39vw rgba($black, .7);
        }
        &-content {
          right: 25%;
        }
      }
    }
  }
}

</style>
