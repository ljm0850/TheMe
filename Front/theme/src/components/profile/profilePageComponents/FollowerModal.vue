<template>
<div class="modal fade" id="followerModal" tabindex="-1" aria-labelledby="followerModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="followerModalLabel">Follower</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div v-for="follower in followerList" :key="follower" :value="follower" data-bs-dismiss="modal">
                    <div @click = "moveProfile(follower.nickname)" >
                        <img :src="follower.picture" alt="" class="user-profile">
                        {{follower.nickname}}
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
    setup() {
        const store = useStore();
        const router = useRouter();
        const selectedUser = computed(()=>store.getters.selectedUser)
        // store.dispatch("getFollowerList", props.userInfo.userIdx)
        
        const followerList = computed(()=> store.getters.followerList)

        const moveProfile= (nickname:string) => {
            router.push({
                name: "Profile", 
                params: { 
                    nickname : nickname,
                } 
            })
            // store.dispatch("getUserInfoByNickname",nickname)
            // store.dispatch("getFollowerList", followerIdx)
            // store.dispatch("getFollowingList", followerIdx)
        } 
        return {  selectedUser, followerList, moveProfile}
    }
}
</script>

<style lang="scss">

</style>