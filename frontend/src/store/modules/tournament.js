import {API} from '@/api/api_config'

const state = {
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
  lastMinuteTournaments: state => state.lastMinuteTournaments
}


const actions = {
  'CREATE_TOURNAMENT': function({commit, state}, tournament) {
    return new Promise((resolve, reject) => {
      API.post("/user/" + state.user_info.userId + "/tournament", {tournament})
        .then(function (response) {
          console.log(response);
          resolve(response);
        }).catch(function (error) {
        console.log(error);
        reject(error);
      })
    })
  },
  'GET_USERS_TOURNAMENTS': ({commit}, userId) => {
    return new Promise((resolve, reject) => {
      API.get("/user/" + userId + "/ownedtournaments")
        .then(function (response) {
          console.log(response);
          resolve(response);
        }).catch(function (error) {
        console.log(error);
        reject(error);
      })
    })
  }
}


const mutations = {}

export default {
  state,
  getters,
  actions,
  mutations
}
