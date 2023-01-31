<template>
  <div class="comp-wrapper">
    <div class="profile">
      <div class="image">profile img</div>
      <div></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'comp_aboutMe'
}
</script>

<style scoped lang="scss">
.comp-wrapper {
  width: 50rem;
  height: 25rem;
  //background-color: darkgrey;
  .profile {
    width: 100%;
    height: 100%;
    margin: auto auto auto 0;
    background-color: blue;
    .image {
      width: 250px;
      height: 250px;
      margin: 1rem;
      background-color: rgb(1,1,1,0.1);
      border: 2px white solid;
      border-radius: 100%;
    }
  }
}
</style>
