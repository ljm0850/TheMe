<template>
  <div>
    <form action="">
      <div>게시글 등록</div>
      <div>테마 선택</div>
      <div class="input-group mb-3">
        <!-- <label class="input-group-text">테마 선택</label> -->
        <select class="form-select" id="inputGroupSelect01">
          <option selected>테마 선택</option>
          <!-- 현재 유저 테마의 idx가 선택되어 있음 -->
          <option v-for="theme in themeList" :key="theme" :value="theme.idx">{{ theme.name }} 지금 이름값이 안넘어와서</option>
        </select>
      </div>
      <!-- <div>장소 등록</div>
      <div class="input-group mb-3">
        <label class="input-group-text">장소 등록</label>
        <input class="form-control" type="text" placeholder="장소등록" aria-label="default input example" v-model="state.searchValue">
      </div> -->
      <!-- 지도 -->
      <ArticleMapVue />
      <!-- 내용 -->
      <div class="mb-3">
        <label for="exampleFormControlTextarea1" class="form-label">내용</label>
        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
      </div>
      <input type="file" multiple accept="image/*" @change="fileChange" />
    </form>
    {{ themeList }}
  </div>
</template>

<script lang="ts">
import { computed, reactive } from "vue";
import { useStore } from "vuex";
import "firebase/compat/storage"
import "firebase/compat/auth";
import ArticleMapVue from "../map/ArticleMap.vue";
// import { articleImageUpload, getImageUrl } from "@/store/firebase/firebase"
export default {
  components: {
    ArticleMapVue,
  },
  setup (){
    const state = reactive({
      theme: 0,
      coordinate_x:0,
      coordinate_y: 0,
      selectFile: [],
      previewImgUrl: null,
      searchValue : "",
    })
    const store = useStore();
    const createArticle = ()=>{
      store.dispatch("createArticle",{
        theme:state.theme
      })
    }
    store.dispatch("getMyThemeList")
    const themeList = computed(() => store.getters.selectedUserThemeList)

    const fileChange = (e: any) => {
      console.log(e.target.files)
      state.selectFile = e.target.files
    }
    return { state,createArticle, themeList,fileChange }
  }
}
</script>

<style lang="scss`">


</style>