<template>
  <div class="upload">
      <table class="table">
          <tr>
          <td><input type="file" name="file" id='doUpload' /></td>
          </tr>
          <tr>
          <td></td>
          <td><input type="submit" v-on:click='doUpload()' value="Upload" id="doUpload"/></td>
          </tr>
      </table>
      <!-- <div class="waiting" v-if="waiting" :style='this.waitingInfoStyle'>waiting</div> -->
  </div>
</template>
<script>
import axios from 'axios'

export default {
  name: 'Upload',
  data: function () {
    return {
      url: 'http://localhost:8105/api/file',
      errors: 'no error',
      items: []
    }
  },
  methods: {
    doUpload: function () {
      console.log('doUpload')
      var formData = new FormData()
      var uploadFile = document.getElementById('doUpload').files[0]
      formData.append('file', uploadFile)
      // this.activateWaiting('waiting for upload!')
      // Vue.$status = 3
      var headers = {
        'Content-Type': 'multipart/form-data',
        'Centent-Length': formData.length,
        'token': this.$store.state.token
      }
      axios.post(this.url, formData, headers)
        .then(function (response) {
          console.log(response)
          // vVue.$status = 1
          // this.deactivateWaiting()
        })
        .catch(function () {
          // vVue.$status = 2
          // vVue.$statusText = 'SERVER NOT REACHABLE'
        })
    },
    updateView: function () {
      console.log('updateView!')
      if (this.waitingError) {
        this.waitingError()
      } else {
        this.deactivateWaiting()
      }
    }
  }
}
</script>
<style scoped>
.waiting{
  margin-inline: 52%;
  position: absolute;
  height: 27%;
  width: 7%;
  background: #849f87;
  float: right;
  font-size: x-large;

}
</style>
