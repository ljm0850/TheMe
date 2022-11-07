<template>
    <div>
        <div class="mb-3">
            <div class="d-flex">
                <router-link :to="{ name: 'Search' }" class="logo">
                    🎨
                </router-link>
                <input type="text" class="form-control" id="" placeholder="" v-model="state.inputValue" @input="temp()">
                <button class="btn btn-light type-button border">검색</button>
                <!-- {{liveSearchTheme}} -->
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
        // const selectedUser = computed(() => store.getters.selectedUser)
        const selectedUser = computed(() => store.getters.loginUser)


        return {state,temp,liveSearchTheme,selectedUser}
        
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