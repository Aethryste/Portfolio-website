<template>
  <div class="component-wrapper">
    <div class="content-wrapper">
      <div class="left">
        <div class="content">
          <h3 class="G_unselectable G_sectionHeader">CONTACT</h3>
          <h1 class="G_unselectable G_sectionSlogan">Got a problem to solve?</h1>
          <p class="G_unselectable G_paragraph">Feel free to share your ideas or requests with me!</p>
          <div class="email">
            <div class="icon" ref="email" :style="getIconStyle('email')"></div>
            <p id="emailAddress" @click="copyToClipboard('emailAddress')">contact@richardalgra.com</p>
            <transition name="fade" appear>
              <p v-if="copied" class="copied-text"> - Copied!</p>
            </transition>
          </div>
          <div class="icons-group">
            <div class="icon" ref="linkedin" :style="getIconStyle('linkedin')"
                 @click="redirect('https://www.linkedin.com/in/richard-algra/', true)"></div>
            <div class="icon" ref="github" :style="getIconStyle('github')"
                 @click="redirect('https://github.com/Aethryste?tab=repositories', true)"></div>
            <div class="icon" ref="codepen" :style="getIconStyle('codepen')"
                 @click="redirect('https://codepen.io/Aethryste', true)"></div>
          </div>
        </div>
      </div>
      <div class="right">
        <form class="form-box">
          <label class="G_unselectable">Name</label>
          <input :class="{ 'error-border': !nameFilled }" @blur="checkInput('name')" v-model="nameValue">
          <p class="G_unselectable error-text" v-if="nameErrorMsg">{{ nameErrorMsg }}</p>

          <label class="G_unselectable">Email</label>
          <input :class="{ 'error-border': !emailFilled }" @blur="checkInput('email')" v-model="emailValue">
          <p class="G_unselectable error-text" v-if="emailErrorMsg">{{ emailErrorMsg }}</p>

          <label class="G_unselectable">Message</label>
          <textarea
            :class="{ 'error-border': !messageFilled }"
            @blur="checkInput('message')"
            v-model="messageValue"></textarea>
          <p class="G_unselectable error-text" v-if="messageErrorMsg">{{ messageErrorMsg }}</p>

<!--          <button @click.prevent="sendMessage"><span class="icon"></span>Reach out!</button>-->
          <button @click.prevent="sendMessage" @mouseover="changeIcon('sendIcon', 'sendWhite')" @mouseout="changeIcon('sendIcon', 'sendBlack')">
            <span class="icon" ref="sendIcon"></span>Reach out!
          </button>


          <Transition name="slide-fade">
            <div v-if="messageSent" class="message-sent-notification font_inter">Message Sent!</div>
          </transition>
          <Transition name="slide-fade">
            <div v-if="messageFailed" class="message-failed-notification font_inter">
              Something went wrong, please try again later..
            </div>
          </Transition>
          <span v-if="loading" class="loader"></span>
        </form>
      </div>
    </div>
    <footer>
      <div class="footer-content">
        <p class="G_unselectable">Handcrafted with VueJS © 2024 Richard Algra</p>
        <p>contact@richardalgra.com</p>
      </div>
    </footer>
  </div>
</template>

<script>
import Form from '../classes/form.js';
import { backendFetch } from "../globals.ts";

