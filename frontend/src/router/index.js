import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import view_AboutMe from '@/views/view_AboutMe'
import view_Home from '@/views/view_Home'

const routes = [
  {
    path: '/',
    name: 'home',
    component: view_Home
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
export default router
