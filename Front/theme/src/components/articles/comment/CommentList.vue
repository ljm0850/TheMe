<template>
    <div>
        <div>댓글</div>
        <CommentItemVue v-for="comment in temp" :key="comment.title" :comment="comment" />
    </div>
</template>

<script lang="ts">
import CommentItemVue from './CommentItem.vue';
// import { useStore } from "vuex";
export default {
    components: {
        CommentItemVue,
    },
    setup() {
        const temp = [{ title: "이건 하드코딩" }, { title: "아티클디테일에서 props로" }, { title: "구현하자" }, {title:"무한스크롤도 해야됨"}]
        return {temp}
    }
}
</script>

<style lang="scss">

</style>