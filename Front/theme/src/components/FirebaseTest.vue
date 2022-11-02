<template>
    <div>
        <input type="file" multiple accept="image/*" @change="fileChange" />
        <!-- <p>
            upload 이미지 :{{file}} ({{file.size}}) / {{file.type}}
          </p> -->
        <div v-if="state.selectFile">{{ state.selectFile }}</div>
        <button @click="test()">test</button>
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
import { articleImageUpload } from "@/store/firebase/firebase"

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

        // const firebaseConfig = {
        //     apiKey: process.env.VUE_APP_FB_API_KEY,
        //     authDomain: process.env.VUE_APP_FB_AUTH_DOMAIN,
        //     projectId: process.env.VUE_APP_FB_PROJECT_ID,
        //     storageBucket: process.env.VUE_APP_FB_STORAGEBUCKET,
        //     messagingSenderId: process.env.VUE_APP_FB_MESSAGING_SENDER_ID,
        //     appId: process.env.VUE_APP_FB_APP_ID,
        //     measurementId: process.env.VUE_APP_FB_MEASUREMENT_ID
        // };

        // firebase.initializeApp(firebaseConfig);
        // let auth_obj = firebase.auth;
        // let storage_obj = firebase.storage();
        const test = () => {
            // const storageRef = storage_obj.ref("/article");
            // const imagesRef = storageRef.child("test2");
            // console.log(state.selectFile[0])
            // imagesRef.put(state.selectFile[0])
            articleImageUpload("???",state.selectFile[0])
        }
        // return { file, fileChange, state, test, auth_obj}
        return { file, fileChange, state, test,}
    }
}
</script>

<style lang="scss">

</style>