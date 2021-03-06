import Vue from 'vue'
import Auth from '@/api/modules/auth'
import {API} from '@/api/api_config'

const state = {
  token_type: '',
  token: ''
}

const getters = {
  isAuthenticated: state => !!state.token,
  getToken: state => state.token,
  getTokenType: state => state.token_type
}


const actions = {
  'LOG_IN_GOOGLE': ({commit, dispatch}) => {
    return new Promise((resolve, reject) => {
      Vue.googleAuth().directAccess()
      Vue.googleAuth().signIn((response) => {

        const token = response.Zi.access_token
        const token_type = response.Zi.token_type
        commit('SET_USER_INFO', response.w3)
        commit('SET_TOKEN', {token, token_type})
        API.defaults.headers.common['Authorization'] = token_type + ' ' + token
        resolve(response)
      }, (error) => {
        dispatch('LOG_OUT_GOOGLE')
        reject(error)
      })
    })
  },

  'LOG_OUT_GOOGLE': ({commit, dispatch}) => {
    return new Promise((resolve, reject) => {
      commit('CLEAR_TOKEN')
      commit('CLEAR_USER_INFO')
      delete API.defaults.headers.common['Authorization']
      resolve()
    })
  }

}

const mutations = {
  'SET_TOKEN': (state, {token, token_type}) => {
    state.token = token
    state.token_type = token_type
  },
  'CLEAR_TOKEN': (state) => {
    state.token = ''
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
