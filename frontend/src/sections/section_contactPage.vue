<template>
  <div class="component-wrapper">
    <div class="content-wrapper">
      <div class="left">
        <div class="content">
          <h3 class="G_unselectable G_sectionHeader">CONTACT</h3>
          <h1 class="G_unselectable G_sectionSlogan">Got a problem to solve?</h1>
          <p class="G_unselectable G_paragraph">Feel free to share your ideas or requests with me!</p>
          <div class="email">
            <div class="icon"></div>
            <p id="emailAddress" @click="copyToClipboard('emailAddress')">placeholder@address.com</p>
            <transition name="fade" appear>
              <p v-if="copied" class="copied-text"> - Copied!</p>
            </transition>
          </div>
          <div class="icons-group">
            <div class="icon" id="icon-linkedin"
                 @click="redirect('https://www.linkedin.com/in/richard-algra/', true)"></div>
            <div class="icon" id="icon-github"
                 @click="redirect('https://github.com/Aethryste?tab=repositories', true)"></div>
<!--            <div class="icon" id="icon-gitlab"-->
<!--                 @click="redirect('locked by university..', true)"></div>-->
            <div class="icon" id="icon-codepen"
                 @click="redirect('https://codepen.io/Aethryste', true)"></div>
          </div>
        </div>
      </div>
      <div class="right">
        <form class="form-box">
          <label class="unselectable">Name</label>
          <input :class="{ 'error-border': !nameFilled }" @blur="checkInput('name')" v-model="nameValue">
          <p class="error-text" v-if="nameErrorMsg">{{ nameErrorMsg }}</p>

          <label class="unselectable">Email</label>
          <input :class="{ 'error-border': !emailFilled }" @blur="checkInput('email')" v-model="emailValue">
          <p class="error-text" v-if="emailErrorMsg">{{ emailErrorMsg }}</p>

          <label class="unselectable">Message</label>
          <textarea :class="{ 'error-border': !messageFilled }" @blur="checkInput('message')" v-model="messageValue"></textarea>
          <p class="error-text" v-if="messageErrorMsg">{{ messageErrorMsg }}</p>

          <button @click.prevent="sendMessage"><span class="icon"></span>Reach out!</button>

          <Transition name="slide-fade">
            <div v-if="messageSent" class="message-sent-notification">Message Sent!</div>
          </transition>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
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
      messageSent: false,
      copied: false
    };
  },
  methods: {
    redirect(path, newTab) { // TODO: Move function to global script.
      if (newTab) {
        window.open(
          path, '_blank'
        );
      }
    },
    copyToClipboard(id) { // TODO: Move function to global script.
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
          this.emailErrorMsg = this.emailFilled ? '' : 'Please enter a valid email';
          break;
        case 'message':
          this.messageFilled = !!trimValue(this.messageValue);
          this.messageErrorMsg = this.messageFilled ? '' : 'Message cannot be empty';
          break;
      }
    },
    sendMessage() {
      this.checkInput('name');
      this.checkInput('email');
      this.checkInput('message');
      console.log("send message!")
      if (!this.nameErrorMsg && !this.emailErrorMsg && !this.messageErrorMsg) {
        this.messageSent = true;
        setTimeout(() => {
          this.messageSent = false;
        }, 50000);
      }
    }
  }
}
</script>

<style scoped lang="scss">
@import "src/styles/theme";
.component-wrapper {
  display: flex;
  width: 100vw;
  height: 100vh;
  color: white;
  font-family:
    Inter,
    ui-sans-serif,
    system-ui,
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
          margin: 1em 1em 1em 0 !important;
          text-align: center;
          p {
            height: 100%;
            margin: auto auto auto 5px !important;
            color: white;
            cursor: pointer;
          }
          .icon {
            width: 24px;
            height: 24px;
            background: url("src/assets/icons-svg/icon-email.svg") center;
            background-size: contain;
            image-rendering: crisp-edges;
            image-rendering: -moz-crisp-edges;          /* Firefox */
            image-rendering: -o-crisp-edges;            /* Opera */
            -ms-interpolation-mode: nearest-neighbor;   /* IE (non-standard property) */
          }
        }
        .icons-group {
          display: flex;
          flex-direction: row;
          width: 50%;
          height: 60px;
          margin: 30px 0 0 0 !important;
          .icon {
            width: 30px;
            height: 30px;
            margin: auto 8px auto 0 !important;
            transition: 100ms;
            opacity: 0.3;
            &:hover {
              opacity: 0.8;
            }
          }
          #icon-linkedin {
            background: url("src/assets/icons-svg/icon-linkedin.svg") center no-repeat;
            background-size: cover;
          }
          #icon-github {
            background: url("src/assets/icons-svg/icon-github.svg") center no-repeat;
            background-size: cover;
          }
          #icon-gitlab {
            background: url("src/assets/icons-svg/icon-gitlab.svg") center no-repeat;
            background-size: cover;
          }
          #icon-codepen {
            background: url("src/assets/icons-svg/icon-codepen.svg") center no-repeat;
            background-size: cover;
          }
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
        margin: auto auto 3em 0 !important;
        label {
          margin: 1em auto 5px 0 !important;
        }
        input, textarea {
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
          padding: 0.8em;
          font-family:
            Inter,
            ui-sans-serif,
            system-ui,
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
            background: url("src/assets/icons-svg/icon-send-black.svg") center no-repeat;
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
              background: url("src/assets/icons-svg/icon-send-white.svg") center no-repeat;
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
}
.message-sent-notification {
  position: relative;
  margin: 0.5em auto auto auto !important;
  padding: 0.3em;
  border: 2px solid green;
  text-align: center;
  border-radius: 0.2em;
  color: green;
  font-weight: bold;
  width: 25%;
  transition: 300ms;
}
.error-border {
  border-color: red !important;
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
  color: limegreen!important;
  font-size: 0.9em!important;
  font-weight: 300!important;
  margin-left: 5px!important;
  font-family:
    Inter,
    ui-sans-serif,
    system-ui,
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
    "Noto Color Emoji"!important;
}
</style>