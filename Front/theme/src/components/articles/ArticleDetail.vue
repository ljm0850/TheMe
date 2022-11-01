<template>
    <div class="card">
        <div class="card-body">
            {{article}}
            <img src="@/assets/image/articleDetail.png" alt="articleDetail" style="width:350px">
        </div>
    </div>
</template>

<script lang="ts">
// import { useStore } from "vuex";
export default {
    props: {
      article:Object
    },
    components: {
    },
    setup() {
    }
}
</script>

<style sc lang="scss">

</style>