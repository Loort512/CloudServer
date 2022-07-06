<template>
    <div class="login">
        <Alert v-if="showAlert" msg="Login Credentials Incorrect"></Alert>
        <div class="loginStaff">
            <h1>Staff Login</h1>
            <p>
                <label for="emailStaff"></label>
                <input v-model="emailStaff" 
                        id="usernameStaff" 
                        class="input" 
                        type="text" 
                        placeholder="Benutzername"
                        :disabled="inputDisabled === 1"/>

                <label for="passwordStaff"></label>
                <input v-model="passwordStaff" 
                        id="passwordStaff" 
                        class="input" 
                        type="password" 
                        placeholder="Passwort" 
                        :disabled="inputDisabled === 1"/><br/>
                <button id="btnLoginStaff" type="button" @click="login('staff')">Login</button>
            </p>
        </div>
        <div class="loginAdmin">
            <h1>Admin Login</h1>
            <p>
                <input v-model="emailAdmin" 
                        id="usernameAdmin" 
                        class="input" 
                        type="text" 
                        placeholder="Benutzername"
                        :disabled="inputDisabled === 1"/><br/>

                <input v-model="passwordAdmin" 
                        id="passwordAdmin" 
                        class="input" 
                        type="password" 
                        placeholder="Passwort"
                        :disabled="inputDisabled === 1"/><br/>
                <button id="btnLoginAdmin" type="button" @click="login('admin')">Login</button>
            </p>
        </div>
    </div>
</template>
<script>
import Alert from '../components/Alert.vue'

export default {
  name: 'LoginView',
  data(){
      return{
          emailStaff: '',
          passwordStaff: '',
          emailAdmin: '',
          passwordAdmin: '',
          showAlert: false,
          alertMessage: '',
          inputDisabled: 0,
          loginCounter: 0
      } 
  }, 
  setup(){
      
  },
  components:{
      Alert
  }, 
  methods:{
      login(type){
          var succeed = false;
          console.log("login: ", type);
          if(type === "staff"){
              if(this.emailStaff === "staff" && this.passwordStaff === "staff"){
                var roleStaff = "staff";
                this.$store.dispatch('loggedInAs', "staff");
                //sessionStorage.setItem("loggedIn", "staff");
                succeed = true;
              } 
          }else if(type === "admin"){
              if(this.emailAdmin === "admin" && this.passwordAdmin === "admin"){
                var roleAdmin = "admin"
                this.$store.dispatch('loggedInAs', "admin");
                //sessionStorage.setItem("loggedIn", "admin");
                succeed = true;
              } 
          }  
          console.log("loggedIn: ", this.$store.state.loggedInAs);
          if(succeed){
              this.showAlert = false;
              this.$router.push( {path: '/'}  )
          }else{
              this.showAlert = true;
              this.loginCounter += 1;
              if(this.loginCounter >= 3){
                  this.inputDisabled = 1;
              } 
          } 
        
      } 
  } 
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.alert{
    display: flex;
    height: 10%;
    justify-content: center;
    width: auto;
    padding: 1%;
    color: red; 
} 
</style>
