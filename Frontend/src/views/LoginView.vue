<template>
    <div class="login">
        <Alert v-if="showAlert" msg="Login Credentials Incorrect"></Alert>
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
          password: ''
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
        axios.get('http://localhost:8105/api/user/login?username=fff&password=abc')
        .then(response => {
            console.log(response);
            // handle success
            //commit('SET_LOADED_TODOS', response.data.results)
        })
        .catch(error => {
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
