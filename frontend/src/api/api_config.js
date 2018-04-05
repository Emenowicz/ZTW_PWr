import axios from 'axios';
import Config from '../config'

export const API =  axios.create({
  baseURL: Config.API_BASE_URL,
  headers: {
    'Access-Control-Allow-Origin': 'http://localhost:8080'
  }
})
