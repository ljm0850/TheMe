<template>
<div>
    <div>
        <button @click="changeViewMode(true)">Themes</button>
        <button @click="changeViewMode(false)">Following</button>
    </div>
    <br>
    <ul v-if="state.vueTarget">
        <li v-for="item in selectedUser.themeDtoList" :key="item">
            <SearchThemeCardVue :theme="item" class="themeCard"/>
        </li>
    </ul>
    <ul v-if="!state.vueTarget">
        <li v-for="item in selectedUser.followingDtoList" :key="item">
            <SearchThemeCardVue :theme="item" class="themeCard"/>
        </li>
    </ul>
    
</div>
</template>

<script lang="ts">
import SearchThemeCardVue from '@/components/theme/SearchThemeCard.vue';
import { useStore } from "vuex";
import { computed } from '@vue/runtime-core';
import { reactive } from '@vue/reactivity'
export default {
    components: {
        SearchThemeCardVue
    },
    setup() {
        const store = useStore();
        const selectedUser = computed(() => store.getters.selectedUser)
        // console.log("selectedUser")
        
        const state = reactive({vueTarget : true});
        const changeViewMode = (type : boolean) => {
            state.vueTarget = type;
        }



        return { selectedUser,state, changeViewMode}

    
    }
}
</script>

<style lang="scss">
    ul{
        list-style:none;
        padding-left:0px;
    }

    .themeCard{
        margin-bottom: 10px;
    }
</style>