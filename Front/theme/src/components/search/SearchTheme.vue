<template>
    <div>
        <div>테마 검색</div>
        <div class="mb-3">
            <div class="d-flex">
                <router-link :to="{ name: 'Search' }">
                    <img src="@/assets/logo.png" alt="" class="test">
                </router-link>
                <input type="text" class="form-control" id="" placeholder="" v-model="state.inputValue" @input="temp()">
                <button>검색</button>
                {{liveSearchTheme}}
            </div>
        </div>
    </div>
    <SearchThemeCardVue />
</template>

<script lang="ts">
import SearchThemeCardVue from '../theme/SearchThemeCard.vue';
import { computed, reactive } from '@vue/reactivity'
import { useStore } from "vuex";
export default {
    components: {
        SearchThemeCardVue
    },
    setup() {
        const liveSearchTheme = computed(() => store.getters.liveSearchTheme)
        const store = useStore();
        const state = reactive({
            liveSearchTheme : store.getters.liveSearchTheme,
            inputValue : ""
        });
        const temp = () => {
            store.dispatch("liveSearchTheme",state.inputValue)
        }
        
        return {state,temp,liveSearchTheme}
        
    }
}
</script>

<style scoped lang="scss">
.test {
    width: 25px;
    height: 25px;
}
</style>