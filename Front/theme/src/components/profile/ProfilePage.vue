<template>
<div>
  <IntroduceUserVue />
  <ProfileThemeFollowingVue />
</div>
</template>

<script lang="ts">
import IntroduceUserVue from './profilePageComponents/IntroduceUser.vue';
import ProfileThemeFollowingVue from './profilePageComponents/ProfileThemeFollowing.vue'
// import { useStore } from "vuex";

export default {
  components: {
    IntroduceUserVue,
    ProfileThemeFollowingVue
  },
  setup (){

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