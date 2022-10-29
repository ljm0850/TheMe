<template>
  <div class="container position-center row">
    <div class="row align-items-center">
      <a :href="kakaoLogin">
        <img src="@/assets/image/kakao_login_large_wide.png" alt="kakao Login" class="img-size">
      </a>
      <!-- <button @click="test()">asdf</button> -->
    </div>
  </div>
  
</template>

<script lang="ts">
import rest from '@/API/rest';
// import { LocationQuery, useRoute } from 'vue-router'
// import { onMounted, } from '@vue/runtime-core';
// import { useStore } from "vuex";
export default {
  components: {
  },
  setup (){
    // const route = useRoute();
    const kakaoLogin = rest.kakao.getAuth()
    let kakao_token = ""
    if (window.location.search){
      kakao_token = window.location.search.slice(6)
      console.log(kakao_token)
    }
    // let code: LocationQuery|undefined = route.query
    // console.log(code)
    // const test = () => {
    //   let testaet = route.query
    //   console.log(testaet)
    // }

    return {kakaoLogin}
  }
}
</script>

<style lang="scss">
@media screen {
  .position-center {
    height: 95vh;
    .img-size{
      width: 370px;
    }
  }
}

</style>