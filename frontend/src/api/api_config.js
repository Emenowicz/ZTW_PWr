import axios from 'axios';
import API_BASE_URL from '../config'

export const HTTP = axios.create({
  baseURL: [API_BASE_URL],
})