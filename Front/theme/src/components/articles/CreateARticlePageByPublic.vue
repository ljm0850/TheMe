<template>
  <div>
        <!-- <form action=""> -->
            <div class="create-page container"  style="background-color: #fbe9d2;">
                <br>
                <div class="title-text-custom" style="margin-top: -10px; ">테마 선택</div>
                <div class="input-group mb-3">
                    <!-- <label class="input-group-text">테마 선택</label> -->
                    <select class="form-select" id="inputGroupSelect01" v-model="state.theme" disabled>
                        <option selected>테마 선택</option>
                        <!-- 현재 유저 테마의 idx가 선택되어 있음 -->
                        <option v-for="theme in themeList" :key="theme"
                            :value="{ 'public': theme.themeIdx, 'user': theme.idx }">
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
                    <label for="exampleFormControlTextarea1" class="form-label">내용</label>
                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"
                        v-model="state.description"></textarea>
                </div>
                <button v-if="checkCreate" @click.prevent="createArticle()" class="btn btn-outline-secondary white-add-button">등록</button>
                <div v-else class="btn btn-outline-secondary block-button"  style="margin-bottom: 10px;">등록</div>
            </div>
        <!-- </form> -->
        <br>
        <br>
        <br>
    </div>
</template>

<script lang="ts">
import { computed, onMounted, reactive } from "vue";
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
            // theme: 0,
            theme: { public: 0, user: 0 },
            coordinate_x: 0,
            coordinate_y: 0,
            selectFile: [],
            previewImgUrl: null,
            searchValue: "",
            description: "",
            createFlag: false
        });

        const isSelectFile = computed(() => !_.isEmpty(state.selectFile));
        const imageUrls: string[] = [];
        const store = useStore();
        const createArticle = async () => {
            if (state.createFlag){
                return
            }
            for (let i = 0; i < state.selectFile.length; i++) {
                const url = articleImageUpload(
                    `${state.selectFile[i].name}`,
                    state.selectFile[i]
                );
                imageUrls.push(url);
            }
            // console.log("올라갈 사진 url", imageUrls)
            store.dispatch("createArticle", {
                description: state.description,
                pictures: imageUrls,
                themeIdx: state.theme.user,
                publicThemeIdx: state.theme.public
            });
        };
        store.dispatch("getMyThemeList");
        const themeList = computed(() => store.getters.selectedUserThemeList);

        const dataURLtoFile = (dataurl:any, fileName:any) => {
      let arr = dataurl.split(','),
          mime = arr[0].match(/:(.*?);/)[1],
          bstr = atob(arr[1]), 
          n = bstr.length, 
          u8arr = new Uint8Array(n);   
      while(n--){
          u8arr[n] = bstr.charCodeAt(n);
      }
      return new File([u8arr], fileName, {type:mime});
    }
    
    // 리사이징
    const getThumbFile = (_IMG:any,_name:string)=>{
      const canvas = document.createElement("canvas")
      canvas.width = 360;
      canvas.height = 360;
      canvas.getContext("2d")?.drawImage(_IMG,0,0,360,360)
      const dataUrl = canvas.toDataURL("image/png");
      const tmpThumbFile = dataURLtoFile(dataUrl,_name)
      return {file:tmpThumbFile, url:dataUrl}
    }

    let body;
    const removeImg = ()=>{
      body = document.querySelector("#previewImg");
      // 기존에 올린 이미지 제거
      while (body?.firstChild) {
        body.firstChild.remove();
      }
    }
    onMounted(()=>{
        removeImg()
    })
    const fileChange = (e: any) => {
      const imageList = e.target.files
      state.selectFile = e.target.files;
      removeImg()
      const tempList:any[] = [];
      for (let i = 0; i < imageList.length; i++) {
        let reader = new FileReader();
        reader.onload = () => {
          const img = new Image;
          img.onload = () =>{
            const thumbFileObj = getThumbFile(img,imageList[i].name);
            const thumbFile = thumbFileObj.file
            const url = thumbFileObj.url
            createPreview(url, i);
            tempList.push(thumbFile)
          }
          if (typeof(reader.result)=="string"){
            img.src = reader.result;
          }
        };
        reader.readAsDataURL(imageList[i]);
      }
      state.selectFile = tempList
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
        const userThemeIdx = computed(() => store.getters.selectedUserThemeIdx)
        const setTheme = () => {
            if (themeDetail.value != null) {
                state.theme.public = themeDetail.value.idx;
            }
            state.theme.user = userThemeIdx
        };

        setTheme();
        const checkCreate = computed(()=> state.description && !_.isEmpty(state.selectFile) && state.theme.user && state.theme.public)
        // console.log(state.theme);

        return {
            state,
            createArticle,
            themeList,
            fileChange,
            createPreview,
            isSelectFile,
            checkCreate
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
.modal-dialog{
  margin-left: 10px;
  margin-right: 10px;
}
</style>
