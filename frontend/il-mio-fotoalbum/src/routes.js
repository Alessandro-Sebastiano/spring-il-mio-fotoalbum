import { createRouter, createWebHistory } from 'vue-router';
import Home from './pages/Home.vue';
import Show from './pages/Show.vue';
import Contact from './pages/Contact.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/show/:id',
      name: 'show',
      component: Show,
    },
    {
      path: '/contact',
      name: 'contact',
      component: Contact,
    },
  ]
})

export default router
