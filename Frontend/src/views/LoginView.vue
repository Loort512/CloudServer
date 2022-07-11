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
                <input type="button" value="Login" @click="login()" >
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
          alertMessage: '',
          showAlert: false
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
        axios.get('http://localhost:8105/api/user/login?username='+data.username+'&password='+data.password)
        .then(response => {
            console.log("login Response: ", response);
            this.$store.dispatch('addToken', response);
            axios.defaults.headers.common['Authorization'] = `Bearer TOKEN`;
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
        var data = {
            username: this.username,
            password: this.password
        }
        axios.post('http://localhost:8105/api/user/register', data)
        .then(response => {
            console.log(response);
            this.showAlert = true;
            this.alertMessage = "Registration complete. Please login";
            // handle success
            //commit('SET_LOADED_TODOS', response.data.results)
        })
        .catch(error => {
            this.showAlert = true;
            this.alertMessage = "Registration error";
            // handle error
            console.log(error)
        })
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
