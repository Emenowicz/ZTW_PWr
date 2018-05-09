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
           resolve(response)
       }).catch((error) => {
            reject(error)
       })
  })
}

export default {create, update, getAll}
