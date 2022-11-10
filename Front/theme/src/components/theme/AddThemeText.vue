<template>
  <div>
     <div class="d-flex theme-style justify-content-between" v-if="theme">
      <div class="form-control form-custom col-8">{{theme.name}}</div>
      <button v-if="theme.openType==0" type="button" class="btn btn-outline-secondary white-button col-3" data-bs-toggle="modal" data-bs-target="#createThemeModal" @click="selectTheme()">
        추가
      </button>
      <button v-if="theme.openType!=0" type="button" class="btn btn-outline-secondary white-button col-3" data-bs-toggle="modal" data-bs-target="#createThemeModal" @click="selectTheme()">
        추가
      </button>
    </div>
    <hr>
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
    margin: auto;
  }
  .form-custom{
    // outline-color: #CDCDCD;
    outline-color: black;
    border-color: #cdcdcd;
    width: 270px;
  }
  .white-button{
    width: 80px;
    color: black;
    background-color: white;
    outline-color: black;
    border-color: #cdcdcd;
    margin-left: 1vw;
  }
}
</style>