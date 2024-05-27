<script lang="ts">
import { defineComponent } from 'vue';
import ProjectsTile from './projects_tile.vue';

export default defineComponent({
  components: {
    ProjectsTile,
  },
  data() {
    return {
      projects: [
        {
          passiveImg: '../assets/project_portfolio_front.png',
          activeImg: 'right.jpg',
          title: 'Portfolio website',
          description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vestibulum metus a lorem convallis eleifend. Vivamus tempus in metus et rutrum.',
          tools: ['Vue', 'Vite', 'TypeScript', 'SCSS'],
        },
        {
          passiveImg: 'front.jpg',
          activeImg: 'right.jpg',
          title: 'Mailing Service',
          description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vestibulum metus a lorem convallis eleifend. Vivamus tempus in metus et rutrum.',
          tools: ['Java', 'Spring', 'JavaMail'],
        },
        {
          passiveImg: 'front.jpg',
          activeImg: 'right.jpg',
          title: 'None1',
          description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vestibulum metus a lorem convallis eleifend. Vivamus tempus in metus et rutrum.',
          tools: [],
        },
        {
          passiveImg: 'front.jpg',
          activeImg: 'right.jpg',
          title: 'None2',
          description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vestibulum metus a lorem convallis eleifend. Vivamus tempus in metus et rutrum.',
          tools: [],
        }
      ],
    };
  },
});
</script>

<template>
  <div class="tile-container">
    <projects-tile v-for="project in projects" :key="project.title" :project="project" />
  </div>
</template>

<style scoped lang="scss">
.tile-container {
  display: flex;
  flex-wrap: wrap;
  width: fit-content;
  height: fit-content;
  margin: auto;
}
</style>