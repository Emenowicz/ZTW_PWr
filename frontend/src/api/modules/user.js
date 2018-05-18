import {API, Path} from '@/api/api_config'
import {TOURNAMENTS} from '@/api/mock'

function getPlayingTournaments (userId) {
  return new Promise((resolve, reject) => {
    API.get(Path.USER_PLAYING_TOURNAMENTS.format(userId))
      .then(function (response) {
        resolve(response.data)
      })
      .catch(function (error) {
        reject(error)
      })
    // resolve(TOURNAMENTS)
  })
}

function getOwnedTournaments (userId) {
  return new Promise((resolve, reject) => {
    API.get(Path.USER_OWNED_TOURNAMENTS.format(userId))
      .then(function (response) {
        resolve(response.data)
      })
      .catch(function (error) {
        reject(error)
      })
    // resolve(TOURNAMENTS)
  })
}

function joinTournament (tournamentId) {
  return new Promise((resolve, reject) => {
    API.post(Path.JOIN_TOURNAMENT.format(tournamentId))
      .then(function (response) {
        resolve(response)
      })
      .catch(function (error) {
        reject(error)
      })
  })
}

export default {getPlayingTournaments, joinTournament, getOwnedTournaments}
