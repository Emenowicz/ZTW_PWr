import Vue from 'vue'
import Vuex from 'vuex'

import user from './modules/user'
import tournament from './modules/tournament'
import auth from './modules/auth'

import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    user,
    tournament,
    auth
  },
  plugins: [createPersistedState()]
})
