<template>
<div>

  <!-- <button type="button" class="btn btn-light">1</button> -->
  <!-- <button type="button" class="btn btn-light create-button">2</button> -->
  <nav class="navbar navbar-expand-lg bg-light navbar">
    <div class="container-fluid">
      <router-link :to="{ name: 'Main' }">
        <button type="button" class="btn btn-light">메인</button>
      </router-link>
      
      <router-link :to="{ name: 'Map' }">
      <button type="button" class="btn btn-light">지도</button>
    </router-link>
    
    <button type="button" class="btn btn-light">+</button>
    
    <router-link :to="{ name: 'Search' }">
      <button type="button" class="btn btn-light">검색</button>
    </router-link>
    
    <router-link :to="{ name: 'Profile', params: { nickname: 'test'} }">
      <button type="button" class="btn btn-light">프로필</button>
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
  .navbar {
    position: fixed;
        left: 0;
        right: 0;
        bottom: 0;
  }
</style>