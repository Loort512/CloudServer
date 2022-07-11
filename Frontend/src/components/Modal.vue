<template>
    <div class="modal">
        <div class="modalHeader">
            <slot name="modalHeader">
                Modal Header
            </slot>
        </div>
        <div class="modalContent">
            <slot name="modalContent">
                Modal Content
            </slot>
        </div>
    </div>
</template>
<script>
</script>
<style scoped>
.modal {
    background-color: lightpink;
    border: solid;
} 
.modalHeader{
    
} 
.modalContent{
    font-size: 67%;
    padding: 5%;
} 
</style>