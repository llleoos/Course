<template>
  <div class="portfolio-list">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>Portfolio Assets</span>
          <el-button type="primary" @click="goToAdd">
            <el-icon><Plus /></el-icon>
            Add Asset
          </el-button>
        </div>
      </template>
      
      <el-table :data="store.items" v-loading="store.loading" stripe>
        <el-table-column prop="ticker" label="Ticker" width="120" />
        <el-table-column prop="assetType" label="Asset Type" width="120">
          <template #default="{ row }">
            <el-tag :type="getAssetTypeTag(row.assetType)">{{ row.assetType }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="quantity" label="Quantity" width="100" />
        <el-table-column prop="purchasePrice" label="Purchase Price" width="130">
          <template #default="{ row }">
            ${{ row.purchasePrice?.toFixed(2) }}
          </template>
        </el-table-column>
        <el-table-column prop="currentPrice" label="Current Price" width="130">
          <template #default="{ row }">
            ${{ row.currentPrice?.toFixed(2) }}
          </template>
        </el-table-column>
        <el-table-column prop="profit" label="Profit" width="120">
          <template #default="{ row }">
            <span :class="getProfitClass(row.profit)">
              ${{ row.profit?.toFixed(2) }}
            </span>
          </template>
        </el-table-column>
        <el-table-column prop="purchaseDate" label="Purchase Date" width="130">
          <template #default="{ row }">
            {{ formatDate(row.purchaseDate) }}
          </template>
        </el-table-column>
        <el-table-column label="Actions" width="180">
          <template #default="{ row }">
            <el-button size="small" @click="goToEdit(row.id)">Edit</el-button>
            <el-button size="small" type="danger" @click="handleDelete(row.id)">Delete</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import { usePortfolioStore } from '@/store/portfolio'

const router = useRouter()
const store = usePortfolioStore()

onMounted(() => {
  store.fetchAll()
})

function goToAdd() {
  router.push('/portfolio/add')
}

function goToEdit(id) {
  router.push(`/portfolio/edit/${id}`)
}

async function handleDelete(id) {
  try {
    await ElMessageBox.confirm(
      'Are you sure you want to delete this asset?',
      'Confirm Delete',
      {
        confirmButtonText: 'Delete',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }
    )
    await store.deleteItem(id)
    ElMessage.success('Asset deleted successfully')
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error('Failed to delete asset')
    }
  }
}

function formatDate(date) {
  if (!date) return '-'
  return new Date(date).toLocaleDateString()
}

function getAssetTypeTag(type) {
  const types = {
    STOCK: '',
    BOND: 'success',
    CASH: 'info',
    ETF: 'warning',
    CRYPTO: 'danger'
  }
  return types[type] || ''
}

function getProfitClass(profit) {
  if (profit > 0) return 'profit-positive'
  if (profit < 0) return 'profit-negative'
  return ''
}
</script>

<style scoped>
.portfolio-list {
  max-width: 1200px;
  margin: 0 auto;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.profit-positive {
  color: #67c23a;
  font-weight: bold;
}

.profit-negative {
  color: #f56c6c;
  font-weight: bold;
}
</style>
