import axios from 'axios';
import Config from '../config'
import * as Path from './paths'

const API = axios.create({
  baseURL: Config.API_BASE_URL
})

export {API, Path}
