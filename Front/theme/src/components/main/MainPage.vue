<template>
    <div>메인</div>
</template>

<script>
import { useStore } from "vuex";
export default {
    setup() {
        const store = useStore();
        console.log(store.getters.isLoggedIn)
    }
}
</script>

<style lang="scss`">


</style>