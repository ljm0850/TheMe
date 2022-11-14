<template>
<div>
  <nav class="navbar-position " >
    <div class="d-flex  justify-content-around " v-if="state.plusButton">
      <div></div>
      <div></div>
      <div></div>
      <router-link :to="{ name: 'PlusTheme' }" @click.prevent="togglePlusButton()">
        <button type="button" class="btn btn-light btn-lg" >&nbsp;테마&nbsp;</button>
      </router-link>
      <router-link :to="{ name: 'CreateArticle' }" @click.prevent="togglePlusButton()">
        <button type="button" class="btn btn-light btn-lg " >게시글</button>
      </router-link>
      <div></div>
      <div></div>
      <div></div>
    </div>
    
    <div class="d-flex flex-row bg-white bottomeNav" >
      <router-link @mousedown.left ="mouseLeft" :to="{ name: 'Main' }"> 
        <button type="button" class="button-size"><img src="@/assets/image/homeIcon.png" alt="" class="icon"></button>
        
      </router-link>  
      
      <router-link @mousedown.left ="mouseLeft" :to="{ name: 'ThemeList' }">
        <button type="button" class="button-size"><img src="@/assets/image/mapIcon.png" alt="" class="icon"></button>
      </router-link>
    
      <button type="button" @click.prevent="togglePlusButton()" class="button-size "><img src="@/assets/image/plusIcon.png" alt="" class="plus-icon"></button>
    
      <router-link @mousedown.left ="mouseLeft" :to="{ name: 'Search' }">
        <button type="button" class="button-size"><img src="@/assets/image/searchIcon.png" alt="" class="icon"></button>
      </router-link>
    
      <router-link @mousedown.left ="mouseLeft"  ter-link :to="{ name: 'Profile', params: { nickname: myNickname } }">
        <button type="button" class="button-size"><img src="@/assets/image/profileIcon.png" alt="" class="icon"></button>
      </router-link>
    </div>
  </nav>
</div>
</template>

<script lang="ts">
import { reactive } from '@vue/reactivity'
import { useStore } from "vuex";
import { computed } from '@vue/runtime-core';
export default {
  components: {
  },
  setup (){
    const state = reactive({
      plusButton : false,
    })
    const togglePlusButton = ()=>{
      state.plusButton = !state.plusButton
    }
    const store = useStore();
    const myNickname = computed(()=>store.getters.loginUser.nickname)
    const mouseLeft = (e : any) => {
      if(e.button == 0 ){ // 좌클릭 시
        state.plusButton = false
      }
    }
    return { state, togglePlusButton,myNickname ,mouseLeft}
  }
}
</script>

<style scoped lang="scss">
.plus-icon{
  width: 30px;
  height: 30px;
}
.btn-lg{
  border: 0px solid #E89A3D;
  background-color: #fbe9d2;
  font-size: 14px;
  color: #000;
  margin-bottom: 4px;
}
.bottomeNav{
  border-top: 1px solid #EEEEEE;
}
  .navbar-position {
    position: fixed;
    bottom: 0;
    z-index: 150;
  }
  .button-size {
    // width: 20vw; (뷰포트 대비 화면비율)
    width: 70px; // (390 / 5 = 78)
    height: 5vh;
    margin: 4px;
    border-radius: 100px;
    border: 0px solid black;
    
    background-color: white;
    padding: 0;
  }
  .icon{
    width: 20px;
    height: 20px;
  }
  .bottomeNav .router-link-active button{
    // border: 3px solid #61D85B;
    background-color: #e4ffed;
  }
</style>