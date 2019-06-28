//权限vuex 
const permission = {
  state: {
    routers:null
  },
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.routers = routers;
    }
  },
  actions: {
    GenerateRoutes({ commit }, data) {
      return new Promise(resolve => {
        commit('SET_ROUTERS', data); 
        resolve();
      })
    }
  }
};
export default permission;
