<template>
    <div>
        <div class="d-flex justify-content-between">
            <div>{{ theme_name }}</div>
            <div>
                <button>인기순</button>
                <button>최신순</button>
            </div>
        </div>
        <KakaoMapVue />
        <button>+</button>
        <ArticleListVue />
    </div>
</template>

<script lang="ts">
import ArticleListVue from "@/components/articles/ArticleList.vue"
import KakaoMapVue from "../map/KakaoMap.vue"
import { useRoute } from 'vue-router'
// import { useStore } from "vuex";
export default {
    components: {
        ArticleListVue,
        KakaoMapVue,
    },
    setup() {
        const route = useRoute()
        let theme_name: string | string[] = route.params.themeName
        return { theme_name }
    }
}
</script>

<style lang="scss`">


</style>