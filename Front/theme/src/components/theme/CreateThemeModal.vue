<template>
<div class="modal fade" id="createThemeModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div>이모티콘</div>
        <input type="text" v-model="state.emoticon">
        <div>테마제목</div>
        <input type="text" v-model="state.name">
        <div>테마 설명</div>
        <input type="text" v-model="state.description">
        <div>공개 여부 설정</div>
        <div class="d-flex">
            <button @click="changeType(0)">검색 허용</button>
            <button @click="changeType(1)">친구 공개</button>
            <button @click="changeType(2)">비공개</button>
        </div>
        <button @click="createTheme()">테마 추가</button>

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
  export default {
    components: {
    },
    setup (){
        const state = reactive({
            emoticon: "",
            name: "",
            description: "",
            type: 0,
        })

        const store = useStore();
        const createTheme = ()=>{
            store.dispatch('registTheme',{ emoticon: state.emoticon, name: state.name}
            )
        }

        const changeType = (_numer:number)=>{
            state.type = _numer
        }
        return {state,createTheme,changeType}
    }
  }
  </script>
  
  <style lang="scss">
  
  
  </style>