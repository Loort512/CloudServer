import { createStore } from 'vuex'

export default createStore({
  state: {
    students:[] ,
    loggedInAs: String,
    staff:[] 
  },
  getters: {
  },
  mutations: {
    addStudent: (state, payload) =>{
      console.log("addStudent: ", payload)
      state.students.push(payload);
    } ,
    loggedInAs: (state, payload) =>{
      state.loggedInAs = payload;
    } ,
    deleteStudent: (state, studentID) =>{

      //state.students.splice(studentID, 1)
      let newList =[]; 
      for(let i = 0; i < state.students.length; i++){
        console.log("el id: ", state.students[i].id )
        if(state.students[i].id !== studentID){
          console.log("found one element to push: ", state.students[i] )
          newList.push(state.students[i]);
        } else{
          console.log("el to delete: ", state.students[i] )
          console.log("el to delete di: ", studentID);
        } 
      } 
      console.log("newList: ", newList);
      state.students = newList;
      console.log("state: ", state.students);
    } ,
    deleteStaff: (state, staffID) =>{
      let newList =[]; 
      for(let i = 0; i < state.staff.length; i++){
        console.log("el id: ", state.staff[i].id )
        if(state.staff[i].id !== staffID){
          console.log("found one element to push: ", state.staff[i] )
          newList.push(state.staff[i]);
        } else{
          console.log("el to delete: ", state.staff[i] )
          console.log("el to delete di: ", staffID);
        } 
      } 
      console.log("newList: ", newList);
      state.staff = newList;
      console.log("state: ", state.staff);
    }, 
    addStaff: (state, staff1) =>{
      state.staff.push(staff1);
    } 
  },
  actions: {
    addStudent: (context, payload) =>{
      context.commit('addStudent', payload);
    },
    loggedInAs: (context, payload) =>{
      context.commit("loggedInAs", payload);
    } ,
    deleteStudent: (context, payload) =>{
      context.commit("deleteStudent", payload);
    } ,
    addStaff: (context, payload) =>{
      context.commit("addStaff", payload);
    } ,
    deleteStaff: (context, payload) =>{
      context.commit("deleteStaff", payload);
    } 
    
  },
  modules: {
  }
})
