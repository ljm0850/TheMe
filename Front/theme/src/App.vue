<template>
  <div class="default_app">
    <LoginPage v-if="!isLoggedIn" />
    <router-view v-if="isLoggedIn"/>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent } from 'vue';
import { useStore } from "vuex";
import LoginPage from '@/components/login/LoginPage.vue';
export default defineComponent({
  name: 'App',
  components: {
    LoginPage,
  },
  setup() {
        const store = useStore();
        console.log(store.getters.isLoggedIn)
        const isLoggedIn = computed(()=> store.getters.isLoggedIn)

        return {isLoggedIn}
    }
});
</script>

<style lang="scss">
@import "./App.scss"
/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
} */


</style>
