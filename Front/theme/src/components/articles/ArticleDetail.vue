<template>
    <div class="card custom-card d-flex justify-content-center">
        <div class="card-body" v-if="article">
            <div class="theme-name">{{ article.themeName }}</div>
            <div class="d-flex justify-content-between">
                <div class="d-flex">
                    <img :src="article.profile" class="profile-img" />
                    <div>{{ article.nickname }}</div>
                </div>
                <div>{{ article.modifyTime }}</div>
            </div>
            <!-- 사진 -->
            <ArticleImageVue :pictures="article.picture" :carouselId="articleCarouseId" />
            <!-- 사진 끝 -->
            <div class="d-flex justify-content-between">
                <div class="d-flex">
                    <!-- {{article}}  -->
                    <div v-if="!state.likeFlag" @click="likeClick" class="">🤍
                        {{ state.likeCnt }}</div>
                        <div v-if="state.likeFlag" @click="likeCancle" class="">💙
                            {{ state.likeCnt }}</div>
                    <div>🧾 {{ article.commentCount }}</div>
                </div>
                <div class="d-flex">
                    <!-- <div>❗</div>
                    <div>❌</div>
                    <div>❓</div> -->
                    <div class="article-name">{{ article.name }}</div>
                </div>
            </div>
            <span @click="displayComment()" v-if="!state.commentFlag">더보기</span>
            <!-- 댓글 -->
            <CommentListVue v-if="state.commentFlag" :commentList ="commentList"/>
            <!-- 댓글 끝 -->
        </div>
    </div>
</template>

<script lang="ts">
import {   reactive } from '@vue/reactivity'
import { useStore } from 'vuex'
import ArticleImageVue from "./ArticleImage.vue"
import CommentListVue from "./comment/CommentList.vue"
import { useStore } from "vuex";
export default {
    props: {
      article:Object,
      commentList : Object,
    },
    components: {
        ArticleImageVue,
        CommentListVue,
    },
    setup(props: any) {
        const store = useStore();
        const state = reactive({
            commentFlag: false,
            likeFlag: props.article.likeMy,
            likeCnt :props.article.likeCount,
            test : store.getters.detailArticle,
        })
        const displayComment = () => {
            state.commentFlag = true;
        }
        store.commit("detailArticle",props.article.boardIdx)
        const likeClick = () => {
            store.dispatch("likeArticle",props.article.boardIdx)
            state.likeFlag = !state.likeFlag
            state.likeCnt +=1
        }
        const likeCancle = () => {
            store.dispatch("unlikeArticle",props.article.boardIdx)
            state.likeFlag = !state.likeFlag
            state.likeCnt -=1
        }
        const articleCarouseId = `picture${props.article.boardIdx}-${props.article.themeIdx}-${props.article.userIdx}`

        return { state, displayComment, articleCarouseId, likeClick, likeCancle}

    }
}
</script>

<style scoped lang="scss">
.article-name {
    color: gray;
}

.profile-img {
    width: 30px;
    height: 30px;
    border-radius: 70%;
}

.custom-card {
    width: 380px;
    border-radius: 10px !important;
    margin: 5px;
    .theme-name {
        font-size: large;
        font-weight: bold;
        text-align: center;
    }
}
</style>