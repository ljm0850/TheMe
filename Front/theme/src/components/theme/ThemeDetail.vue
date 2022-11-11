<template>
    <div>
        <div class="theme-header">
            <div class="theme-title-box">
                <div v-if="!state.isMarked" class="bookmark" @click="clickBookmark()">
                    <img src="@/assets/image/emptyBookmark.png" alt="" class="emtpyBookmark">
                </div>
                <div v-if="state.isMarked" class="bookmark" @click="clickBookmark()">
                    <img src="@/assets/image/fillBookmark.png" alt="" class="fillBookmark">
                </div>
                <div class="theme-title-text">{{themeDetail.emoticon}}</div>
                <div class="theme-title-text">{{themeDetail.name}}</div>
            </div>
            <!-- <div class="theme-sort">
                <button>인기순</button>
                <button>최신순</button>
            </div> -->
        </div>
        <KakaoMapVue class="kakao-map" />
        <button class="theme-plus-button">+</button>
        <ArticleListVue class="article-list" :themeDetail="themeDetail"/>
    </div>
</template>

<script lang="ts">
import ArticleListVue from "@/components/articles/ArticleList.vue"
import KakaoMapVue from "../map/KakaoMap.vue"
import { computed, reactive } from "vue";
import { useRoute } from 'vue-router'
import { useStore } from "vuex";
export default {
    components: {
        ArticleListVue,
        KakaoMapVue,
    },
    setup() {
        const store = useStore()
        const route = useRoute()

        let theme_idx = route.params.themeIdx
        store.dispatch("detailTheme", theme_idx)

        const themeDetail = computed(() => store.getters.publicThemeDetail)
        

        const state = reactive({
            isMarked : themeDetail.value.isMarked,
        });

        const clickBookmark = () => {
            if(state.isMarked){
                // 북마크 취소
                store.dispatch("scrapTheme", theme_idx)
            } else {
                // 북마크 하기
                store.dispatch("unScrapTheme", theme_idx)
            }
            state.isMarked = !state.isMarked
            console.log(state.isMarked)
        }

        return { themeDetail, state, clickBookmark }
    }
}
</script>

<style>

.theme-header{
    position: relative;
    top: 20px;
    z-index: 1;
    width: 200px;
    height: 60px;
    background: rgba(255, 255, 255, 0.8);
    border-radius: 5px;
    align-items: center;
}
.theme-title-text{
    display: inline;
    margin: 10px;
    font-size: 17px;
}

.theme-title-box{
    text-align: center;
    padding-top: 10px;
    padding-left: 10px;
}

.emtpyBookmark{
  width: 15px;
  height: 15px;
}

.fillBookmark{
  width: 22px;
  height: 19px;
}

.bookmark{
  position: absolute;
  top:10px;
  left:8px;
}

.theme-sort{
    position: relative;
    top: -25px;
    left: 255px;
}
.theme-sort button{
    background: rgba(255, 255, 255, 0.8);
    border: 1px solid #C7C7C7;
    width: 65px;
    height: 30px;
    font-weight: 400;
    font-size: 15px;
}
.kakao-map{
    top : -40px;
    position: relative; 
    z-index: -1;
}
.theme-plus-button{
    top: -85px;
    left:345px;
    position: relative;
    z-index: 1;
    box-sizing: border-box;
    background: #FFFFFF;
    width: 40px;
    height: 40px;
    border-radius: 50%;
    border: 1px solid #CDCDCD;
}
.article-list{
    position: relative;
    top:-40px;
}
</style>