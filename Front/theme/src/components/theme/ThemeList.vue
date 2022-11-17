<template>
<div>
  <div class="d-flex justify-content-between">
    <div>
      <button :class="{bold:state.isMarked==0}" class = "filter-button" @click="setisMarked(0)">전체 테마</button>
      <button :class="{bold:state.isMarked==1}" class = "filter-button" @click="setisMarked(1)">북마크</button>
    </div>
    <div>
      <button v-show="state.isMarked==0" :class="{bold:state.sort==0}" class="sort-button" @click="setSort(0)">인기순</button>
      <button v-show="state.isMarked==0" :class="{bold:state.sort==1}" class="sort-button" @click="setSort(1)">최신순</button>
    </div>
  </div>
  <div class="d-flex card-list theme-all-card-list">
    <ThemeMiniCardVue page="themeList" v-for="theme in publicThemeList" :key="theme" :theme="theme"/>
  </div>
  <!-- 무한스크롤용 -->
  <ScrollObserverVue @triggerIntersected="loadMore" />
  <br>
  <br>
</div>

</template>

<script lang="ts">
import ThemeMiniCardVue from './ThemeMiniCard.vue';
import ScrollObserverVue from '../ScrollObserver.vue';
import { computed, reactive } from "vue";
import { useStore } from "vuex";
import _ from "lodash"
export default {
  components: {
    ThemeMiniCardVue,
    ScrollObserverVue,
  },
  setup() {
    // 테마 전체 목록 불러오기
    const store = useStore()
    // 초기화
    store.commit("SET_PUBLIC_THEME_LIST", [])

    const state = reactive({
      isMarked: 0,
      sort: 0,
      pageSize: 15,
      pageIdx: 0
    })
    // 클릭했을 때 state 값 변하고 난 후에 다시 리스트 불러오기
    const setisMarked = (clickIdx : number) => {
      state.isMarked = clickIdx
      state.pageIdx = 0
      store.commit("SET_PUBLIC_THEME_LIST", {})
      store.dispatch("getPublicThemeList", state) //스크롤에 의해 작동이 됨
    }
    const setSort = (clickIdx : number) => {
      state.sort = clickIdx
      state.pageIdx = 0
      store.commit("SET_PUBLIC_THEME_LIST", {})
      store.dispatch("getPublicThemeList", state)
    }
    // store.dispatch("getPublicThemeList", state)
    // const publicThemeList = computed(() => store.getters.publicThemeListPart)
    const publicThemeList = computed(() => store.getters.publicThemeList)

    //북마크 하기

    const loadMore = () => {
      if (_.isEmpty(publicThemeList)) {
        return
      }
      store.dispatch("getPublicThemeList", {
        isMarked: state.isMarked,
        sort: state.sort,
        pageSize: state.pageSize,
        pageIdx: state.pageIdx
      })
      state.pageIdx += 1
    }

    return { publicThemeList, setisMarked, setSort, state, loadMore }
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

.bold{
  border : 2px solid #aaa9a9 !important;
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
.theme-all-card-list{
  margin-left: 5px;
  margin-right: 5px;
}
</style>