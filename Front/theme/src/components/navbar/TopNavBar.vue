<template>      
  <div class="nav-header">
<router-link :to="{ name: 'Main' }"> 
    <img src="@/assets/image/logoLeft.png" alt="TheMe" class="img-size">
    <span class="The">The</span>
    <span class="Me">Me</span>
    <img src="@/assets/image/logoRight.png" alt="TheMe" class="img-size">
  </router-link>  
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
  margin-top: 5px;
  margin-bottom: 5px;
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
a {
  text-decoration: none;
}

</style>
