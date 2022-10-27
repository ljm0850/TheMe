<template>
<div class="modal fade" id="articleListModal" tabindex="-1" aria-labelledby="articleListModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="articleListModalLabel">리디파인</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <ArticleDetailVue/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <!-- <button type="button" class="btn btn-primary">Save changes</button> -->
            </div>
        </div>
    </div>
</div>
</template>

<script lang="ts">
import ArticleDetailVue from './ArticleDetail.vue';
// import { useStore } from "vuex";
export default {
    components: {
        ArticleDetailVue,
    },
    setup() {
    }
}
</script>

<style lang="scss`">


</style>