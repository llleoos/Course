import { defineStore } from 'pinia'
import { ref } from 'vue'
import api from '@/api/portfolio'

export const usePortfolioStore = defineStore('portfolio', () => {
  const items = ref([])
  const loading = ref(false)
  const totalValue = ref(0)
  const totalProfit = ref(0)

  async function fetchAll() {
    loading.value = true
    try {
      const response = await api.listPortfolio()
      items.value = response.data
    } catch (error) {
      console.error('Failed to fetch portfolio items:', error)
    } finally {
      loading.value = false
    }
  }

  async function fetchSummary() {
    try {
      const [valueRes, profitRes] = await Promise.all([
        api.getTotalValue(),
        api.getTotalProfit()
      ])
      totalValue.value = valueRes.data.totalValue
      totalProfit.value = profitRes.data.totalProfit
    } catch (error) {
      console.error('Failed to fetch summary:', error)
    }
  }

  async function createItem(data) {
    const response = await api.createPortfolio(data)
    await fetchAll()
    return response.data
  }

  async function updateItem(id, data) {
    const response = await api.updatePortfolio(id, data)
    await fetchAll()
    return response.data
  }

  async function deleteItem(id) {
    await api.deletePortfolio(id)
    await fetchAll()
  }

  return {
    items,
    loading,
    totalValue,
    totalProfit,
    fetchAll,
    fetchSummary,
    createItem,
    updateItem,
    deleteItem
  }
})
