<template>
    <div class="card custom-card d-flex justify-content-center">
        <div class="card-body main-card-body" v-if="article" >
            <div class="theme-name" style="margin-top: -5px; margin-bottom:10px;">{{ article.themeName }}</div>
            <div class="d-flex justify-content-between">
                <div class="d-flex">
                    <img :src="article.profile" class="profile-img" />
                    <div class="center-view">{{ article.nickname }}</div>
                </div>
                <div class="center-view">{{ state.changeTime }}</div>
            </div>
            <!-- 사진 -->
            <ArticleImageVue :pictures="article.picture" :carouselId="articleCarouseId" class="term"/>
            <!-- 사진 끝 -->
            <div class="d-flex justify-content-between">
                <div class="d-flex term">
                    <!-- {{article}}  -->
                    <div v-if="!state.likeFlag" @click="likeClick" class="">🤍
                        {{ state.likeCnt }}</div>
                        <div v-if="state.likeFlag" @click="likeCancle" class="">💙
                            {{ state.likeCnt }}</div>
                    <div class="comment-term">🧾 {{ article.commentCount }}</div>
                </div>
                <div class="d-flex term">
                    <!-- <div>❗</div>
                    <div>❌</div>
                    <div>❓</div> -->
                    <div class="article-name"><img src="@/assets/image/mapIcon.svg" alt="" class="feed-map-icon">{{ article.name }}</div>
                </div>
            </div>
            <span @click="displayComment()" v-if="!state.commentFlag">더보기</span>
            <!-- 댓글 -->
            <CommentListVue v-if="state.commentFlag" :article="article" :commentList ="article.commentListDtoList" />
            <!-- 댓글 끝 -->
        </div>
    </div>
</template>

<script lang="ts">
import {   reactive } from '@vue/reactivity'
import { useStore } from 'vuex'
import ArticleImageVue from "./ArticleImage.vue"
import CommentListVue from "./comment/CommentList.vue"
// import { useStore } from "vuex";
export default {
    props: {
      article:Object,
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
            changeTime : "",
        })
        const displayComment = () => {
            state.commentFlag = true;
        }
       
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
        const today = new Date();
        const timeValue = new Date(props.article.modifyTime);

        const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
        if (betweenTime < 1) state.changeTime =  '방금 전';
        if (betweenTime < 60) {
            state.changeTime =  `${betweenTime}분 전`;
        }

        const betweenTimeHour = Math.floor(betweenTime / 60);
        if (betweenTimeHour < 24) {
            state.changeTime =  `${betweenTimeHour}시간 전`;
        }

        const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
        if (betweenTimeDay < 365) {
            state.changeTime =  `${betweenTimeDay}일 전`;
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
    width: 370px;
    margin-left: 10px !important;
    border-radius: 10px !important;
    margin-top: 5px;
    margin-bottom: 15px !important; 
    .theme-name {
        font-size: large;
        font-weight: bold;
        text-align: center;
    }
}
.center-view{
    margin-top: 4px;
    margin-left: 5px;
}
.term{
    margin-top: 5px;
}
.comment-term{
    margin-left: 5px;
}
.main-card-body{
    margin-left: 0px;
}
.feed-map-icon{
    width: 12px;
    height: 12px;
    margin-top: -3px;
    margin-right: 2px;
    filter: invert(26%) sepia(48%) saturate(339%) hue-rotate(132deg) brightness(87%) contrast(88%);
}
</style>