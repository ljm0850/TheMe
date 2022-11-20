<template>
    <div>
        <div class="comment-header">
            <div class="header-comment">댓글</div>
            <div class="" @click.prevent="alertArticle()">🚨</div>
        </div>
        <div class="inputComment">
            <div>
                <input type="text" class="form-control" id="createComment" placeholder="댓글을 입력해주세요" @input="updateComment">
            </div>
            <button class="btn btn-light type-button border commentBtn comment" @click.prevent="createComment()">작성</button>
        </div>
        <CommentItemVue @triggerDelete="reload(idx)" v-for="comment,idx in state.comments" :key="comment.title" :comment="comment" :idx="idx"/>
    </div>
</template>

<script lang="ts">

import CommentItemVue from './CommentItem.vue';
import { reactive, computed } from "vue";
import { useStore } from "vuex";
// import { computed } from '@vue/runtime-core';
export default {
    components: {
        CommentItemVue,
    },
    props: {
      commentList : Object,
      article : Object,
    },
    setup(props:any) {
        const store = useStore();

        const state : any= reactive({
            boardIdx : props.article.boardIdx,
            content : "",
            comments : [...props.commentList]
            // commentList : {},
        });
        // state.commentList = props.commentList;
        console.log(state.comments)
        // 댓글 입력받기
        const updateComment = (e:any) => {
            state.content = e.target.value
        }

        // 댓글 등록하기
        const createComment = () => {
            console.log(state);
            store.dispatch("createComment", state);
            const loginUser = computed(() => store.getters.loginUser);

            // 댓글 추가하기
            const newcomment = {
                alertCount:0,
                boardIdx:46,
                content:state.content,
                nickname:loginUser.value.nickname,
                profile:loginUser.value.picture,
                userIdx:loginUser.value.userIdx,
                writer:true
            }

            state.comments.push(newcomment)
            
        }

        // 게시글 신고하기
        const alertArticle = () => {
            var input = prompt('게시글 신고', '신고 사유를 입력해 주세요.');
            alert(input);
            state.content = input;
            store.dispatch("reportArticle", state);
            
        }

        const reload = (value:number) => {
            console.log(state.comments.length)
            state.comments.splice(value, 1);
            console.log(state.comments)
        }
        

        return { state, updateComment, createComment, alertArticle, reload }
    }
}
</script>

<style lang="scss">


.commentBtn{
    position: relative;
    left: 290px;
}

.comment-header{
    // display: inline;
    padding: 4px;
    display: flex;
    justify-content: space-between;
}

// .header-comment{
//     position: relative;
//     top:15px;
// }

.comment{
    margin-top: 5px !important;
}
</style>