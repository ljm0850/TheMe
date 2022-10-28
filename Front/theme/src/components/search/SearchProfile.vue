<template>
<div>
    <div>프로필 검색</div>
    <div class="mb-3">
        <div class="d-flex">
            <router-link :to="{ name: 'Search' }">
                <img src="@/assets/logo.png" alt="" class="test">
            </router-link>
            <input type="text" class="form-control" id="" placeholder="">
            <button>검색</button>
        </div>
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

<style scoped lang="scss">
.test {
    width: 25px;
    height: 25px;
}

</style>