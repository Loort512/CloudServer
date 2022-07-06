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
