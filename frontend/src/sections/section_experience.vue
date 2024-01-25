<template>
  <slideshowTimeline id="slideshowTimeline"/>
</template>

<script>
import slideshowTimeline from '@/components/SlideshowTimeline'

export default {
  name: 'section_experience',
  components: {
    slideshowTimeline
  },
  data() {
    return {
      timelineEntries: [
        {
          date: "2022-06-01",
          title: "Danone - IT Service Desk Agent (L1)",
          subtitle: "May 2022 - Jan 2022",
          description: "First point-of-contact for end-users regarding IT incidents, requests and questions."
        },
        {
          date: "2022-06-01",
          title: "Total Energies - IT Service Desk Agent (L1)",
          subtitle: "Feb 2022 - May 2022",
          description: "First point-of-contact for end-users regarding IT incidents, requests and questions."
        },
        {
          date: "2022-03-01",
          title: "Mondial Movers - Mover/IT-Mover",
          subtitle: "Aug 2018 - Feb 2022",
          description: "My position here provided me with close to four consecutive years of experience in customer contact, care and satisfaction."
        },
        {
          date: "2018-09-01",
          title: "Grafisch Lyceum Utrecht - MBO4",
          subtitle: "2014 - 2018",
          description: "Studied GameArt & Development, graduated. (Comparable to associate's degree)"
        },
        {
          date: "2017-06-01",
          title: "Novility - 3D Artist (Generalist & Technical Artist)",
          subtitle: "Jan 2017 - Dec 2017",
          description: "Here I have spend most of my time working on props along with some rigging & animation-fixing tasks."
        }
      ].reverse()
    };
  }
}
</script>

<style scoped lang="scss">
#slideshowTimeline {
  width: 100%;
  height: 100%;
}
</style>
