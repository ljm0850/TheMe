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
            <ArticleImageVue :pictures="article.picture"/>
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
        return {state,displayComment}
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