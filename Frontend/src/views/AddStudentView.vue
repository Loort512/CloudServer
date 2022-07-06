<template>
    <div class="addStudent">
        <div class="header">
            <router-link v-if="this.$store.state.loggedInAs === 'admin'" to="/student" >Student</router-link> |  
            <router-link v-if="this.$store.state.loggedInAs === 'admin'" to="/staff" >Staff</router-link><br>
            <router-link v-if="this.$store.state.loggedInAs === 'staff'" to="/courses" >Courses</router-link>
        </div>
        <form>

        <h1>Add Student</h1>
        <div class="inputForm">
            <label for="studentID">ID</label>
            <input v-model="student.id" 
                id="studentID" 
                class="input" 
                type="text"/>
        </div>

        <div class="inputForm">
            <label for="firstName">First Name</label>
            <input v-model="student.firstName" 
                id="firstName" 
                class="input" 
                type="text"/>
        </div>
        
        <div class="inputForm">
            <label for="lastName">Last Name</label>
            <input v-model="student.lastName" 
                id="lastName" 
                class="input" 
                type="text"/>
        </div>
        

        <div class="inputForm">
            <label for="dob">DOB</label>
                <input v-model="student.dob" 
                id="DOB" 
                class="input" 
                type="text"/>
        </div>
        

        <div class="inputForm">
            <label for="dob">Gender</label>
            <input v-model="student.gender" 
                id="gender" 
                class="input" 
                type="text"/>
        </div>

        <div class="inputForm">
            <label for="dob">Department</label>
            <input v-model="student.department" 
                id="department" 
                class="input" 
                type="text"/>
        </div>

        <div class="inputForm">
            <label for="dob">Email ID</label>
            <input v-model="student.emailID" 
                id="emailID" 
                class="input" 
                type="text"/>
        </div>

        

        <button id="btnAddStudent" type="button" @click="addStudent()">Add</button>
</form>
    </div>
</template>
<script>
import Alert from '../components/Alert.vue'

export default {
  name: 'LoginView',
  data(){
      return{
          student:{
              id: '',
              firstName: '',
              lastName: '',
              dob: '',
              gender: '',
              department: '',
              emailID: ''
          } 
      } 
  }, 
  components:{
      Alert
  }, 
  methods:{
      addStudent(){
          console.log("addStudent");
          let student ={
              id: this.student.id,
              firstName: this.student.firstName,
              lastName: this.student.lastName,
              dob: this.student.dob,
              gender: this.student.gender,
              department: this.student.department,
              emailID: this.student.emailID
          } 
          this.$store.dispatch('addStudent', student);
          console.log("store: ",this.$store.state.students);

          this.$router.push({ path: '/student' }  )
      } 
  } 
}
</script>
<style scoped>
.addStudent{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
} 
.inputForm{
    display: flex;
    flex-direction: column;
} 
</style>