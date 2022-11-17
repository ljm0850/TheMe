<template>
<div class="modal fade" id="settingModal" tabindex="-1" aria-labelledby="settingModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="settingModalLabel">프로필</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <span style="line-height:50%"><br></span>
                <div id="profileImage">프로필 사진</div>
                <span style="line-height:50%"><br></span>
                <div id="profileImage">
                    <img :src="selectedUser.picture" alt="" class="test">
                </div>
                <!-- <img v-if="!state.profileChanged"  :src="selectedUser.picture" alt="" class="test"> -->
                <span style="line-height:50%"><br></span>
                <input type="file" accept="image/*" @change="fileChange"/>
                
                <span style="line-height:50%"><br></span>
                <span style="line-height:50%"><br></span>

                <div id="profileImage">닉네임<input type="text" class="form-control" id="" :placeholder="selectedUser.nickname"  @input="getDuplicateNickname"></div>
                
                <div v-if="state.inputNicknameText.length  >= 1">
                    <div class="redColor" v-if="state.inputNicknameText == selectedUser.nickname">
                        기존 닉네임과 같습니다.
                    </div>
                    <div class="blueColor" v-else-if = isPossible>
                        사용 가능한 닉네임입니다.
                    </div>
                    <div class = "redColor" v-else-if = !isPossible >
                        사용 불가능한 닉네임입니다.
                    </div>
                </div>
                <span style="line-height:50%"><br></span>
            <!-- v-if문으로 띄우기 눌렀느지 안눌렀는지는 여기 스테이트에서 처리 설정버튼 눌렀을때 false로초기화-->
                <div>자기 소개 <input type="text" class="form-control" id="" :placeholder="selectedUser.description" @input="updateDescription"></div>
            </div>
            <div class="modal-footer">
                <button v-if="state.isChanged" type="button" class="btn btn-secondary" data-bs-dismiss="modal" @click="updateUserInfo">Update</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" @click="logout">LogOut</button>
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
import { profileImageUpload } from "@/store/firebase/firebase";
export default {
    props:{
        userInfo:Object
    },
    components: {
    },
    setup() {
        const store = useStore();
        const router = useRouter();
        const state : any= reactive({
            inputNicknameText : "",
            inputDescriptionText : "",
            isChanged : false,
            nickname : "",
            selectFile : {},
            url : "",
            profileChanged : false,
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
            console.log(state.inputNicknameText)
            store.dispatch("updateUserInfo", {description: state.inputDescriptionText, nickname : state.inputNicknameText, picture : state.url})
            router.push({
                name: "Profile", 
                params: { 
                    nickname : state.inputNicknameText,
                } 
            })
        } 
        
        const logout = () => {
            store.dispatch("logout")
            router.push({
                name:"Main"
            })
        }
        
        const fileChange = (e: any) => {
        // console.log(e.target.files)
        state.selectFile = e.target.files;
        
            for (let i = 0; i < state.selectFile.length; i++) {
                let reader = new FileReader();
                reader.onload = (e: any) => {
                    createPreview(e.target?.result);
                };
                reader.readAsDataURL(state.selectFile[i]);
            }

            state.url = profileImageUpload(e.target.files[0].name, e.target.files[0]);
        
            state.selectFile = e.target.files;
            state.profileChanged = true
            state.isChanged = true
        };

        const createPreview = (_img: string) => {
            console.log("여기옴?")
            let imageDiv = document.querySelector('#profileImage')
            while (imageDiv?.firstChild) {
                console.log("?????")
                imageDiv.firstChild.remove();
            }
            const newImg = document.createElement('img')
            newImg.src = _img
            imageDiv?.append(newImg)
        };   
        
        return { state,getDuplicateNickname, isPossible, updateUserInfo, selectedUser, updateDescription, logout, fileChange}
    }
}
</script>

<style lang="scss">
.test{
    border-radius: 200px;
    
}
#profileImage {
    text-align: center;
}

.redColor {
    color: red;
}
.blueColor {
    color:blue
}
</style>