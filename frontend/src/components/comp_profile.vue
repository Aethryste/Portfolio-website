<template>
  <div class="comp-wrapper">
    <div class="box-shadow">
      <div class="shape">
        <div class="image"></div>
      </div>
    </div>
    <div class="short-description">
      <h3>Nice to meet you!</h3>
      <p>I'm a Software Engineering student at the Amsterdam University of Applied Sciences, specializing in Front-end development.</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'comp_profile'
}
</script>

<style scoped lang="scss">
.comp-wrapper {
  .box-shadow {
    filter: drop-shadow(-1px 6px 3px rgba(50, 50, 0, 0.5));
    .shape {
      position: relative;
      width: 26vh;
      height: 26vh;
      margin: auto;
      clip-path: polygon( 50% 0%, 95% 25%, 95% 75%, 50% 100%, 5% 75%, 5% 25%);
      transition: 400ms;
      overflow: hidden;
      .image {
        position: relative;
        width: 100%;
        height: 100%;
        margin:auto;
        background-image: url("https://www.thisiscolossal.com/wp-content/uploads/2019/02/moon_crop.jpg");
        background-size: cover;
      }
      &:hover {
        transform: translateY(-5px);
      }
    }
  }
  .short-description {
    position: relative;
    width: 30%;
    height: fit-content;
    margin: auto;
    color: rgb(50,50,50);
    font-family: "Roboto Light", sans-serif;
    text-align: center;
    h3 {
      margin: 10px;
      font-size: 1.5em;
      font-weight: 600;
    }
  }
}
</style>
