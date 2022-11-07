<template>
  <div>
     <div class="d-flex theme-style">
      <div class="form-control">{{theme.name}}</div>
      <button v-if="theme.openType==0" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#createThemeModal" @click="selectTheme()">
        추가
      </button>
    </div>
    <CreateThemeModalVue />
  </div>
</template>

<script lang="ts">
import { useStore } from "vuex";
import CreateThemeModalVue from './CreateThemeModal.vue';
export default {
  props: {
    theme:Object,
  },
  components: {
    CreateThemeModalVue
  },
  setup(props:any) {
    const store = useStore();
    const selectTheme = () => {
      store.dispatch("selectedThemeIdxForCreate", props.theme.themeIdx)
      store.dispatch("selectedThemeNameForCreate", props.theme.name)
      store.dispatch("selectedThemeEmoticonForCreate", props.theme.emoticon)
    };

    return {selectTheme}
  }
}
</script>

<style scoped lang="scss">
@media screen {
  .theme-style {
    margin-bottom: 1vh;
  }
}

</style>