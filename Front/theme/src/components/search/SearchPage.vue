<template>
    <div class="top-box">
        <button v-if="!state.isClicked" class="btn btn-light type-button border" @click="selectSearch(0)">👨‍👦인물</button>
        <div v-if="state.isClicked && state.inputValue==0">
          <div class="d-flex">
            <div class=" logo"  @click="selectSearch(0)"> 👨‍👦 </div>
            <input type="text" class="form-control" id="" placeholder="누구를 찾고 있나요?"    @input="searchUser">
            <button class="btn btn-light type-button border"  @click="getSerchPerson(state.inputPersonText)">검색</button>
          </div>
        </div>
        <button  v-if="!state.isClicked" class="btn btn-light type-button border" @click="selectSearch(1)">🎨테마</button>
        <div v-if="state.isClicked && state.inputValue==1">
          <div class="d-flex">
            <div class=" logo"  @click="selectSearch(1)"> 🎨 </div>
            <input type="text" class="form-control" id="" placeholder="어떤 테마를 찾고 있나요?"   @input="searchTheme">
            <button class="btn btn-light type-button border" @click="getSerchTheme(state.inputThemeText)">검색</button>
          </div>
        </div>
    </div>
    <br>
    <div v-if="!state.isSerched && (state.inputThemeText.length < 1 && state.inputPersonText.length < 1)" >
      &nbsp; 🔥 현재인기있는 사람들
      <br>
      <SearchProfileCardVue v-for="person in getRecommandPersonList" :key="person" :person="person" />
    </div>
    <div v-else-if="state.isSerched && state.inputThemeText.length >= 1">
      <SearchThemeCardVue v-for="theme in getSerchThemeList" :key="theme" :theme="theme" />
    </div>
    <div v-else-if="state.isSerched && state.inputPersonText.length >= 1 ">
      <SearchProfileCardVue v-for="person in getSerchPersonList" :key="person" :person="person" />
    </div>
    <div v-else-if="!state.isSerched && state.inputThemeText.length >= 1" class="search-theme">
      <div v-for="theme in liveSearchTheme" :key="theme" class="search-theme-card" @click="getSerchTheme(theme)">
        {{theme}}
      </div>
    </div>
    <div v-else-if="!state.isSerched && state.inputPersonText.length >= 1 "  class="search-person">
      <div v-for="person in liveSearchPerson" :key="person" class="search-person-card" @click="getSerchPerson(person)">
        {{person}}
      </div>
    </div>
</template>

<script lang="ts">
import SearchProfileCardVue from "@/components/profile/SearchProfileCard.vue"
import SearchThemeCardVue from '../theme/SearchThemeCard.vue';
import { computed } from "@vue/reactivity";
import { reactive } from "vue";
import { useStore } from "vuex";
export default {
  components: {
    SearchProfileCardVue,
    SearchThemeCardVue
  },
  setup() {
    
    let liveSearchTheme :any;
    let liveSearchPerson :any;
    const store = useStore();
    const state = reactive({
      isClicked : false,
      isSerched : false,
      inputValue : -1,
      inputThemeText : "",
      inputPersonText : "",
    });
    store.dispatch("getRecommendPersonList")
    // 카테고리 선택 시
    const selectSearch = (clickIdx : number) => {
      state.isClicked = !state.isClicked
      state.inputValue = clickIdx
      state.inputPersonText = ""
      state.inputThemeText = ""
      state.isSerched = false
    }
    // 실시간 검색정보
    const searchTheme = (e:any) => {
      state.isSerched = false
      state.inputThemeText = e.target.value
      if (state.inputThemeText.length >= 1 ) {
        store.dispatch("liveSearchTheme",state.inputThemeText)
      }else{
        liveSearchTheme = null
      }
    }
    const searchUser = (e:any) => {
      state.isSerched = false
      state.inputPersonText = e.target.value
      if (state.inputPersonText.length >= 1 ) {
        store.dispatch("liveSearchPerson",state.inputPersonText)
      }else{
        liveSearchPerson = null
      }
    }

    // 검색 정보 받아오기
    const getSerchTheme = (inputText : string) => {
      state.isSerched = true
      state.inputThemeText = inputText
      store.dispatch("searchThemeInfo",inputText)
    }
    const getSerchPerson = (inputText : string) => {
      // store.commit("getSerchThemeList")
      state.isSerched = true
      state.inputPersonText = inputText
      store.dispatch("searchPersonInfo",inputText)
    }
    // 데이터 리턴
    const getSerchThemeList = computed(() => store.getters.searchThemeList)
    const getSerchPersonList = computed(() => store.getters.searchPersonInfo)
    const getRecommandPersonList = computed(() => store.getters.recommandPersonList)
    
    liveSearchTheme = computed(() => store.getters.liveSearchTheme)
    liveSearchPerson = computed(() => store.getters.liveSearchPerson)
    
    return {state,selectSearch,getSerchTheme,getSerchThemeList,getSerchPerson,liveSearchPerson,searchTheme,liveSearchTheme,getSerchPersonList,searchUser,getRecommandPersonList,}
  }
}
</script>

<style>
.top-box{
  height: 38px;
}
.search-theme-card{
  margin: 4px;
  border: 1px solid;
  border-color: beige;
  font-size: 20px;
}
.search-theme{
padding-top: 20px;
padding-left:30px;
padding-right: 30px;
overflow:scroll; 
width:390px; 
height:300px;
}
.type-button{
width: 50%;

}
.logo{
margin: 0px;
  font-size: 3vh;
  height: 38px;
}
</style>