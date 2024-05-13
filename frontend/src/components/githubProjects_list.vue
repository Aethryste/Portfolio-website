<script lang="ts">
import GithubProjects_item from "./githubProjects_item.vue";

export default {
  name: "githubProjects_list",
  components: {GithubProjects_item}
}
let sample = []
</script>

<template>
<div class="container">

  <github-projects_item>
    <template v-slot:header>
      Project-1: Project name
    </template>
    <template v-slot:body>
      The body goes here.
    </template>
    <template v-slot:footer>
      And this is the footer.
    </template>
  </github-projects_item>

  <github-projects_item>
    <template v-slot:header>
      Project-2: Project name
    </template>
    <template v-slot:body>
      The body goes here.
    </template>
    <template v-slot:footer>
      And this is the footer.
    </template>
  </github-projects_item>

  <github-projects_item>
    <template v-slot:header>
      Project-3: Project name
    </template>
    <template v-slot:body>
      The body goes here.
    </template>
    <template v-slot:footer>
      And this is the footer.
    </template>
  </github-projects_item>

</div>
</template>

<style scoped lang="scss">
.container {
  border: 1px solid red;
  width: 80%;
  height: 30%;

  display: flex;
  margin: auto;
  justify-content: center;
}
</style>