<template>
<div>

  <!-- <button type="button" class="btn btn-light">1</button> -->
  <!-- <button type="button" class="btn btn-light create-button">2</button> -->
  <nav class="navbar-position bg-light">
    <div class="d-flex flex-row">
      <router-link :to="{ name: 'Main' }">
        <button type="button" class="btn btn-primary button-size">
          메인
        </button>
      </router-link>  
      
      <router-link :to="{ name: 'Map' }">
        <button type="button" class="button-size">지도</button>
      </router-link>
    
      <button type="button" class="btn btn-primary button-size">+</button>
    
      <router-link :to="{ name: 'Search' }">
        <button type="button" class="btn btn-primary button-size">검색</button>
      </router-link>
    
      <router-link  ter-link :to="{ name: 'Profile', params: { nickname: 'test'} }">
        <button type="button" class="btn btn-primary button-size">프로필</button>
      </router-link>
    </div>
  </nav>
</div>
</template>

<script lang="ts">
export default {
  components: {
  },
  setup (){

    return {}
  }
}
</script>

<style scoped lang="scss">
  .create-button{
    position: absolute;
    top: 90vh;
    left: 195px;
  }
  .navbar-position {
    position: fixed;
        left: 0;
        right: 0;
        bottom: 0;
  }
  .button-size {
    width: 20vw;
    height: 5vh;
    margin: 0;
    border: 0;
    padding: 0;
  }
</style>