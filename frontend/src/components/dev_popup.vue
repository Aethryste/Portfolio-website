<template>
  <div id="DevPopUp" class="G_unselectable" @click="disable">
    <h3>Oh, hey there!</h3>
    <div class="close-btn">x</div>
    <p>You've found this page somewhat early, it's still in active development.. 😳</p>
    <p>( Some features, or even sections, might not work as intended at this time. )</p>
  </div>
</template>

<script>
export default {
  name: 'dev_popup',
  methods: {
    disable: function () {
      let elem = document.querySelector('#DevPopUp');
      elem.classList.add('anim-pageTitle-fade-out');
      elem.addEventListener('animationend', function callback() {
        elem.style.display = 'none';
        elem.removeEventListener('animationend', callback);
      });
    }
  }
}
</script>
<style scoped lang="scss">
#DevPopUp {
  width: 35%;
  min-width: 330px;
  height: fit-content;
  position: absolute;
  border: 2px solid white;
  border-radius: 0.3em;
  padding: 1em;
  margin: 0.5em!important;
  font-family: "Roboto Light", sans-serif;
  backdrop-filter: blur(10px);
  box-shadow: 10px 10px 10px black;
  z-index: 1000;
  .close-btn {
    position: absolute;
    top: 7px;
    right: 7px;
    width: 20px;
    height: 20px;
    border: 2px solid white;
    border-radius: 0.2em;
    line-height: 0.9em;
    font-size: 1.2em;
    text-align: center;
    opacity: 0.7;
    transition: 300ms;
    &:hover {
      opacity: 1!important;
      cursor: pointer;
    }
  }
  h3 {
    margin: 0 0 0.8em 0!important;
  }
  p {
    margin: 0!important;
  }
  :last-child {
    font-size: 0.8em;
    color: darkorange;
  }
}

@media screen and (max-width: 675px) {
  #DevPopUp {
    position: relative;
    width: 50%;
    margin: 10% auto 90% auto!important;
  }
}
@media screen and (max-width: 470px) {
  #DevPopUp {
    min-width: 250px;
  }
}
</style>
