<template>
<div id="recommandTheme" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
        <div class="d-flex">
            <ThemeTextCardVue />
            <ThemeTextCardVue />
        </div>
    </div>
    <div class="carousel-item">
        <div class="d-flex">
            <ThemeTextCardVue />
            <ThemeTextCardVue />
        </div>
    </div>
    <div class="carousel-item">
        <div class="d-flex">
            <ThemeTextCardVue />
            <ThemeTextCardVue />
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
import ThemeTextCardVue from '@/components/theme/ThemeTextCard.vue';
// import { useStore } from "vuex";
export default {
components: {
    ThemeTextCardVue,
},
setup (){
}
}
</script>

<style scoped lang="scss`">


</style>