<template>
  <div class="d-flex justify-content-between">
    <div>
      <button>전체 테마</button>
      <button>북마크</button>
    </div>
    <div>
      <button>인기순</button>
      <button>최신순</button>
    </div>
  </div>
  <div class="d-flex">
    <ThemeTextCardVue />
    <ThemeTextCardVue />
  </div>

</template>

<script lang="ts">
import ThemeTextCardVue from '../theme/ThemeTextCard.vue';
// import { useStore } from "vuex";
export default {
  components: {
    ThemeTextCardVue
  },
  setup (){
  }
}
</script>

<style lang="scss`">


</style>