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
            </div>
        </div>
    </div>
    <SearchThemeCardVue />
</template>

<script lang="ts">
import SearchThemeCardVue from '../theme/SearchThemeCard.vue';
import { reactive } from '@vue/reactivity'
import { useStore } from "vuex";
export default {
    components: {
        SearchThemeCardVue
    },
    setup() {
        const store = useStore();
        const state = reactive({
            searchList : store.getters.searchList,
            inputValue : ""
        });
        const temp = () => {
            store.dispatch("liveSearchTheme",state.inputValue)
        }
        return {state,temp}
        
    }
}
</script>

<style scoped lang="scss">
.test {
    width: 25px;
    height: 25px;
}
</style>