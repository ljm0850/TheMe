<template>
  <div>
    <div class="theme-header">
      <div class="theme-title-box">
        <div v-if="!state.isMarked" class="bookmark" @click="clickBookmark()">
          <img
            src="@/assets/image/emptyBookmark.png"
            alt=""
            class="emtpyBookmark"
          />
        </div>
        <div v-if="state.isMarked" class="bookmark" @click="clickBookmark()">
          <img
            src="@/assets/image/fillBookmark.png"
            alt=""
            class="fillBookmark"
          />
        </div>
        <div class="theme-title-text">{{ themeDetail.emoticon }}</div>
        <div class="theme-title-text">{{ themeDetail.name }}</div>
      </div>
      <!-- <div class="theme-sort">
                <button>인기순</button>
                <button>최신순</button>
            </div> -->
    </div>
    <KakaoMapVue class="kakao-map" />
    <button @click="goCreateArticle()" class="theme-plus-button">+</button>
    <ArticleListVue
      class="article-list"
      page="publicTheme"
      :articleList="articleList"
    />
  </div>
</template>

<script lang="ts">
import ArticleListVue from "@/components/articles/ArticleList.vue";
import KakaoMapVue from "../map/KakaoMap.vue";
import { computed, reactive } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useStore } from "vuex";
export default {
  components: {
    ArticleListVue,
    KakaoMapVue,
  },
  setup() {
    const store = useStore();
    const route = useRoute();
    const router = useRouter();

    const theme_idx = route.params.themeIdx;
    store.dispatch("detailTheme", theme_idx);

    const themeDetail = computed(() => store.getters.publicThemeDetail);

    const param = reactive({
      themeIdx: theme_idx,
      pageSize: 10,
      pageIdx: 0,
    });

    store.dispatch("themeArticleList", param);
    const articleList = computed(() => store.getters.publicThemeArticleList);

    const state = reactive({
      isMarked: themeDetail.value.bookmarked,
    });

    const clickBookmark = () => {
      if (state.isMarked) {
        // 북마크 취소
        store.dispatch("unScrapTheme", theme_idx);
      } else {
        // 북마크 하기
        store.dispatch("scrapTheme", theme_idx);
      }
      state.isMarked = !state.isMarked;
      console.log(state.isMarked);
    };

    const isBookmarked = () => {
      state.isMarked = themeDetail.value.bookmarked;
    };
    setTimeout(() => isBookmarked(), 100);

    console.log(state.isMarked);

    const goCreateArticle = () => {
      // 글쓰러가는 테마 정보 state에 올려놓기
      store.dispatch("selectedThemeForArticle", themeDetail.value);
      // 글쓰는 페이지로 넘어가기
      router.push({
        name: "CreateArticle",
      });
    };
    console.log(themeDetail.value);

    return { themeDetail, state, clickBookmark, goCreateArticle, theme_idx, articleList };
  },
};
</script>

<style>
.theme-header {
  position: relative;
  top: 25px;
  z-index: 1;
  width: 200px;
  height: 60px;
  background: rgba(255, 255, 255, 0.8);
  border-radius: 5px;
  align-items: center;
}
.theme-title-text {
  display: inline;
  margin: 10px;
  font-size: 17px;
}

.theme-title-box {
  text-align: center;
  padding-top: 20px;
  padding-left: 10px;
}

.emtpyBookmark {
  width: 15px;
  height: 15px;
}

.fillBookmark {
  width: 22px;
  height: 19px;
}

.bookmark {
  position: absolute;
  top: 18px;
  left: 8px;
}

.theme-sort {
  position: relative;
  top: -25px;
  left: 255px;
}
.theme-sort button {
  background: rgba(255, 255, 255, 0.8);
  border: 1px solid #c7c7c7;
  width: 65px;
  height: 30px;
  font-weight: 400;
  font-size: 15px;
}
.kakao-map {
  top: -40px;
  position: relative;
  z-index: -1;
}
.theme-plus-button {
  top: -85px;
  left: 345px;
  position: relative;
  z-index: 1;
  box-sizing: border-box;
  background: #ffffff;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: 1px solid #cdcdcd;
}
.article-list {
  position: relative;
  top: -40px;
}
</style>
