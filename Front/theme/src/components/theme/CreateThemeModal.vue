<template>
<div class="modal fade" id="createThemeModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <h1 class="modal-title fs-5" id="exampleModalLabel">테마 생성</h1>
      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    </div>
    <div class="modal-body">
      <div class="text-style-custom">이모티콘</div>
      <div class="input-group-text d-flex justify-content-center">
        <div v-if="emoticon">{{emoticon}}</div>
        <input type="text" class="form-control input-text" v-if="!emoticon" v-model="state.emoticon" maxlength="1">
      </div>
      <br>
      <div class="text-style-custom">테마제목</div>
      <div class="input-group-text d-flex justify-content-center">
        <div v-if="themeName">{{themeName}}</div>
        <input type="text" class="form-control input-text" v-if="!themeName" v-model="state.name" maxlength="20">
      </div>
      <br>
      <div class="text-style-custom">공개 여부 설정</div>
      <div class="d-flex justify-content-around">
          <button @click="changeType(0)" class="white-add-button">공개</button>
          <button @click="changeType(1)" class="white-add-button">팔로우</button>
          <button @click="changeType(2)" class="white-add-button">비공개</button>
      </div>
      
    </div>
    <div class="modal-footer">
      <div class="d-flex justify-content-center">
        <button v-if="emoticon" @click="createTheme()" class="white-add-button item" data-bs-dismiss="modal">테마 추가</button>
        <button v-if="!emoticon" @click="registTheme()" class="white-add-button item" data-bs-dismiss="modal">테마 추가</button>
      </div>
      <button type="button" class="white-add-button item" data-bs-dismiss="modal">Close</button>
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
        type: 0,

      })

    const store = useStore();
    
    const createTheme = () => {
      store.dispatch('createUserTheme', { openType: state.type, challenge: false })
    }
    const registTheme = () => {
        store.dispatch('registTheme', { 
          openType: state.type,
          emoticon: state.emoticon,
          challenge: false,
        })
    }
    
    const isThemeIdx = computed(() => store.getters.isSelectedThemeIdxForCreate)
    const themeIdx = computed(() => store.getters.selectedThemeIdxForCreate)
    const themeName = computed(() => store.getters.selectedThemeNameForCreate)
    const emoticon = computed(()=>store.getters.selectedThemeEmoticonForCreate)
    const changeType = (_numer:number)=>{
        state.type = _numer
    }
    
    return { state, changeType, themeIdx, themeName, emoticon, isThemeIdx, registTheme,createTheme }
  }
}
</script>
  
<style scoped lang="scss">
.text-style-custom{
  text-align: center;
  font-size: large;
  margin: 5px;
}
.input-text {
  text-align: center;
}
</style>