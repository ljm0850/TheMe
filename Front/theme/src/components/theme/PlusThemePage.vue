<template>
<div>
  <div class="create-theme-page container">
    <br>
    <div>테마 생성 페이지</div>
    <div class="mb-3">
      <input type="text" class="form-control" id="searchTheme" placeholder="원하는 테마를 검색해주세요." :value="state.searchValue" @input="searchTheme">
    </div>
    <!-- 추천 테마 -->
    <div v-if="!isSearchThemeList && !state.searchValue">
      <AddThemeTextVue v-for="theme in recommendList" :key="theme" :theme=theme />
    </div>
    <!-- 검색된 테마 -->
    <AddThemeTextVue v-for="theme in searchThemeList" :key="theme" :theme=theme />
    <br>
    <div>
      <div v-if="state.searchValue.length >= 2 && newArticleFlag">
        <div>{{state.searchValue}}</div>
        <div>검색 결과 해당 테마가 존재하지 않습니다.</div>
        <div>추가 버튼을 눌러 새로운 테마를 만들어 주세요!!</div>
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#createThemeModal" @click="newArticle()">
          추가
        </button>
      </div>
    </div>
    <!-- 생성창 모달 -->
    <CreateThemeModalVue />
    <div>
    </div>
  </div>
</div>
</template>

<script lang="ts">
import AddThemeTextVue from "./AddThemeText.vue"
import CreateThemeModalVue from "./CreateThemeModal.vue";
import { computed, reactive } from "vue";
import { useStore } from "vuex";
import _ from "lodash"
export default {
  components: {
    AddThemeTextVue,
    CreateThemeModalVue,
  },
  setup() {
    const store = useStore()
    const state = reactive({
      searchValue: "",
    })
    const searchTheme = (e:any) => {
      state.searchValue = e.target.value
      if (state.searchValue.length >= 2) {
        console.log(state.searchValue)
        store.dispatch("searchTheme",state.searchValue)
      }
    }
    const isSearchThemeList = computed(()=> store.getters.isSearchThemeList)
    const searchThemeList = computed(() => store.getters.searchThemeList)
    const newArticleFlag = computed(()=> _.isEmpty(store.getters.searchThemeList))
    const newArticle = () => {
      store.dispatch("selectedThemeIdxForCreate", 0)
      store.dispatch("selectedThemeNameForCreate", state.searchValue)
      store.dispatch("selectedThemeEmoticonForCreate", "")
    }
    store.dispatch("getRecommendThemeList")
    store.commit('SET_SEARCH_THEME_LIST',[])
    const recommendList = computed(() => store.getters.getRecommendThemeList)

    return { state, searchTheme, searchThemeList, newArticleFlag, newArticle, recommendList, isSearchThemeList }
  }
}
</script>

<style scoped lang="scss">
.create-theme-page {
  background-color: #CDCDCD;
}
</style>