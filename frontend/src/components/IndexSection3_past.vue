<template>
  <div class="component-wrapper" ref="wrapper">
    <div class="timeline-component">
      <p class="description" v-if="selectedPoint">{{ selectedPoint.description }}</p>
      <div ref="line" class="timeline">
        <transition name="fade-scale" mode="out-in">
          <div v-if="isActive && pointVisible">
            <button
              v-for="(point, index) in points"
              :key="index"
              class="point"
              @click="selectPoint(point)"
              @mouseover="changeBackground"
              @mouseout="resetBackground"
            ></button>
          </div>
        </transition>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'IndexSection3_History',
  props: {
    isActive: Boolean
  },
  data() {
    return {
      points: [
        { id: 1, description: 'Example 1' },
        { id: 2, description: 'Example 2' },
        { id: 3, description: 'Example 3' },
        { id: 4, description: 'Example 4' },
        { id: 5, description: 'Example 5' },
        { id: 6, description: 'Example 6' },
        { id: 7, description: 'Example 7' },
        { id: 8, description: 'Example 8' },
      ],
      selectedPoint: null,
      pointVisible: false,
      isHovered: false
    };
  },
  watch: {
    isActive(val) {
      if (val) {
        this.$refs.wrapper.style.opacity = '1';
        this.$refs.line.classList.add('slide-in');
        setTimeout(() => {
          this.pointVisible = true;
        }, 1000);
      } else {
        this.$refs.wrapper.style.opacity = '0';
        this.pointVisible = false;
      }
    }
  },
  methods: {
    selectPoint(point) {
      this.selectedPoint = point;
    },
    changeBackground() {
      this.$refs.wrapper.classList.add('point-hovered');
    },
    resetBackground() {
      this.$refs.wrapper.classList.remove('point-hovered');
    }
  }
};
</script>

<style scoped lang="scss">
$points: 1 2 3 4 5 6 7 8;

.component-wrapper {
  color: white;
  width: 100vw;
  height: 100vh;
  opacity: 0;
  display: flex;
  justify-content: center;
  align-items: flex-end;
  background: linear-gradient(30deg, #063B44, #190C31);
  transition: background 0.8s;
  &.point-hovered {
    animation: changeBackground 1s forwards;
  }
  .timeline-component {
    position: relative;
    height: 200px;
    width: 100vw;
    max-width: 100vw;
    margin: 0 auto !important;
    border: 1px solid red;
    .description {
      position: absolute;
      top: 20px;
      width: 100%;
      text-align: center;
    }
    .timeline {
      position: absolute;
      bottom: 50%;
      left: 0;
      right: 0;
      height: 2px;
      width: 100%;
      background-color: white;
      transform: scaleX(0);
      transform-origin: left;
      transition: transform 0.8s 0.8s;
      &.slide-in {
        transform: scaleX(1);
      }
      .point {
        position: absolute;
        width: 15px;
        height: 15px;
        bottom: -7px;
        background-color: white;
        opacity: 0;
        transform: scale(0);
        border-radius: 50%;
        border-style: none;
        @for $i from 1 through length($points) {
          &:nth-child(#{$i}) {
            $i-factor: $i / (length($points) + 1);
            left: calc(100% * #{$i-factor} - 7.5px);
            animation: fadeInScale 0.5s forwards;
            animation-delay: #{$i * 0.25}s; // Starts after the line animation.
          }
        }
      }
    }
  }
}
@keyframes fadeInScale {
  from {
    opacity: 0;
    transform: scale(0);
  }
  to {
    opacity: 1;
    transform: scale(1);
  }
}
@keyframes fadeOut {
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
  }
}
@keyframes changeBackground {
  from {
    background: linear-gradient(30deg, #063B44, #190C31);
  }
  to {
    background: linear-gradient(90deg, #2F9FB3, #351E8A);
  }
}
@keyframes reverseChangeBackground {
  from {
    background: linear-gradient(90deg, #2F9FB3, #351E8A);
  }
  to {
    background: linear-gradient(30deg, #063B44, #190C31);
  }
}
</style>
