<template>

    <div class="addStudent">
        <div class="header">
            <router-link v-if="this.$store.state.loggedInAs === 'admin'" to="/student" >Student</router-link> |  
            <router-link v-if="this.$store.state.loggedInAs === 'admin'" to="/staff" >Staff</router-link><br>
            <router-link v-if="this.$store.state.loggedInAs === 'staff'" to="/courses" >Courses</router-link>
        </div>
        <form>

        <h1>Add Staff</h1>
        <div class="inputForm">
            <label for="studentID2">ID</label>
            <input v-model="staff.id" 
                id="studentID2" 
                class="input" 
                type="text"/>
        </div>

        <div class="inputForm">
            <label for="firstName">First Name</label>
            <input v-model="staff.firstName" 
                id="firstName" 
                class="input" 
                type="text"/>
        </div>
        
        <div class="inputForm">
            <label for="lastName">Last Name</label>
            <input v-model="staff.lastName" 
                id="lastName" 
                class="input" 
                type="text"/>
        </div>
        

        <div class="inputForm">
            <label for="dob">DOB</label>
                <input v-model="staff.dob" 
                id="DOB" 
                class="input" 
                type="text"/>
        </div>
        

        <div class="inputForm">
            <label for="dob">Gender</label>
            <input v-model="staff.gender" 
                id="gender" 
                class="input" 
                type="text"/>
        </div>

        <div class="inputForm">
            <label for="dob">Department</label>
            <input v-model="staff.department" 
                id="department" 
                class="input" 
                type="text"/>
        </div>

        <div class="inputForm">
            <label for="dob">Email ID</label>
            <input v-model="staff.emailID" 
                id="emailID" 
                class="input" 
                type="text"/>
        </div>

        

        <button id="btnAddSTaff" type="button" @click="addStaff()">Add</button>
</form>
    </div>
</template>
<script>
import Alert from '../components/Alert.vue'

export default {
  name: 'LoginView',
  data(){
      return{
          staff:{
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
      addStaff(){
          console.log("addStaff");
          let newStaff ={
              id: this.staff.id,
              firstName: this.staff.firstName,
              lastName: this.staff.lastName,
              dob: this.staff.dob,
              gender: this.staff.gender,
              department: this.staff.department,
              emailID: this.staff.emailID
          } 
          this.$store.dispatch('addStaff', newStaff);
          console.log("store: ",this.$store.state.staff);

          this.$router.push({ path: '/staff' }  )
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