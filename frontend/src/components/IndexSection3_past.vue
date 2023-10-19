<template>
  <slideshowTimeline id="slideshowTimeline"/>
<!--  <Timeline>-->
<!--    <template #default="slotProps">-->
<!--      <TimelineEntry-->
<!--        v-for="entry in timelineEntries"-->
<!--        :key="entry.date"-->
<!--        :date="entry.date"-->
<!--        :title="entry.title"-->
<!--        :subtitle="entry.subtitle"-->
<!--        :description="entry.description"-->
<!--        :years="slotProps.years"-->
<!--      />-->
<!--    </template>-->
<!--  </Timeline>-->
</template>

<script>
// import Timeline from './Timeline.vue'; // Replace with the actual path
// import TimelineEntry from './TimelineEntry.vue'; // Replace with the actual path
import slideshowTimeline from '@/components/SlideshowTimeline'

export default {
  name: 'IndexSection3_past',
  components: {
    // Timeline,
    // TimelineEntry
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
  width: calc(100% - 2px);
  height: calc(100% - 2px);
  border:1px solid red;
}

@import "./src/styles/theme.scss";
.component-wrapper {
  color: white;
  width: 100vw;
  height: 100vh;
  opacity: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  Timeline {
    position: absolute;
    top:0;
    left: 0;
  }
}
</style>
