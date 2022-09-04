import Vue from 'vue'
import axios from 'axios'
//import VueAxios from 'vue-axios'
//import AxiosPlugin from 'vue-axios-cors'
import App from './App.vue'
import router from './router'
import {store}  from  './store/store.js'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

// Import Bootstrap an BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)

// Vue.use(AxiosPlugin);
// axios.defaults.headers.common['Content-Type'] = 'application/x-www-form-urlencoded' 
// axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';

//Vue.use(VueAxios,axios)
Vue.prototype.$axios = axios

Vue.config.productionTip = false

new Vue({
  router,
  store:store,
  render: h => h(App)
}).$mount('#app')
