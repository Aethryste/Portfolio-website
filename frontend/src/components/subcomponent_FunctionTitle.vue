<template>
  <div class="G_unselectable">
    <h1><span>{{ displayText }}</span><span v-if="playAnimation" class="blink">|</span></h1>
  </div>
</template>

<script>
export default {
  name: "FunctionTitle",
  props: {
    playAnimation: {
      type: Boolean,
      default: true,
    },
  },
  data () {
    return {
      words: ["Front-end Developer", "Software Engineer"],
      wordIndex: 0,
      letterIndex: 0,
      reverse: false,
      displayText: "",
    };
  },
  watch: {
    playAnimation (val) {
      if (val) {
        this.startTyping();
      } else {
        this.stopTyping();
      }
    },
  },
  mounted () {
    if (this.playAnimation) {
      this.startTyping();
    }
  },
  methods: {
    type () {
      if (!this.reverse) {
        this.displayText += this.words[this.wordIndex][this.letterIndex];
        this.letterIndex++;

        if (this.letterIndex === this.words[this.wordIndex].length) {
          this.reverse = true;
          setTimeout(this.type, 4000);
        } else {
          setTimeout(this.type, 100);
        }
      } else {
        this.displayText = this.words[this.wordIndex].substring(0, this.letterIndex - 1);
        this.letterIndex--;

        if (this.letterIndex === 0) {
          this.reverse = false;
          this.wordIndex = (this.wordIndex + 1) % this.words.length;
          setTimeout(this.type, 500);
        } else {
          setTimeout(this.type, 50);
        }
      }
    },
    startTyping () {
      this.type();
    },
    stopTyping () {
      clearTimeout(this.type);
      this.wordIndex = 0;
      this.letterIndex = 0;
      this.reverse = false;
    },
  },
};
</script>

<style scoped>
.blink {
  animation: blinker 1s steps(1, end) infinite;
}
@keyframes blinker {
  50% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}
@media screen and (max-width: 500px) {
  h1 {
    font-size: 1.5em!important;
  }
}
</style>
