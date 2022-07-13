<template>
    <div class="cloudStore">
        <Content headerTitle="Personal Storage">
          <template v-slot:header>
                Hello Mark
 <br>                Your private Storage
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
  created(){
    
  },
  methods:{
    routeLogin(){
        this.$router.push( {path: '/login'}  )
    } 
  }
}
</script>