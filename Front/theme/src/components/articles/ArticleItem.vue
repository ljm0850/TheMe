<template>
    <div class="d-flex justify-content-between">
        <div>
            <div>{{article.name}}</div>
            <div>{{article.place}}</div>
            <div>{{article.nickname}}</div>
        </div>
        <div>{{article.boardCount}}</div>
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
    setup(props:any) {
        console.log(props.article)
    }
}
</script>

<style lang="scss">


</style>