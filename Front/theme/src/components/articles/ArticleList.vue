<template>
    <div>
        <!-- <div>아티클 리스트 목록</div>-->
        <ArticleItemVue v-for="article in articleList" :key="article" :article="article"/>
        <!-- 모달 -->
        <!-- <ArticleListModalVue /> -->
    </div>
</template>

<script lang="ts">
import ArticleItemVue from './ArticleItem.vue';
// import ArticleListModalVue from './ArticleListModal.vue';
import { computed, reactive } from "vue";
import { useStore } from "vuex";
export default {
    components: {
        ArticleItemVue,
        // ArticleListModalVue,
    },
    props: {
        themeDetail:Object
    } ,
    setup(props:any) {
        const state = reactive({
            themeIdx: props.themeDetail.idx,
            pageSize: 15,
            pageIdx: 0
        })

        const store = useStore()
        store.dispatch("themeArticleList", state)
        const articleList = computed(() => store.getters.publicThemeArticleList)
        return { articleList }
    }
}
</script>

<style lang="scss">


</style>