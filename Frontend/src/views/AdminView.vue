<template>
    <div class="adminView">
        <Content headerTitle="Admin View">
          <template v-slot:header>
                User Management
            </template>
            <template v-slot:content>
                <Modal v-if="false">
                    <template v-slot:modalHeader>
                        Mark
                    </template>
                    <template v-slot:modalContent>
                        <FormInput>
                            <template v-slot:label>
                                <label for="text">Username:</label>
                            </template>
                            <template v-slot:input>
                                <input type="text" value="Mark123">
                            </template>
                        </FormInput>
                        <FormInput>
                            <template v-slot:label>
                                <label for="text">First Name:</label>
                            </template>
                            <template v-slot:input>
                                <input type="text" value="Mark">
                            </template>
                        </FormInput>
                        <FormInput>
                            <template v-slot:label>
                                <label for="text">Last Name:</label>
                            </template>
                            <template v-slot:input>
                                <input type="text" value="Muster">
                            </template>
                        </FormInput>
                        <br><input type="Button" value="Rename">
                    </template>
                </Modal>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Admin</th>
                        <th>Actions</th>
                    </tr>
                    <tr v-for="user in users" >
                        <th>{{user.id}}</th>
                        <th>{{user.username}}</th>
                        <th>{{user.firstName}}</th>
                        <th>{{user.lastName}}</th>
                        <th>{{user.admin}}</th>
                        <th>
                            <input type="Button" value="Delete" @click="deleteUser(user.id)" >
                        </th>
                    </tr>
                </table>
            </template>
        </Content>
    </div>
</template>
<script>
import Content from '@/components/Content.vue'
import Modal from '@/components/Modal.vue'
import FormInput from '@/components/FormInput.vue'
import axios from 'axios'


export default {
  name: 'AdminView',
  components: {
    Content,
    Modal,
    FormInput
  },
  data(){
    return{
        userURL: 'http://localhost:8105/api/user/',
        users: []  
    } 
  } ,
  created(){
    axios.defaults.headers.common['AuthorizationToken'] = this.$store.state.token;

    this.loadAllUsers();
  },
  methods:{
    loadAllUsers(){    
        axios.get(this.userURL)
            .then(response => {
            this.users = response.data;
            })
            .catch(error => {
                // handle error
                console.log("login error:",error)
            })   
    } ,
    deleteUser(userId){
        axios.delete(this.userURL + userId)
            .then(response => {
            this.loadAllUsers();
            })
            .catch(error => {
                // handle error
                console.log("login error:",error)
            })  
    } 
  }
}
</script>
<style scoped>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>