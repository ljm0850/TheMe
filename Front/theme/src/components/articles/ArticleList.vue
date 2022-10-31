<template>
    <div>
        <div>아티클 리스트 목록</div>
        <hr>
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#articleListModal">
            <ArticleItemVue />
        </button>

        <!-- 모달 -->
        <ArticleListModalVue />
    </div>
</template>

<script lang="ts">
import ArticleItemVue from './ArticleItem.vue';
import ArticleListModalVue from './ArticleListModal.vue';
// import { useStore } from "vuex";
export default {
    components: {
        ArticleItemVue,
        ArticleListModalVue,
    },
    setup() {
    }
}
</script>

<style lang="scss`">


</style>