<template>
  <div class="container">
    <div class="timeline">
      <swiper
        direction="vertical"
        :navigation="{ nextEl: '.swiper-button-next', prevEl: '.swiper-button-prev' }"
        ref="mySwiper"
      >
        <swiper-slide
          v-for="slide in slides"
          :key="slide.year"
          :class="slide.active ? 'swiper-slide-active' : ''"
          :style="{ backgroundImage: `url(${slide.imageUrl})` }"
          :data-year="slide.year"
        >
          <div class="swiper-slide-content">
            <span class="timeline-year">{{ slide.year }}</span>
            <h4 class="timeline-title">{{ slide.title }}</h4>
            <p class="timeline-text">{{ slide.text }}</p>
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
      slides: [
        // TODO: retrieve all info in json from backend.
        // TODO: Reverse slide order, latest first.
        {
          year: 2020,
          imageUrl: 'https://unsplash.it/1920/500?image=11',
          title: 'All earlier experiences.',
          text: "I've got about 5 years of working experience before my decision to career-switch to IT took place, " +
            "most of this experience is related to Graphic Design, Game Development, and providing moving/transport " +
            "services. More details can be found on my LinkedIn.",
          active: false
        },
        {
          year: 2021,
          imageUrl: 'https://unsplash.it/1920/500?image=12',
          title: 'The start of it all.',
          text: "During 2021 I successfully completed a starter course on Python through Udemy, completing this " +
            "course sparked my passion for programming. Not long after I decided to properly career-switch to IT " +
            "by starting my Bachelor's degree at AUAS.",
          active: false
        },
        {
          year: 2022,
          imageUrl: 'https://unsplash.it/1920/500?image=13',
          title: 'My first IT occupations.',
          text: 'Shortly after starting my Bachelor I joined "Newskool", a secondment company specialised in ' +
            'providing, ambitious, starting IT-professionals to businesses in need. Through Newskool I gained my ' +
            'first three IT service/operations occupations, which provided me two consecutive years of professional experience working in large corporations and their digital infrastructures.',
          active: false
        },
        {
          year: 2023,
          imageUrl: 'https://unsplash.it/1920/500?image=14',
          title: 'The next step.',
          text: "Now, having built up professional experience working in IT-operations and having completed about " +
            "half of my Bachelor, it's time for me to land my first Software Engineering / Developer occupation. " +
            "This will be the next big step in my career, which i'm heavily looking forward to!",
          active: false
        }
      ]
    };
  },
  methods: {
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
      .timeline-year {
        display: block;
        font-style: italic;
        font-size: 42px;
        margin-bottom: 50px;
        transform: translate3d(20px, 0, 0);
        color: $primary;
        font-weight: 300;
        opacity: 0;
        transition: .2s ease .4s;
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



<!--<template>-->
<!--  <div class="container">-->
<!--    <div class="timeline">-->
<!--      <swiper :options="swiperOptions">-->
<!--        <swiper-slide-->
<!--          v-for="slide in slides"-->
<!--          :key="slide.year"-->
<!--          :class="slide.active ? 'swiper-slide-active' : ''"-->
<!--          :style="{ backgroundImage: `url(${slide.imageUrl})` }"-->
<!--          :data-year="slide.year"-->
<!--        >-->
<!--          <div class="swiper-slide-content">-->
<!--            <span class="timeline-year">{{ slide.year }}</span>-->
<!--            <h4 class="timeline-title">{{ slide.title }}</h4>-->
<!--            <p class="timeline-text">{{ slide.text }}</p>-->
<!--          </div>-->
<!--        </swiper-slide>-->

<!--        &lt;!&ndash; Navigation buttons &ndash;&gt;-->
<!--        <div class="swiper-button-prev"></div>-->
<!--        <div class="swiper-button-next"></div>-->

<!--        &lt;!&ndash; Pagination &ndash;&gt;-->
<!--        <div class="swiper-pagination"></div>-->
<!--      </swiper>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import { Swiper, SwiperSlide } from 'vue-awesome-swiper'-->
<!--import 'swiper/swiper-bundle.css'-->

<!--export default {-->
<!--  name: 'SlideshowTimeline',-->
<!--  components: {-->
<!--    Swiper,-->
<!--    SwiperSlide,-->
<!--  },-->
<!--  data() {-->
<!--    return {-->
<!--      swiperOptions: {-->
<!--        pagination: {-->
<!--          el: '.swiper-pagination',-->
<!--          clickable: true-->
<!--        },-->
<!--        navigation: {-->
<!--          nextEl: '.swiper-button-next',-->
<!--          prevEl: '.swiper-button-prev',-->
<!--        },-->
<!--      },-->
<!--      slides: [-->
<!--        {-->
<!--          year: 2011,-->
<!--          imageUrl: 'https://unsplash.it/1920/500?image=11',-->
<!--          title: 'Our nice super title',-->
<!--          text: 'Lorem ipsum dolor site amet...',-->
<!--          active: false-->
<!--        },-->
<!--        {-->
<!--          year: 2012,-->
<!--          imageUrl: 'https://unsplash.it/1920/500?image=12',-->
<!--          title: 'Our nice super title',-->
<!--          text: 'Lorem ipsum dolor site amet...',-->
<!--          active: false-->
<!--        },-->
<!--        {-->
<!--          year: 2013,-->
<!--          imageUrl: 'https://unsplash.it/1920/500?image=13',-->
<!--          title: 'Our nice super title',-->
<!--          text: 'Lorem ipsum dolor site amet...',-->
<!--          active: false-->
<!--        },-->
<!--        {-->
<!--          year: 2014,-->
<!--          imageUrl: 'https://unsplash.it/1920/500?image=14',-->
<!--          title: 'Our nice super title',-->
<!--          text: 'Lorem ipsum dolor site amet...',-->
<!--          active: false-->
<!--        },-->
<!--        {-->
<!--          year: 2015,-->
<!--          imageUrl: 'https://unsplash.it/1920/500?image=15',-->
<!--          title: 'Our nice super title',-->
<!--          text: 'Lorem ipsum dolor site amet...',-->
<!--          active: false-->
<!--        },-->
<!--      ]-->
<!--    };-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style scoped lang="scss">-->
<!--// Colors-->
<!--$white: #fff;-->
<!--$black: #000;-->
<!--$primary: #d4a024;-->
<!--$gray: #616161;-->

<!--.container {-->
<!--  display: flex;-->
<!--  justify-content: center;-->
<!--  align-items: center;-->
<!--  flex-direction: column;-->
<!--  margin: 0!important;-->
<!--  //width: 100%!important;-->
<!--  //height: 100%!important;-->
<!--  .timeline {-->
<!--    width: 100%;-->
<!--    height: 100%;-->
<!--    //background-color: #fff;-->
<!--    //box-shadow: 0 5px 25px 5px rgba($black, .2);-->
<!--    .swiper {-->
<!--      width: 100%;-->
<!--      height: 100%;-->
<!--      //margin: 0!important;-->
<!--    }-->
<!--    .swiper-container {-->
<!--      //height: 100%;-->
<!--      //width: 100%;-->
<!--      //position: relative;-->
<!--    }-->
<!--    .swiper-wrapper {-->
<!--      //transition: 2s cubic-bezier(.68,-0.4,.27,1.34) .2s;-->
<!--    }-->
<!--    .swiper-slide {-->
<!--      position: relative;-->
<!--      //color: $white;-->
<!--      overflow: hidden;-->
<!--      background-size: cover;-->
<!--      background-repeat: no-repeat;-->
<!--      background-position: center center;-->
<!--      //width: 100%;-->
<!--      //height: 100%;-->
<!--      &::after {-->
<!--        content: "";-->
<!--        position: absolute;-->
<!--        z-index: 1;-->
<!--        right: -115%;-->
<!--        bottom: -10%;-->
<!--        width: 100%;-->
<!--        height: 100%;-->
<!--        background-color: rgba($black, .7);-->
<!--        box-shadow: -230px 0 150px 60vw rgba($black, .7);-->
<!--        border-radius: 100%;-->
<!--      }-->
<!--      &-content {-->
<!--        position: absolute;-->
<!--        text-align: center;-->
<!--        width: 80%;-->
<!--        max-width: 310px;-->
<!--        right: 50%;-->
<!--        top: 13%;-->
<!--        transform: translate(50%, 0);-->
<!--        font-size: 12px;-->
<!--        z-index: 2;-->
<!--      }-->
<!--      .timeline-year {-->
<!--        display: block;-->
<!--        font-style: italic;-->
<!--        font-size: 42px;-->
<!--        margin-bottom: 50px;-->
<!--        transform: translate3d(20px, 0, 0);-->
<!--        color: $primary;-->
<!--        font-weight: 300;-->
<!--        opacity: 0;-->
<!--        transition: .2s ease .4s;-->
<!--      }-->
<!--      .timeline-title {-->
<!--        font-weight: 800;-->
<!--        font-size: 34px;-->
<!--        margin: 0 0 30px;-->
<!--        opacity: 0;-->
<!--        transform: translate3d(20px, 0, 0);-->
<!--        transition: .2s ease .5s;-->
<!--      }-->
<!--      .timeline-text {-->
<!--        line-height: 1.5;-->
<!--        opacity: 0;-->
<!--        transform: translate3d(20px, 0, 0);-->
<!--        transition: .2s ease .6s;-->
<!--      }-->
<!--      &-active {-->
<!--        .timeline-year {-->
<!--          opacity: 1;-->
<!--          transform: translate3d(0, 0, 0);-->
<!--          transition: .4s ease 1.6s;-->
<!--        }-->
<!--        .timeline-title {-->
<!--          opacity: 1;-->
<!--          transform: translate3d(0, 0, 0);-->
<!--          transition: .4s ease 1.7s;-->
<!--        }-->
<!--        .timeline-text {-->
<!--          opacity: 1;-->
<!--          transform: translate3d(0, 0, 0);-->
<!--          transition: .4s ease 1.8s;-->
<!--        }-->
<!--      }-->
<!--    }-->
<!--    .swiper-pagination {-->
<!--      //right: 15%!important;-->
<!--      width: 100%;-->
<!--      height: 100%;-->
<!--      display: none; // Set to flex if screen wide enough.-->

<!--      flex-direction: column;-->
<!--      justify-content: center;-->
<!--      font-style: italic;-->
<!--      font-weight: 300;-->
<!--      font-size: 18px;-->
<!--      z-index: 5;-->
<!--      &::before {-->
<!--        content: "";-->
<!--        position: absolute;-->
<!--        left: -30px;-->
<!--        top: 0;-->
<!--        height: 100%;-->
<!--        width: 1px;-->
<!--        background-color: rgba($white, .2);-->
<!--      }-->
<!--      &-bullet {-->
<!--        width: auto;-->
<!--        height: auto;-->
<!--        text-align: center;-->
<!--        opacity: 1;-->
<!--        background: transparent;-->
<!--        color: $primary;-->
<!--        margin: 15px 0!important;-->
<!--        position: relative;-->
<!--        &::before {-->
<!--          content: "";-->
<!--          position: absolute;-->
<!--          top: 8px;-->
<!--          left: -32.5px;-->
<!--          width: 6px;-->
<!--          height: 6px;-->
<!--          border-radius: 100%;-->
<!--          background-color: $primary;-->
<!--          transform: scale(0);-->
<!--          transition: .2s;-->
<!--        }-->
<!--        &-active {-->
<!--          color: $primary;-->
<!--          &::before { transform: scale(1); }-->
<!--        }-->
<!--      }-->
<!--    }-->
<!--    .swiper-button-next, .swiper-button-prev {-->
<!--      background-size: 20px 20px;-->
<!--      top: 15%;-->
<!--      width: 20px;-->
<!--      height: 20px;-->
<!--      margin-top: 0;-->
<!--      z-index: 2;-->
<!--      transition: .2s;-->
<!--      color: transparent;-->
<!--    }-->
<!--    .swiper-button-prev {-->
<!--      left: 8%;-->
<!--      background-image: url("data:image/svg+xml;charset=utf-8,%3Csvg%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20viewBox%3D'0%200%2027%2044'%3E%3Cpath%20d%3D'M0%2C22L22%2C0l2.1%2C2.1L4.2%2C22l19.9%2C19.9L22%2C44L0%2C22L0%2C22L0%2C22z'%20fill%3D'%23d4a024'%2F%3E%3C%2Fsvg%3E");-->
<!--      &:hover {-->
<!--        transform: translateX(-3px);-->
<!--      }-->
<!--    }-->
<!--    .swiper-button-next {-->
<!--      right: 8%;-->
<!--      background-image: url("data:image/svg+xml;charset=utf-8,%3Csvg%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20viewBox%3D'0%200%2027%2044'%3E%3Cpath%20d%3D'M27%2C22L27%2C22L5%2C44l-2.1-2.1L22.8%2C22L2.9%2C2.1L5%2C0L27%2C22L27%2C22z'%20fill%3D'%23d4a024'%2F%3E%3C%2Fsvg%3E");-->
<!--      &:hover {-->
<!--        transform: translateX(3px);-->
<!--      }-->
<!--    }-->
<!--    @media screen and (min-width: 768px) {-->
<!--      .swiper-slide {-->
<!--        &::after {-->
<!--          right: -30%;-->
<!--          bottom: -8%;-->
<!--          width: 240px;-->
<!--          height: 50%;-->
<!--          box-shadow: -230px 0 150px 50vw rgba($black, .7);-->
<!--        }-->
<!--        &-content {-->
<!--          right: 30%;-->
<!--          top: 50%;-->
<!--          transform: translateY(-50%);-->
<!--          width: 310px;-->
<!--          font-size: 11px;-->
<!--          text-align: right;-->
<!--        }-->
<!--        .timeline-year {-->
<!--          margin-bottom: 0;-->
<!--          font-size: 32px;-->
<!--        }-->
<!--        .timeline-title {-->
<!--          font-size: 46px;-->
<!--          margin: 0;-->
<!--        }-->
<!--      }-->
<!--      .swiper-pagination {-->
<!--        display: flex;-->
<!--        border: 1px solid blue;-->
<!--      }-->
<!--      .swiper-button-prev {-->
<!--        top: 15%;-->
<!--        left: auto;-->
<!--        right: 15%;-->
<!--        transform: rotate(90deg) translate(0, 10px);-->
<!--        &:hover {-->
<!--          transform: rotate(90deg) translate(-3px, 10px);-->
<!--        }-->
<!--      }-->
<!--      .swiper-button-next {-->
<!--        top: auto;-->
<!--        bottom: 15%;-->
<!--        right: 15%;-->
<!--        transform: rotate(90deg) translate(0, 10px);-->
<!--        &:hover {-->
<!--          transform: rotate(90deg) translate(3px, 10px);-->
<!--        }-->
<!--      }-->
<!--    }-->
<!--    @media screen and (min-width: 1024px) {-->
<!--      .swiper-slide {-->
<!--        &::after {-->
<!--          right: -20%;-->
<!--          bottom: -12%;-->
<!--          width: 240px;-->
<!--          height: 50%;-->
<!--          box-shadow: -230px 0 150px 39vw rgba($black, .7);-->
<!--        }-->
<!--        &-content {-->
<!--          right: 25%;-->
<!--        }-->
<!--      }-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</style>-->
