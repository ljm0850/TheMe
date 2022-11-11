<template>
    <div id="previewImageCarousel" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-inner" id="previewImg">
    <!-- <div class="carousel-item active">
      <img src="" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="" class="d-block w-100" alt="...">
    </div> -->
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#previewImageCarousel" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#previewImageCarousel" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
</template>

<script lang="ts">
// import { reactive } from '@vue/reactivity'
// import { useStore } from "vuex";
export default {
    components: {
    },
    setup() {
    }
}
</script>

<style scoped lang="scss">
.preview-img-size {
  width: 360px;
  height: 360px;
}
</style>