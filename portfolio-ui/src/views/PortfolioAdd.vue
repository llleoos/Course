<template>
  <div class="portfolio-add">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>Add New Asset</span>
        </div>
      </template>
      
      <el-form :model="form" :rules="rules" ref="formRef" label-width="140px">
        <el-form-item label="Ticker" prop="ticker">
          <el-input v-model="form.ticker" placeholder="e.g., AAPL" />
        </el-form-item>
        
        <el-form-item label="Asset Type" prop="assetType">
          <el-select v-model="form.assetType" placeholder="Select asset type">
            <el-option label="Stock" value="STOCK" />
            <el-option label="Bond" value="BOND" />
            <el-option label="Cash" value="CASH" />
            <el-option label="ETF" value="ETF" />
            <el-option label="Crypto" value="CRYPTO" />
          </el-select>
        </el-form-item>
        
        <el-form-item label="Quantity" prop="quantity">
          <el-input-number v-model="form.quantity" :min="1" />
        </el-form-item>
        
        <el-form-item label="Purchase Price" prop="purchasePrice">
          <el-input-number v-model="form.purchasePrice" :precision="2" :min="0" />
        </el-form-item>
        
        <el-form-item label="Purchase Date" prop="purchaseDate">
          <el-date-picker
            v-model="form.purchaseDate"
            type="date"
            placeholder="Select date"
            format="YYYY-MM-DD"
            value-format="YYYY-MM-DD"
          />
        </el-form-item>
        
        <el-form-item>
          <el-button type="primary" @click="handleSubmit" :loading="loading">
            Create Asset
          </el-button>
          <el-button @click="goBack">Cancel</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { usePortfolioStore } from '@/store/portfolio'

const router = useRouter()
const store = usePortfolioStore()

const formRef = ref()
const loading = ref(false)

const form = reactive({
  ticker: '',
  assetType: '',
  quantity: 1,
  purchasePrice: 0,
  purchaseDate: ''
})

const rules = {
  ticker: [{ required: true, message: 'Ticker is required', trigger: 'blur' }],
  assetType: [{ required: true, message: 'Asset type is required', trigger: 'change' }],
  quantity: [{ required: true, message: 'Quantity is required', trigger: 'blur' }]
}

async function handleSubmit() {
  try {
    await formRef.value.validate()
    loading.value = true
    await store.createItem(form)
    ElMessage.success('Asset created successfully')
    goBack()
  } catch (error) {
    if (error !== false) {
      ElMessage.error('Failed to create asset')
    }
  } finally {
    loading.value = false
  }
}

function goBack() {
  router.push('/portfolio')
}
</script>

<style scoped>
.portfolio-add {
  max-width: 600px;
  margin: 0 auto;
}

.card-header {
  font-size: 18px;
  font-weight: bold;
}
</style>
