<template>
<div>
    {{ selectedUser }}
    <div class="d-flex justify-content-end">
        <button type="button" data-bs-toggle="modal" data-bs-target="#settingModal">세팅</button>
    </div>
    <!-- 세팅 모달 -->
    <SettingModalVue />
    <!-- 모달 끝 -->
    <div class="d-flex flex-column align-items-center">
        <img :src="selectedUser.picture" alt="" class="test">
        <div class="">{{ selectedUser.nickname }}</div>
        <div>{{ selectedUser.description }}</div>
        <div class="d-flex flex-row">
            <div class="inroduce-margin">
                <div>Post</div>
                <div>1</div>
            </div>
            <div class="inroduce-margin">
                <div>Themes</div>
                <div>2</div>
            </div>
            <div class="inroduce-margin">
                <div>Follower</div>
                <div>3</div>
            </div>
            <div class="inroduce-margin">
                <div>Following</div>
                <div>4</div>
            </div>
        </div>
    </div>
</div>
</template>

<script lang="ts">
import SettingModalVue from './SettingModal.vue'
// import { useRoute } from 'vue-router'
import { useStore } from "vuex";
import { computed } from '@vue/runtime-core';
export default {
    components: {
        SettingModalVue
    },
    setup() {
        // 추후에 nickname으로 selectedUser 갱신
        // const route = useRoute();
        // let nickname: string | string[] = route.params.nickname
        const store = useStore();
        // const selectedUser = computed(() => store.getters.selectedUser)
        const selectedUser = computed(() => store.getters.loginUser)


        return { selectedUser }
    },
}
</script>

<style lang="scss">

</style>