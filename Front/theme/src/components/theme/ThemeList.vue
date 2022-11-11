<template>
<div>
  <div class="d-flex justify-content-between">
    <div>
      <button class="filter-button" @click="setisMarked(0)">전체 테마</button>
      <button class="filter-button" @click="setisMarked(1)">북마크</button>
    </div>
    <div>
      <button class="sort-button" @click="setSort(0)">인기순</button>
      <button class="sort-button" @click="setSort(1)">최신순</button>
    </div>
  </div>
  <div class="d-flex card-list">
    <ThemeMiniCardVue v-for="theme in publicThemeList" :key="theme" :theme="theme"/>
  </div>
</div>

</template>

<script lang="ts">
import ThemeMiniCardVue from './ThemeMiniCard.vue';
import { computed, reactive } from "vue";
import { useStore } from "vuex";
export default {
  components: {
    ThemeMiniCardVue
  },
  setup() {
    // 테마 전체 목록 불러오기
    const store = useStore()
    store.commit("SET_PUBLIC_THEME_LIST",[])
    const state = reactive({
      isMarked: 0,
      sort: 0,
      pageSize: 15,
      pageIdx: 0
    })
    // 클릭했을 때 state 값 변하고 난 후에 다시 리스트 불러오기
    const setisMarked = (clickIdx : number) => {
        state.isMarked = clickIdx
        store.dispatch("getPublicThemeList", state)
    }
    const setSort = (clickIdx : number) => {
        state.sort = clickIdx
        store.dispatch("getPublicThemeList", state)
    }
    store.dispatch("getPublicThemeList", state)
    const publicThemeList = computed(() => store.getters.publicThemeList)

    //북마크 하기

    return {publicThemeList, setisMarked, setSort}
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