<template>
    <div class="card custom-card d-flex justify-content-center">
        <div class="card-body">
            <div class="theme-name">{{article.themeName}}</div>
            <div class="d-flex justify-content-between">
                <div class="d-flex">
                    <div>프로필사진</div>
                    <div>글쓴이</div>
                </div>
                <div>3년전</div>
            </div>
            <!-- 사진 -->
            <ArticleImageVue :test="article.themeName"/>
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
            <CommentListVue v-if="state.commentFlag"/>

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
    setup() {
        const state = reactive({
            commentFlag : false,
        })
        const displayComment = ()=>{
            state.commentFlag = true;
        }
        return {state,displayComment}
    }
}
</script>

<style scoped lang="scss">
.custom-card{
    width: 390px;
    border-radius: 20px !important;
    .theme-name{
        font-size: large;
        font-weight: bold;
        text-align: center;
    };

}

</style>