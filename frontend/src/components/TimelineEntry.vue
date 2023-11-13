<template>
  <div class="entry" :style="position">
    <div class="title">{{ title }}</div>
    <div class="subtitle">{{ subtitle }}</div>
    <div class="description">{{ description }}</div>
  </div>
</template>

<script>
export default {
  props: {
    date: { type: String, required: true },
    title: { type: String, required: true },
    subtitle: { type: String, required: true },
    description: { type: String, default: '' },
    years: { type: Array, default: () => [] }
  },
  computed: {
    position() {
      return { top: this.calculatePosition() };
    }
  },
  methods: {
    calculatePosition() {
      let [year, month] = this.date.split('-').map(Number);
      let yearIndex = this.years.indexOf(year);
      if (yearIndex === -1) return '0%';  // handle year not found
      let yearFraction = yearIndex;

      // Calculate the month fraction
      let monthFraction = (month - 1) / 12;

      // Total position as a fraction
      let totalPositionFraction = yearFraction + monthFraction;

      // Factoring in the margin and the equal spacing between years
      const marginFraction = 0.1; // same margin fraction as in Timeline
      let position = ((1 - 2 * marginFraction) * totalPositionFraction / 6) + marginFraction; // still assuming a total of 7 years, so divide by 6

      return (position * 100) + "%";
    }
  }
}
</script>

<style scoped lang="scss">
.entry {
  position: relative;
  transform: translateY(-50%);
  left: 20px;
  background-color: #fff;
  padding: 10px 20px;
  border-radius: 6px;
  width: 350px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.15);
  margin-top: 10px;
  .date {
    font-weight: bold;
    margin-bottom: 10px;
  }
  .title {
    margin-bottom: 10px;
    font-weight: bold;
  }
  .subtitle {
    color: dimgrey;
    font-size: 0.8em;
    margin-bottom: 0.2em!important;
  }
}
.entry::before {
  content: "";
  position: absolute;
  top: 50%;
  left: -10px; /* adjust as needed to position the triangle */
  transform: translateY(-50%); /* center the triangle vertically */
  width: 0;
  height: 0;
  border-style: solid;
  border-width: 10px 10px 10px 0; /* adjust these values to change the triangle size */
  border-color: transparent white transparent transparent;
}
</style>
