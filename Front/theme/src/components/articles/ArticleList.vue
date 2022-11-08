<template>
    <div>
        <!-- <div>아티클 리스트 목록</div>
        <hr> -->
        <ArticleItemVue />
        <ArticleItemVue />
        <ArticleItemVue />
        <ArticleItemVue />
        <!-- 모달 -->
        <!-- <ArticleListModalVue /> -->
    </div>
</template>

<script lang="ts">
import ArticleItemVue from './ArticleItem.vue';
// import ArticleListModalVue from './ArticleListModal.vue';
// import { useStore } from "vuex";
export default {
    components: {
        ArticleItemVue,
        // ArticleListModalVue,
    },
    setup() {
    }
}
</script>

<style lang="scss">


</style>