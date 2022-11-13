<template>
  <div>
    <!-- <div>아티클 리스트 목록</div>-->
    <ArticleItemVue
      v-for="article in articleList"
      :key="article"
      :article="article"
    />
    <!-- 모달 -->
    <!-- <ArticleListModalVue /> -->
  </div>
</template>

<script lang="ts">
import ArticleItemVue from "./ArticleItem.vue";
// import ArticleListModalVue from './ArticleListModal.vue';
import { computed, reactive } from "vue";
import { useStore } from "vuex";
export default {
  components: {
    ArticleItemVue,
    // ArticleListModalVue,
  },
  props: {
    themeDetail: String,
    page: String,
  },
  setup(props: any) {
    const state = reactive({
      themeIdx: props.themeDetail,
      pageSize: 10,
      pageIdx: 0,
    });

    const store = useStore();
    let articleList = null;
    
    console.log(props.page);

    if (props.page == "publicTheme") {
      store.dispatch("themeArticleList", state);
      articleList = computed(() => store.getters.publicThemeArticleList);
    } else {
      store.dispatch("getUserThemeArticleList", state);
      articleList = computed(() => store.getters.userThemeArticleList);
    }

    return { articleList };
  },
};
</script>

<style lang="scss"></style>
