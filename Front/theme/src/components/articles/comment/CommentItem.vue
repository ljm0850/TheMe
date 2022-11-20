<template>
    <div class="comment-wrap">
        <div class="commentContent">
            <div>
                <img :src="comment.profile" alt="" class="comment-profileImg">
                {{comment.nickname}}
            </div>
            <div>
                <div v-if="!comment.writer" class="" @click.prevent="alertComment()">신고</div>
                <div v-if="comment.writer" class="" @click.prevent="deleteComment(idx)">삭제</div>
            </div>
        </div>
        <div>
            {{comment.content}} 
        </div>
        <!-- <div class="commentContent">
            
        </div> -->
    </div>
</template>

<script lang="ts">
import { reactive } from "vue";
import { useStore } from "vuex";
export default {
    props: {
        comment:Object,
        idx:Number
    },
    components: {
    },
    setup(props:any, {emit} : any ) {
        const store = useStore();

        const state : any= reactive({
            commentIdx : props.comment.commentIdx,
            content : "",
            // commentList : {},
        });

        const deleteComment = (idx:number) => {
            store.dispatch("deleteComment", props.comment.commentIdx);
            emit('triggerDelete', idx);
            console.log(idx)
        }

        const alertComment = () => {
            var input = prompt('댓글 신고', '신고 사유를 입력해 주세요.');
            alert(input);
            state.content = input;
            store.dispatch("reportComment", state);
            
        }

        return{ alertComment, deleteComment }
    }
}
</script>

<style lang="scss">


.commentContent{
    display: flex;
    justify-content: space-between;
}

.comment-btn{
    position:relative;
    top : -10px;
    left : 290px;
}

.comment-profileImg{
    border-radius: 200px;
    border: 2px;
    width: 20px;
}

.comment-wrap{
    margin-top: 13px;
}

</style>