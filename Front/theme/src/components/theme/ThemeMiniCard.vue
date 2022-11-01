<template>
<div class="card-custom card">
  <div class="card-body" @click="clickTheme()">
    <div>테마미니카드</div>
    <div class="card-title">{{theme.title}}</div>
    <div>x명이 참여함</div>
  </div>
</div>
</template>
  
<script lang="ts">
import { useRouter } from 'vue-router'
// import { useStore } from "vuex";
export default {
  components: {
  },
  props: {
    theme:Object
  },
  setup() {
    const router = useRouter();
    const clickTheme = () => {
      router.push({ name: "PublicTheme", params: { themeName:"ThemeTextCard에서 바꾸자" } })
    }

    // const store = useStore(); 
  return {clickTheme,}

  }
}
</script>
  
<style scoped lang="scss">
.card-custom{
  margin: 10px;
  width: 180px;
  height: 180px !important;
  border-radius: 30px !important;
}  
  
</style>