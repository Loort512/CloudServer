<template>
   <div class="fileModal">
    <!-- <Alert v-if="showAlert" :msg="alertMessage"></Alert> -->
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
      showAlert: false
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
            console.log("File loading: ", response)
            //this.item.name = "renamed";
            this.items = response.data
          })
          .catch(e => {
            this.showAlert = true;
            this.alertMsg = "Failed to update filename!";
            console.log(e)
          })
        this.openRenameModal = false;
      } else{
        // open Modal
        this.openRenameModal = true;
      } 
      
    } ,
    downloadItem: function (item) {
      console.log('download item: ' + item)
      var url = this.fileUrl + '/' + item.id + "?download=true"
      axios.get(url)
        .then(response => {
          console.log("downloadItem response: ", response);
          var file = new Blob([response.data], {type: "image/jpeg"});
          saveAs(file, item.name);

          // var blob = new Blob([response.data], {type: "image/jpeg"});
          // var link = document.createElement("a");
          // link.href = window.URL.createObjectURL(blob);
          // link.download = "myFileName.pdf";
          // link.click();
          //this.loadItems()
        })
        .catch(e => {
          console.log(e)
        })
    },
    deleteItem: function (id){
      console.log("delete item ", id);
      var url = this.fileUrl + "/" + id;
      axios.delete(url).then(response => {
        console.log("response: ", response);
      })
      .catch(e => {
        console.log("Loeschen fehlgeschlagen", e);
      })
      .finally(() =>{
        console.log("emit reload items");
        this.$emit("reloadItems");
      } )
    } 
  }
}
</script>
<style scoped>
</style>