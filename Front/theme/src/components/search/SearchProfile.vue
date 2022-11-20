<template>
<div>
    <div class="mb-3">
        <div class="d-flex">
            <router-link :to="{ name: 'Search' }" class="logo">
                👨‍👦 
            </router-link>
            <input type="text" class="form-control" id="" placeholder="">
            <button class="btn btn-light type-button border">검색</button>
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
.type-button{
  width: 15%;
  font-size: 1vh;
}
.logo{
    font-size: 3vh;
}
</style>