<template>
  <div class="card-custom card">
    <div class="card-body" v-if="theme">
      <div @click="clickTheme()">{{ theme.emoticon }}</div>
      <div class="card-title" @click="clickTheme()">{{ theme.name }}</div>
      <div
        v-show="state.isTheme"
        v-if="!state.isMarked"
        class="bookmark"
        @click="clickBookmark()"
      >
        <img
          src="@/assets/image/emptyBookmark.png"
          alt=""
          class="emtpyBookmark"
        />
      </div>
      <div
        v-show="state.isTheme"
        v-if="state.isMarked"
        class="bookmark"
        @click="clickBookmark()"
      >
        <img
          src="@/assets/image/fillBookmark.png"
          alt=""
          class="fillBookmark"
        />
      </div>
      <div class="position-absolute top-0 end-0 userCount">
        <div class="card-count">👤</div>
        <div class="card-count">{{ theme.userCount }}</div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { reactive } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
export default {
  components: {},
  props: {
    page: String,
    theme: Object,
  },
  setup(props: any) {
    const store = useStore();
    const router = useRouter();

    const state = reactive({
      isMarked: false,
      isTheme: false,
    });

    const clickBookmark = () => {
      if (state.isMarked) {
        // 북마크 취소
        store.dispatch("unScrapTheme", props.theme.idx);
      } else {
        // 북마크 하기
        store.dispatch("scrapTheme", props.theme.idx);
      }

      state.isMarked = !state.isMarked;
      console.log(state.isMarked);
    };

    const isThemePage = () => {
      if (props.page == "themeList") {
        state.isMarked = props.theme.bookmarked;
        state.isTheme = true;
      }
    };

    isThemePage();

    const clickTheme = () => {
      router.push({
        name: "PublicTheme",
        params: {
          themeIdx: props.theme.idx,
        },
      });
    };
    return { clickTheme, state, clickBookmark };
  },
};
</script>

<style scoped lang="scss">
.card-custom {
  display: flex;
  align-items: center;
  justify-content: center;
  // flex-wrap: wrap;
  margin: 5px;
  width: 180px;
  height: 100px !important;
  background: #ffffff;
  border: 1px solid rgba(205, 205, 205, 0.81);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 10px !important;
}
.emtpyBookmark {
  width: 15px;
  height: 15px;
}

.fillBookmark {
  width: 22px;
  height: 19px;
  margin-left: -3px;
}

.bookmark {
  position: absolute;
  top: 5px;
  left: 10px;
}

.userCount {
  margin: 5px;
}
.card-body {
  text-align: center;
}
.card-title {
  font-size: 16px;
  font-weight: 600;
}
.card-count {
  display: inline;
  margin: 3px;
  font-size: 8px;
}
</style>
