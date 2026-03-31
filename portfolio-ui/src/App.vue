<template>
  <div id="app">
    <el-container>
      <el-header>
        <div class="header-content">
          <h1>Portfolio Manager</h1>
          <el-menu mode="horizontal" :default-active="activeMenu" router>
            <el-menu-item index="/portfolio">Portfolio List</el-menu-item>
            <el-menu-item index="/portfolio/summary">Summary</el-menu-item>
          </el-menu>
        </div>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const activeMenu = computed(() => route.path)
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.el-header {
  background-color: #409eff;
  color: white;
  padding: 0;
}

.header-content {
  display: flex;
  align-items: center;
  height: 100%;
  padding: 0 20px;
}

.header-content h1 {
  margin-right: 50px;
  font-size: 24px;
}

.el-header .el-menu {
  background-color: transparent;
  border: none;
}

.el-header .el-menu-item {
  color: white !important;
}

.el-header .el-menu-item:hover {
  background-color: rgba(255, 255, 255, 0.2) !important;
}

.el-header .el-menu-item.is-active {
  color: white !important;
  border-bottom-color: white !important;
}

.el-main {
  padding: 20px;
  background-color: #f5f5f5;
  min-height: calc(100vh - 60px);
}
</style>
