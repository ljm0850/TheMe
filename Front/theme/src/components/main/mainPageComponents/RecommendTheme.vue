<template>
<div id="recommandTheme" class="carousel slide" data-bs-ride="carousel" data-bs-interval="10000">
  <div class="carousel-inner" v-if =" recommendThemeList.length > 0">
    <div class="carousel-item active">
        <div class="d-flex justify-content-around">
          <ThemeMiniCardVue :theme="recommendThemeList[0]" :key="recommendThemeList[0]"/>  
          <ThemeMiniCardVue :theme="recommendThemeList[1]" :key="recommendThemeList[1]"/> 
        </div>
    </div>
    <div class="carousel-item" v-for="idx in tempIdx" :key="idx">
      <div class="d-flex justify-content-around">
        <ThemeMiniCardVue :theme="recommendThemeList[idx]" :key="recommendThemeList[idx]"/>
        <ThemeMiniCardVue :theme="recommendThemeList[idx+1]" :key="recommendThemeList[idx+1]"/>
      </div>
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#recommandTheme" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#recommandTheme" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
</template>
  
<script lang="ts">
import ThemeMiniCardVue from '@/components/theme/ThemeMiniCard.vue';
import { computed,reactive } from '@vue/runtime-core';
import { useStore } from "vuex";
export default {
components: {
    ThemeMiniCardVue,
},
  setup() {
    const store = useStore();
    // store.commit("SET_PUBLIC_THEME_LIST",Object)
    const state = reactive({
      isMarked: 0,
      sort: 0,
      pageSize: 24,
      pageIdx: 0
    })
    
    store.dispatch("getPublicThemeList", state)
    const recommendThemeList = computed(()=>store.getters.publicThemeList);
    let tempIdx = []
    for (let i = 2; i < store.getters.publicThemeList.length; i+=2){ //6
      tempIdx.push(i)
    }
    // 6개 랜덤으로 비동기로 돌려야될듯???
    // const sixThemeList:any[] =[]
    // let sixIdxList :number[] = []
    // while(sixIdxList.length <6){
    //   let randIdx = Math.floor(Math.random() * 100 % 24)
    //   let ptr = 0
    //   for(let i=0; i<sixIdxList.length;i++){
    //     if(randIdx == sixIdxList[i]){
    //       ptr=1
    //       break
    //     }
    //   }
    //   if(ptr==0){
    //     sixIdxList.push(randIdx)
    //   }
    // }
    // for(let i=0; i<sixIdxList.length; i++){
    //   sixThemeList.push(store.getters.publicThemeList[sixIdxList[i]])
    // }
    return { recommendThemeList,tempIdx }

  }
}
</script>

<style>
.carousel-control-next-icon, .carousel-control-prev-icon{
  background-color: rgb(205, 204, 204);
}

</style>