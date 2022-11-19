<template>
  <div class="themeDetail-wrap">
    <div class="theme-header">
      <div class="theme-title-box">
        <div data-bs-toggle="modal" data-bs-target="#settingThemeModal">
          <div v-if="state.isMine" class="settingbutton">⚙</div>
        </div>
        <div>
          <div v-show="!state.isMine" v-if="!state.isFollow" @click="clickFollow()" class="followbutton">🤍</div>
        </div>
        <div>
          <div v-show="!state.isMine" v-if="state.isFollow" @click="clickFollow()" class="unfollowbutton">💙</div>
        </div>
        <div class="theme-title-text">{{ themeDetail.emoticon }}</div>
        <span class="theme-title-text">{{ themeDetail.name }}</span>
      </div>
    </div>
    <br>
    <SettingThemeModalVue :themeDetail="themeDetail"/>
    <br>
    <KakaoMapVue :articleList="articleList"/>
    <div class="d-flex justify-content-end">
      <span class="user-theme">{{selectedUser.nickname}}님의 테마</span>
    </div>
    <br>
    <br>
    <ArticleListVue
      class="article-list"
      :publicThemeIdx="publicThemeIdx"
      :articleList="articleList"
      page="user"
    />
    <br>
  </div>
</template>

<script lang="ts">
import ArticleListVue from "@/components/articles/ArticleList.vue";
import KakaoMapVue from "../map/ThemeMap.vue";
import SettingThemeModalVue from "@/components/theme/SettingThemeModal.vue";
import { computed, reactive } from "vue";
import { useRoute} from "vue-router";
import { useStore } from "vuex";
export default {
  components: {
    ArticleListVue,
    KakaoMapVue,
    SettingThemeModalVue
  },
  setup() {
    const store = useStore();
    const route = useRoute();

    const publicThemeIdx = route.params.publicThemeIdx;
    const userThemeIdx = route.params.userThemeIdx;

    store.dispatch("detailUserTheme", userThemeIdx);

    const themeDetail = computed(() => store.getters.userThemeDetail);

    const param = reactive({
      themeIdx: userThemeIdx,
      pageSize: 10,
      pageIdx: 0,
    });

    // console.log(themeDetail.value);

    store.dispatch("getUserThemeArticleList", param);
    const articleList = computed(() => store.getters.userThemeArticleList);

    // console.log(articleList.value);
    // console.log(themeDetail.value);

    const state = reactive({
      isFollow: themeDetail.value.follow,
      isMine : themeDetail.value.mine,
    });

    const clickFollow = () => {
      if (state.isFollow) {
        // 팔로우 취소
        store.dispatch("cancelFollow", userThemeIdx);
      } else {
        // 팔로우 하기
        const follow_param = reactive({
          themeId: userThemeIdx,
          targetUserId: articleList.value[0].userIdx,
        });
        store.dispatch("followTheme", follow_param);
      }
      state.isFollow = !state.isFollow;
      // console.log(state.isFollow);
    };

    const clickSetting = () => {
      
    };

    const isfollow = () => {
      state.isFollow = themeDetail.value.follow;
      state.isMine = themeDetail.value.mine;
    };

    setTimeout(() => isfollow(), 200);

    const selectedUser = computed(()=>store.getters.selectedUser)

    return { themeDetail, state, clickFollow, userThemeIdx, articleList, publicThemeIdx, clickSetting,selectedUser };
  },
};
</script>

<style scoped>
.themeDetail-wrap{
  position: relative !important;
  top: -25px !important;
}

.theme-header {
  position: relative;
  top: 25px;
  z-index: 1;
  height: 60px;
  background: rgba(255, 255, 255, 0.8);
  border-radius: 5px;
  align-items: center;
}
.theme-title-text {
  display: inline;
  position:relative;
  left:12px !important;
  margin: 5px;
  font-size: 20px;
}

.theme-title-box {
  padding-top: 20px;
  padding-left: 10px;
  width:380px;
}

.emtpyBookmark {
  width: 15px;
  height: 15px;
}

.fillBookmark {
  width: 22px;
  height: 19px;
}

.followbutton {
  position: absolute;
  top: 18px;
  left: 8px;
}
.unfollowbutton {
  position: absolute;
  top: 18px;
  left: 8px;
}

.settingbutton{
  position: absolute;
  top: 18px;
  left: 8px;
  font-size: 20px;
}

.theme-sort {
  position: relative;
  top: -25px;
  left: 255px;
}
.theme-sort button {
  background: rgba(255, 255, 255, 0.8);
  border: 1px solid #c7c7c7;
  width: 65px;
  height: 30px;
  font-weight: 400;
  font-size: 15px;
}
.kakao-map {
  top: -40px;
  position: relative;
  z-index: -1;
}
.user-theme {
  top: -35px;
  /* left: 345px; */
  text-align: end;
  position: relative;
  z-index: 1;
  box-sizing: border-box;
  background: #ffffff;
  /* width: 40px;
  height: 40px; */
  /* opacity: 0; */
  border-radius: 5%;
  border: 1px solid #cdcdcd;
  background-color:transparent;
}
.article-list {
  position: relative;
  top: -15px !important;
}
</style>
