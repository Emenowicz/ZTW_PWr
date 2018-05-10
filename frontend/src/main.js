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

Vue.use(GoogleAuth, {client_id: Config.GOOGLE_CLIENT_ID});
Vue.googleAuth().load();

Vue.use(Vuetify);
Vue.config.productionTip = false;

const token = store.token
const token_type = store.token_type
if (token) {
  API.defaults.headers.common['Authorization'] = token_type + '' + token
}

Vue.prototype.$http = API;

String.prototype.format = function () {
    var a = this;
    for (var k in arguments) {
      a = a.replace(new RegExp("\\{" + k + "\\}", 'g'), arguments[k]);
    }
  return a
}

new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
});
