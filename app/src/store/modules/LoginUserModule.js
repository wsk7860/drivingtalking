export default {
  namespaced: true,
  getters: {
    id: (state, getter, rootState) => {
      return rootState.loginUser.id
    },
    isAuthentication: (state, getter, rootState) => {
      return rootState.loginUser.isAuthentication
    },
    loginName: (state, getter, rootState) => {
      return rootState.loginUser.loginName
    },
    mobile: (state, getter, rootState) => {
      return rootState.loginUser.mobile
    },
    sex: (state, getter, rootState) => {
      return rootState.loginUser.sex
    }
  }
}