import {API, Path} from '@/api/api_config'

function getTournaments() {
  return new Promise((resolve, reject) => {
    API.get(Path.USER_TOURNAMENTS)
       .then(function (response) {
           resolve(response);
        })
       .catch(function (error) {
           reject(error);
       })
  })
}


export default {getTournaments}
