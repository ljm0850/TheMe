<template>
  <div>
    <RecommandThemeVue />
    <NewFeedVue />
  </div>
    
</template>

<script lang="ts">
import RecommandThemeVue from './mainPageComponents/RecommendTheme.vue';
import NewFeedVue from './mainPageComponents/NewFeed.vue' 
// import { useStore } from "vuex";
export default {
  components: {
    RecommandThemeVue,
    NewFeedVue,
  },
  setup (){
  }
}
</script>

<style scoped lang="scss`">


</style>