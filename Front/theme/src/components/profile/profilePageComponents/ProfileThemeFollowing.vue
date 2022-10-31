<template>
<div>
    <div>
        <button>Themes</button>
        <button>Following</button>
    </div>
    <SearchThemeCardVue />
</div>
</template>

<script lang="ts">
import SearchThemeCardVue from '@/components/theme/SearchThemeCard.vue';
// import { useStore } from "vuex";
export default {
    components: {
        SearchThemeCardVue
    },
    setup() {
    }
}
</script>

<style lang="scss">

</style>