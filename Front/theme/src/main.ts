import { createApp } from 'vue'
import App from './App.vue'
import _ from "lodash"
import router from '@/router/index'
import store from "@/store/index"
// createApp(App).mount('#app')
const app = createApp(App);
app.use(router);
app.use(store);
app.mount('#app');