<template>
  <div class="top-box">
    <button
      v-if="!state.isClicked"
      class="btn btn-light type-button border top-search"
      @click="selectSearch(0)"
    >
      👨‍👦인물
    </button>
    <div v-if="state.isClicked && state.inputValue == 0">
      <div class="d-flex">
        <div class="logo ms-2 me-2" @click="selectSearch(0)">👨‍👦</div>
        <input
          type="text"
          class="form-control"
          id=""
          placeholder="누구를 찾고 있나요?"
          @input="searchUser"
        />
        <button
          class="btn btn-light type-button border btn-size-38 ms-2 me-2"
          @click="getSerchPerson(state.inputPersonText)"
        >
          검색
        </button>
      </div>
    </div>
    <button
      v-if="!state.isClicked"
      class="btn btn-light type-button border btn-size-38 top-search"
      @click="selectSearch(1)"
    >
      🎨테마
    </button>
    <div v-if="state.isClicked && state.inputValue == 1">
      <div class="d-flex">
        <div class="logo ms-2 me-2" @click="selectSearch(1)">🎨</div>
        <input
          type="text"
          class="form-control"
          id=""
          placeholder="어떤 테마를 찾고 있나요?"
          @input="searchTheme"
        />
        <button
          class="btn btn-light type-button border ms-2 me-2"
          @click="getSerchTheme(state.inputThemeText)"
        >
          검색
        </button>
      </div>
    </div>
  </div>

  <div
    v-if="!state.isSerched && state.inputThemeText.length < 1 && state.inputPersonText.length < 1"
    class="popular-title"
  >
    &nbsp; 🔥 현재인기있는 사람들
    <br />
    <SearchMainPageVue v-for="person in getRecommandPersonList" :key="person" :person="person" />
  </div>
  <div v-else-if="state.isSerched && state.inputThemeText.length >= 1">
    <div v-if="getSerchThemeList.length != 0">
      <SearchThemeCardVue v-for="theme in getSerchThemeList" :key="theme" :theme="theme" />
    </div>
    <div v-else class="no-search content-tap">
      검색한 테마가 없는 것 같아요. <br />
      테마 등록 버튼을 통해 만들어볼까요? <br />
      다른 사람들도 함께 참여할 수 있어요! <br />
      <router-link :to="{ name: 'PlusTheme' }">
        <button type="button" class="btn btn-light theme-add-button">&nbsp;테마등록&nbsp;</button>
      </router-link>
    </div>
  </div>
  <div v-else-if="state.isSerched && state.inputPersonText.length >= 1">
    <div v-if="getSerchPersonList.length != 0">
      <SearchProfileCardVue v-for="person in getSerchPersonList" :key="person" :person="person" />
    </div>
    <div v-else class="no-search content-tap">검색한 사람이 없는 것 같아요...</div>
  </div>
  <div v-else-if="!state.isSerched && state.inputThemeText.length >= 1" class="search-theme">
    <div
      v-for="theme in liveSearchTheme"
      :key="theme.name"
      class="search-theme-card"
      @click="getSerchTheme(theme.name)"
    >
      {{ theme.name }}
    </div>
  </div>
  <div v-else-if="!state.isSerched && state.inputPersonText.length >= 1" class="search-person">
    <div
      v-for="person in liveSearchPerson"
      :key="person"
      class="search-person-card"
      @click="getSerchPerson(person)"
    >
      {{ person }}
    </div>
  </div>
  <div v-else class="no-search content-tap">검색어를 입력해주세요!</div>
</template>

<script lang="ts">
import SearchMainPageVue from "@/components/profile/SearchMainPage.vue";
import SearchProfileCardVue from "@/components/profile/SearchProfileCard.vue";
import SearchThemeCardVue from "../theme/SearchThemeCard.vue";
import { computed } from "@vue/reactivity";
import { reactive } from "vue";
import { useStore } from "vuex";
export default {
  components: {
    SearchProfileCardVue,
    SearchMainPageVue,
    SearchThemeCardVue,
  },
  setup() {
    let liveSearchTheme: any;
    let liveSearchPerson: any;

    const store = useStore();
    const state = reactive({
      isClicked: false,
      isSerched: false,
      inputValue: -1,
      inputThemeText: "",
      inputPersonText: "",
    });

    store.dispatch("getRecommendPersonList");
    // 카테고리 선택 시
    const selectSearch = (clickIdx: number) => {
      state.isClicked = !state.isClicked;
      state.inputValue = clickIdx;
      state.inputPersonText = "";
      state.inputThemeText = "";
      state.isSerched = false;
    };
    // 실시간 검색정보
    const searchTheme = (e: any) => {
      state.isSerched = false;
      state.inputThemeText = e.target.value;
      if (state.inputThemeText.length >= 1) {
        store.dispatch("liveSearchTheme", state.inputThemeText);
      } else {
        liveSearchTheme = null;
      }
    };
    const searchUser = (e: any) => {
      state.isSerched = false;
      state.inputPersonText = e.target.value;
      if (state.inputPersonText.length >= 1) {
        store.dispatch("liveSearchPerson", state.inputPersonText);
      } else {
        liveSearchPerson = null;
      }
    };

    // 검색 정보 받아오기
    const getSerchTheme = (inputText: string) => {
      state.isSerched = true;
      state.inputThemeText = inputText;
      store.dispatch("searchThemeInfo", inputText);
    };
    const getSerchPerson = (inputText: string) => {
      // store.commit("getSerchThemeList")
      state.isSerched = true;
      state.inputPersonText = inputText;
      store.dispatch("searchPersonInfo", inputText);
    };
    // 데이터 리턴
    const getSerchThemeList = computed(() => store.getters.searchThemeList);
    const getSerchPersonList = computed(() => store.getters.searchPersonInfo);
    const getRecommandPersonList = computed(() => store.getters.recommandPersonList);

    liveSearchTheme = computed(() => store.getters.liveSearchTheme);
    liveSearchPerson = computed(() => store.getters.liveSearchPerson);

    return {
      state,
      selectSearch,
      getSerchTheme,
      getSerchThemeList,
      getSerchPerson,
      liveSearchPerson,
      searchTheme,
      liveSearchTheme,
      getSerchPersonList,
      searchUser,
      getRecommandPersonList,
    };
  },
};
</script>

<style scoped>
.no-search {
  margin-left: 20px;
}

.top-search {
  margin-left: 12.5px;
}

.btn-size-38 .top-box {
  height: 38px;
}

.search-theme-card,
.search-person-card {
  margin: 4px;
  border: 1px solid;
  border-color: beige;
  font-size: 20px;
}

.search-theme {
  padding-top: 20px;
  padding-left: 30px;
  padding-right: 30px;
  overflow: scroll;
  width: 360px;
  height: 300px;
}

.type-button {
  width: 45%;
}

.logo {
  margin: 0px;
  font-size: 3vh;
  height: 38px;
}
.popular-title {
  margin-top: 10px;
}
.content-tap {
  margin-top: 10px;
  margin-left: 50px;
}
.theme-add-button {
  margin-top: 3px;
  background-color: #fbe9d2;
  color: #e89a3d;
}
</style>
