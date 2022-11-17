<template>
  <div>
    <div>
      <button class="btn btn-light type-button border" @click="changeViewMode(true)">Themes</button>
      <button class="btn btn-light type-button border"  @click="changeViewMode(false)">Following</button>
    </div>
    
    <ul v-if="state.vueTarget" class="paddingZero">
      <li v-for="item in selectedUser.themeDtoList" :key="item" >
        <UserThemeCard
          :theme="item"
          class="themeCard"
        />
      </li>
    </ul>
    <ul v-if="!state.vueTarget" class="paddingZero">
      <li v-for="item in selectedUser.followingDtoList" :key="item" >
        <FollowingThemeCard
          :theme="item"
          class="themeCard"
        />
      </li>
    </ul>
  </div>
</template>

<script lang="ts">
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";
import { reactive } from "@vue/reactivity";
import FollowingThemeCard from "@/components/theme/FollowingThemeCard.vue";
import UserThemeCard from "@/components/theme/UserThemeCard.vue";
export default {
  components: {
    FollowingThemeCard,
    UserThemeCard
},
    setup() {
        const store = useStore();
        
        const selectedUser = computed(() => store.getters.selectedUser)
        // console.log("selectedUser")
        
        const state = reactive({vueTarget : true});
        const changeViewMode = (type : boolean) => {
            state.vueTarget = type;
        }
      
    // const selectedUser = computed(() => store.getters.selectedUser);
    // console.log("여기아래 집중");
    // console.log(selectedUser);
    // // console.log("selectedUser")

    // const state = reactive({ vueTarget: true });
    // const changeViewMode = (type: boolean) => {
    //   state.vueTarget = type;
    // };

    return { selectedUser, state, changeViewMode };
  },
};
</script>

<style scoped lang="scss">
.paddingZero {
  padding-left: 0;
}
ul {
  list-style: none;
  padding-left: 0px;
}

.themeCard {
  margin-bottom: 10px;
  // margin-left : 0px;
}

.type-button {
  width: 50%;
}
</style>
