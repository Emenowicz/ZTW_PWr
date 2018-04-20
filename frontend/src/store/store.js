import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'
import state from './state'

import tournament from './modules/tournament'
import auth from './modules/auth'
import user from './modules/user'

import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions,
  modules: {
    tournament,
    auth,
    user
  },
  plugins:[createPersistedState()]
})
