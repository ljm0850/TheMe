<template>
  <div>
    <div>
        <button v-if="!state.isClicked" class="btn btn-light type-button border" @click="selectSearch(0)">👨‍👦인물</button>
        <div v-if="state.isClicked && state.inputValue==0">
          <div class="d-flex">
            <div class=" logo"  @click="selectSearch(0)"> 👨‍👦 </div>
            <input type="text" class="form-control" id="" placeholder="누구를 찾고 있나요?"  v-model ="state.inputText">
            <button class="btn btn-light type-button border"  @click="getSerchPerson(state.inputText)">검색</button>
          </div>
        </div>
        <button  v-if="!state.isClicked" class="btn btn-light type-button border" @click="selectSearch(1)">🎨테마</button>
        <div v-if="state.isClicked && state.inputValue==1">
          <div class="d-flex">
            <div class=" logo"  @click="selectSearch(1)"> 🎨 </div>
            <input type="text" class="form-control" id="" placeholder="어떤 테마를 찾고 있나요?" v-model ="state.inputText">
            <button class="btn btn-light type-button border" @click="getSerchTheme(state.inputText)">검색</button>
          </div>
        </div>
    </div>
    <br>
    <div class="">
      🔥 현재인기있는 사람들
    </div>
    <br>
    <SearchProfileCardVue />
  </div>
</template>

<script lang="ts">
import SearchProfileCardVue from "@/components/profile/SearchProfileCard.vue"
import { computed } from "@vue/reactivity";
import { reactive } from "vue";
import { useStore } from "vuex";
export default {
  components: {
    SearchProfileCardVue
  },
  setup() {
    const store = useStore();
    const state = reactive({
            isClicked : false,
            inputValue : -1,
            inputText : "",
        });
    const selectSearch = (clickIdx : number) => {
        state.isClicked = !state.isClicked
        state.inputValue = clickIdx
        state.inputText = ""
    }
    const getSerchTheme = (inputText : string) => {
            store.dispatch("searchThemeInfo",inputText)
        }
        const getSerchPerson = (inputText : string) => {
            store.dispatch("searchPersonInfo",inputText)
        }
    const getSerchList = computed(() => store.getters.loginUser)
    return {state,selectSearch,getSerchTheme,getSerchList,getSerchPerson}
  }
}
</script>

<style>
.type-button{
  width: 50%;

}
.logo{
  margin: 0px;
    font-size: 3vh;
      
}
</style>