<template>
  <div>
    <div>프로필: {{nickname}}</div>
  </div>
</template>

<script lang="ts">
// import { useStore } from "vuex";
import { useRoute } from 'vue-router'

export default {
  components: {
  },
  setup (){
    const route = useRoute();
    let nickname:string|string[] = route.params.nickname
    
    return {nickname}
  },
};
</script>

<style lang="scss`">


</style>