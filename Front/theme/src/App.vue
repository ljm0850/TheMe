<template>
  <div id="App">
    <div id="default_app">
      <TopNavBarVue />
      <LoginPageVue v-if="!isLoggedIn" />
      <router-view v-if="isLoggedIn" class="router-view-setting"/>
      <BottomNavBarVue v-if="isLoggedIn"/>
    </div>

  </div>
</template>

<script lang="ts">
import LoginPageVue from '@/components/login/LoginPage.vue';
import BottomNavBarVue from '@/components/navbar/BottomNavBar.vue';
import TopNavBarVue from './components/navbar/TopNavBar.vue';
import { computed, defineComponent } from 'vue';
import { useStore } from "vuex";
export default defineComponent({
  name: 'App',
  components: {
    LoginPageVue,
    BottomNavBarVue,
    TopNavBarVue,
  },
  setup() {
    const store = useStore();
    const isLoggedIn = computed(() => store.getters.isLoggedIn)

        return {isLoggedIn}
    }
});
</script>

<style lang="scss">
.router-view-setting {
  // 추후에 100vh - 네브바 높이로 하자
  min-height: 90vh;
  // max-width: 380px !important;
}
#default_app {
  max-height: 844px;
  // max-width: 370px !important;
    // justify-content: center;    
    font-family: 'GangwonEdu_OTFBoldA';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEdu_OTFBoldA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
    
}
@font-face {
    font-family: 'GangwonEdu_OTFBoldA';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEdu_OTFBoldA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
</style>
