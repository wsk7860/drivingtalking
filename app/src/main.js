import Vue from 'vue'
import Cube from 'cube-ui'
import App from './App.vue'
import router from './router'
import store from './store'

Vue.use(Cube)
Vue.config.productionTip = false

/**
 * 引入cordova
 */
if (window.location.protocol === 'file:' || window.location.port === '3000') {
  let cordovaScript = document.createElement('script')
  cordovaScript.setAttribute('type', 'text/javascript')
  cordovaScript.setAttribute('src', 'cordova.js')
  document.body.appendChild(cordovaScript)
  // 设置启用cordova
  store.commit('setCordova', true)
}

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
