<template>
    <div class="contentBox">
        <div class="header">
            <slot name="header">
                {{ headerTitle }}
            </slot>
        </div>
        <div class="flex-container">
            <div class="content">
                <slot name="content">
                    Content
                </slot> 
            </div> 
        </div>
    </div>
</template>
<script>
export default {
  name: 'Content',
  props: {
    headerTitle: String
  }
}
</script>
<style scoped>
.header{
    background-color: #a8a9abe8;
    padding: 30px;
    font-size: 43px;
} 
.content{
    padding: 5%;
    background-color: #a8a9abe8;
} 
.flex-container {
  display: flex;
  background-color: #a8a9abe8;
  width: 100%;
  justify-content: center;
}

.flex-container > div {
  background-color: #78a7728a;
  margin: 5%;
  padding: 5%;
  font-size: 30px;
}
</style>