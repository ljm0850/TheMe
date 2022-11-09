<template>
<div class="card" style="border-radius: 12px;">
  <div class="card-body" style="padding: 0px;">
    <div class="d-flex">
      <img :src="selectedUser.picture" alt="" class="user-profile">
      <div class="ms-2">{{selectedUser.nickname}}</div>
      <button class="btn position-absolute top-0 end-0">🤍</button>
    </div>
  </div>
  <div class="d-flex card-total">
    <img src="https://hobbyen.co.kr/news/data/20190923/p179512992441679_996.png" alt="" class="best-img">

    <div class="d-flex">
      <div class='row'>
  <div class='col-sm-6' style="padding: 0px;"> 
        <img src="https://s3-ap-northeast-2.amazonaws.com/mp-seoul-image-production/598583_1566712699742938.jpg?fit=around|600:*&crop=600:*;*,*&output-format=jpg&output-quality=80" alt="" class="default-img">
        <img src="https://mp-seoul-image-production-s3.mangoplate.com/454608/449685_1623483714472_41747" alt="" class="default-img">
      </div> 
  <div class='col-sm-6' style="padding: 0px; ">   
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRiFZOqzQeHKyL1eA4JoksLk1COqIWmMs5rVw&usqp=CAU" alt="" class="default-img">
        <img src="https://blog.kakaocdn.net/dn/QLxeI/btqBOC35A9Z/ykuIhpLRqEGLLGPz6V6oE0/img.jpg" alt="" class="default-img more-img">
      </div>
</div>
      
  
    </div>
    <div class="view-info">
      <div class="default-img btn-type"><div class="view-info-img">👨‍👦</div> <div class="view-info-text">&nbsp;&nbsp;2.7K</div></div>
      <div class="default-img btn-type"><div class="view-info-img">💬</div> <div class="view-info-text">&nbsp;&nbsp;1.3M</div></div>
    </div>
    <!-- <div class="d-flex">
      <div class="small-img">
        <img src="https://s3-ap-northeast-2.amazonaws.com/mp-seoul-image-production/598583_1566712699742938.jpg?fit=around|600:*&crop=600:*;*,*&output-format=jpg&output-quality=80" alt="" class="default-img">
        <img src="https://mp-seoul-image-production-s3.mangoplate.com/454608/449685_1623483714472_41747" alt="" class="default-img">
      </div>
      <div class="small-img">
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRiFZOqzQeHKyL1eA4JoksLk1COqIWmMs5rVw&usqp=CAU" alt="" class="default-img">
        <img src="https://blog.kakaocdn.net/dn/QLxeI/btqBOC35A9Z/ykuIhpLRqEGLLGPz6V6oE0/img.jpg" alt="" class="default-img">
      </div>
    </div>
    <div>
      <div class="default-img"><div class="view-info-img">👨‍👦</div> <div class="view-info-text">2.7K</div></div>
      <div class="default-img"><div class="view-info-img">💬</div> <div class="view-info-text">1.3M</div></div>
    </div> -->
  </div>
</div>

</template>

<script lang="ts">
import { useStore } from "vuex";
import { computed } from '@vue/reactivity'
export default {
  components: {
  },
  setup (){
    const store = useStore();
    const selectedUser = computed(() => store.getters.loginUser)
    return { selectedUser}
  }
}
</script>

<style lang="scss">
.btn{
  padding: 0px;
  margin: 0px;
}
.card{
  width: 360px;
  border-radius: 100px;
  margin-left : 15px;
  margin-top: 10px;
  background: #FAFAFA;
border: 1px solid #CDCDCD;
box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
.col-sm-6{
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