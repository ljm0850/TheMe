<template>
  <div class="default_app">
    <LoginPage v-if="!isLoggedIn" />
    <!-- <div class="flex"> -->
      <router-view v-if="isLoggedIn" class="test123"/>
      <NavBarVue v-if="isLoggedIn"/>
    <!-- </div> -->

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
.test123 {
  // 추후에 100vh - 네브바 높이로 하자
  min-height: 95vh;
}

</style>
