<template>
<div>{{tt}}</div>
<div :id="`${tt}`" class="carousel slide" data-bs-ride="true">
    <div class="carousel-indicators">
        <button type="button" :data-bs-target="`#${tt}`" data-bs-slide-to="0" class="active"
            aria-current="true" aria-label="Slide 1"></button>
        <button type="button" :data-bs-target="`#${tt}`" data-bs-slide-to="1"
            aria-label="Slide 2"></button>
        <button type="button" :data-bs-target="`#${tt}`" data-bs-slide-to="2"
            aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="@/assets/image/article.jpg" alt="articleDetail" style="width:100%">
        </div>
        <div class="carousel-item">
            <img src="@/assets/image/article.jpg" alt="articleDetail" style="width:100%">
        </div>
        <div class="carousel-item">
            <img src="@/assets/image/article.jpg" alt="articleDetail" style="width:100%">
        </div>
    </div>
    <button class="carousel-control-prev" type="button" :data-bs-target="`#${tt}`"
        data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" :data-bs-target="`#${tt}`"
        data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>
</template>
<!-- <img src="@/assets/image/article.jpg" alt="articleDetail" style="width:100%"></img> -->
    <!-- <img src="@/assets/image/article.jpg" alt="articleDetail" style="width:300px"> -->

<script lang="ts">
// import { useStore } from "vuex";
export default {
    props: {
        test:String
    },
    components: {
    },
    setup(props:any) {
        // eslint-disable-next-line vue/no-setup-props-destructure
        const tt = props.test
        return {tt}
    }
}
</script>

<style lang="scss">

</style>