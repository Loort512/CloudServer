<template>
    <div class="formInput">
        <slot name="fullContent">
            <div class="label">
                <slot name="label">
                
                </slot>
            </div>
            <div class="input">
                <slot name="input">
                    Input
                </slot>
            </div>
        </slot>
    </div>
</template>
<script>
</script>
<style scoped>
.formInput{
    background-color: #939395;
    display: flex;
    margin: -7%;
} 
.label{
    padding: 3%;
} 
.input{
    padding: 2%;
} 
</style>