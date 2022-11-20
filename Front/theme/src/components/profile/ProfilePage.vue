<template>
<div>
  <IntroduceUserVue v-if="isUser"/>
  <ProfileThemeFollowingVue v-if="isUser"/>
  <div v-if="!isUser" class="d-flex justify-content-center loading-position">
    <img src="@/assets/image/whiteLoading.gif" alt="">
  </div>
</div>
</template>

<script>
import IntroduceUserVue from './profilePageComponents/IntroduceUser.vue';
import ProfileThemeFollowingVue from './profilePageComponents/ProfileThemeFollowing.vue'
import { useStore } from "vuex";
import { useRoute} from "vue-router";
import { reactive } from '@vue/reactivity'
import { computed } from '@vue/runtime-core';

export default {
  components: {
    IntroduceUserVue,
    ProfileThemeFollowingVue
  },
  
  setup (){
    const store = useStore();
    const route = useRoute();
    const state = reactive({
      userNickname : route.params.nickname
    });

    const resetUser = ()=>{
      store.commit('SET_SELECTED_USER',{})
      store.commit('SET_FOLLOWER_LIST',[])
      store.commit('SET_FOLLOWING_LIST',[])
    }
    resetUser()
    store.dispatch("getUserInfoByNickname",route.params.nickname);
    const check = ()=>{
      store.dispatch("getUserInfoByNickname", route.params.nickname)
    }
    const isUser = computed(()=>store.getters.isSelectedUser)
    return { state, check,isUser}
  },
  watch: {
   $route(to, form) {
     if (to.path !== form.path) this.check();
   },
  },
};
</script>

<style lang="scss">
.test{
  width: 200px;
  height: 200px;
  border-radius: 200px;
}
.inroduce-margin{
  margin: 10px;

}
.loading-position{
  position: relative;
  margin-top: 40vh;
}
</style>