export default {
  name: 'section_contactPage',
  data() {
    return {
      nameValue: '',
      emailValue: '',
      messageValue: '',
      nameFilled: true,
      emailFilled: true,
      messageFilled: true,
      nameErrorMsg: '',
      emailErrorMsg: '',
      messageErrorMsg: '',
      loading: false,
      messageSent: false,
      messageFailed: false,
      copied: false,
      form: null,
      submissionResponse: null,
      lastSubmissionTime: 0,
      throttleDuration: 60000,
      icons: [
        { id: 'email', url: 'icon-email', data: '' },
        { id: 'linkedin', url: 'icon-linkedin', data: '' },
        { id: 'github', url: 'icon-github', data: '' },
        { id: 'codepen', url: 'icon-codepen', data: '' },
        { id: 'sendBlack', url: 'icon-send-black', data: '' },
        { id: 'sendWhite', url: 'icon-send-white', data: '' }
      ]
    };
  },
  watch: {
    icons: {
      handler() {
        this.updateIconStyles();
      },
      deep: true
    }
  },
  methods: {
    getIconStyle(iconId) {
      const icon = this.icons.find(icon => icon.id === iconId);
      return icon ? {
        width: '24px',
        height: '24px',
        background: `url(data:image/svg+xml,${encodeURIComponent(icon.data)}) center no-repeat`,
        backgroundSize: 'cover',
        imageRendering: 'crisp-edges',
        msInterpolationMode: 'nearest-neighbor'
      } : {};
    },
    changeIcon(iconRef, iconName) {
      const element = this.$refs[iconRef];
      const icon = this.icons.find(icon => icon.id === iconName);
      if (element && icon) {
        element.style.background = `url(data:image/svg+xml,${encodeURIComponent(icon.data)}) center no-repeat`;
        element.style.backgroundSize = 'contain';
      }
    },
    updateIconStyles() {
      for (const icon in this.icons) {
        const element = this.$refs[icon];
        if (element && this.icons[icon]) {
          element.style.background = `url(data:image/svg+xml,${encodeURIComponent(this.icons[icon])}) center no-repeat`;
        }
      }
    },
    redirect(path, newTab) {
      if (newTab) {
        window.open(
          path, '_blank'
        );
      }
    },
    copyToClipboard(id) {
      let copyText = document.getElementById(id);
      navigator.clipboard.writeText(copyText.textContent);
      this.copied = true;
      setTimeout(() => {
        this.copied = false;
      }, 1000);
    },
    checkInput(field) {
      const trimValue = (value) => value && value.trim();
      switch (field) {
        case 'name':
          this.nameFilled = !!trimValue(this.nameValue);
          this.nameErrorMsg = this.nameFilled ? '' : 'Name is required';
          break;
        case 'email':
          this.emailFilled = trimValue(this.emailValue) && this.emailValue.includes('@');
          this.emailErrorMsg = this.emailFilled ? '' : 'Please enter a valid email address';
          break;
        case 'message':
          this.messageFilled = !!trimValue(this.messageValue);
          this.messageErrorMsg = this.messageFilled ? '' : 'Message cannot be empty';
          break;
      }
    },
    sendMessage() {
      const currentTime = Date.now();
      this.checkInput('name');
      this.checkInput('email');
      this.checkInput('message');
      if (!this.nameErrorMsg && !this.emailErrorMsg && !this.messageErrorMsg) {
        this.loading = true;
        let obj = JSON.stringify(new Form(this.emailValue, this.nameValue, this.messageValue));
        let ajax = new XMLHttpRequest()
        ajax.open('POST', '/sendEmail')
        ajax.setRequestHeader('Content-Type', 'application/json')

        ajax.onreadystatechange = () => {
          if (ajax.readyState === XMLHttpRequest.DONE && ajax.status === 200) {
            if (ajax.responseText === 'success') {
              this.loading = false;
              this.messageSent = true;
              setTimeout(() => {
                this.messageSent = false;
              }, 5000);
            }
            if (ajax.responseText === 'failed') {
              this.loading = false;
              this.messageFailed = true;
              setTimeout(() => {
                this.messageFailed = false;
              }, 5000);
            }
            this.lastSubmissionTime = currentTime;
          }
        };
        ajax.send(obj);
      }
    }
  },
  async mounted() {
    for (const icon of this.icons) {
      icon.data = await backendFetch(`/res/icon/${icon.url}.svg`);
    }
    this.changeIcon('sendIcon', 'sendBlack');
  },

}
</script>

