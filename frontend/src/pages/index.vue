<template>
  <div class="container">
    <nav class="navigation" ref="navigation" :style="{ opacity: isNavigationVisible ? 1 : 0 }">
      <button
        class="scroll-nav-button"
        @click="navigateToSection(index - 1)"
        v-for="index in sectionsCount"
        :key="index"
        :style="buttonBackground(index - 1)">
      </button>
    </nav>

    <div class="scroll-container G_HideScrollbars" ref="scrollContainer">
      <IndexSection1_landing class="section" :isActive="this.S1active"/>
      <indexSection2_now class="section" :isActive="this.S2active"/>
      <IndexSection3_past class="section" :isActive="this.S3active"/>
      <IndexSection4_projects class="section" :isActive="this.S4active"/>
      <IndexSection5_contact class="section" :isActive="this.S5active"/>
    </div>
  </div>
</template>

<script>
import IndexSection1_landing from '@/sections/section_landingPage';
import indexSection2_now from '@/sections/section_aboutMe';
import IndexSection3_past from '@/sections/section_experience';
import IndexSection4_projects from '@/sections/section_projects';
import IndexSection5_contact from '@/sections/section_contactPage'

export default {
  components: {
    IndexSection1_landing,
    indexSection2_now,
    IndexSection3_past,
    IndexSection4_projects,
    IndexSection5_contact
  },
  data() {
    return {
      isScrolling: false,
      currentSection: 0,
      sectionsCount: 5,
      S1active: true,
      S2active: false,
      S3active: false,
      S4active: false,
      S5active: false,
      isNavigationVisible: true
    };
  },
  watch: {
    S1active(newValue) {
      if (newValue && this.currentSection === 0) {
        this.isNavigationVisible = false;
      } else {
        this.isNavigationVisible = true;
      }
    },
    currentSection() {
      if (this.currentSection === 0) {
        this.S1active = true
        this.S2active = false
        this.S3active = false
        this.S4active = false
        this.S5active = false
      }
      else if (this.currentSection === 1) {
        this.S1active = false
        this.S2active = true
        this.S3active = false
        this.S4active = false
        this.S5active = false
      }
      else if (this.currentSection === 2) {
        this.S1active = false
        this.S2active = false
        this.S3active = true
        this.S4active = false
        this.S5active = false
      }
      else if (this.currentSection === 3) {
        this.S1active = false
        this.S2active = false
        this.S3active = false
        this.S4active = true
        this.S5active = false
      }
      else if (this.currentSection === 4) {
        this.S1active = false
        this.S2active = false
        this.S3active = false
        this.S4active = false
        this.S5active = true
      }
    }
  },
  methods: {
    navigateToSection(sectionIndex) {
      this.currentSection = sectionIndex;
      this.scrollToSection();
    },
    scrollUp() {
      if (this.currentSection > 0) {
        this.currentSection--;
        this.scrollToSection();
      }
    },
    scrollDown() {
      if (this.currentSection < this.sectionsCount - 1) {
        this.currentSection++;
        this.scrollToSection();
      }
    },
    scrollToSection() {
      const targetY = this.currentSection * window.innerHeight;
      this.isScrolling = true;
      this.$refs.scrollContainer.scrollTo({
        top: targetY,
        behavior: "smooth",
      });
      setTimeout(() => {
        this.isScrolling = false;
      }, 1000);
    },
    handleScroll(event) {
      if (this.isScrolling) {
        event.preventDefault();
        return;
      }
      if (event.deltaY < 0) {
        this.scrollUp();
      } else {
        this.scrollDown();
      }
    },
    buttonBackground(sectionIndex) {
      const baseURI = sectionIndex === this.currentSection
        ? require('../assets/icons-svg/icon-hexagon-thick.svg')
        : require('../assets/icons-svg/icon-hexagon-thin.svg');
      return {
        background: `url(${baseURI}) center no-repeat`,
        backgroundSize: 'contain'
      };
    }
  },
  mounted() {
    this.$refs.scrollContainer.addEventListener('wheel', this.handleScroll, { passive: false });
    this.isNavigationVisible = false;
  },
  beforeUnmount() {
    this.$refs.scrollContainer.removeEventListener('wheel', this.handleScroll);
  }
};
</script>

<style scoped>
.container {
  position: relative;
  overflow: hidden!important;
}
.scroll-container {
  width: 100vw;
  height: 100vh;
  overflow-y: scroll;
  overflow-x: hidden;
}
.navigation {
  position: fixed;
  top: 50%;
  right: 20px;
  transform: translateY(-50%);
  display: flex;
  flex-direction: column;
  gap: 10px;
  transition: opacity 300ms ease-in-out;
}
button {
  background: none;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
  transition: background-color 0.3s;
}
button:hover {
  background-color: rgba(0, 0, 0, 0.1);
}
.scroll-nav-button {
  opacity: 0.5;
  background-size: contain;
  min-width: 15px;
  min-height: 15px;
  margin: 0 1em 0 0 !important;
  transition: 300ms;
}
</style>
