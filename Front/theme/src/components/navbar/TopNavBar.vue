<template>
  <div class="nav-header">
    <img src="@/assets/image/logoLeft.png" alt="TheMe" class="img-size">
    <span class="The">The</span>
    <span class="Me">Me</span>
    <img src="@/assets/image/logoRight.png" alt="TheMe" class="img-size">
  </div>
    
</template>

<script lang="ts">
// import { useStore } from "vuex";
export default {
  components: {
  },
  setup (){
  }
}
</script>

<style scoped>
.nav-header{
  width: 390px;
  height: 55px; 
  text-align: center;
  padding-top: 10px;
  /* margin-top: 10px; */
}
.The{
  color : #E89A3D
}
span{
  font-size: 30px;
}
.Me{
  
  color: #61D85B;
}
.img-size {
  height: 30px; 
  width: 30px  !important;
  margin-bottom: 5px;
  /* margin: auto; */
  /* margin-top: 10px; */
  /* display: block; */
}


</style>
