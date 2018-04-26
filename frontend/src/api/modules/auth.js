import {API, Path} from '@/api/api_config'

export default {
  registerInBackend() {
    return new Promise((resolve, reject) => {
    API.get(Path.ME)
    .then((response) => {
      resolve(response)
    }, (error) => {
      reject(error)
    })
  })
}
}
