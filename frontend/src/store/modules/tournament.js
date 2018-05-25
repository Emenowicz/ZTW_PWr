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
  },
  'GET_TOURNAMENTS_MATCHES': function ({commit}, tournamentId) {
    return Tournament.getMatches(tournamentId)
  },
  'START_TOURNAMENT': function ({commit}, tournamentId) {
    return Tournament.start(tournamentId)
  },
  'SET_MATCH_SCORE': function ({commit}, {matchId, scores}) {
    return Tournament.setMatchScore(matchId, scores)
  },
  'GET_MATCH': function ({commit}, matchId) {
    return Tournament.getMatch(matchId)
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
