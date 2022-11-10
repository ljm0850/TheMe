<template>
  <div class="nav-header">
    <img src="@/assets/image/logo.png" alt="TheMe" class="img-size"><br>
    <span class="The">The</span>
    <span class="Me">Me</span>
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

<style>
.nav-header{
  width: 390px;
  height: 55px; 
  text-align: center;
}
.The{
  color : #E89A3D
}
.Me{
  color: #61D85B;
}
.img-size {
  height: 20px; 
  width: 40px  !important;
  /* margin: auto; */
  margin-top: 10px;
  /* display: block; */
}


</style>