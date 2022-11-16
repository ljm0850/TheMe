<template>
  <div v-if="theme" :class="[theme.isSame ? 'isSame card' : 'card']">
    <div class="card-body" style="padding: 0px;">
      <div class="d-flex">
        <div>{{ theme.emoticon }}</div>
        <div class="ms-2">{{ theme.name }}</div>
      </div>
    </div>
    <!-- {{theme}} -->
    <div  v-if="theme.pictures.length > 0" class="d-flex card-total">
      <img :src="theme.pictures[0]" alt="" class="best-img">
      <div class="d-flex">
        <div class='row'>
            <div class='col-sm-6' style="padding: 0px;">
              <img v-if="theme.pictures[1]" :src="theme.pictures[1]" alt="" class="default-img">
              <img v-if="theme.pictures[2]" :src="theme.pictures[2]" alt="" class="default-img">
            </div>
            <div class='col-sm-6' style="padding: 0px; ">
              <img v-if="theme.pictures[3]" :src="theme.pictures[3]" alt="" class="default-img">
              <span v-if="theme.pictures.length >= 5">
                <img v-if="theme.pictures[4]" :src="theme.pictures[4]" alt="" class="more-img default-img">
                <div class="more-size">+{{ theme.pictures.length }}</div>
              </span>
              <span v-else>
                <img v-if="theme.pictures[4]" :src="theme.pictures[4]" alt="" class="default-img" />
              </span>
            </div>

          </div>
        <div class="view-info">
          <div class="default-img btn-type">
            <div class="view-info-img"><br><br>💬</div>
            <div class="view-info-text">&nbsp;&nbsp;{{ theme.boardCount }}</div>
          </div>
        </div>
      </div>
    </div>
    <div v-else>
        테마에 게시글이 없어요. <br> 
        하단 플러스 버튼으로 게시글을 추가해주세요!
      </div>
  </div>
</template>

<script lang="ts">
import { computed } from "@vue/reactivity";
import { useStore } from "vuex";

export default {
  props: {
    theme: Object
  },
  components: {
  },
  setup() {

    const store = useStore();
    const selectedUser = computed(() => store.getters.selectedUser)
    const loginUser = computed(() => store.getters.loginUser)


    return { selectedUser, loginUser }
  }
}
</script>

<style lang="scss">
.isSame {
  border: 3px solid #bddaff !important;

}

.btn {
  padding: 0px;
  margin: 0px;
}

.card {
  border-radius: 12px !important;
  width: 360px;
  margin-left: 15px;
  margin-top: 10px;
  background: #FAFAFA;
  border: 1px solid #CDCDCD;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}

.col-sm-6 {
  padding: 0px !important;
  text-align: center;

}

.card-body {
  margin-top: 8px;
  margin-left: 8px;
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
    width: 4vh;
    height: 4vh;
    border-radius: 70%;
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
}
</style>