<template>
    <div class="files">
      <FileModal :showModal="showModal" :item="modalItem"></FileModal>
        <div id="fileList">
            <div class="fileItem" v-for="item in items" v-bind:key="item.id" v-on:click="downloadItem(item)">
                <div class="icon" ><img class="folderPic" src="../assets/folder.png"></div>
                <div class="name">{{item.name}}</div>
            </div>
        </div>
    </div>
</template>
<script>
import axios from 'axios'
import FileModal from './FileModal.vue'

export default {
  name: 'FileList',
  components:{
    FileModal
}, 
  data: function () {
    return {
      urlGetAll: 'http://localhost:8105/api/file',
      urlDownload: 'http://localhost:8105/api/file',
      errors: [] ,
      items: [],
      interval: '',
      showModal: false,
      modalItem: Object
    }
  },
  methods: {
    loadItems: function () {
      axios.defaults.headers.common['Authorization'] = `Bearer TOKEN`;
      axios.get(this.urlGetAll)
        .then(response => {
          console.log(response)
          this.items = response.data
        })
        .catch(e => {
          console.log(e)
          this.errors.push(e)
        })
    },
    todo: function () {
      const self = this
      this.intervalid1 = setInterval(function () {
        console.log('update File list')
        self.loadItems()
      }, 3000)
    },
    downloadItem: function (item) {
      if(this.showModal){
        this.modalItem ={} ;
        this.showModal = false;
      } else{
        this.modalItem = item;
        this.showModal = true;
      } 
    }
  },
  mounted: function () {
    // this.todo()
    this.loadItems();
    axios.defaults.headers.common['Authorization'] = `Bearer TOKEN`;
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
