import { createRouter, createWebHistory } from 'vue-router';
import index from '@/pages/index.vue';

const routes = [
  {
    path: '/',
    name: 'index',
    component: index,
    meta: {
      auth: false,
      title: 'Richard Algra'
    }
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

// Navigation guard to update the page title based on meta.title
router.beforeEach((to, from, next) => {
  document.title = to.meta.title || 'Default Title';
  next();
});

export default router;
