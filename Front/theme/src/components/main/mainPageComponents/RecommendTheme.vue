<template>
<div id="recommandTheme" class="carousel slide" data-bs-ride="carousel" data-bs-interval="10000">
  <div class="carousel-inner">
    <div class="carousel-item active">
        <div class="d-flex justify-content-around">
          <ThemeMiniCardVue :theme="recommendThemeList1[0]" />
          <ThemeMiniCardVue :theme="recommendThemeList2[0]" />
        </div>
    </div>

    <div class="carousel-item" v-for="idx in listIdx" :key="idx">
      <div class="d-flex justify-content-around">
        <ThemeMiniCardVue :theme="recommendThemeList1[idx]"/>
        <ThemeMiniCardVue :theme="recommendThemeList2[idx]"/>
      </div>
    </div>

  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#recommandTheme" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#recommandTheme" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
</template>
  
<script lang="ts">
import ThemeMiniCardVue from '@/components/theme/ThemeMiniCard.vue';
import { computed } from '@vue/runtime-core';
import { useStore } from "vuex";
export default {
components: {
    ThemeMiniCardVue,
},
  setup() {
    const store = useStore();
    const recommendThemeList1 = computed(()=>store.getters.getFeedRecommendThemeList1);
    const recommendThemeList2 = computed(()=>store.getters.getFeedRecommendThemeList2);
    let listIdx = []
    // 이부분 바꿔야 할 가능성 높은듯
    for (let i = 1; i < store.getters.getFeedRecommendThemeList2.length; i++){
      listIdx.push(i)
    }

    return { recommendThemeList1, recommendThemeList2,listIdx }


}
}
</script>

<style scoped lang="scss`">


</style>