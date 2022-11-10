<template>
    <div>
        <div v-for="Feed in FeedList" :key="Feed">
            <ArticleDetailVue :article="Feed" />
            <br>
        </div>
        <!-- 피드가 없을시 보이는 내용 -->
        <div class="card">
            <div class="card-body">
                <div>피드가 더이상 없습니다.</div>
                <div>검색 페이지에서</div>
                <div>원하는 테마를 팔로우 해보세요!</div>
                <button>전체 테마 보기</button>
                <button>검색하러 가기</button>
            </div>
        </div>
        <br>
        <br>
        <br>
    </div>
</template>

<script lang="ts">
import { useStore } from "vuex";
import ArticleDetailVue from '@/components/articles/ArticleDetail.vue';
import { computed } from '@vue/runtime-core';
export default {
    components: {
        ArticleDetailVue,
    },
    setup() {
        const store = useStore();
        store.dispatch("getFeedTheme",3)
        const FeedList = computed(() => store.getters.getFeedTheme)

        return {FeedList}
    }
}
</script>

<style lang="scss`">


</style>