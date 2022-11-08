<template>
<div class="modal fade" id="settingModal" tabindex="-1" aria-labelledby="settingModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="settingModalLabel">프로필</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div>별명 <input type="text" class="form-control" id="" placeholder="변경할 닉네임을 입력하세요."  v-model ="state.inputNicknameText"></div>
                <button @click="getDuplicateNickname(state.inputNicknameText)">중복검사</button>
            <!-- v-if문으로 띄우기 눌렀느지 안눌렀는지는 여기 스테이트에서 처리 설정버튼 눌렀을때 false로초기화-->
                <div>자기 소개 <input type="text" class="form-control" id="" placeholder="변경할 자기소개를 입력하세요."  v-model ="state.inputDescriptionText"></div>
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
import { reactive } from "vue";
import { computed } from "@vue/reactivity";
import { useStore } from "vuex";
export default {
    props:{
        userInfo:Object
    },
    components: {
    },
    setup() {
        const store = useStore();
        const state = reactive({
            inputNicknameText : "",
            inputDescriptionText : "",
        });

        const duplicationnickname = computed(() => store.getters.duplicationnickname)


        const getDuplicateNickname = (inputText : string) => {
            store.dispatch("duplicationnickname",inputText)
        }
        return { state,getDuplicateNickname , duplicationnickname}
    }
}
</script>

<style lang="scss">

</style>