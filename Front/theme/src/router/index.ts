import { createWebHistory, createRouter } from "vue-router";
import MainPage from "@/components/main/MainPage.vue"
import ThemeListPage from "@/components/theme/ThemeList.vue";
import ProfilePage from "@/components/profile/ProfilePage.vue"
import SearchPage from "@/components/search/SearchPage.vue"
import CreateArticlePage from "@/components/articles/CreateArticlePage.vue"
import PlusThemePage from "@/components/theme/PlusThemePage.vue"
import ThemeDetail from "@/components/theme/ThemeDetail.vue"
import SearchProfile from "@/components/search/SearchProfile.vue";
import SearchTheme from "@/components/search/SearchTheme.vue";
import FirebaseTestVue from "@/components/FirebaseTest.vue";
const routes = [
  // 메인
  {
    path: "/",
    // alias: ["/home"],
    name: "Main",
    component: MainPage,
  },
  // 테마
  {
    path: "/themelist",
    name: "ThemeList",
    component: ThemeListPage,
  },
  {
    path: "/theme/public/:themeName",
    name: "PublicTheme",
    component: ThemeDetail,
  },
  {
    path: "/PlusTheme",
    name: "PlusTheme",
    component: PlusThemePage,
  },
  // 검색
  {
    path: "/search",
    name: "Search",
    component: SearchPage,
  },
  {
    path: "/search/profile",
    name: "SearchProfile",
    component: SearchProfile,
  },
  {
    path: "/search/theme",
    name: "SearchTheme",
    component: SearchTheme,
  },
  // 게시글
  {
    path: "/createArticle",
    name: "CreateArticle",
    component: CreateArticlePage,
  },
  //프로필
  {
    path: "/profile/:nickname",
    name: "Profile",
    component: ProfilePage,
  },
  {
    path: "/test",
    name: "Test",
    component: FirebaseTestVue,
  }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
  });

export default router;