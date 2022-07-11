<template>
    <div class="alert">
        <p>{{ msg }}</p>
    </div>
</template>
<script>

export default{
    name: 'Alert',
    props:{
        msg: String
    } 
}
 
</script>
<style scoped>
.alert{
    display: flex;
    height: 10%;
    justify-content: center;
    width: auto;
    padding: 1%;
    color: red; 
    background-color: lightpink;
} 
</style>
