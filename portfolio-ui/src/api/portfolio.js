import axios from 'axios'

const apiClient = axios.create({
  baseURL: '/api',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json'
  }
})

apiClient.interceptors.response.use(
  response => response,
  error => {
    console.error('API Error:', error)
    return Promise.reject(error)
  }
)

export default {
  listPortfolio() {
    return apiClient.get('/portfolio/list')
  },
  
  getPortfolio(id) {
    return apiClient.get(`/portfolio/${id}`)
  },
  
  createPortfolio(data) {
    return apiClient.post('/portfolio', data)
  },
  
  updatePortfolio(id, data) {
    return apiClient.put(`/portfolio/${id}`, data)
  },
  
  deletePortfolio(id) {
    return apiClient.delete(`/portfolio/${id}`)
  },
  
  getTotalValue() {
    return apiClient.get('/portfolio/value')
  },
  
  getTotalProfit() {
    return apiClient.get('/portfolio/profit')
  }
}
