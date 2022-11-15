<template>
    <div class="d-flex justify-content-between" data-bs-toggle="modal" data-bs-target="#articleListModal">
        <div>
            <div>{{article.name}}</div>
            <div>{{article.place}}</div>
            <div>{{article.nickname}}</div>
        </div>
        <div>{{article.boardCount}}</div>
    </div>
    <!-- 모달 -->
    <ArticleListModalVue :article="article" :boardList="boardList"/>
    <hr>
</template>

<script lang="ts">
import ArticleListModalVue from './ArticleListModal.vue';
import { useStore } from "vuex";
import { computed, reactive } from "vue";
export default {
    components: {
        ArticleListModalVue,
    },
    props:{
        article:Object,
        publicTheme:String
    },
    setup(props:any) {
        const store = useStore();
        const param = reactive({
            themeIdx: props.publicTheme,
            name: props.article.name,
            pageSize: 10,
            pageIdx: 0,
        });
        
        store.dispatch("placeArticleList", param);
        const boardList = computed(() => store.getters.placeArtilceList);

        // console.log(boardList.value);

        return { boardList }
    }
}
</script>

<style lang="scss">


</style>