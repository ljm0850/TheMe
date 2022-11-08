<template>
    <div class="card">
        <!-- <div>공용인지 개인인지는 props 이용해서 처리하자</div> -->
        <div class="card-body">
            <div class="d-flex justify-content-between">
                <div>{{theme.name}}</div>
                <button>🔖</button>
                
            </div>
        </div>
        <div class="d-flex card-total">
            <img src="@/assets/logo.png" alt="" class="best-img">
            <div class="d-flex column">
                <div>
                    <img src="@/assets/logo.png" alt="" class="default-img">
                    <img src="@/assets/logo.png" alt="" class="default-img">
                </div>
                <div>
                    <img src="@/assets/logo.png" alt="" class="default-img">
                    <img src="@/assets/logo.png" alt="" class="default-img">
                </div>
            </div>
            <div>
                <div class="default-img">👨</div>
                <div class="default-img">👨</div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
// import { useStore } from "vuex";
export default {
    props:{
        theme:Object
    },
    components: {
    },
    setup() {
    }
}
</script>

<style lang="scss">
@media screen {
    .card-total {
        max-width: 390px;

        .best-img {
            width: 128px;
            height: 128px;
        }

        .default-img {
            width: 64px;
            height: 64px;
        }
    }
    .card {
        width: 360px;
        height: 190px;

        background: #FAFAFA;
        border: 1px solid #CDCDCD;
        box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
        border-radius: 15px;
    }

}
</style>