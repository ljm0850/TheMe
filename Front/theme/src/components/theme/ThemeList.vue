<template>
<div>
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
    <ThemeMiniCardVue :theme="temp"/>
    <ThemeMiniCardVue :theme="temp"/>
  </div>
</div>

</template>

<script lang="ts">
import ThemeMiniCardVue from './ThemeMiniCard.vue';
// import { useStore } from "vuex";
export default {
  components: {
    ThemeMiniCardVue
  },
  setup() {
    const temp = { title: "하드코딩" }
    return {temp}
  }
}
</script>

<style lang="scss`">


</style>