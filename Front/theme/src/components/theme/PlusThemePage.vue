<template>
<div>
  <div>테마 생성 페이지</div>
  <div class="mb-3">
    <input type="text" class="form-control" id="searchTheme" placeholder="원하는 테마를 검색해주세요." v-model="state.searchValue" @input="test">
  </div>
  <button @click="test">테스트 버튼</button>
  <AddThemeTextVue />
  <div>
    <div>검색 결과 해당 테마가 존재하지 않습니다.</div>
    <div>추가 버튼을 눌러 새로운 테마를 만들어 주세요!!</div>
    <button>추가</button>
  </div>

</div>
</template>

<script lang="ts">
import AddThemeTextVue from "./AddThemeText.vue"
import { reactive } from "vue";
// import { useStore } from "vuex";
export default {
  components: {
    AddThemeTextVue
  },
  setup() {
    const state = reactive({
      searchValue : ""
    })

    const test = () => {
      
    }


    return {state,test}
  }
}
</script>

<style lang="scss`">


</style>