<template>
    <div class="files">
      <Alert v-if="showAlert" :msg="alertMsg" :level="alertLevel"></Alert>
      <FileModal :showModal="showModal" :item="modalItem" @reloadItems="loadItems" ></FileModal>
        <div id="fileList">
            <div class="fileItem" v-for="item in items" v-bind:key="item.id" v-on:click="downloadItem(item)">
                <div class="icon" ><img class="folderPic" src="../assets/folder.png"></div>
                <div class="name">{{item.name}}</div>
            </div>
        </div>
        <table class="table">
          <tr>
          <td><input type="file" name="file" id='doUpload' /></td>
          </tr>
          <tr>
          <td></td>
          <td><input type="submit" v-on:click='doUpload()' value="Upload" id="doUpload"/></td>
          </tr>
      </table>
    </div>
</template>
<script>
import axios from 'axios'
import FileModal from './FileModal.vue'
import Alert from './Alert.vue'

export default {
  name: 'FileList',
  components:{
    FileModal,
    Alert
}, 
  data: function () {
    return {
      urlGetAll: 'http://localhost:8105/api/file',
      urlDownload: 'http://localhost:8105/api/file',
      url: 'http://localhost:8105/api/file',
      errors: [] ,
      items: [],
      interval: '',
      showModal: false,
      modalItem: Object,
      showAlert: false,
      alertMsg: "",
      alertLevel: ""
    }
  },
  methods: {
    loadItems: function () {
      this.showModal = false;
      axios.defaults.headers.common['AuthorizationToken'] = `${this.$store.state.token}`;
      axios.get(this.urlGetAll)
        .then(response => {
          this.items = response.data
        })
        .catch(e => {
          this.errors.push(e)
          this.alertMsg = "Cannot load Files from Server - please login first";
          this.showAlert = true;
        })
    },
    downloadItem: function (item) {
      if(this.showModal){
        this.modalItem ={} ;
        this.showModal = false;
      } else{
        this.modalItem = item;
        this.showModal = true;
      } 
    },
    doUpload: function () {
      var formData = new FormData()
      var uploadFile = document.getElementById('doUpload').files[0]
      formData.append('file', uploadFile)

      var headers = {
        'Content-Type': 'multipart/form-data',
        'Centent-Length': formData.length,
        'token': this.$store.state.token
      }
      axios.post(this.url, formData, headers)
        .then(function (response) {
        })
        .catch(e => {
          this.alertLevel = "error";
          this.alertMsg = "Cannot upload File";
          this.showAlert = true;
        })
        .finally(() => {
          this.loadItems();
        } )
    }
  },
  created() {
    axios.defaults.headers.common['AuthorizationToken'] = `${this.$store.state.token}`;
    this.loadItems();
  } 
}
</script>
<style scoped>
.fileItem {
    margin:1%;
    padding:1%;
    float:left;
  }
  .fileItem:hover {
    background: #ff8f00;
  }
  .folderPic {
    height: 28px;
  }
  .fileItem.name{
    display:table-cell;
  }
  .upload{
    background-color: darkgrey;
  }
  .table{
    display:inline-block;
  }
</style>
