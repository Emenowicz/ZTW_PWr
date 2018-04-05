// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import store from './store/store'
import GoogleAuth from 'vue-google-oauth'
import {API} from './api/api_config'
import Config from './config'

Vue.use(GoogleAuth, { client_id: Config.GOOGLE_CLIENT_ID });
Vue.googleAuth().load();

Vue.use(Vuetify);
Vue.config.productionTip = false;

Vue.prototype.$http = API;

new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
});
