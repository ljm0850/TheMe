<template>
    <div>
        <input type="file" multiple accept="image/*" @change="fileChange" />
        <!-- <p>
            upload 이미지 :{{file}} ({{file.size}}) / {{file.type}}
          </p> -->
        <div v-if="state.selectFile">{{ state.selectFile }}</div>
        <button @click="test()">test</button>
        <button @click="test2()">이미지 주소 찍어보자</button>
        <!-- <img v-if="" src="" alt=""> -->
    </div>
</template>

<script lang="ts">
import { reactive } from '@vue/reactivity'
// import { useStore } from "vuex";
// import { storage } from '@/store/firebase/firebase';
// import firebase from "firebase/compat/app";
import "firebase/compat/storage"
import "firebase/compat/auth";
import { articleImageUpload,testUrl } from "@/store/firebase/firebase"

export default {
    components: {
    },
    setup() {
        const state = reactive({
            selectFile: [],
            previewImgUrl: null
        });
        // const temp = () => {
        //   console.log(state.selectFile)
        // }
        let file = ''
        const fileChange = (e: any) => {
            console.log(e.target.files)
            file = e.target.files[0];
            state.selectFile = e.target.files
        }
        
        const test = () => {
            articleImageUpload("???",state.selectFile[0])
        }

        const test2 = ()=>{
            testUrl()
        }

        // return { file, fileChange, state, test, auth_obj}
        return { file, fileChange, state, test,test2}
    }
}
</script>

<style lang="scss">

</style>