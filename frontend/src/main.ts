import { createApp } from 'vue'
import App from './App.vue'
import viewportMixin from "./utils/viewportMixin";
import '/src/styles/theme.scss'

const app = createApp(App);
app.mixin(viewportMixin);
app.mount('#app');
