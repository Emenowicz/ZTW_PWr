import {API} from '@/api/api_config'

const state = {
  usersTournaments: []
}


const getters = {
  usersTournaments: state => state.usersTournaments
}


const actions = {
  'LOAD_USERS_TOURNAMENTS': ({commit}, userId) => {
    return new Promise((resolve, reject) => {
      API.get("/user/" + userId + "/ownedtournaments")
        .then(function (response) {
          console.log(response);
          commit('SET_USERS_TOURNAMENTS', response.data);
          resolve(response);
        }).catch(function (error) {
        console.log(error);
        reject(error);
      })
    })
  }
}


const mutations = {
  SET_USERS_TOURNAMENTS(state, usersTournaments) {
    state.usersTournaments = usersTournaments;
  },
  CLEAR_USERS_TOURNAMENTS(state) {
    state.usersTournaments = [];
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
