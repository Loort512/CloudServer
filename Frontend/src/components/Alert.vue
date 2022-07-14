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
    padding: 1%;
    color: red;
    background-color: lightpink;;
    position: fixed;
    width: 100%;
    bottom: 0;
    left: 0;
} 
</style>
