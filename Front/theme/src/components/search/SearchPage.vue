<template>
  <div class="top-box">
    <button
      v-if="!state.isClicked"
      class="btn btn-light type-button border top-search"
      @click="selectSearch(0)"
    >
      π¨βπ¦μΈλ¬Ό
    </button>
    <div v-if="state.isClicked && state.inputValue == 0">
      <div class="d-flex">
        <div class="logo ms-2 me-2" @click="selectSearch(0)">π¨βπ¦</div>
        <input
          type="text"
          class="form-control"
          id=""
          placeholder="λκ΅¬λ₯Ό μ°Ύκ³  μλμ?"
          @input="searchUser"
        />
        <button
          class="btn btn-light type-button border btn-size-38 ms-2 me-2"
          @click="getSerchPerson(state.inputPersonText)"
        >
          κ²μ
        </button>
      </div>
    </div>
    <button
      v-if="!state.isClicked"
      class="btn btn-light type-button border btn-size-38 top-search"
      @click="selectSearch(1)"
    >
      π¨νλ§
    </button>
    <div v-if="state.isClicked && state.inputValue == 1">
      <div class="d-flex">
        <div class="logo ms-2 me-2" @click="selectSearch(1)">π¨</div>
        <input
          type="text"
          class="form-control"
          id=""
          placeholder="μ΄λ€ νλ§λ₯Ό μ°Ύκ³  μλμ?"
          @input="searchTheme"
        />
        <button
          class="btn btn-light type-button border ms-2 me-2"
          @click="getSerchTheme(state.inputThemeText)"
        >
          κ²μ
        </button>
      </div>
    </div>
  </div>

  <div
    v-if="!state.isSerched && state.inputThemeText.length < 1 && state.inputPersonText.length < 1"
    class="popular-title"
  >
    &nbsp; π₯ νμ¬μΈκΈ°μλ μ¬λλ€
    <br />
    <SearchMainPageVue v-for="person in getRecommandPersonList" :key="person" :person="person" />
  </div>
  <div v-else-if="state.isSerched && state.inputThemeText.length >= 1">
    <div v-if="getSerchThemeList.length != 0">
      <SearchThemeCardVue v-for="theme in getSerchThemeList" :key="theme" :theme="theme" />
    </div>
    <div v-else class="no-search content-tap">
      κ²μν νλ§κ° μλ κ² κ°μμ. <br />
      νλ§ λ±λ‘ λ²νΌμ ν΅ν΄ λ§λ€μ΄λ³ΌκΉμ? <br />
      λ€λ₯Έ μ¬λλ€λ ν¨κ» μ°Έμ¬ν  μ μμ΄μ! <br />
      <router-link :to="{ name: 'PlusTheme' }">
        <button type="button" class="btn btn-light theme-add-button">&nbsp;νλ§λ±λ‘&nbsp;</button>
      </router-link>
    </div>
  </div>
  <div v-else-if="state.isSerched && state.inputPersonText.length >= 1">
    <div v-if="getSerchPersonList.length != 0">
      <SearchProfileCardVue v-for="person in getSerchPersonList" :key="person" :person="person" />
    </div>
    <div v-else class="no-search content-tap">κ²μν μ¬λμ΄ μλ κ² κ°μμ...</div>
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
  <div v-else class="no-search content-tap">κ²μμ΄λ₯Ό μλ ₯ν΄μ£ΌμΈμ!</div>
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
    // μΉ΄νκ³ λ¦¬ μ ν μ
    const selectSearch = (clickIdx: number) => {
      state.isClicked = !state.isClicked;
      state.inputValue = clickIdx;
      state.inputPersonText = "";
      state.inputThemeText = "";
      state.isSerched = false;
    };
    // μ€μκ° κ²μμ λ³΄
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

    // κ²μ μ λ³΄ λ°μμ€κΈ°
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
    // λ°μ΄ν° λ¦¬ν΄
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
  height: 38px !important;
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
