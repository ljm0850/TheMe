<template>
<div id="recommandTheme" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
        <div v-for="item1 in RecommendThemeList1" :key="item1.title" :theme="item1">
          <div class="d-flex">
            <ThemeMiniCardVue />
            <ThemeMiniCardVue />
          </div>
        </div>
        <!-- <div v-for="item2 in RecommendThemeList2" :key="item2.title" :theme="item2" class="d-flex">
        </div> -->
    </div>

    <!-- <div class="carousel-item">
        <div class="d-flex">
          <ThemeMiniCardVue />
          <ThemeMiniCardVue />
        </div>
    </div>
    <div class="carousel-item">
        <div class="d-flex">
          <ThemeMiniCardVue />
          <ThemeMiniCardVue />
        </div>
    </div> -->
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
import { useStore } from "vuex";
export default {
components: {
    ThemeMiniCardVue,
},
  setup() {
    const store = useStore();
    const RecommendThemeList1 = store.getters.getFeedRecommendThemeList1;
    const RecommendThemeList2 = store.getters.getFeedRecommendThemeList2;

    return { RecommendThemeList1, RecommendThemeList2 }


}
}
</script>

<style scoped lang="scss`">


</style>