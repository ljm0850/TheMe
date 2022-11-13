<template>
<div class="modal fade" id="followingModal" tabindex="-1" aria-labelledby="followingModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="followingModalLabel">Following</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div v-for="following in followingList" :key="following" :value="following">
                    <div @click="moveProfile(following.nickname)">
                        <img :src="following.picture" alt="" class="user-profile">
                        {{following.nickname}}
                    </div>
                    
                </div>
                
    
            <!-- v-if문으로 띄우기 눌렀느지 안눌렀는지는 여기 스테이트에서 처리 설정버튼 눌렀을때 false로초기화-->
            <!-- <div>자기 소개 <input type="text" class="form-control" id="" :placeholder="selectedUser.description" @input="updateDescription"></div> -->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</template>

<script lang="ts">
// import { useStore } from "vuex";

import { computed } from "@vue/reactivity";
import { useStore } from "vuex";
import { useRouter } from 'vue-router'
export default {
    props:{
        userInfo:Object
    },
    components: {
    },
    setup(props:any) {
        const store = useStore();
        const router = useRouter();
        const selectedUser = computed(()=>store.getters.selectedUser)
        store.dispatch("getFollowingList", props.userInfo.userIdx)
        
        const followingList = computed(()=> store.getters.followingList)

        const moveProfile= (nickname:string) => {
            router.push({
                name: "Profile", 
                params: { 
                    nickname : nickname,
                } 
            })
        } 
        return {  selectedUser, followingList, moveProfile}
    }
}
</script>

<style lang="scss">

</style>