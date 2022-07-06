<template>
    <div class="staff">
        <div class="header">
            <router-link v-if="this.$store.state.loggedInAs === 'admin'" to="/student" >Student</router-link> |  
            <router-link v-if="this.$store.state.loggedInAs === 'admin'" to="/staff" >Staff</router-link><br>
            <router-link v-if="this.$store.state.loggedInAs === 'staff'" to="/courses" >Courses</router-link>
        </div>
        <h1>Staff</h1>
        <table border=1>
            <tr>
                <th>Staff ID</th>
                <th>First name</th>
                <th>Last name</th>
                <th>DOB</th>
                <th>Gender</th>
                <th>Department</th>
                <th>Email ID</th>
            </tr>
            <tr v-for="entry in staff">
                <th> {{entry.id}} </th>
                <th> {{entry.firstName}} </th>
                <th> {{entry.lastName}} </th>
                <th> {{entry.dob}} </th>
                <th> {{entry.gender}} </th>
                <th> {{entry.department}} </th>
                <th> {{entry.emailID}} </th>
                <th>  <input type="button" value="UPDATE" @click="updateStaff(entry)"/> </th>
                <th>  <input type="button" value="DELETE" @click="deleteStaff(entry.id)"/> </th>
            </tr>
        </table>
        <br><br>
        <input type="button" value="ADD" @click="addStaff()"/>

        <!-- UPDATE Staff -->
        <div v-if="activeStaff" class="inputGroup">
            <h3>Update Staff</h3>
            <div class="inputForm">
                <label for="studentID">ID</label>
                <input v-model="activeStaff.id" 
                    id="studentID1" 
                    class="input" 
                    type="text"/>
            </div>

            <div class="inputForm">
                <label for="firstName">First Name</label>
                <input v-model="activeStaff.firstName" 
                    id="firstName1" 
                    class="input" 
                    type="text"/>
            </div>
            
            <div class="inputForm">
                <label for="lastName">Last Name</label>
                <input v-model="activeStaff.lastName" 
                    id="lastName1" 
                    class="input" 
                    type="text"/>
            </div>
            

            <div class="inputForm">
                <label for="dob">DOB</label>
                    <input v-model="activeStaff.dob" 
                    id="DOB1" 
                    class="input" 
                    type="text"/>
            </div>
            

            <div class="inputForm">
                <label for="dob">Gender</label>
                <input v-model="activeStaff.gender" 
                    id="gender1" 
                    class="input" 
                    type="text"/>
            </div>

            <div class="inputForm">
                <label for="dob">Department</label>
                <input v-model="activeStaff.department" 
                    id="department1" 
                    class="input" 
                    type="text"/>
            </div>

            <div class="inputForm">
                <label for="dob">Email ID</label>
                <input v-model="activeStaff.emailID" 
                    id="emailID1" 
                    class="input" 
                    type="text"/>
            </div>

            <input type="button" value="Update Staff" @click="updateStaff(activeStaff)"/>
        </div>
    
    </div>
</template>

<script>
import InputModal from '../components/InputModal.vue'

export default{
    name: 'staffView',
    data(){
        return{
            staff:[] ,
            activeStaff: '' ,
            showUpdateContent: false
        }  
    }, 
    components:{
        InputModal
    } ,
    mounted(){
        console.log("created Staff")
        this.staff = this.$store.state.staff
        console.log("staff: ", this.staff)
        console.log("active staff: ", this.activeStaff)
    },
    methods:{
      addStaff(){
          console.log("addSTaff")
          this.$router.push({path: '/addStaff'}  )
      } ,
      showUpdateContent(st){
        this.activeStaff = st;
        this.showUpdateContent = true;
      } ,
      updateStaff(st){
          if(this.activeStaff){
              this.activeStaff = undefined;
          } else{
              this.activeStaff = st;
          } 
      } ,
      deleteStaff(id){
          console.log("delete staff ", id)
          this.$store.dispatch('deleteStaff', id);
          this.$router.push({path: '/'}  );
          console.log("state in view: ", this.$store.state.staff);
      } 
    }  
} 
</script>
<style scoped>

</style>
