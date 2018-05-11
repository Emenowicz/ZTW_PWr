import Tournament from '@/api/modules/tournament'

const state = {

}

const getters = {

}

const actions = {
  'CREATE_TOURNAMENT': function ({commit}, tournament) {
    return Tournament.create(tournament)
  },
  'UPDATE_TOURNAMENT': function ({commit}, tournament) {
    return Tournament.update(tournament)
  },
  'QUERY_TOURNAMENTS_PAGE': function ({commit}, {query, startDate, endDate, page, size}) {
    return new Promise((resolve, reject) => {
      Tournament.queryPage(query, startDate, endDate, page, size)
        .then((response) => {
          resolve(response.data)
        }).catch((error) => {
          reject(error)
        })
    })
  }
}

const mutations = {

}

export default {
  state,
  getters,
  actions,
  mutations
}
