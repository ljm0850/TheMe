<template>
  <div>
    <div>
      <button
        class="btn btn-light type-button border"
        style="margin-left: 16px"
        @click="changeViewMode(true)"
      >
        Themes
      </button>
      <button
        class="btn btn-light type-button border"
        @click="changeViewMode(false)"
      >
        Following
      </button>
    </div>

    <ul v-if="state.vueTarget" class="paddingZero">
      <li v-for="item in selectedUser.themeDtoList" :key="item">
        <div><UserThemeCard :theme="item" class="themeCard" style="margin-left: 0" /></div>
      </li>
    </ul>
    <ul v-if="!state.vueTarget" class="paddingZero">
      <li v-for="item in selectedUser.followingDtoList" :key="item">
        <div><FollowingThemeCard
          :theme="item"
          class="themeCard"
          style="margin-left: 0"
        /></div>
        
      </li>
    </ul>
    <br>
    <br>
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
    UserThemeCard,
  },
  setup() {
    const store = useStore();

    const selectedUser = computed(() => store.getters.selectedUser);
    // console.log("selectedUser")

    const state = reactive({ vueTarget: true });
    const changeViewMode = (type: boolean) => {
      state.vueTarget = type;
    };

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
  margin-left: 16px;
}

.themeCard {
  margin-bottom: 10px;
  width: 360px;
  
}

.type-button {
  width: 180px;
}
</style>
