<template>
    <div v-if="person">
      <!-- {{person}} -->
      <div :class="[person.isSame ? 'isSame card' : 'card']" @click="moveProfile()">
        <div class="card-body d-flex">
          <img :src="person.picture" alt="" class="user-profile">
          <div class="content">
            {{ person.nickname }} 
            <span class="position-absolute end-0 follow-count">👨‍👦{{ person.followCount }}</span>
            <span class="position-absolute end-0 board-count">📜{{ person.boardCount }}</span>
          </div>
        </div>
        
      </div>
    </div>
  </template>
  
  <script lang="ts">
  import { useStore } from "vuex";
  import { computed } from '@vue/reactivity'
  import { useRouter } from "vue-router";
  export default {
    props: {
      person: Object
    },
    components: {
    },
    setup(props: any) {
      const router = useRouter();
      const moveProfile = () => {
  
        router.push({
          name: "Profile",
          params: {
            nickname: props.person.nickname,
          }
        })
        store.dispatch("getUserInfoByNickname", props.person.nickname);
      }
      const store = useStore();
      const selectedUser = computed(() => store.getters.loginUser)
      return { selectedUser, moveProfile }
    }
  }
  </script>
  
  <style lang="scss" scoped>
  .content{
    margin-top: 8px;
    margin-left: 8px;
  }
  .follow-count{
    margin-right: 45px;
  }
  .board-count{
    margin-right: 8px;
  }
  .more-size {
    transform: translate(-50%, -50%);
    text-align: center;
    position: absolute;
    top: 80%;
    left: 65%;
    color: white;
    font-size: 30px;
    -webkit-text-stroke: 1px rgb(182, 182, 182);
  }
  
  .more-img {
    filter: blur(1px);
    -webkit-filter: blur(1px);
  }

  
  .isSame {
    border: 3px solid #bddaff !important;
  
  }
  
  .btn {
    padding: 0px;
    margin: 0px;
  }
  
  .card {
    width: 360px;
    border-radius: 100px;
    margin-left: 15px;
    margin-top: 10px;
    background: #FAFAFA;
    border: 1px solid #CDCDCD;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  }
  
  
  .card-body {
    margin-top: 8px;
    margin-left: 8px;
    margin-bottom: 8px;
    padding: 0px;
  }
  
  @media screen {
    .card-total {
      margin: 10px;
      max-width: 390px;
  
      .best-img {
        border-radius: 8px;
        width: 131px;
        height: 131px;
        // margin-right: 1vh;
      }
  
      .default-img {
        // margin-left: 1px;
        border-radius: 8px;
        margin-bottom: 3px;
        margin-right: 3px;
        width: 64px;
        height: 64px;
  
      }
  
      .more-img {
        opacity: 0.3;
      }
  
    }
  
    .user-profile {
      width: 36px;
      height: 36px;
      border-radius: 30%;
    }
  
    .view-info-text {
      font-size: 1.5vh;
      text-align: center;
      width: 56px;
      height: 56px;
    }
  
    .view-info-img {
      padding-top: 15px;
      font-size: 15px;
      text-align: center;
    }
  
    .view-info {
      width: 56px;
      height: 56px;
    }
    .mt-1{
      vertical-align: center !important;
    }
  }
  </style>