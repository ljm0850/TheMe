<template>
    <div>
        {{FeedList}}
        <ArticleDetailVue :article="tempArticle" />
        <div v-for="Feed,idx in FeedList" :key="Feed">
            <ArticleDetailVue :article="Feed" :feedIdx="idx" />
            <br>
        </div>
        <!-- 피드가 없을시 보이는 내용 -->
        <div class="card">
            <div class="card-body">
                <div>피드가 더이상 없습니다.</div>
                <div>검색 페이지에서</div>
                <div>원하는 테마를 팔로우 해보세요!</div>
                <button class="btn btn-gray">전체 테마 보기</button>
                <button class="btn btn-gray">검색하러 가기</button>
            </div>
        </div>
        <br>
        <br>
        <br>
        <div class="select-btn">
            <select class="" @change="selectCity">
                <option class = "custom-select-option" value="0" selected >전국</option>
                <option class = "custom-select-option" value="1" >서울</option>
                <option class = "custom-select-option" value="2" >대전</option>
                <option class = "custom-select-option" value="3" >광주</option>
                <option class = "custom-select-option" value="4" >구미</option>
                <option class = "custom-select-option" value="5" >부울경</option>
            </select>
        </div>
    </div>
</template>

<script lang="ts">
import { useStore } from "vuex";
// import { reactive } from "vue";
import ArticleDetailVue from '@/components/articles/ArticleDetail.vue';
import { computed } from '@vue/runtime-core';
export default {
    components: {
        ArticleDetailVue,
    },
    setup() {
        const store = useStore();
        // const state = reactive({
            
        // });
        store.dispatch("getFeedTheme",0)
        const FeedList = computed(() => store.getters.getFeedTheme)
        const selectCity = (e : any) => {
            store.dispatch("getFeedTheme",e.target.value)
            
        }

        const tempArticle = {
            themeName: "피드 없어서 임시 하드 코딩",
            nickname: "가라사대",

        }
        return {FeedList,selectCity,tempArticle}
    }
}
</script>

<style >
.custom-select-option{
    text-align: center !important;
    border-radius: 10px;
    width:10px
}
select {
    -webkit-appearance:none;
    moz-appearance:none;
    color: #E89A3D;
    appearance:none;
    background:#fff;
    text-align: center;
    border-color: #E89A3D;
    border-radius: 10px ;
}
select::-ms-expand {
    opacity:0;
}
.select-btn{
    position: fixed;
    margin-bottom: 100px;
    padding-bottom: 100px;
    left: 340px;
    bottom: -140px;
}
.card-body{
    padding-bottom:0px !important;
}

</style>