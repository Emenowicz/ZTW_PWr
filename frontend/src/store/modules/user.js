import {API} from '@/api/api_config'

const state = {
    user_info: {
          id: "",
          firstname: "",
          lastname: "",
          email: "",
          picture_url: ""
        },
    editedTournament: '',
    usersTournaments: []
};

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
  'SET_USER_INFO': (state, user_info) => {
    state.user_info.id = user_info.Eea;
    state.user_info.firstname = user_info.ofa;
    state.user_info.lastname = user_info.wea;
    state.user_info.email = user_info.U3;
    state.user_info.picture_url = user_info.Paa;
  },
  'CLEAR_USER_INFO': (state) => {
    Object.keys(state.user_info).forEach(k => state.user_info[k] = '');
  },
  'SET_USERS_TOURNAMENTS': (state, usersTournaments) => {
    state.usersTournaments = usersTournaments;
    },
    'SET_EDITED_TOURNAMENT': (state, tournament) => {
        state.editedTournament = tournament;
      },
      'CLEAR_EDITED_TOURNAMENT': (state) => {
        state.editedTournament = '';
      }
};

const getters = {
  userId: state => state.user_info.id,
  userInfo: state => state.user_info,
  editedTournament: state => state.editedTournament,
  usersTournaments: state => state.usersTournaments
}

export default {
    state,
    getters,
    actions,
    mutations
}
