import Vue from 'vue'
import Vuex from 'vuex'
import persistedState from 'vuex-persistedstate'
import cordova from './modules/CordovaModule'
import user from './modules/LoginUserModule'
import room from './modules/RoomModule'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    cordova: { enabled: false },
    loginUser: {},
    room: {}
  },
  mutations: {
    setCordova (state, params) {
      state.cordova.enabled = params
    },
    setLoginUser (state, params) {
      state.loginUser = params
    },
    // TODO:仅启用房间号
    setRoom (state, params) {
      state.room.id = params
    }
  },
  modules: {
    cordova,
    user,
    room
  },
  plugins: [persistedState()]
})
