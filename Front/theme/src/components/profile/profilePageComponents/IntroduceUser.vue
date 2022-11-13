<template>
<div>
    <div class="d-flex justify-content-end">
        <button v-if="state.isProfileMine" type="button" data-bs-toggle="modal" data-bs-target="#settingModal">🛠</button>
    </div>
    <!-- 세팅 모달 -->
    <SettingModalVue :userInfo="selectedUser"/>
    <!-- 모달 끝 -->
    <div class="d-flex flex-column align-items-center">
        <img :src="selectedUser.picture" alt="" class="test">
        <div class="">{{ selectedUser.nickname }}</div>
        <div>{{ selectedUser.description }}</div>
        <div class="d-flex flex-row">
            <div class="inroduce-margin">
                <div>Post</div>
                <div>{{selectedUser.posts}}</div>
            </div>
            <div class="inroduce-margin">
                <div>Themes</div>
                <div>{{selectedUser.themes}}</div>
            </div>
            <div class="inroduce-margin">
                <div>Follower</div>
                <div>{{selectedUser.following}}</div>
            </div>
            <div class="inroduce-margin">
                <div>Following</div>
                <div>{{selectedUser.follower}}</div>
            </div>
        </div>
    </div>
</div>
</template>

<script lang="ts">
import SettingModalVue from './SettingModal.vue'
// import { useRoute } from 'vue-router'
import { reactive } from "vue";
import { useStore } from "vuex";
import { computed } from '@vue/runtime-core';
export default {
    components: {
        SettingModalVue
    },
    setup() {
        const state = reactive({
            isProfileMine : false
        })
        // 추후에 nickname으로 selectedUser 갱신
        // const route = useRoute();
        // let nickname: string | string[] = route.params.nickname
        const store = useStore();
   
        // const selectedUser = computed(() => store.getters.selectedUser)
        const loginUser = computed(() => store.getters.loginUser)
        const selectedUser = computed(()=>store.getters.selectedUser)

        const test = async () => {
            state.isProfileMine = await store.dispatch("isProfileMine")
        }
        
        test()

        return { loginUser, selectedUser, state }
    },
}
</script>

<style lang="scss">

</style>