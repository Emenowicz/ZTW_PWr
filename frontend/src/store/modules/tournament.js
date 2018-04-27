import Tournament from '@/api/modules/tournament'

const state = {
  allTournaments: [],
  lastMinuteTournaments: [
    {
      name: 'Wroclaw XX Table Soccer Cups',
      remainingTime: 6456546,
      type1: 'Local',
      type2: '2 vs. 2',
      locationName: 'Wroclaw',
      freeSlots: 4
    },
    {
      name: 'Poland IV Table Soccer Cups',
      remainingTime: 6456546,
      type1: 'Virtual',
      type2: '1 vs. 1',
      locationName: '',
      freeSlots: 16
    },
    {
      name: 'Kraków V Table Soccer Cups',
      remainingTime: 6456546,
      type1: 'Local',
      type2: '1 vs. 1',
      locationName: 'Kraków',
      freeSlots: 4
    },
    {
      name: 'Random Tournament',
      remainingTime: 6456546,
      type1: 'Local',
      type2: '1 vs. 1',
      locationName: 'Kraków',
      freeSlots: 4
    }
  ]
}

const getters = {
  lastMinuteTournaments: state => state.lastMinuteTournaments,
  allTournaments: state => state.allTournaments,
  getTournament: (state) => (id) => state.allTournaments.find(tournament => tournament.id === id)
}

const actions = {
  'CREATE_TOURNAMENT': function ({commit}, tournament) {
    return Tournament.create(tournament)
  },
  'UPDATE_TOURNAMENT': function ({commit}, tournament) {
    return Tournament.update(tournament)
  },
  'GET_ALL_TOURNAMENTS': function ({commit}) {
    return new Promise((resolve, reject) => {
      Tournament.getAll().then((response) => {
        resolve(response.data)
      }).catch((error) => {
        reject(error);
      })
    })
  },
  'LOAD_ALL_TOURNAMENTS': function ({commit, dispatch}) {
      dispatch('GET_ALL_TOURNAMENTS')
        .then((tournaments) => {
          commit('SET_ALL_TOURNAMENTS', tournaments);
        })
  }
}

const mutations = {
  'SET_ALL_TOURNAMENTS': (state, tournaments) => {
    state.allTournaments = tournaments;
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
