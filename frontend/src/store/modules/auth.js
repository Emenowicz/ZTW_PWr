import Vue from 'vue'
import Auth from '@/api/auth'
import { API } from '@/api/api_config'

const state = {
  token: localStorage.getItem('user-token') || '',
  status: '',
  hasLoadedOnce: false
};

const getters = {
  isAuthenticated: state => !!state.token,
  authStatus: state => state.status,
};

const actions = {
  'GOOGLE_AUTH_REQUEST': ({commit, dispatch}) => {
    return new Promise((resolve, reject) => {
      commit('AUTH_REQUEST');
      Vue.googleAuth().directAccess();
      Vue.googleAuth().signIn((response) => {
        const token = response.Zi.access_token;
        const token_type = response.Zi.token_type;
        console.log(response);
        commit('SET_USER_INFO', response.w3);
        localStorage.setItem('user-token', token);
        API.defaults.headers.common['Authorization'] = token_type + " " + token;
        commit('AUTH_SUCCESS', token);
        Auth.registerInBackend(()=>{},()=>{});
        resolve(response);
      }, (error) => {
        commit('AUTH_ERROR', error);
        localStorage.removeItem('user-token');
        reject(error)
      })
    })
  },

  'AUTH_LOGOUT': ({commit, dispatch}) => {
    return new Promise((resolve, reject) => {
      commit('AUTH_LOGOUT');
      commit('CLEAR_USER_INFO');
      commit('CLEAR_USERS_TOURNAMENTS');
      localStorage.removeItem('user-token');
      delete API.defaults.headers.common['Authorization']
      resolve()
    })
  }
};

const mutations = {
  'AUTH_REQUEST': (state) => {
    state.status = 'loading'
  },
  'AUTH_SUCCESS': (state, token) => {
    state.status = 'logged';
    state.token = token;
    state.hasLoadedOnce = true
  },
  'AUTH_ERROR': (state) => {
    state.status = 'error';
    state.hasLoadedOnce = true
  },
  'AUTH_LOGOUT': (state) => {
    state.token = ''
    state.status = ''
  }
};

export default {
  state,
  getters,
  actions,
  mutations,
}
