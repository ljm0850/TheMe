<template>
  <div v-if="person">
    <div :class="[person.isSame ? 'isSame card' : 'card']" @click="moveProfile()">
      <div class="card-body" style="padding: 0px;">
        <div class="d-flex">
          <img :src="person.picture" alt="" class="user-profile">
          <div class="mt-1">{{ person.nickname }} </div>

        </div>
      </div>
      <div v-if="person.pictures" class="d-flex card-total">
        <img :src="person.pictures[0]" alt="" class="best-img">
        <div class="d-flex">
          <div class='row'>
            <div class='col-sm-6' style="padding: 0px;">
              <img v-if="person.pictures[1]"
                :src="person.pictures[1]"
                alt="" class="default-img">
              <img v-if="person.pictures[2]"
                :src="person.pictures[2]"
                alt="" class="default-img">
            </div>
            <div class='col-sm-6' style="padding: 0px; ">
              <img v-if="person.pictures[3]"
                :src="person.pictures[3]"
                alt="" class="default-img">
              <img v-if="person.pictures[4]"
                :src="person.pictures[4]"
                alt="" class="default-img">
            </div>
          </div>
        </div>
        <div class="view-info">
        <div class="default-img btn-type">
          <div class="view-info-img">👨‍👦</div>
          <div class="view-info-text">&nbsp;&nbsp;{{ person.followCount }}</div>
        </div>
        <div class="default-img btn-type">
          <div class="view-info-img">💬</div>
          <div class="view-info-text">&nbsp;&nbsp;{{ person.commentCount }}</div>
        </div>
      </div>
      </div>
      <div v-else>
        등록된 게시글이 없습니다.
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

<style lang="scss">
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

.col-sm-6 {
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
}
</style>