<template>
    <div class="cloudStore">
        <Content headerTitle="Personal Storage">
          <template v-slot:header>
                Hello Mark
 <br>                Your private Storage
            </template>
            <template v-slot:content>
                <FileList/>
                <Upload/>
            </template>
        </Content>
    </div>
</template>
<script>
import Content from '@/components/Content.vue'
import FileList from '@/components/FileList.vue'
import Upload from '@/components/Upload.vue'

export default {
  name: 'StoreView',
  components: {
    Content,
    FileList,
    Upload
  },
  created(){
    
  },
  methods:{
    routeLogin(){
        this.$router.push( {path: '/login'}  )
    } 
  }
}
</script>