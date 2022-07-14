import { createStore } from 'vuex'

export default createStore({
  state: {
    token: "",
    loggedIn: "",
    isAdmin: false
  },
  getters: {
  },
  mutations: {
    addToken: (state, payload) =>{
      state.token = payload;
      state.loggedIn = "true";
    } ,
    logout: (state, payload) => {
      state.token = "";
      state.loggedIn = false;
      state.isAdmin = false;
    } ,
    addAdmin: (state, payload) => {
      state.isAdmin = payload;
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
    } ,
    addAdmin: (context, payload) =>{
      context.commit('addAdmin', payload);
    } 
    
  },
  modules: {
  }
})
