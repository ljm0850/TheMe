<template>
<div>
  <div>
    <button>인물</button>
    <button>테마</button>
  </div>
  <div>
    현재인기있는 사람들
  </div>
  <MiniProfileCardVue />
</div>
</template>

<script lang="ts">
import MiniProfileCardVue from "@/components/profile/MiniProfileCard.vue"
// import { useStore } from "vuex";
export default {
  components: {
    MiniProfileCardVue
  },
  setup (){
  }
}
</script>

<style lang="scss">


</style>