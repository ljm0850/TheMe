<template>
  <div>
    <!-- <div>아티클 리스트 목록</div>-->
    <ArticleItemVue
      v-for="article in articleList"
      :key="article"
      :article="article"
    />
  </div>
</template>

<script lang="ts">
import ArticleItemVue from "./ArticleItem.vue";
export default {
  components: {
    ArticleItemVue,
    // ArticleListModalVue,
  },
  props: {
    articleList: Object,
    page: String,
  },
  setup() {
    
    return { };
  },
};
</script>

<style lang="scss"></style>
