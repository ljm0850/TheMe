<template>
<div>
  <IntroduceUserVue />
  <ProfileThemeFollowingVue />
</div>
</template>

<script>
import IntroduceUserVue from './profilePageComponents/IntroduceUser.vue';
import ProfileThemeFollowingVue from './profilePageComponents/ProfileThemeFollowing.vue'
import { useStore } from "vuex";
import { useRoute} from "vue-router";
import { reactive } from '@vue/reactivity'
// import { useRouter } from 'vue-router' 
// import { computed } from '@vue/runtime-core';
// import { useStore } from "vuex";

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
    
    // const reload = () => {
    //   window.location.reload();
    // }
    
    // const router = useRouter();
    // router.push({
    //             name: "Profile", 
    //             params: { 
    //                 nickname : userNickname,
    //             } 
    //         })
    store.dispatch("getUserInfoByNickname",route.params.nickname);
    // const selectedUser = computed(()=>store.getters.selectedUser)
    const check = ()=>{
      store.dispatch("getUserInfoByNickname", route.params.nickname)
    }
    return { state, check}
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
}
.inroduce-margin{
  margin: 10px;

}

</style>