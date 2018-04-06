import {
  GOOGLE_AUTH_REQUEST,
  AUTH_REQUEST,
  AUTH_ERROR,
  AUTH_SUCCESS,
  AUTH_LOGOUT,
} from '../actions/auth'

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
  [GOOGLE_AUTH_REQUEST]: ({commit, dispatch}) => {
    return new Promise((resolve, reject) => {
      commit(AUTH_REQUEST);
      Vue.googleAuth().signIn((authorizationCode) => {
        Auth.getGoogleAccessToken(authorizationCode,
          (response) => {
            const token = response.data;
            localStorage.setItem('user-token', token);
            API.defaults.headers.common['Authorization'] = token;
            commit(AUTH_SUCCESS, token);
            resolve(response)
          },
          (error) => {
            commit(AUTH_ERROR, err);
            localStorage.removeItem('user-token');
            reject(error)
          });
      }, (error) => {
        commit(AUTH_ERROR, error);
        localStorage.removeItem('user-token');
        reject(error)
      })
    })
  },

  [AUTH_LOGOUT]: ({commit, dispatch}) => {
    return new Promise((resolve, reject) => {
      commit(AUTH_LOGOUT)
      localStorage.removeItem('user-token');
      delete API.defaults.headers.common['Authorization']
      resolve()
    })
  }
};

const mutations = {
  [AUTH_REQUEST]: (state) => {
    state.status = 'loading'
  },
  [AUTH_SUCCESS]: (state, token) => {
    state.status = 'success';
    state.token = token;
    state.hasLoadedOnce = true
  },
  [AUTH_ERROR]: (state) => {
    state.status = 'error';
    state.hasLoadedOnce = true
  },
  [AUTH_LOGOUT]: (state) => {
    state.token = ''
  }
};

export default {
  state,
  getters,
  actions,
  mutations,
}
