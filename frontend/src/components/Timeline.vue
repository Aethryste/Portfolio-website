<template>
  <div class="timeline G_unselectable">
    <div class="year" v-for="(year, index) in years" :key="year" :style="{ top: getYearPosition(index, years.length) }">{{ year }}</div>
    <slot :years="years"></slot> <!-- Slot allows you to inject content dynamically -->
  </div>
</template>

<script>
export default {
  name: 'Timeline',
  data() {
    return {
      years: [2023, 2022, 2021, 2020, 2019, 2018, 2017].reverse()
    };
  },
  methods: {
    getYearPosition(index, total) {
      const marginFraction = 0.1; // 10% margin at top and bottom
      let positionFraction = ((1 - 2 * marginFraction) * (total - index - 1)) / (total - 1) + marginFraction;
      return (positionFraction * 100) + "%";
    }
  }
}
</script>

<style scoped>
.timeline {
  width: 5px;
  height: 100%;
  margin: auto!important; /* Centering the timeline */
  position: relative;
  background-color: white;
  color: black;
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

.timeline:before {
  content: "";
  position: absolute;
  top: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 2px;
  height: 100%;
  background-color: white;
}

.year {
  position: absolute;
  left: 5px;
  transform: translateX(-100%);
  background-color: white;
  padding: 5px 10px;
  border-radius: 2px;
  font-weight: 500;
}
</style>
