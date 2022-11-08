<template>
<div class="card-custom card">
  <div class="card-body" @click="clickTheme()">
    <div>{{theme.emoticon}}</div>
    <div class="card-title">{{theme.title}}</div>
    <div>{{theme.partipation}}명이 참여함</div>
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
      router.push({ name: "PublicTheme", params: { 
        emoticon : "☕",
        themeName:"코딩 잘되는 카페" } })
    }

    // const store = useStore(); 
  return {clickTheme,}

  }
}
</script>
  
<style scoped lang="scss">
.card-custom{
  display: flex;
  align-items: center;
  justify-content: center;
  // flex-wrap: wrap;
  margin: 5px;
  width: 185px;
  height: 100px !important;
  background: #FFFFFF;
  border: 1px solid rgba(205, 205, 205, 0.81);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 10px !important;

}
.card-body{
  text-align: center;
}
  
</style>