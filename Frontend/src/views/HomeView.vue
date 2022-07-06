<template>
  <div class="home">
    <div class="header">
      <router-link v-if="this.$store.state.loggedInAs === 'admin'" to="/student" >Student</router-link> |  
      <router-link v-if="this.$store.state.loggedInAs === 'admin'" to="/staff" >Staff</router-link><br>
      <router-link v-if="this.$store.state.loggedInAs === 'staff'" to="/courses" >Courses</router-link>
    </div>
    <img alt="Vue logo" src="../assets/background.jpeg">
    <HelloWorld msg="Welcome to your Management System"/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld
  },
  created(){
    console.log("created")
    this.$store.state.loggedInAs === "admin"
    console.log("login as: ", this.$store.state.loggedInAs )
  } 
}
</script>
