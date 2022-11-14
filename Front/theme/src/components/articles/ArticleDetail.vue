<template>
    <div class="card custom-card d-flex justify-content-center">
        <div class="card-body" v-if="article">
            <div class="theme-name">{{article.themeName}}</div>
            <div class="d-flex justify-content-between">
                <div class="d-flex">
                    <img :src="article.profile" class="profile-img"/>
                    <div>{{article.nickname}}</div>
                </div>
                <div>3년전</div>
            </div>
            <!-- 사진 -->
            <ArticleImageVue :pictures="tempPicture" :carouselId ="article.nickname"/>
            <!-- 사진 끝 -->
            <div class="d-flex justify-content-between">
                <div class="d-flex">
                    <div>❤ 1111</div>
                    <div>🧾 2222</div>
                </div>
                <div class="d-flex">
                    <div>❗</div>
                    <div>❌</div>
                    <div>❓</div>
                </div>
            </div>
            <span @click="displayComment()" v-if="!state.commentFlag">더보기</span>
            <!-- 댓글 -->
            <CommentListVue v-if="state.commentFlag"/>
            <!-- 댓글 끝 -->
        </div>
    </div>
</template>

<script lang="ts">
import { reactive } from '@vue/reactivity'
import ArticleImageVue from "./ArticleImage.vue"
import CommentListVue from "./comment/CommentList.vue"
// import { useStore } from "vuex";
export default {
    props: {
      article:Object
    },
    components: {
        ArticleImageVue,
        CommentListVue,
    },
    setup(props:any) {
        const state = reactive({
            commentFlag : false,
        })
        const displayComment = ()=>{
            state.commentFlag = true;
        }
        console.log(props.article)

        const tempPicture = [
            "https://firebasestorage.googleapis.com/v0/b/theme-b8677.appspot.com/o/article%2F1536724809274.jpg%2B112?alt=media&token=e84fd445-0c53-4e69-80cb-8e091b4846d7",
            "https://firebasestorage.googleapis.com/v0/b/theme-b8677.appspot.com/o/article%2F20191230_133301.jpg%2B111?alt=media&token=349c40b1-32c2-4f52-993b-979149a7b7a8",
            "https://firebasestorage.googleapis.com/v0/b/theme-b8677.appspot.com/o/article%2FSDC12246.JPG1?alt=media&token=6b27b194-28a1-418f-b9d3-286626a13bb5"
        ]

        return {state,displayComment,tempPicture}
    }
}
</script>

<style scoped lang="scss">
.profile-img{
    width: 32px;
    height: 32px;
    border-radius: 70%;
}
.custom-card{
    width: 380px;
    border-radius: 10px !important;
    margin: 5px;
    .theme-name{
        font-size: large;
        font-weight: bold;
        text-align: center;
    };

}

</style>