export default {
  namespaced: true,
  getters: {
    id: (state, getter, rootState) => {
      return rootState.room.id
    }
  }
}
