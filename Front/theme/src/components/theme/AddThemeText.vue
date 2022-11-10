<template>
  <div>
     <div class="d-flex theme-style" v-if="theme">
      <div class="form-control form-custom">{{theme.name}}</div>
      <button v-if="theme.openType==0" type="button" class="btn btn-outline-secondary white-button" data-bs-toggle="modal" data-bs-target="#createThemeModal" @click="selectTheme()">
        추가
      </button>
    </div>
  </div>
</template>

<script lang="ts">
import { useStore } from "vuex";
export default {
  props: {
    theme:Object,
  },
  components: {
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
  .form-custom{
    // outline-color: #CDCDCD;
    outline-color: black;
    border-color: black;
  }
  .white-button{
    background-color: white;
    outline-color: black;
    border-color: black;
    margin-left: 1vw;
  }
}
</style>