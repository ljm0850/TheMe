<template>
    <div  v-if="theme" :class="[theme.isSame ? 'isSame card' : 'card']">
        <div class="card-body" style="padding: 0px;">
            <div class="d-flex">
                <div>{{theme.emoticon}}</div>
                    <div class="ms-2" @click="clickTheme(theme.userThemeIdx, theme.themeIdx)">{{theme.name}}</div>
                    <div v-if="!state.isSame">
                        <button v-if="!state.isFollow" @click="addFollow" class="btn position-absolute top-0 end-0">🤍</button>
                        <button v-if="state.isFollow" @click="cancelFollow" class="btn position-absolute top-0 end-0">💙</button>
                    </div>
                    
                </div>
            </div>
            <div class="d-flex card-total"  @click="clickTheme(theme.userThemeIdx, theme.themeIdx)">
                <img src="https://hobbyen.co.kr/news/data/20190923/p179512992441679_996.png" alt="" class="best-img">
                <div class="d-flex">
                    <div class='row'>
                        <div class='col-sm-6' > 
                        <img src="https://s3-ap-northeast-2.amazonaws.com/mp-seoul-image-production/598583_1566712699742938.jpg?fit=around|600:*&crop=600:*;*,*&output-format=jpg&output-quality=80" alt="" class="default-img">
                        <img src="https://mp-seoul-image-production-s3.mangoplate.com/454608/449685_1623483714472_41747" alt="" class="default-img">
                        </div> 
                    <div class='col-sm-6' >   
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRiFZOqzQeHKyL1eA4JoksLk1COqIWmMs5rVw&usqp=CAU" alt="" class="default-img">
                        <img src="https://blog.kakaocdn.net/dn/QLxeI/btqBOC35A9Z/ykuIhpLRqEGLLGPz6V6oE0/img.jpg" alt="" class="default-img more-img">
                    </div>
                    
                </div>
                <div class="view-info">
                    <div class="default-img btn-type"><div class="view-info-img"><br><br>💬</div> <div class="view-info-text">&nbsp;&nbsp;{{theme.boardCount}}</div></div>
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
import { useRoute} from "vue-router";

export default {
    props:{
        theme:Object
    },
    components: {
    },
    setup(props:any) {
      const store = useStore();
      const router = useRouter();
      const route = useRoute();
    
      const state = reactive({
      searchValue: "",
      isSame: false,
      isFollow : false
    })

    const test = async () => {
        state.isSame = await store.dispatch("isSame", props.theme.userIdx)
  
        state.isFollow = await store.dispatch("isFollow", { 
            userIdx : props.theme.userIdx,
            themeIdx: props.theme.userThemeIdx})
    }

    test()
    
    const selectedUser = computed(()=>store.getters.selectedUser)
    const loginUser = computed(()=>store.getters.loginUser)
      
  
    const cancelFollow = () => {
      store.dispatch("cancelFollow", props.theme.userThemeIdx)
      state.isFollow = !state.isFollow
      const userNickname = route.params.nickname;
      store.dispatch("getUserInfoByNickname",userNickname);
    }

    const addFollow = () => {
      store.dispatch("followTheme", { themeId : props.theme.userThemeIdx, targetUserId : props.theme.userIdx})
      state.isFollow = !state.isFollow
      const userNickname = route.params.nickname;
      store.dispatch("getUserInfoByNickname",userNickname);
    }
    const clickTheme = (userThemeIdx: string, publicThemeIdx: string) => {
      router.push({
        name: "UserTheme",
        params: {
          userThemeIdx: userThemeIdx,
          publicThemeIdx: publicThemeIdx,
        },
      });
    };
    return { selectedUser, loginUser, state, cancelFollow, addFollow, clickTheme }
    }
}
</script>

<style lang="scss">
.isSame{
    border : 3px solid #bddaff !important;

}
.btn{
  padding: 0px;
  margin: 0px;
}
.follow-btn{
  z-index: 10;
}
.card{
  border-radius: 12px !important;
  width: 360px;
  margin-left : 15px;
  margin-top: 10px;
  background: #FAFAFA;
  border: 1px solid #CDCDCD;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
.col-sm-6{
  padding: 0px !important;
  text-align: center;

}
.card-body{
  margin-top: 8px;
  margin-left: 8px;
}
@media screen {
  .card-total{
    margin: 10px;
    max-width: 390px;
    .best-img{
      border-radius: 8px;
      width: 131px;
      height: 131px;
      // margin-right: 1vh;
    }
    .default-img{
      // margin-left: 1px;
      border-radius: 8px;
      margin-bottom: 3px;
      margin-right: 3px;
      width: 64px;
      height: 64px;
      
    }
    .more-img{
      opacity: 0.3;
    }
    
  }
  .user-profile{
    width: 4vh;
    height: 4vh;
    border-radius: 70%;
  }
  .view-info-text{
    font-size:1.5vh;
    text-align: center;
    width: 56px;
    height: 56px;
  }
  .view-info-img{
    padding-top: 15px;
    font-size: 15px;
    text-align: center;
  }
  .view-info{
    width: 56px;
    height: 56px;
  }
}

</style>