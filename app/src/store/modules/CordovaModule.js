export default {
  namespaced: true,
  getters: {
    enabled: (state, getter, rootState) => {
      return rootState.cordova.enabled
    }
  }
}
