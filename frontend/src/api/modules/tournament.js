import {API, Path} from '@/api/api_config'

function create(tournament) {
  return new Promise((resolve, reject) => {
    API.post(Path.TOURNAMENT, tournament)
       .then(function (response) {
           resolve(response);
        })
       .catch(function (error) {
           reject(error);
       })
  })
}

function update(tournament) {
  return new Promise((resolve, reject) => {
     API.patch(Path.TOURNAMENT, tournament)
        .then(function (response) {
           resolve(response);
        })
        .catch(function (error) {
           reject(error);
        })
  })
}

function getAll() {
  return new Promise((resolve, reject) => {
    API.get(Path.TOURNAMENT)
       .then((response) => {
         console.log(response);
           resolve(response)
       }).catch((error) => {
            reject(error)
       })
  })
}

function queryPage(query, startDate, endDate, page, size) {
  return new Promise((resolve, reject) => {
      // API.get(Path.TOURNAMENT_QUERY_PAGE.format(query, startDate, endDate, page, size))
      //    .then((response) => {
      //        resolve(response)
      //    }).catch((error) => {
      //         reject(error)
      //    })

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
                      "id": 4,
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
                  }
        ],
        'totalElements': 4,
        'totalPages': 1,
        'last': true,
        'first': true,
        'size': 10,
        'number': 1,
        'numberOfElements': 4,
        'sort': null
      }

      resolve(page);
  })
}

export default {create, update, getAll, queryPage}
