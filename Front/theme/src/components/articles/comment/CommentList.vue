<template>
    <div>
        <div>댓글</div>
        <div class="inputComment">
            <div>
                <input type="text" class="form-control" id="createComment" placeholder="댓글을 입력해주세요" @input="updateComment">
            </div>
            <button class="commentBtn" @click.prevent="createComment()">작성</button>
        </div>
        <CommentItemVue v-for="comment in commnetList" :key="comment.title" :comment="comment" />
    </div>
</template>

<script lang="ts">
import CommentItemVue from './CommentItem.vue';
import { reactive } from "vue";
import { useStore } from "vuex";
// import { computed } from '@vue/runtime-core';
export default {
    components: {
        CommentItemVue,
    },
    props: {
      article:Object,
      commnetList : Object,
    },
    setup(props:any) {
        const store = useStore(); 

        const state : any= reactive({
            boardIdx : props.article.boardIdx,
            content : "",
        });
        
        console.log(props.commentList)

        // 댓글 입력받기
        const updateComment = (e:any) => {
            state.content = e.target.value
        }
        // 댓글 등록하기
        const createComment = () => {
            console.log(state);
            store.dispatch("createComment", state);

            // 댓글 로딩 후 새로 불러오기
            store.dispatch("detailArticle",props.article.boardIdx)
            // const articleDetail = computed(() => store.getters.detailArticle)
            
        }
        
        return { state, updateComment, createComment }
    }
}
</script>

<style lang="scss">


.commentBtn{
    position: relative;
    left: 290px;
}


</style>