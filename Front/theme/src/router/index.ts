import { createWebHistory, createRouter } from "vue-router";
import MainPage from "@/components/main/MainPage.vue"
import ThemeListPage from "@/components/theme/ThemeList.vue";
import ProfilePage from "@/components/profile/ProfilePage.vue"
import SearchPage from "@/components/search/SearchPage.vue"
import CreateArticlePage from "@/components/articles/CreateArticlePage.vue"
import CreateThemePage from "@/components/theme/createTheme/CreateThemePage.vue"
import ThemeDetail from "@/components/theme/ThemeDetail.vue"

const routes = [
  // 네브바에 의해 이동
  {
    path: "/",
    // alias: ["/home"],
    name: "Main",
    component: MainPage,
  },
  {
    path: "/themelist",
    name: "ThemeList",
    component: ThemeListPage,
  },
  {
    path: "/search",
    name: "Search",
    component: SearchPage,
  },
  {
    path: "/createArticle",
    name: "CreateArticle",
    component: CreateArticlePage,
  },
  {
    path: "/createTheme",
    name: "CreateTheme",
    component: CreateThemePage,
  },
  {
    path: "/profile/:nickname",
    name: "Profile",
    component: ProfilePage,
  },
  // 그 외 이동
  {
    path: "/theme/public/:themeName",
    name: "PublicTheme",
    component: ThemeDetail,
  }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
  });

export default router;