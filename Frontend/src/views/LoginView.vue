<template>
    <div class="login">
        <Alert v-if="showAlert" :msg="alertMessage"></Alert>
        <Content headerTitle="Welcome to your Private Cloud">
            <template v-slot:header>
                Login
            </template>
            <template v-slot:content>
                <FormInput>
                    <template v-slot:label>
                        <label for="text">Username:</label>
                    </template>
                    <template v-slot:input>
                        <input type="text" id="username" v-model="username">
                    </template>
                </FormInput>
                <FormInput>
                    <template v-slot:label>
                        <label for="text">Password:</label>
                    </template>
                    <template v-slot:input>
                        <input type="password" id="password" v-model="password" >
                    </template>
                </FormInput>
                <div v-if="showRegisterForm" class="registerAdditionalForms">
                    <FormInput>
                        <template v-slot:label>
                            <label for="text">First Name:</label>
                        </template>
                        <template v-slot:input>
                            <input type="text" id="firstName" v-model="firstName" >
                        </template>
                    </FormInput>
                    <FormInput>
                        <template v-slot:label>
                            <label for="text">Last Name:</label>
                        </template>
                        <template v-slot:input>
                            <input type="text" id="lastName" v-model="lastName" >
                        </template>
                    </FormInput>
                </div>

                <input type="button" value="Login" v-if="!this.showRegisterForm" @click="login()" >
                <input type="button" value="Register" @click="register()" >
            </template>
    </Content>
    </div>
</template>
<script>
import Alert from '../components/Alert.vue'
import Content from '@/components/Content.vue'
import FormInput from '@/components/FormInput.vue'
import axios from 'axios'

export default {
  name: 'LoginView',
  data(){
      return{
          username: '',
          password: '',
          firstName: '',
          lastName: '',
          alertMessage: '',
          showAlert: false,
          showRegisterForm: false
      } 
  }, 
  setup(){
      
  },
  components:{
      Alert,
      Content,
      FormInput
  }, 
  methods:{
    login(){
        var data = {
            username: this.username,
            password: this.password
        }  
        console.log("data: ", data);
        axios.get('http://localhost:8105/api/user/login?username='+data.username+'&password='+data.password+"&firstName")
        .then(response => {
            console.log("login Response: ", response);
            this.$store.dispatch('addToken', response.data.token);
            this.$store.dispatch('addAdmin', response.data.admin);
            axios.defaults.headers.common['AuthorizationToken'] = this.$store.state.token;
            this.$router.push( {path: '/cloudStore'}  )
        })
        .catch(error => {
            // handle error
            console.log("login error:",error)
            this.showAlert = true;
            this.alertMessage = "Wrong credentials";
        })
    } ,
    register() {
        console.log("showRegisterForm: ",this.showRegisterForm)
        if(!this.showRegisterForm){
            this.showRegisterForm = true;
            return;
        } 

        var data = {
            username: this.username,
            password: this.password,
            firstName: this.firstName,
            lastName: this.lastName
        }
        console.log("register data: ", data);
        axios.put('http://localhost:8105/api/user/register', data)
        .then(response => {
            console.log(response);
            this.showAlert = true;
            this.alertMessage = "Registration complete. Please login";
            // handle success
            //commit('SET_LOADED_TODOS', response.data.results)
        })
        .catch(error => {
            console.log("error: ", error);
            this.showAlert = true;
            this.alertMessage = "Registration error\n";
            this.alertMessage += error.response.data;
            // handle error
            console.log(error)
        })
        .finally(() => {
             this.showRegisterForm = false;
        } )
    } 
  } 
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.login{
    
} 
input[type="button"] {
    margin: 13%;
} 
</style>
