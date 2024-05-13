<script lang="ts">
export default {
  name: "githubProjects_item"
}
</script>

<template>
  <div class="container">
    <header>
      <slot name="header"/>
    </header>
    <main>
      <slot name="body"/>
    </main>
    <footer>
      <slot name="footer"/>
    </footer>
  </div>
</template>

<style scoped lang="scss">
.container {
  border: 1px solid blue;
  min-width: 300px;
  width: fit-content;
  min-height: 200px;
  height: fit-content;
  margin: 10px;

  display: flex;
  flex-direction: column;
  justify-content: start;
  text-align: start;
  header {
    font-size: 1.5em;
    width: 95%;
    margin: 0.5em auto 0.5em auto;
  }
  main {
    margin: auto;
    border: 1px solid green;
    height: 100%;
    width: 95%;
  }
  footer {
    width: 95%;
    margin: auto auto 0.5em auto;
  }
}
</style>