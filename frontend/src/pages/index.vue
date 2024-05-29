<template>
  <div class="container">
    <nav class="navigation" ref="navigation" :class="{ 'hidden': !isNavigationVisible }">
      <button
        class="scroll-nav-button" type="button"
        @click="navigateToSection(index - 1)"
        v-for="index in sectionsCount"
        :key="index"
        :style="setIcons(index - 1)">
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
import IndexSection1_landing from '../sections/section_landingPage.vue';
import indexSection2_now from '../sections/section_aboutMe.vue';
import IndexSection3_past from '../sections/section_experience.vue';
import IndexSection4_projects from '../sections/section_projects.vue';
import IndexSection5_contact from '../sections/section_contactPage.vue';
import { backendFetch } from "../utils/generalUtils.ts";

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
      isNavigationVisible: true,
      icons: {
        thick: null,
        thin: null,
      },
    };
  },
  watch: {
    S1active(newValue) {
      this.isNavigationVisible = !(newValue && this.currentSection === 0);
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
    updateSectionHeights() {
      const viewportHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);
      const sections = this.$refs.scrollContainer.querySelectorAll('.section');
      sections.forEach((section, index) => {
        section.style.height = `${viewportHeight}px`;
      });
      this.sectionHeights = Array.from(sections).map(section => section.clientHeight);
    },
    handleTouchStart(event) {
      this.touchStartY = event.touches[0].clientY;
    },
    handleTouchMove(event) {
      event.preventDefault();
      const touchEndY = event.touches[0].clientY;
      const touchDelta = this.touchStartY - touchEndY;
      const sensitivity = window.innerHeight / 4;
      if (touchDelta > sensitivity) {
        this.scrollDown();
        this.touchStartY = touchEndY;
      } else if (touchDelta < -sensitivity) {
        this.scrollUp();
        this.touchStartY = touchEndY;
      }
    },
    handleTouchEnd() {
      // Implement snapping behavior if needed
    },
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
      event.preventDefault();
      if (this.isScrolling) {
        return;
      }
      if (event.deltaY < 0) {
        this.scrollUp();
      } else {
        this.scrollDown();
      }
    },
    async fetchIcon(filename) {
      try {
        const base64SVG = btoa(await backendFetch(`/res/icon/${filename}`));
        return {
          background: `url('data:image/svg+xml;base64,${base64SVG}')`,
          backgroundSize: 'contain'
        };
      } catch (error) {
        console.error(`Error fetching icon: ${filename}`, error);
        return {};
      }
    },
    setIcons(sectionIndex) {
      const icon = sectionIndex === this.currentSection ? this.icons.thick : this.icons.thin;
      return icon;
    },
  },
  async created() {
    this.icons.thick = await this.fetchIcon('icon-hexagon-thick.svg');
    this.icons.thin = await this.fetchIcon('icon-hexagon-thin.svg');
  },
  mounted() {
    this.updateSectionHeights();
    window.addEventListener('resize', this.updateSectionHeights);
    this.$refs.scrollContainer.addEventListener('wheel', this.handleScroll, { passive: false });
    this.$refs.scrollContainer.addEventListener('touchstart', this.handleTouchStart, { passive: false });
    this.$refs.scrollContainer.addEventListener('touchmove', this.handleTouchMove, { passive: false });
    this.$refs.scrollContainer.addEventListener('touchend', this.handleTouchEnd, { passive: false });

    this.isNavigationVisible = false;
  },
  beforeUnmount() {
    window.removeEventListener('resize', this.updateSectionHeights);
    this.$refs.scrollContainer.removeEventListener('wheel', this.handleScroll);
    this.$refs.scrollContainer.removeEventListener('touchstart', this.handleTouchStart);
    this.$refs.scrollContainer.removeEventListener('touchmove', this.handleTouchMove);
    this.$refs.scrollContainer.removeEventListener('touchend', this.handleTouchEnd);
  }
};
</script>

<style scoped>
.container {
  position: relative;
  overflow: hidden!important;
  width: 100dvw;
  height: 100dvh;
}
.scroll-container {
  width: 100dvw;
  height: 100dvh;
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
   z-index: 500;
 }
.navigation.hidden {
  opacity: 0;
  visibility: hidden;
}
button {
  border: none;
  cursor: pointer;
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
@media screen and (max-width: 600px) {
  .navigation {
    right: 0;
  }
}
</style>
