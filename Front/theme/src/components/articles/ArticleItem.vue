<template>
    <div class="d-flex justify-content-between">
        <div>
            <div>{{article.themeName}}</div>
            <div>{{article.place}}</div>
            <div>{{article.nickname}}</div>
        </div>
        <div>{{article.count}}</div>
    </div>
    <hr>
</template>

<script lang="ts">
// import { useStore } from "vuex";
export default {
    components: {
    },
    props:{
        article:Object
    },
    setup() {
        
    }
}
</script>

<style lang="scss">


</style>