<template>
<div class="modal fade" id="settingModal" tabindex="-1" aria-labelledby="settingModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="settingModalLabel">프로필</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div>닉네임<input type="text" class="form-control" id="" :placeholder="selectedUser.nickname"  @input="getDuplicateNickname"></div>
                
                <div v-if="state.inputNicknameText.length  >= 1">
                    <div v-if="state.inputNicknameText == selectedUser.nickname">
                        기존 닉네임과 같습니다.
                    </div>
                    <div v-else-if = isPossible>
                        사용 가능한 닉네임입니다.
                    </div>
                    <div v-else-if = !isPossible >
                        사용 불가능한 닉네임입니다.
                    </div>
                </div>

            <!-- v-if문으로 띄우기 눌렀느지 안눌렀는지는 여기 스테이트에서 처리 설정버튼 눌렀을때 false로초기화-->
                <div>자기 소개 <input type="text" class="form-control" id="" :placeholder="selectedUser.description" @input="updateDescription"></div>
            </div>
            <div class="modal-footer">
                <button v-if="state.isChanged" type="button" class="btn btn-secondary" data-bs-dismiss="modal" @click="updateUserInfo">변경</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
            
        </div>
    </div>
</div>
</template>

<script lang="ts">
// import { useStore } from "vuex";
import { reactive } from "vue";
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
        const state = reactive({
            inputNicknameText : "",
            inputDescriptionText : "",
            isChanged : false,
            nickname : "",
        });

        const isPossible = computed(()=>store.getters.duplicationnickname)
        const selectedUser = computed(()=>store.getters.selectedUser)
        
        const getDuplicateNickname = (e:any) => {
            state.inputNicknameText = e.target.value
            store.dispatch("duplicationnickname", state.inputNicknameText),
            state.isChanged = true;
        }
        
        const updateDescription = (e:any) => {
            state.inputDescriptionText = e.target.value
            state.isChanged = true;
            console.log(state.isChanged)
        }

        const updateUserInfo = () => {
            store.dispatch("updateUserInfo", {description: state.inputDescriptionText, nickname : state.inputNicknameText, picture : "https://velog.velcdn.com/images%2Fjini_eun%2Fpost%2F107f5cfb-e97c-4c4c-b997-06098062e5b3%2Fimage.png"})
            router.push({
                name: "Profile", 
                params: { 
                    nickname : state.inputNicknameText,
                } 
            })
        } 
        
        return { state,getDuplicateNickname, isPossible, updateUserInfo, selectedUser, updateDescription}
    }
}
</script>

<style lang="scss">

</style>