<style scoped lang="scss">
@import "src/styles/theme";
.component-wrapper {
  display: flex;
  flex-wrap: wrap;
  width: 100vw;
  height: 100vh;
  color: white;
  align-items: center;
  justify-content: center;
  .content-wrapper {
    display: flex;
    flex-direction: row;
    height: 70%;
    width: 90%;
    margin: auto!important;
    .left {
      display: flex;
      flex-direction: column;
      width: 50%;
      height: 100%;
      .content {
        width: fit-content;
        height: fit-content;
        margin: auto!important;
        font-size: 1.1em;
        .email {
          display: flex;
          flex-direction: row;
          height: fit-content;
          width: fit-content;
          margin: 1em 1em 1em 0!important;
          text-align: center;
          p {
            height: 100%;
            margin: auto auto auto 5px!important;
            color: white;
            cursor: pointer;
          }
          .icon {
            width: 24px;
            height: 24px;
            //background: url("../assets/icons-svg/icon-email.svg") center;
            background-size: contain;
            image-rendering: crisp-edges;
            image-rendering: -moz-crisp-edges;
            image-rendering: -o-crisp-edges;
            -ms-interpolation-mode: nearest-neighbor;
          }
        }
        .icons-group {
          display: flex;
          flex-direction: row;
          width: 50%;
          height: 60px;
          margin: 30px 0 0 0!important;
          .icon {
            width: 30px;
            height: 30px;
            margin: auto 8px auto 0!important;
            transition: 100ms;
            opacity: 0.3;
            &:hover {
              opacity: 0.8;
            }
          }
          //#icon-linkedin {
          //  background: url("../assets/icons-svg/icon-linkedin.svg") center no-repeat;
          //  background-size: cover;
          //}
          //#icon-github {
          //  background: url("../assets/icons-svg/icon-github.svg") center no-repeat;
          //  background-size: cover;
          //}
          //#icon-gitlab {
          //  background: url("../assets/icons-svg/icon-gitlab.svg") center no-repeat;
          //  background-size: cover;
          //}
          //#icon-codepen {
          //  background: url("../assets/icons-svg/icon-codepen.svg") center no-repeat;
          //  background-size: cover;
          //}
        }
      }
    }
    .right {
      display: flex;
      width: 50%;
      height: 100%;
      .form-box {
        display: flex;
        flex-direction: column;
        width: 80%;
        height: 70%;
        margin: auto auto 3em 0!important;
        label {
          margin: 1em auto 5px 0!important;
        }
        input, textarea {
          @extend .font_inter;
          padding: 0.8em;
          background-color: #161A2C;
          border: 1px solid darkslateblue;
          border-radius: 0.2em;
          color: white;
          &:focus {
            outline: none;
            border-color: #00AEFF;
            box-shadow: 0 0 3px #9ecaed;
          }
        }
        textarea {
          resize:vertical;
          min-height: 8em;
        }
        button {
          @extend .font_inter;
          padding: 0.8em;
          font-size: 1em;
          font-weight: 500;
          line-height: 130%;
          transition: 100ms;
          border-radius: 0.2em;
          border: 2px solid white;
          margin-top: 1.5em!important;
          &:hover {
            cursor: pointer;
          }
          span.icon {
            background: url("../assets/icons-svg/icon-send-black.svg") center no-repeat;
            background-size: contain;
            padding: 2px 30px 2px 0;
            width: 30px;
            height: 30px;
          }
          &:hover {
            background-color: rgba(0,0,0,0);
            border: 2px solid white;
            color: white;
            span.icon {
              background: url("../assets/icons-svg/icon-send-white.svg") center no-repeat;
              background-size: contain;
            }
          }
        }
        #message {
          resize: vertical;
        }
      }
    }
  }
  footer {
    display: flex;
    position: relative;
    margin: auto auto 0 auto!important;
    background-color: black;
    min-width: 100%;
    height: 90px;
    .footer-content {
      display: flex;
      align-items: center;
      justify-content: center;
      flex-direction: column;
      width: fit-content;
      height: 50%;
      padding: 10px;
      margin: auto auto auto auto!important;
      p {
        margin: 4px!important;
        font-size: 0.8em;
        line-height: 1em;
        color: $theme-primary-color;
        &:nth-child(1) {
          color: white;
          border-bottom: 1px solid white;
          padding-bottom: 10px;
        }
      }
    }
  }
}
.message-sent-notification {
  position: relative;
  margin: 0.5em auto auto auto!important;
  padding: 0.4em;
  text-align: center;
  border-radius: 0.2em;
  color: green;
  font-weight: bold;
  width: fit-content;
  transition: 300ms;
}
.message-failed-notification {
  position: relative;
  margin: 0.5em auto auto auto!important;
  padding: 0.3em;
  text-align: center;
  border-radius: 0.2em;
  color: orange;
  font-weight: bold;
  width: fit-content;
  transition: 300ms;
}
.error-border {
  border-color: red!important;
  box-shadow: 0 0 3px red;
}
.error-text {
  color: red;
  font-size: 0.8em;
  margin-top: 0.2em;
}
.slide-fade-enter-active, .slide-fade-leave-active {
  transition: opacity 1s;
}
.slide-fade-enter, .slide-fade-leave-to {
  opacity: 0;
}
.fade-enter-active, .fade-leave-active {
  transition: opacity 300ms;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}
