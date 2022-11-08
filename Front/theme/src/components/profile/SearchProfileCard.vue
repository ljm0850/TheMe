<template>
<div class="card">
  <div class="card-body">
    <div class="d-flex justify-content-between">
      <img :src="selectedUser.picture" alt="" class="user-profile">
      <div>{{selectedUser.nickname}}</div>
      <button class="btn btn-light">🤍</button>
    </div>
  </div>
  <div class="d-flex card-total">
    <img src="https://hobbyen.co.kr/news/data/20190923/p179512992441679_996.png" alt="" class="best-img">

    <div class="d-flex">
      <div class='row'>
  <div class='col-sm-6'> 
        <img src="https://s3-ap-northeast-2.amazonaws.com/mp-seoul-image-production/598583_1566712699742938.jpg?fit=around|600:*&crop=600:*;*,*&output-format=jpg&output-quality=80" alt="" class="default-img">
        <img src="https://mp-seoul-image-production-s3.mangoplate.com/454608/449685_1623483714472_41747" alt="" class="default-img">
      </div> 
  <div class='col-sm-6'>   
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRiFZOqzQeHKyL1eA4JoksLk1COqIWmMs5rVw&usqp=CAU" alt="" class="default-img">
        <img src="https://blog.kakaocdn.net/dn/QLxeI/btqBOC35A9Z/ykuIhpLRqEGLLGPz6V6oE0/img.jpg" alt="" class="default-img">
      </div>
</div>
      
  
    </div>
    <div>
      <div class="default-img"><div class="view-info-img">👨‍👦</div> <div class="view-info-text">2.7K</div></div>
      <div class="default-img"><div class="view-info-img">💬</div> <div class="view-info-text">1.3M</div></div>
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
.card{
  background: #FAFAFA;
border: 1px solid #CDCDCD;
box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
border-radius: 15px;
}
.col-sm-6{
  text-align: center;

  // padding:0px;
  // margin: 0px;
}
.card-body{
  height: 7vh;
}
@media screen {
  .card-total{
    
    max-width: 390px;
    .best-img{
      
      width: 128px;
      height: 128px;
      margin-right: 1vh;
    }
    .default-img{
      width: 64px;
      height: 64px;
      
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
  }
  .view-info-img{
    font-size:3vh;
    text-align: center;
  }
}

</style>