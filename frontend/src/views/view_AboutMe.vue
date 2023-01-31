<template>
  <div class="page-wrapper">
    <h1 class="page-title">About Me</h1>
    <div class="profile">
      <div class="image">

      </div>
      <div class="details">

      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'view_AboutMe'
}
</script>

<style scoped lang="scss">
.page-wrapper {
  .page-title {
    color: ghostwhite;
    margin: 10% 10px 10px 10%;
    position: relative;
    font-size: 4em;
    font-family: 'montserrat', sans-serif;
    font-weight: 100;
  }
  .profile {
    border: red 1px solid;
    width: 30%;
    height: 500px;
    margin: 2% 0 0 10%;
    .image {
      border: orange 1px solid;
      width: 200px;
      height: 200px;
      border-radius: 100%;
      margin: 0 auto 0 auto;
    }
  }
}
</style>
