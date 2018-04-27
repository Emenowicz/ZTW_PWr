import {API, Path} from '@/api/api_config'

function getTournaments(userId) {
  return new Promise((resolve, reject) => {
    API.get(Path.USER_TOURNAMENTS.format(userId))
       .then(function (response) {
           resolve(response);
        })
       .catch(function (error) {
           reject(error);
       })
  })
}

function joinTournament(tournamentId) {
  return new Promise((resolve, reject) => {
    API.post(Path.JOIN_TOURNAMENT.format(tournamentId))
      .then(function (response) {
        resolve(response);
      })
      .catch(function (error) {
        reject(error);
      })
  })
}


export default {getTournaments, joinTournament}
