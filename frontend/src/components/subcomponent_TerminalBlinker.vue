<template>
  <div>{{ displayText }}</div>
</template>

<script>
export default {
  name: "TerminalBlinker",
  data() {
    return {
      states: [">_", ">"],
      stateIndex: 0,
      displayText: ">_",
    };
  },
  mounted() {
    this.startBlinking();
  },
  methods: {
    blink() {
      this.stateIndex = (this.stateIndex + 1) % this.states.length;
      this.displayText = this.states[this.stateIndex];
      setTimeout(this.blink, 1000);
    },
    startBlinking() {
      this.blink();
    },
  },
};
</script>

<style scoped>
div {
  text-align: left;
  font-weight: bold;
  font-size: 1.4em;
  font-family:
    Inter,
    ui-sans-serif,system-ui,
    -apple-system,
    BlinkMacSystemFont,
    Segoe UI,
    Roboto,
    Helvetica Neue,
    Arial,
    Noto Sans,
    sans-serif,
    "Apple Color Emoji",
    "Segoe UI Emoji",
    Segoe UI Symbol,
    "Noto Color Emoji";
}
</style>
