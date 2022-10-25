import { createWebHistory, createRouter } from "vue-router";
import MainPage from "@/components/main/MainPage.vue"
import MapPage from "@/components/map/MapPage.vue"
import ProfilePage from "@/components/profile/ProfilePage.vue"
import SearchPage from "@/components/search/SearchPage.vue"
import CreateArticlePage from "@/components/createArticle/CreateArticlePage.vue"
import CreateThemePage from "@/components/createTheme/CreateThemePage.vue"

const routes = [
    {
      path: "/",
      // alias: ["/home"],
      name: "Main",
      component: MainPage,
    },
    {
      path: "/map",
      name: "Map",
      component: MapPage,
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
]

const router = createRouter({
    history: createWebHistory(),
    routes,
  });

export default router;