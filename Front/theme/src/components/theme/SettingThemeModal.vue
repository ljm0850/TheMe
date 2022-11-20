<template>
<div class="modal fade" id="settingThemeModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <h1 class="modal-title fs-5" id="exampleModalLabel">테마 수정</h1>
      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    </div>
    <div class="modal-body">
      <div class="text-style-custom">이모티콘</div>
      <div class="input-group-text d-flex justify-content-center">
        <div v-if="themeDetail.emoticon">{{themeDetail.emoticon}}</div>
        <input type="text" class="form-control input-text" v-if="!themeDetail" v-model="state.emoticon" maxlength="1">
      </div>
      <br>
      <div class="text-style-custom">테마제목</div>
      <div class="input-group-text d-flex justify-content-center">
        <div>{{ themeDetail.name}}</div>
        <input type="text" class="form-control input-text" v-if="!themeDetail" v-model="state.name" maxlength="20">
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
        <button @click="modifyTheme()" class="white-add-button item" data-bs-dismiss="modal">테마 수정</button>
        <button @click="deleteTheme()" class="white-add-button item" data-bs-dismiss="modal">테마 삭제</button>
      </div>
    </div>
  </div>
</div>
</div>
</template>
  
<script lang="ts">
import { reactive } from '@vue/reactivity'
import { useStore } from "vuex";
import { useRoute} from "vue-router";
// import { computed } from '@vue/runtime-core';
export default {
  components: {
  },
  props: {
    themeDetail:Object,
  },
  setup (){
      const state = reactive({
        type: 0,

      })

    const store = useStore();
    const route = useRoute();

    // console.log(route.params.userThemeIdx)

    const modifyTheme = () => {
      store.dispatch('updateUserTheme', { openType: state.type, themeIdx: route.params.userThemeIdx })
    }
    
    const deleteTheme = () => {
        store.dispatch('deleteUserTheme', { themeIdx: route.params.userThemeIdx  })
    }

    const changeType = (_numer:number)=>{
        state.type = _numer
    }
    
    return { state, changeType, modifyTheme,deleteTheme }
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