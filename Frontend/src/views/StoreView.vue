<template>
    <div class="cloudStore">
        <Content headerTitle="Personal Storage">
          <template v-slot:header>              
                Your private Storage
            </template>
            <template v-slot:content>
                <FileList/>
            </template>
        </Content>
    </div>
</template>
<script>
import Content from '@/components/Content.vue'
import FileList from '@/components/FileList.vue'

export default {
  name: 'StoreView',
  components: {
    Content,
    FileList
  },
  data(){
    return{
      alertMessage: "hijpalökadhödölksdasd",
      showAlert: true
    } 
  } ,
  created(){
    
  },
  methods:{
    routeLogin(){
        this.$router.push( {path: '/login'}  )
    } 
  }
}
</script>