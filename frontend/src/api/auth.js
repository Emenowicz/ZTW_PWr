import { API } from './api_config'

export default {
  getGoogleAccessToken (authorizationCode, onSuccess, onError) {
    API.post('http://localhost:8088/auth/google',
      {authorizationCode}
    ).then((response) => {
      onSuccess(response)
    }, (error) => {
      onError(error)
    })
  }
}
