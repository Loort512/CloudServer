<template>
  <div class="home">
    <div class="header">
      <!-- <router-link v-if="this.$store.state.loggedInAs === 'admin'" to="/student" >Student</router-link> |  
      <router-link v-if="this.$store.state.loggedInAs === 'admin'" to="/staff" >Staff</router-link><br>
      <router-link v-if="this.$store.state.loggedInAs === 'staff'" to="/courses" >Courses</router-link> -->
    </div>
    <Content headerTitle="Welcome to your Private Cloud">
      <template v-slot:content>
        <img alt="Vue logo" src="../assets/privacy-background-image.jpeg">
        <p>
          Store your data in a personal and private Cloud 
          <br><br>
          Start by logging in 
        </p>
        <input type="button" value="Login" @click="this.$router.push( {path: '/login'}  )" >
      </template>
    </Content>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import Content from '@/components/Content.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld,
    Content
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