p.copied-text {
  @extend .font_inter;
  color: limegreen!important;
  font-size: 0.9em!important;
  font-weight: 300!important;
  margin-left: 5px!important;
}
@media screen and (max-width: 1000px) {
  .component-wrapper .content-wrapper .left .content {
    h1 {
      font-size: 1.5em;
    }
  }
}
@media screen and (max-width: 800px) {
  .component-wrapper .content-wrapper {
    flex-direction: column;
    width: 80%;
    height: calc(100% - 90px);
    .left, .right {
      margin: auto!important;
      width: 100%;
      height: fit-content;
      text-align: center;
    }
    .left {
      margin: auto auto 0 auto!important;
      .content {
        .email {
          display: none;
        }
        .icons-group {
          margin: auto!important;
          justify-content: center;
          height: fit-content;
          .icon {
            margin: 0 auto 0 auto!important;
          }
        }
      }
    }
    .right {
      margin: 0 auto auto auto!important;
      .form-box {
        margin: 0 auto auto auto!important;
        label {
          font-size: 0.9em;
        }
      }
    }
  }
}
@media screen and (max-width: 550px) {
  .component-wrapper .content-wrapper .left .content {
    h1 {
      font-size: 1.2em;
    }
    p {
      font-size: 0.7em;
    }
  }
  .component-wrapper .content-wrapper .right .form-box {
    label {
      font-size: 0.8em;
    }
    button {
      font-size: 0.8em;
    }
  }
}
.loader {
  width: 10px;
  min-height: 10px;
  height: 10px;
  border-radius: 50%;
  display: block;
  margin: 15px auto!important;
  position: relative;
  color: #FFF;
  left: -100px;
  box-sizing: border-box;
  animation: shadowRolling 2s linear infinite;
}

@keyframes shadowRolling {
  0% {
    box-shadow: 0 0 rgba(255, 255, 255, 0), 0 0 rgba(255, 255, 255, 0), 0 0 rgba(255, 255, 255, 0), 0 0 rgba(255, 255, 255, 0);
  }
  12% {
    box-shadow: 100px 0 white, 0 0 rgba(255, 255, 255, 0), 0 0 rgba(255, 255, 255, 0), 0 0 rgba(255, 255, 255, 0);
  }
  25% {
    box-shadow: 110px 0 white, 100px 0 white, 0 0 rgba(255, 255, 255, 0), 0 0 rgba(255, 255, 255, 0);
  }
  36% {
    box-shadow: 120px 0 white, 110px 0 white, 100px 0 white, 0 0 rgba(255, 255, 255, 0);
  }
  50% {
    box-shadow: 130px 0 white, 120px 0 white, 110px 0 white, 100px 0 white;
  }
  62% {
    box-shadow: 200px 0 rgba(255, 255, 255, 0), 130px 0 white, 120px 0 white, 110px 0 white;
  }
  75% {
    box-shadow: 200px 0 rgba(255, 255, 255, 0), 200px 0 rgba(255, 255, 255, 0), 130px 0 white, 120px 0 white;
  }
  87% {
    box-shadow: 200px 0 rgba(255, 255, 255, 0), 200px 0 rgba(255, 255, 255, 0), 200px 0 rgba(255, 255, 255, 0), 130px 0 white;
  }
  100% {
    box-shadow: 200px 0 rgba(255, 255, 255, 0), 200px 0 rgba(255, 255, 255, 0), 200px 0 rgba(255, 255, 255, 0), 200px 0 rgba(255, 255, 255, 0);
  }
}
</style>
