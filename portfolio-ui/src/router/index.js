import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    redirect: '/portfolio'
  },
  {
    path: '/portfolio',
    name: 'PortfolioList',
    component: () => import('@/views/PortfolioList.vue')
  },
  {
    path: '/portfolio/add',
    name: 'PortfolioAdd',
    component: () => import('@/views/PortfolioAdd.vue')
  },
  {
    path: '/portfolio/edit/:id',
    name: 'PortfolioEdit',
    component: () => import('@/views/PortfolioEdit.vue')
  },
  {
    path: '/portfolio/summary',
    name: 'PortfolioSummary',
    component: () => import('@/views/PortfolioSummary.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
