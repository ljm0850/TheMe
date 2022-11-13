<template>
  <div>
    <div>
      <button @click="changeViewMode(true)">Themes</button>
      <button @click="changeViewMode(false)">Following</button>
    </div>
    <br />
    <ul v-if="state.vueTarget">
      <li v-for="item in selectedUser.themeDtoList" :key="item">
        <UserThemeCard
          :theme="item"
          class="themeCard"
          @click="clickTheme(item.userThemeIdx, item.themeIdx)"
        />
      </li>
    </ul>
    <ul v-if="!state.vueTarget">
      <li v-for="item in selectedUser.followingDtoList" :key="item">
        <FollowingThemeCard
          :theme="item"
          class="themeCard"
          @click="clickTheme(item.userThemeIdx, item.themeIdx)"
        />
      </li>
    </ul>
  </div>
</template>

<script lang="ts">
import { useStore } from "vuex";
import { useRouter } from "vue-router";
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
    const router = useRouter();

    const selectedUser = computed(() => store.getters.selectedUser);
    console.log("여기아래 집중");
    console.log(selectedUser);
    // console.log("selectedUser")

    const state = reactive({ vueTarget: true });
    const changeViewMode = (type: boolean) => {
      state.vueTarget = type;
    };
    const clickTheme = (userThemeIdx: string, publicThemeIdx: string) => {
      router.push({
        name: "UserTheme",
        params: {
          userThemeIdx: userThemeIdx,
          publicThemeIdx: publicThemeIdx,
        },
      });
    };

    return { selectedUser, state, clickTheme, changeViewMode };
  },
};
</script>

<style lang="scss">
ul {
  list-style: none;
  padding-left: 0px;
}

.themeCard {
  margin-bottom: 10px;
}
</style>
