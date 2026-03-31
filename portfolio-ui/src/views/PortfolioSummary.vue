<template>
  <div class="portfolio-summary">
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card class="summary-card">
          <div class="summary-content">
            <div class="summary-label">Total Portfolio Value</div>
            <div class="summary-value">${{ totalValue.toLocaleString('en-US', { minimumFractionDigits: 2 }) }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="summary-card">
          <div class="summary-content">
            <div class="summary-label">Total Portfolio Profit</div>
            <div class="summary-value" :class="getProfitClass(totalProfit)">
              ${{ totalProfit.toLocaleString('en-US', { minimumFractionDigits: 2 }) }}
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    
    <el-card class="chart-card">
      <template #header>
        <div class="card-header">
          <span>Asset Allocation</span>
        </div>
      </template>
      <div ref="chartRef" class="chart-container"></div>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { usePortfolioStore } from '@/store/portfolio'
import * as echarts from 'echarts'

const store = usePortfolioStore()
const chartRef = ref(null)
let chart = null

const totalValue = computed(() => store.totalValue)
const totalProfit = computed(() => store.totalProfit)

onMounted(async () => {
  await store.fetchAll()
  await store.fetchSummary()
  initChart()
})

function getProfitClass(profit) {
  if (profit > 0) return 'profit-positive'
  if (profit < 0) return 'profit-negative'
  return ''
}

function initChart() {
  if (!chartRef.value) return
  
  chart = echarts.init(chartRef.value)
  
  const assetData = {}
  store.items.forEach(item => {
    const type = item.assetType || 'UNKNOWN'
    const value = (item.currentPrice || 0) * item.quantity
    assetData[type] = (assetData[type] || 0) + value
  })
  
  const pieData = Object.entries(assetData).map(([name, value]) => ({
    name,
    value: Math.round(value * 100) / 100
  }))
  
  const option = {
    tooltip: {
      trigger: 'item',
      formatter: '{b}: ${c} ({d}%)'
    },
    legend: {
      orient: 'vertical',
      left: 'left'
    },
    series: [
      {
        name: 'Asset Allocation',
        type: 'pie',
        radius: '60%',
        data: pieData,
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        },
        label: {
          formatter: '{b}: {d}%'
        }
      }
    ],
    color: ['#409eff', '#67c23a', '#e6a23c', '#909399', '#f56c6c']
  }
  
  chart.setOption(option)
  
  window.addEventListener('resize', () => {
    chart?.resize()
  })
}
</script>

<style scoped>
.portfolio-summary {
  max-width: 1200px;
  margin: 0 auto;
}

.summary-card {
  margin-bottom: 20px;
}

.summary-content {
  text-align: center;
  padding: 20px;
}

.summary-label {
  font-size: 16px;
  color: #606266;
  margin-bottom: 10px;
}

.summary-value {
  font-size: 36px;
  font-weight: bold;
  color: #303133;
}

.profit-positive {
  color: #67c23a;
}

.profit-negative {
  color: #f56c6c;
}

.chart-card {
  margin-top: 20px;
}

.card-header {
  font-size: 18px;
  font-weight: bold;
}

.chart-container {
  width: 100%;
  height: 400px;
}
</style>
