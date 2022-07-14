<template>
    <div v-if="level === 'success'" class="success">
        <p>{{ msg }}</p>
    </div>
    <div v-if="level !== 'success'" class="alert">
        <p>{{ msg }}</p>
    </div>
</template>
<script>

export default{
    name: 'Alert',
    props:{
        msg: String,
        level: String
    } 
} 
 
</script>
<style scoped>
.alert{
    padding: 1%;
    color: red;
    background-color: lightpink;;
    position: fixed;
    width: 24%;
    bottom: 2%;
    left: 73%;
    white-space: pre-wrap;
} 
.success{
    padding: 1%;
    color: green;
    background-color: lightgreen;
    position: fixed;
    width: 24%;
    bottom: 2%;
    left: 73%;
    white-space: pre-wrap;
} 
</style>
