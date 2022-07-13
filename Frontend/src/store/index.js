import { createStore } from 'vuex'

export default createStore({
  state: {
    token: "",
    loggedIn: ""
  },
  getters: {
  },
  mutations: {
    addToken: (state, payload) =>{
      console.log("addToken: ", payload)
      state.token = payload;
      state.loggedIn = "true";
    } ,
    logout: (state, payload) => {
      console.log("logout Vue Store")
      state.token = "";
      state.loggedIn = false;
    } 
  },
  actions: {
    addStudent: (context, payload) =>{
      context.commit('addStudent', payload);
    },
    addToken: (context, payload) =>{
      context.commit("addToken", payload);
    } ,
    logout: (context, payload)  => {
      context.commit('logout', payload);
    } 
    
  },
  modules: {
  }
})
