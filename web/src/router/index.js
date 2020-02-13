import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: {
      name: 'Login'
    }
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import(/* webpackChunkName: "login" */ '../views/Login.vue')
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import(/* webpackChunkName: "home" */ '../views/Home.vue'),
    children: [
      {
        path: '/pageOne',
        name: 'PageOne',
        component: () => import(/* webpackChunkName: "PageOne" */ '../views/PageOne.vue')
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
