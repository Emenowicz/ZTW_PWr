import { API } from './api_config'

export default {
  registerInBackend (onSuccess, onError) {
    API.get('http://localhost:8088/me'
    ).then((response) => {
      onSuccess(response)
    }, (error) => {
      onError(error)
    })
  }
}
