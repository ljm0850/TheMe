<template>
  <div>
    <!-- <div>아티클 리스트 목록</div>-->
    <ArticleItemVue
      :publicTheme = "publicTheme"
      v-for="article in articleList"
      :key="article"
      :article="article"
    />
  </div>
</template>

<script lang="ts">
import ArticleItemVue from "./ArticleItem.vue";
import { reactive } from "vue";
export default {
  components: {
    ArticleItemVue,
    // ArticleListModalVue,
  },
  props: {
    articleList: Object,
    publicThemeIdx: String,
  },
  setup(props:any) {
    const state = reactive({
      publicTheme : props.publicThemeIdx
    });
    const publicTheme = state.publicTheme
    return { publicTheme };
  },
};
</script>

<style lang="scss"></style>
