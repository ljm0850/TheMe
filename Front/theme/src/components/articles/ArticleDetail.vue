<template>
    <div class="card custom-card">
        <div class="card-body">
            <div class="theme-name">{{article.themeName}}</div>
            <div class="d-flex justify-content-between">
                <div class="d-flex">
                    <div>프로필사진</div>
                    <div>글쓴이</div>
                </div>
                <div>3년전</div>
                <img src="" alt="">
            </div>
            
            <!-- <img src="@/assets/image/articleDetail.png" alt="articleDetail" style="width:350px"> -->
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

<style scoped lang="scss">
.custom-card{
    width: 380px;
    .theme-name{
        font-size: large;
        font-weight: bold;
        text-align: center;
    }

}

</style>