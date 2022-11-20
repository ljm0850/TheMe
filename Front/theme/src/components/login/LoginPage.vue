<template>
  <div class="container position-center row">
    <div class="row align-items-center">
      <a :href="kakaoLogin">
        <img src="@/assets/image/kakao_login_large_wide.png" alt="kakao Login" class="img-size" />
      </a>
    </div>
  </div>
</template>

<script lang="ts">
import rest from "@/API/rest";
// import { LocationQuery, useRoute } from 'vue-router'
// import { onMounted, } from '@vue/runtime-core';
import { useStore } from "vuex";
export default {
  components: {},
  setup() {
    // const route = useRoute();
    const store = useStore();
    const kakaoLogin = rest.kakao.getAuth();
    let kakao_code = "";
    if (window.location.search) {
      kakao_code = window.location.search.slice(6);
      store.dispatch("kakaoLogin", kakao_code);
    }
    // const kakaoToken = store.dispatch()
    return { kakaoLogin };
  },
};
</script>

<style scoped lang="scss">
@media screen {
  .position-center {
    height: 95vh;
    .img-size {
      width: 370px;
      margin-left: -6px;
    }
  }
}
.row {
  margin-left: -10px;
}
</style>
