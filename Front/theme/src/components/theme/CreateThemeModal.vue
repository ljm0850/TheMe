<template>
<div class="modal fade" id="createThemeModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    </div>
    <div class="modal-body">
      {{ isThemeIdx }}
      <div>이모티콘</div>
      <span class="input-group-text" v-if="emoticon">{{emoticon}}</span>
      <input type="text" class="form-control" v-if="!emoticon" v-model="state.emoticon">
      <div>테마제목</div>
      <span class="input-group-text" v-if="themeName">{{themeName}}</span>
      <input type="text" class="form-control" v-if="!themeName" v-model="state.name">

      <div>테마 설명</div>
      <input type="text" class="form-control" v-model="state.description">
      <div>공개 여부 설정</div>
      <div class="d-flex">
          <button @click="changeType(0)">검색 허용</button>
          <button @click="changeType(1)">팔로우 공개</button>
          <button @click="changeType(2)">비공개</button>
      </div>
      <button @click="registTheme()">테마 추가</button>
      <button @click="test()">test</button>

    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
    </div>
  </div>
</div>
</div>
</template>
  
<script lang="ts">
import { reactive } from '@vue/reactivity'
import { useStore } from "vuex";
import { computed } from '@vue/runtime-core';
export default {
  components: {
  },
  setup (){
      const state = reactive({
        emoticon: "",
        name: "",
        description: "",
        type: 0,
        challenge: true,
        createTime: new Date(),

      })

    // {
    //   "challenge": true,
    //     "createTime": "2022-11-07T04:55:45.963Z",
    //       "description": "string",
    //         "modifyTime": "2022-11-07T04:55:45.963Z",
    //           "openType": 0,
    //             "themeIdx": 0,
    //               "userIdx": 0
    // }


    const store = useStore();
    
    const createTheme = () => {
    }
    const registTheme = () => {
      store.dispatch('registTheme',{ emoticon: state.emoticon, name: state.name})
    }
    
    const isThemeIdx = computed(() => store.getters.isSelectedThemeIdxForCreate)
    const themeIdx = computed(() => store.getters.selectedThemeIdxForCreate)
    const themeName = computed(() => store.getters.selectedThemeNameForCreate)
    const emoticon = computed(()=>store.getters.selectedThemeEmoticonForCreate)
    const changeType = (_numer:number)=>{
        state.type = _numer
          console.log(state.type)
    }
    const test = () => {
      console.log(new Date())
    }
    return { state, createTheme, changeType, themeIdx, themeName, emoticon, isThemeIdx, registTheme,test }
  }
}
</script>
  
<style lang="scss">
  
  
  </style>