<template>
<div>
    <div>
        <button>Themes</button>
        <button>Following</button>
    </div>
    <br>
    <ul>
        <li v-for="item in selectedUser.themeDtoList" :key="item">
            <SearchThemeCardVue :theme="item" class="themeCard"/>
        
        </li>
        
    </ul>
    
</div>
</template>

<script lang="ts">
import SearchThemeCardVue from '@/components/theme/SearchThemeCard.vue';
import { useStore } from "vuex";
import { computed } from '@vue/runtime-core';
export default {
    components: {
        SearchThemeCardVue
    },
    setup() {
        const store = useStore();
        const selectedUser = computed(() => store.getters.selectedUser)
        // console.log("selectedUser")
    

        return { selectedUser }
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