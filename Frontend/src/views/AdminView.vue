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
                        <th>Actions</th>
                    </tr>
                    <tr>
                        <th>2</th>
                        <th>Moni</th>
                        <th>Monika</th>
                        <th>Muster</th>
                        <th>
                            <input type="Button" value="Delete">
                            <input type="Button" value="Rename">
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


export default {
  name: 'AdminView',
  components: {
    Content,
    Modal,
    FormInput
  },
  created(){
    
  },
  methods:{
     
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