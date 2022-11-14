<template>
<div v-if="pictures" :id="`${carouselId}`" class="carousel slide" data-bs-ride="true">
    <div class="carousel-indicators">
        <button type="button" :data-bs-target="`#${carouselId}`" data-bs-slide-to="0" class="active"
            aria-current="true" aria-label="Slide 1"></button>
        <button v-for="idx in numArray" :key="idx" type="button" :data-bs-target="`#${carouselId}`" :data-bs-slide-to="idx"
            :aria-label="`Slide {idx}`"></button>
    </div>
    <div  class="carousel-inner">
        <div class="carousel-item active">
            <img :src="pictures[0]" alt="articleDetail" style="width:100%">
        </div>
        <div v-for="idx in numArray" :key="idx" class="carousel-item">
            <img :src="pictures[idx]" alt="articleDetail" style="width:100%">
        </div>
    </div>
    <button class="carousel-control-prev" type="button" :data-bs-target="`#${carouselId}`"
        data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" :data-bs-target="`#${carouselId}`"
        data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>
</template>

<script lang="ts">
// import { useStore } from "vuex";
export default {
    props: {
        pictures: Array,
        carouselId:String
    },
    components: {
    },
    setup(props:any) {
        // eslint-disable-next-line vue/no-setup-props-destructure
        const photoLength:number | undefined = props.pictures?.length
        const numArray: number[] = [];
        if (photoLength) {
            for (let idx = 1; idx < photoLength; idx++){
                numArray.push(idx)
            }
        }
        return { numArray}
        
    }
}
</script>

<style lang="scss">

</style>
