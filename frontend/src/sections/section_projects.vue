<template>
  <div class="component-wrapper">
    <div class="content-wrapper">
      <div class="left">
        <div class="content-box">
          <h1 class="G_sectionSlogan">portfolio.</h1>
          <p class="G_paragraph">Projects and contributions.</p>
          <button @click="redirect('https://github.com/Aethryste?tab=repositories', true)">SEE MY WORK</button>
        </div>
      </div>
      <div class="right">
        <div class="content-box">
          <h1 class="G_sectionSlogan">sketchbook.</h1>
          <p class="G_paragraph">Sketches and code snippets.</p>
          <button @click="redirect('https://codepen.io/Aethryste', true)">CHECK OUT MY SKETCHES</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'section_projects',
  methods: {
    redirect(path, newTab) { // TODO: Move function to global script.
      if (newTab) {
        window.open(
          path, '_blank'
        );
      }
    }
  }
}
</script>

<style scoped lang="scss">
@import "src/styles/theme";
.component-wrapper {
  position: relative;
  width: 100vw;
  height: 100vh;
  display: flex;
  .content-wrapper {
    background: linear-gradient(90deg, #8711C1, #2472FC);
    width: 75%;
    height: 60%;
    margin: auto!important;
    display: flex;
    flex-direction: row;
    .left, .right {
      width: 50%;
      height: 100%;
      border-right: 3px solid rgb(30,30,35);
      display: flex;
      align-items: center;
      justify-content: center;
      h1 {
        font-size: 2.8em;
        color: white;
      }
      p {
        font-size: 1.5em;
        color: darkgrey;
      }
      .content-box {
        height: 70%;
        width: 80%;
        margin: auto !important;
        button {
          width: 300px;
          height: 4em;
          padding: 1em;
          text-align: center;
          margin: 5em 2em 2em 0 !important;
          background-color: rgba(0,0,0,0);
          border: 2px solid white;
          color: whitesmoke;
          cursor: pointer;
          letter-spacing: 2px;
          font-weight: bold;
          font-family:
            Inter,
            ui-sans-serif,system-ui,
            -apple-system,
            BlinkMacSystemFont,
            Segoe UI,
            Roboto,
            Helvetica Neue,
            Arial,
            Noto Sans,
            sans-serif,
            "Apple Color Emoji",
            "Segoe UI Emoji",
            Segoe UI Symbol,
            "Noto Color Emoji";
          transition: 300ms;
          &:hover {
            border: 2px solid black;
            color: black;
          }
        }
      }
    }
  }
}
</style>
