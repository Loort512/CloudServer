<template>
  <nav class="nav">
    <router-link to="/">Home</router-link> |
    <router-link to="/login">Login</router-link><br>
  </nav>
  <div class="content">
    <router-view/>
  </div>
  
</template>

<script>

export default{
  data(){
    return{
    } 
  },
  created(){
    // create Test Data
    // this.$store.dispatch('addStudent',{id: '2', firstName: 'Max', lastName: 'Muster', dob: '10.10.1995', gender: 'm', department: 'HTW', emailID: 'max.muster@yahoo.de'} );
    // this.$store.dispatch('addStudent',{id: '3', firstName: 'Moni', lastName: 'Muster', dob: '10.11.1995', gender: 'm', department: 'HTW', emailID: 'moni.muster@yahoo.de'} );
    // this.$store.dispatch('addStudent',{id: '4', firstName: 'Franz', lastName: 'Muster', dob: '10.12.1995', gender: 'm', department: 'HTW', emailID: 'franz.muster@yahoo.de'} );
    // this.$store.dispatch('addStudent',{id: '5', firstName: 'Tom', lastName: 'Muster', dob: '10.10.1997', gender: 'm', department: 'HTW', emailID: 'tom.muster@yahoo.de'} );
    // this.$store.dispatch('addStudent',{id: '6', firstName: 'Henrike', lastName: 'Muster', dob: '10.10.1999', gender: 'w', department: 'HTW', emailID: 'he.muster@yahoo.de'} );
    // this.$store.dispatch('addStudent',{id: '7', firstName: 'Laura', lastName: 'Muster', dob: '10.10.1990', gender: 'w', department: 'HTW', emailID: 'laura.muster@yahoo.de'} );

    // this.$store.dispatch('addStaff',{id: '2', firstName: 'Max1', lastName: 'Muster', dob: '10.10.1989', gender: 'm', department: 'HTW', emailID: 'max.muster@yahoo.de'} );
    // this.$store.dispatch('addStaff',{id: '3', firstName: 'Moni1', lastName: 'Muster', dob: '09.05.1995', gender: 'm', department: 'HTW', emailID: 'moni.muster@yahoo.de'} );
    // this.$store.dispatch('addStaff',{id: '4', firstName: 'Franz1', lastName: 'Muster', dob: '11.04.1995', gender: 'm', department: 'HTW', emailID: 'franz.muster@yahoo.de'} );
    // this.$store.dispatch('addStaff',{id: '5', firstName: 'Tom1', lastName: 'Muster', dob: '10.06.1995', gender: 'm', department: 'HTW', emailID: 'tom.muster@yahoo.de'} );
    // this.$store.dispatch('addStaff',{id: '6', firstName: 'Henrike1', lastName: 'Muster', dob: '10.08.1995', gender: 'w', department: 'HTW', emailID: 'he.muster@yahoo.de'} );
    // this.$store.dispatch('addStaff',{id: '7', firstName: 'Laura1', lastName: 'Muster', dob: '10.10.1995', gender: 'w', department: 'HTW', emailID: 'laura.muster@yahoo.de'} );

  }  

} 
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 5%;
  background: #3c8733e8;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #8103c8;
}

body{
  background-image: url('assets/background.jpeg');
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;

}  

input[type=button]{
  height: 20pt;
  width: 54pt;
} 

button{
  height: 20pt;
  width: 54pt;
} 

table{
  min-width: -moz-available;
} 
</style>
