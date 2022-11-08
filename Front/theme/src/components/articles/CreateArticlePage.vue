<template>
  <div>
    <div>게시글 등록</div>
    <div>테마 선택</div>
    <input type="text">
    <div>장소 등록</div>
    <input type="text" name="" id="">
    <div> 내용 입력인데 나중에 뜯어 고치자</div>
    <input type="text">

  </div>
</template>

<script lang="ts">
import { reactive } from "vue";
import { useStore } from "vuex";
export default {
  components: {
  },
  setup (){
    const state = reactive({
      theme: 0,
      coordinate_x:0,
      coordinate_y:0,
    })
    const store = useStore();
    const createArticle = ()=>{
      store.dispatch("createArticle",{
        theme:state.theme
      })
    }
    return {createArticle}
  }
}
</script>

<style lang="scss`">


</style>