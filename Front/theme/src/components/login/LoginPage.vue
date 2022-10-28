<template>
  <div class="container position-center row">
    <div class="row align-items-center">
      <a href="https://kauth.kakao.com/oauth/authorize?client_id=bab0a08f8b68900521759c285635e38a&redirect_uri=http://localhost:8080&response_type=code">
        <img src="@/assets/image/kakao_login_large_wide.png" alt="kakao Login" class="img-size">
      </a>

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
    .img-size{
      width: 370px;
    }
  }
}

</style>