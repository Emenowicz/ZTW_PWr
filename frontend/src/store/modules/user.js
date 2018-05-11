import User from '@/api/modules/user'

const state = {
  userInfo: {
    id: '',
    firstname: '',
    lastname: '',
    email: '',
    picture_url: ''
  },
  editedTournament: '',
  usersTournaments: []
}

const actions = {
  'GET_USERS_TOURNAMENTS': ({state}, userId) => {
    return User.getTournaments(state.userInfo.id)
  },

  'JOIN_TOURNAMENT': ({commit}, tournamentId) => {
    return User.joinTournament(tournamentId)
  }

}

const mutations = {
  'SET_USER_INFO': (state, userInfo) => {
    state.userInfo.id = userInfo.Eea
    state.userInfo.firstname = userInfo.ofa
    state.userInfo.lastname = userInfo.wea
    state.userInfo.email = userInfo.U3
    state.userInfo.picture_url = userInfo.Paa
  },
  'CLEAR_USER_INFO': (state) => {
    Object.keys(state.userInfo).forEach(k => state.userInfo[k] = '')
  },
  'SET_USERS_TOURNAMENTS': (state, usersTournaments) => {
    state.usersTournaments = usersTournaments
  },
  'SET_EDITED_TOURNAMENT': (state, tournament) => {
    state.editedTournament = tournament
  },
  'CLEAR_EDITED_TOURNAMENT': (state) => {
    state.editedTournament = ''
  }
}

const getters = {
  userId: state => state.userInfo.id,
  userInfo: state => state.userInfo,
  editedTournament: state => state.editedTournament,
  usersTournaments: state => state.usersTournaments
}

export default {
  state,
  getters,
  actions,
  mutations
}
