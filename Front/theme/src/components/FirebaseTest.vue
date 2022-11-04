<template>
    <div>
        <input type="file" multiple accept="image/*" @change="fileChange" />
        <!-- <p>
            upload 이미지 :{{file}} ({{file.size}}) / {{file.type}}
          </p> -->
        <div v-if="state.selectFile">{{ state.selectFile }}</div>
        <button @click="test()">test</button>
        <button @click="test2()">이미지 주소 찍어보자</button>
        <hr>
        <button @click="call()">만능 버튼</button>
        <!-- <img v-if="" src="" alt=""> -->
    </div>
</template>

<script lang="ts">
import { reactive } from '@vue/reactivity'
import { useStore } from "vuex";
// import { storage } from '@/store/firebase/firebase';
// import firebase from "firebase/compat/app";
import "firebase/compat/storage"
import "firebase/compat/auth";
import { articleImageUpload, getImageUrl } from "@/store/firebase/firebase"

export default {
    components: {
    },
    setup() {
        const store = useStore();
        const state = reactive({
            selectFile: [],
            previewImgUrl: null
        });

        let file = ''
        const fileChange = (e: any) => {
            console.log(e.target.files)
            file = e.target.files[0];
            state.selectFile = e.target.files
        }
        
        const test = () => {
            const url = articleImageUpload("sss", state.selectFile[0])
            console.log(url)
        }

        const test2 = ()=>{
            getImageUrl('article','test')
        }

        const call = () => {
            console.log("getUserInfoByNickname")
            store.dispatch('getUserInfoByNickname',"결혼한 해커")
        } 

        // return { file, fileChange, state, test, auth_obj}
        return { file, fileChange, state, test,test2, call}
    }
}
</script>

<style lang="scss">

</style>