<template>
  <div class="container position-center row">
    <div class="row align-items-center">
      <div @click="kakao_auth()">
        <img src="@/assets/image/kakao_login_large_wide.png" alt="kakao Login">
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { useStore } from "vuex";
export default {
  components: {
  },
  setup (){
    const store = useStore();
    const kakao_auth = ()=>{
      store.dispatch("kakaoLogin")
    } 
    return {kakao_auth}

  }
}
</script>

<style lang="scss">
@media screen {
  .position-center {
    height: 95vh;
  }
}

</style>