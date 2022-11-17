<template>
    <div class="article-wrap d-flex justify-content-between" data-bs-toggle="modal" data-bs-target="#articleListModal">
        <div>
            <div class="article-name">{{article.name}}</div>
            <div>{{article.place}}</div>
            <!-- <div>{{article.nickname}}</div> -->
        </div>
        <div class="article-count">
            <div class="">게시글</div>
            <div>{{article.boardCount}}</div>
        </div>
    </div>
    <hr>
    <!-- 모달 -->
    <ArticleListModalVue :article="article" :boardList="boardList"/>
    
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

.article-wrap{
    padding : 0 0 5px 5px;;
    margin: 0 10px 10px 10px;
}

.article-count{
    text-align: center;
}

</style>