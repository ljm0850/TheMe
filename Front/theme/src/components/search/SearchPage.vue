<template>
  <div>
    <div>
      <router-link :to="{ name: 'SearchProfile' }">
        <button>인물</button>
      </router-link>
      <router-link :to="{ name: 'SearchTheme' }">
        <button>테마</button>
      </router-link>
    </div>
    <div>
      현재인기있는 사람들
    </div>
    <SearchProfileCardVue />
  </div>
</template>

<script lang="ts">
import SearchProfileCardVue from "@/components/profile/SearchProfileCard.vue"
// import { useStore } from "vuex";
export default {
  components: {
    SearchProfileCardVue
  },
  setup() {
  }
}
</script>

<style lang="scss">

</style>