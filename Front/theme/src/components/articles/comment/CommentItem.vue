<template>
    <div>
        <div class="commentContent">
            <img :src="comment.profile" alt="" class="profileImg">
            {{comment.nickname}} 
            {{comment.content}} 
        </div>
        <div class="commentContent">
            <button @click.prevent="alertComment()">신고</button>
            <button @click.prevent="deleteComment()">삭제</button>
        </div>
    </div>
</template>

<script lang="ts">
import { useStore } from "vuex";
export default {
    props: {
        comment:Object
    },
    components: {
    },
    setup(props:any) {
        const store = useStore();

        const deleteComment = () => {
            store.dispatch("deleteComment", props.comment.commentIdx);
        }
        const alertComment = () => {
            store.dispatch("reportComment", props.comment.commentIdx);
        }

        return{ alertComment, deleteComment }
    }
}
</script>

<style lang="scss">

.commentContent{
    display: inline !important;
}

.profileImg{
    width: 20px;
}

</style>