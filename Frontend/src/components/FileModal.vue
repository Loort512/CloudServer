<template>
   <div class="fileModal">
    <Alert v-if="showAlert" :msg="alertMsg" :level="alertLevel"></Alert>
      <Modal v-if="showModal">
        <template v-slot:modalHeader>
             {{ item.name }}
        </template>
        <template v-slot:modalContent>
            <div class="rename" v-if="openRenameModal">
              <FormInput>
                  <template v-slot:label>
                      <label for="text">File Name:</label>
                  </template>
                  <template v-slot:input>
                      <input type="text" id="username" v-model="item.name">
                  </template>
              </FormInput>
            </div>
            <input type="button" value="Download" v-if="!openRenameModal" @click="downloadItem(item)" >
            <input type="button" value="Delete" v-if="!openRenameModal" @click="deleteItem(item.id)" >
            <input type="button" value="Rename" @click="renameItem(item.id)" >
        </template>
      </Modal>
   </div>
</template>
<script>
// @ is an alias to /src
import axios from 'axios'
import Modal from '@/components/Modal.vue'
import FormInput from '@/components/FormInput.vue'
import Alert from '@/components/Alert.vue'
import {saveAs} from 'file-saver';

export default {
  name: 'FileModal',
  components: {
    Modal,
    FormInput,
    Alert
  },
  data: function(){
    return {
      openRenameModal: false,
      fileUrl: 'http://localhost:8105/api/file',
      alertMsg: "",
      showAlert: false,
      alertLevel: ""
    } 
  } ,
  props: {
    item: Object,
    showModal: Boolean
  } ,
  created(){
  },
  methods:{
    renameItem: function(id) {
      if(this.openRenameModal){
        //modal is already open - perform update Request

        axios.defaults.headers.common['AuthorizationToken'] = `${this.$store.state.token}`;
        var myFile = {
            id : this.item.id,
            name : this.item.name
        } 

        axios.patch(this.fileUrl, myFile)
          .then(response => {
            this.items = response.data;

            this.alertLevel = "success";
            this.alertMsg = "Successfully renamed File"
            this.showAlert = true;

          })
          .catch(e => {
            this.alertLevel = "error";
            this.showAlert = true;
            this.alertMsg = "Failed to update filename!";
          })
        this.openRenameModal = false;
      } else{
        // open Modal
        this.openRenameModal = true;
      } 
      
    } ,
    downloadItem: function (item) {
      var url = this.fileUrl + '/' + item.id + "?download=true"
      axios.get(url,{
         responseType: 'blob',
      } )
        .then(response => {
          var file = new Blob([response.data]);
          saveAs(file, item.name);

          this.alertLevel = "success";
          this.alertMsg = "Successfully downloaded File";
          this.showAlert = true;
        })
        .catch(e => {
          this.alertLevel = "error";
          this.alertMsg = "Cannot download File";
          this.showAlert = true;
        })
    },
    deleteItem: function (id){
      var url = this.fileUrl + "/" + id;
      axios.delete(url).then(response => {
        this.alertLevel = "success";
        this.alertMsg = "Successfully deleted File";
        this.showAlert = true;
      })
      .catch(e => {
        this.alertLevel = "error";
        this.alertMsg = "Cannot delete File";
        this.showAlert = true;
      })
      .finally(() =>{
        this.$emit("reloadItems");
      } )
    } 
  }
}
</script>
<style scoped>
</style>