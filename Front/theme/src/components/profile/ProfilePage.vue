<template>
<div>
  <IntroduceUserVue />
  <ProfileThemeFollowingVue />
</div>
</template>

<script lang="ts">
import IntroduceUserVue from './profilePageComponents/IntroduceUser.vue';
import ProfileThemeFollowingVue from './profilePageComponents/ProfileThemeFollowing.vue'
import { useStore } from "vuex";
	
import { useRoute} from "vue-router";
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

    const userNickname = route.params.nickname;
    console.log(userNickname)
    store.dispatch("getUserInfoByNickname",userNickname);
    // const selectedUser = computed(()=>store.getters.selectedUser)

    

    return {  userNickname }
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