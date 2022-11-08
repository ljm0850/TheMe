<template>
<div>
  <div class="d-flex justify-content-between">
    <div>
      <button class="filter-button">전체 테마</button>
      <button class="filter-button">북마크</button>
    </div>
    <div>
      <button class="sort-button">인기순</button>
      <button class="sort-button">최신순</button>
    </div>
  </div>
  <div class="d-flex card-list">
    <ThemeMiniCardVue :theme="temp"/>
    <ThemeMiniCardVue :theme="temp"/>
    <ThemeMiniCardVue :theme="temp"/>
    <ThemeMiniCardVue :theme="temp"/>
    <ThemeMiniCardVue :theme="temp"/>
    <ThemeMiniCardVue :theme="temp"/>
    <ThemeMiniCardVue :theme="temp"/>
    <ThemeMiniCardVue :theme="temp"/>
    <ThemeMiniCardVue :theme="temp"/>
    <ThemeMiniCardVue :theme="temp"/>
    <ThemeMiniCardVue :theme="temp"/>
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
    const temp = { 
      title: "우선 테마 제목",
      emoticon: "☕",
      partipation: "8"
    }
    return {temp}
  }
}
</script>

<style lang="scss">
.card-list{
  display: flex;
  flex-wrap: wrap;
}

.filter-button {
  margin-top: 10px;
  width: 85px;
  height: 40px;
  font-weight: 400;
  font-size: 14px;
  background: rgba(255, 255, 255, 0.8);
  border: 1px solid #C7C7C7;
}

.sort-button {
  margin-top: 10px;
  margin-bottom: 10px;
  width: 70px;
  height: 35px;
  font-weight: 400;
  font-size: 13px;
  background: rgba(255, 255, 255, 0.8);
  border: 1px solid #C7C7C7;
}

</style>