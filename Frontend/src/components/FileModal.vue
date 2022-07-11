<template>
   <div class="fileModal">
      <Modal v-if="showModal">
        <template v-slot:modalHeader>
             {{ item.name }}
        </template>
        <template v-slot:modalContent>
             <input type="button" value="Download">
             <input type="button" value="Delete">
             <input type="button" value="Rename">
        </template>
      </Modal>
   </div>
</template>
<script>
// @ is an alias to /src
import Modal from '@/components/Modal.vue'
import FormInput from '@/components/FormInput.vue'

export default {
  name: 'FileModal',
  components: {
    Modal,
    FormInput
  },
  props: {
    item: Object,
    showModal: Boolean
  } ,
  created(){
    
  },
  methods:{
    downloadItem: function (Iid) {
      console.log('download item id: ' + Iid)
      var url2 = this.urlDownload + '/' + Iid + "?download=false"
      axios.get(url2)
        .then(response => {
          window.location.assign(url2)
          this.loadItems()
        })
        .catch(e => {
          console.log(e)
        })
    }
  }
}
</script>
<style scoped>
</style>