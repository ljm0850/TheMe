<template>
  <div class="theme-page">
    <div class="create-page container" style="background-color: #fbe9d2;">
      <br>
      <div style="text-align: center; margin-top: -10px; font-size:large; margin-bottom: 5px;" >테마 생성 페이지</div>
      <div style="text-align: center; font-size:small;" >사람들이 작성하고 있는 테마들을 확인해보세요</div>
      <div style="text-align: center; font-size:small;" >이미 나만의 테마라면 '보유중'</div>
      <div style="text-align: center; font-size:small;" >'추가' 버튼을 눌러 나만의 테마에 등록해주세요</div>
      <div style="text-align: center; font-size:small; margin-bottom: 10px;" >검색 결과가 없다면 새로운 테마를 '생성'해주세요</div>
      <div class="mb-3">
        <input type="text" class="form-control" id="searchTheme" placeholder="원하는 테마를 검색해주세요."
          :value="state.searchValue" @input="searchTheme" maxlength="20">
        <div></div>
      </div>
      <!-- 추천 테마 -->
      <div v-if="!isSearchThemeList && !state.searchValue">
        <div style="text-align: center; margin-top: 0px; margin-bottom: 10px;">✦ 현재 인기 있는 테마들 ✦</div>
        <AddThemeTextVue v-for="theme in recommendList" :key="theme" :theme=theme />
      </div>
      <!-- 검색된 테마 -->
      <AddThemeTextVue v-for="theme in liveSearchTheme" :key="theme" :theme=theme />
      <br>
      <div>
        <div style="text-align: center;" v-if="state.searchValue.length >= 2 && newArticleFlag">
          <div style="text-align: center; margin-top: -20px;">{{ state.searchValue }}</div>
          <div style="text-align: center;">검색 결과 해당 테마가 존재하지 않습니다.</div>
          <div style="text-align: center;">생성 버튼을 눌러 새로운 테마를 만들어 주세요!!</div>
          <button type="button" class="btn btn-outline-secondary white-add-button" style="margin-top: 10px; margin-bottom: 10px;" data-bs-toggle="modal" data-bs-target="#createThemeModal"
            @click="newArticle()">
            생성
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
    const searchTheme = (e: any) => {
      state.searchValue = e.target.value
      if (state.searchValue.length >= 2) {
        // store.dispatch("searchTheme", state.searchValue)
        store.dispatch("liveSearchTheme", state.searchValue)
      }
    }
    const isSearchThemeList = computed(() => store.getters.isSearchThemeList)
    const liveSearchTheme = computed(() => store.getters.liveSearchTheme)
    const newArticleFlag = computed(() => _.isEmpty(store.getters.liveSearchTheme))
    const newArticle = () => {
      store.dispatch("selectedThemeIdxForCreate", 0)
      store.dispatch("selectedThemeNameForCreate", state.searchValue)
      store.dispatch("selectedThemeEmoticonForCreate", "")
    }
    store.dispatch("getRecommendThemeList") //추천 리스트 받아오기
    store.commit('SET_SEARCH_THEME_LIST', []) // 컴포넌트 로딩시 
    store.dispatch('myUserTheme') // selected 유저를 나로 만듬 => 보유 테마 리스트 확보용
    store.commit('LIVE_SEARCH_THEME_LIST',[]) // 검색 기록 없애서 추천 테마 보기 위함


    const recommendList = computed(() => store.getters.getRecommendThemeList)
    // console.log(store.getters.loginUer)



    return { state, searchTheme, liveSearchTheme, newArticleFlag, newArticle, recommendList, isSearchThemeList }
  }
}
</script>

<style scoped lang="scss">
.theme-page{
  margin-left: 10px;
  margin-right: 10px;
}

</style>