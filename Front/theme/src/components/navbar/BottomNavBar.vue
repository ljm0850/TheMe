<template>
<div>
  <nav class="navbar-position bg-light">
    <div class="d-flex justify-content-around" v-if="state.plusButton">
      <router-link :to="{ name: 'PlusTheme' }">
        <button type="button" class="btn btn-light">테마</button>
      </router-link>
      <router-link :to="{ name: 'CreateArticle' }">
        <button type="button" class="btn btn-light">게시글</button>
      </router-link>
    </div>
    
    <div class="d-flex flex-row">
      <router-link :to="{ name: 'Main' }">
        <button type="button" class="btn btn-primary button-size">
          메인
        </button>
      </router-link>  
      
      <router-link :to="{ name: 'ThemeList' }">
        <button type="button" class="button-size">지도(테마)</button>
      </router-link>
    
      <button type="button" @click.prevent="togglePlusButton()" class="btn btn-primary button-size">+</button>
    
      <router-link :to="{ name: 'Search' }">
        <button type="button" class="btn btn-primary button-size">검색</button>
      </router-link>
    
      <router-link  ter-link :to="{ name: 'Profile', params: { nickname: 'BottomNavBar에서 바꾸자'} }">
        <button type="button" class="btn btn-primary button-size">프로필</button>
      </router-link>
    </div>
  </nav>
</div>
</template>

<script lang="ts">
import { reactive } from '@vue/reactivity'
export default {
  components: {
  },
  setup (){
    const state = reactive({
      plusButton : false
    })
    const togglePlusButton = ()=>{
      state.plusButton = !state.plusButton
    }
    return {state,togglePlusButton}
  }
}
</script>

<style scoped lang="scss">
  .navbar-position {
    position: fixed;
    bottom: 0;
    z-index: 50;
  }
  .button-size {
    // width: 20vw; (뷰포트 대비 화면비율)
    width: 78px; // (390 / 5 = 78)
    height: 5vh;
    margin: 0;
    border: 0;
    padding: 0;
  }
</style>