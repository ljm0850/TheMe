<template>
  <div id="default_app">
    <LoginPage v-if="!isLoggedIn" />
    <router-view v-if="isLoggedIn" class="router-view-setting"/>
    <NavBarVue v-if="isLoggedIn" style="max-width 390px"/>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent } from 'vue';
import { useStore } from "vuex";
import LoginPage from '@/components/login/LoginPage.vue';
import NavBarVue from '@/components/navbar/NavBar.vue';
export default defineComponent({
  name: 'App',
  components: {
    LoginPage,
    NavBarVue,
  },
  setup() {
        const store = useStore();
        const isLoggedIn = computed(()=> store.getters.isLoggedIn)

        return {isLoggedIn}
    }
});
</script>

<style lang="scss">
.router-view-setting {
  // 추후에 100vh - 네브바 높이로 하자
  min-height: 95vh;
}
#default_app {
  max-height: 844px;
  max-width: 390px;
    // justify-content: center;
}
</style>
