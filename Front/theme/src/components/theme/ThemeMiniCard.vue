<template>
<div class="card-custom card">
  <div class="card-body" @click="clickTheme()" v-if="theme">
    <div>{{theme.emoticon}}</div>
    <div class="card-title">{{theme.name}}</div>
    <div class="position-absolute top-0 end-0 userCount">
      <div class="card-count">👤</div>
      <div class="card-count">{{theme.userCount}}</div>
    </div>
  </div>
</div>
</template>
  
<script lang="ts">
import { useRouter } from 'vue-router'
export default {
  components: {
  },
  props: {
    theme:Object
  },
  setup(props:any) {
    const router = useRouter();
    const clickTheme = () => {
      router.push({ 
        name: "PublicTheme", 
        params: { 
          themeIdx: props.theme.idx
        } 
      })
    }
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
.userCount{
  margin: 5px;
}
.card-body{
  text-align: center;
}
.card-title{
  font-size: 16px;
  font-weight: 600;
}
.card-count{
  display: inline;
  margin: 3px;
  font-size: 8px;
}
  
</style>