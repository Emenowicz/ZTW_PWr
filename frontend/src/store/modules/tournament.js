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
}


const mutations = {

}

export default {
  state,
  getters,
  actions,
  mutations
}
