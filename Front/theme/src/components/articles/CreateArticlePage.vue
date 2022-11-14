<template>
  <div>
    <form action="">
      <h1 class="" style="text-align: center">게시글 등록</h1>
      <div class="create-page container">
        <br />
        <div class="title-text-custom">테마 선택</div>
        <div class="input-group mb-3">
          <!-- <label class="input-group-text">테마 선택</label> -->
          <select
            class="form-select"
            id="inputGroupSelect01"
            v-model="state.theme"
          >
            <option selected>테마 선택</option>
            <!-- 현재 유저 테마의 idx가 선택되어 있음 -->
            <option v-for="theme in themeList" :key="theme" :value="theme.idx">
              {{ theme.name }}
            </option>
          </select>
        </div>
        <br />
        <!-- 이미지 파일 첨부 -->
        <input type="file" multiple accept="image/*" @change="fileChange" />
        <PreviewImageVue v-if="isSelectFile" />
        <br />
        <!-- 지도 -->
        <ArticleMapVue />
        <!-- 내용 -->
        <div class="mb-3">
          <label for="exampleFormControlTextarea1" class="form-label"
            >내용</label
          >
          <textarea
            class="form-control"
            id="exampleFormControlTextarea1"
            rows="3"
            v-model="state.description"
          ></textarea>
        </div>
        <button @click.prevent="createArticle()">게시물 등록</button>
      </div>
    </form>
    {{ themeList }}
  </div>
</template>

<script lang="ts">
import { computed, reactive } from "vue";
import { useStore } from "vuex";
import "firebase/compat/storage";
import "firebase/compat/auth";
import ArticleMapVue from "../map/ArticleMap.vue";
import PreviewImageVue from "./PreviewImage.vue";
import { articleImageUpload } from "@/store/firebase/firebase";
import _ from "lodash";
export default {
  components: {
    ArticleMapVue,
    PreviewImageVue,
  },
  setup() {
    const state: any = reactive({
      theme: 0,
      coordinate_x: 0,
      coordinate_y: 0,
      selectFile: {},
      previewImgUrl: null,
      searchValue: "",
      description: "",
    });
    
    const isSelectFile = computed(() => !_.isEmpty(state.selectFile));
    const imageUrls: string[] = [];
    const store = useStore();
    const createArticle = async () => {
      for (let i = 0; i < state.selectFile.length; i++) {
        const url = articleImageUpload(
          `${state.selectFile[i].name}`,
          state.selectFile[i]
        );
        imageUrls.push(url);
      }
      store.dispatch("createArticle", {
        description: state.description,
        pictures: imageUrls,
        themeIdx: state.theme,
      });
    };
    store.dispatch("getMyThemeList");
    const themeList = computed(() => store.getters.selectedUserThemeList);
    let body;
    const fileChange = (e: any) => {
      // console.log(e.target.files)
      state.selectFile = e.target.files;
      body = document.querySelector("#previewImg");
      // 기존에 올린 이미지 제거
      while (body?.firstChild) {
        body.firstChild.remove();
      }

      for (let i = 0; i < state.selectFile.length; i++) {
        let reader = new FileReader();
        reader.onload = (e: any) => {
          createPreview(e.target?.result, i);
        };
        reader.readAsDataURL(state.selectFile[i]);
      }
    };

    const createPreview = (_img: string, idx: number) => {
      body = document.querySelector("#previewImg");
      const newDiv = document.createElement("div");
      if (idx === 0) {
        newDiv.className = "carousel-item active";
      } else {
        newDiv.className = "carousel-item";
      }
      const newImg = document.createElement("img");
      newImg.src = _img;
      newImg.className = "preview-img-size";
      newDiv.appendChild(newImg);
      body?.appendChild(newDiv);
    };

    // 공용테마에서 글쓰러 왔을 때
    const themeDetail = computed(() => store.getters.selectedThemeforArticle);

    const setTheme = () => {
      if (themeDetail.value != null) {
        state.theme = themeDetail.value.idx;
      }
    };

    setTheme();

    console.log(state.theme);

    return {
      state,
      createArticle,
      themeList,
      fileChange,
      createPreview,
      isSelectFile,
    };
  },
};
</script>

<style scoped lang="scss">
.title-text-custom {
  text-align: center;
  font-size: 24px;
}
.preview-img-size {
  width: 360px;
  height: 360px;
}
</style>
