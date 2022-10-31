<template>
<div>
  <div>테마 생성 페이지</div>
  <div class="mb-3">
    <input type="text" class="form-control" id="searchTheme" placeholder="원하는 테마를 검색해주세요.">
  </div>
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
// import { useStore } from "vuex";
export default {
  components: {
    AddThemeTextVue
  },
  setup (){
  }
}
</script>

<style lang="scss`">


</style>