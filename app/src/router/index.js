import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

let routes = {
  routes: [
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
      path: '/homePage',
      name: 'HomePage',
      component: () => import(/* webpackChunkName: "homePage" */ '../views/HomePage.vue')
    }
  ]
}

export default new Router(routes)
