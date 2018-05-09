import Tournament from '@/api/modules/tournament'

const state = {
  allTournaments: [],
}

const getters = {
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
  },
  'QUERY_TOURNAMENTS_PAGE': function ({commit}, {query, startDate, endDate, page, size}) {
      return new Promise((resolve, reject) => {
        Tournament.queryPage(query, startDate, endDate, page, size).then((response) => {
          //resolve(response.data)
          let page = {
            'content': [
                        {
                          "id": 1,
                          "name": "Testowy turniej2",
                          "startTime": "2018-04-08",
                          "endTime": "2018-04-30",
                          "description": "Przykładowy opis",
                          "playMode": "TWOVSTWO",
                          "tournamentType": "VIRTUAL",
                          "location": null,
                          "minTeams": 4,
                          "maxTeams": 16,
                          "owner": {
                              "id": "116248526006631545152",
                              "name": "Konrad Drozd",
                              "avatar": null
                          },
                          "teams": [],
                          "matches": [],
                          "players": []
                      },
                      {
                          "id": 2,
                          "name": "Testowy turniej",
                          "startTime": "2018-04-19",
                          "endTime": "2018-04-20",
                          "description": "Przykładowy opis",
                          "playMode": "ONEVSONE",
                          "tournamentType": "LOCAL",
                          "location": "Gdzieś tam",
                          "minTeams": 2,
                          "maxTeams": 6,
                          "owner": {
                              "id": "116248526006631545152",
                              "name": "Konrad Drozd",
                              "avatar": null
                          },
                          "teams": [],
                          "matches": [],
                          "players": []
                      },{
                          "id": 3,
                          "name": "Testowy turniej3",
                          "startTime": "2018-04-08",
                          "endTime": "2018-04-30",
                          "description": "Przykładowy opis",
                          "playMode": "TWOVSTWO",
                          "tournamentType": "VIRTUAL",
                          "location": null,
                          "minTeams": 4,
                          "maxTeams": 16,
                          "owner": {
                              "id": "116248526006631545152",
                              "name": "Konrad Drozd",
                              "avatar": null
                          },
                          "teams": [],
                          "matches": [],
                          "players": []
                      },
                      {
                          "id": 4,
                          "name": "Testowy turniej4",
                          "startTime": "2018-04-19",
                          "endTime": "2018-04-20",
                          "description": "Przykładowy opis",
                          "playMode": "ONEVSONE",
                          "tournamentType": "LOCAL",
                          "location": "Gdzieś tam",
                          "minTeams": 2,
                          "maxTeams": 6,
                          "owner": {
                              "id": "116248526006631545152",
                              "name": "Konrad Drozd",
                              "avatar": null
                          },
                          "teams": [],
                          "matches": [],
                          "players": []
                      }
            ],
            'totalElements': 4,
            'totalPages': 1,
            'last': true,
            'first': true,
            'size': 4,
            'number': 1,
            'numberOfElements': 4,
            'sort': null
          }

          resolve(page);
        }).catch((error) => {
          reject(error);
        })
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
