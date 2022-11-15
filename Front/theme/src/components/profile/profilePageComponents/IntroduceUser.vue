<template>
<div>
    <div class="d-flex justify-content-end">
        <button v-if="isMyProfile" type="button" data-bs-toggle="modal" data-bs-target="#settingModal">🛠</button>
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
            <div class="inroduce-margin"  data-bs-toggle="modal" data-bs-target="#followerModal">
                <div>Follower</div>
                <div>
                    {{selectedUser.following}} 
                    <!-- <button type="button" data-bs-toggle="modal" data-bs-target="#followerModal">🔍</button> -->
                </div>
                <FollowerModalVue :userInfo="selectedUser"/>
                
            </div>
            
            <div class="inroduce-margin" data-bs-toggle="modal" data-bs-target="#followingModal">
                <div>Following</div>
                <div>
                    {{selectedUser.follower}} 
                    <!-- <button type="button" data-bs-toggle="modal" data-bs-target="#followingModal">🔍</button> -->
                </div> 
                <FollowingModalVue :userInfo="selectedUser"/>
            </div>
        </div>
    </div>
</div>
</template>

<script lang="ts">
import SettingModalVue from './SettingModal.vue'
import FollowerModalVue from './FollowerModal.vue'
import FollowingModalVue from './FollowingModal.vue'
// import { useRoute } from 'vue-router'
import { reactive } from "vue";
import { useStore } from "vuex";
import { computed } from '@vue/runtime-core';

export default {
    components: {
        SettingModalVue,
        FollowerModalVue,
        FollowingModalVue
    },
    setup() {
        const state = reactive({
            isProfileMine : false,
            isOpenFollower : false,
        })
        // 추후에 nickname으로 selectedUser 갱신
        // const route = useRoute();
        // let nickname: string | string[] = route.params.nickname
        const store = useStore();
        
        // const selectedUser = computed(() => store.getters.selectedUser)
        const loginUser = computed(() => store.getters.loginUser)
        const selectedUser = computed(()=>store.getters.selectedUser)
        const isMyProfile = computed(()=>store.getters.sameUser)
        
        const test = async () => {
            state.isProfileMine = await store.dispatch("isProfileMine")
        }
        
        test()
        return { loginUser, selectedUser, state,isMyProfile }
    },
}
</script>

<style lang="scss" scoped>
.test{
    border-radius: 200px;
}
</style>