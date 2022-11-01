// import router from '@/router'
import rest from '@/API/rest'
import axios from "axios";
// import { ActionContext } from 'vuex';
import { Commit, Dispatch } from 'vuex';

export default {
    state: {
        feedTheme: [],
        feedRecommendThemeList1: [{title:"코딩하기 좋은 까페"},{title:"낮잠자기 좋은 까페"},{title:"짧"}],
        feedRecommendThemeList2: [{title:"프로젝트 하기 좋은 싸피"},{title:"수완지구 분위기 있는 레스토랑"},{title:"다"}],
    },
    getters: {
        getFeedTheme: (state: { feedTheme: Array<object>; }) => state.feedTheme,
        getFeedRecommendThemeList1: (state: { feedRecommendThemeList1 : Array<object>}) => state.feedRecommendThemeList1,
        getFeedRecommendThemeList2: (state: { feedRecommendThemeList2 : Array<object>}) => state.feedRecommendThemeList2,
    },
    mutations: {
        SET_FEED_THEME: (state: { feedTheme: Array<object>; }, _feedTheme: Array<object>) => state.feedTheme = _feedTheme,
        SET_FEED_RECOMMEND_THEME_List1: (state: { feedRecommendThemeList1: Array<object>}, _feedRecommendThemeList: Array<object>) => state.feedRecommendThemeList1 = _feedRecommendThemeList,
        SET_FEED_RECOMMEND_THEME_List2: (state: { feedRecommendThemeList2: Array<object>}, _feedRecommendThemeList: Array<object>) => state.feedRecommendThemeList2 = _feedRecommendThemeList,
    },
    actions: {
        getFeedTheme({ commit,getters }: { commit: Commit, getters: any }, _region:number) {
            axios({
                url: rest.Feed.feedList(),
                method: 'get',
                headers: getters.authHeader,
                params: { region: _region}
            })
                .then((res) => {
                    commit("SET_FEED_THEME",res.data)    
            })
        },
        getRecommendFeedTheme({ commit,getters }: { commit: Commit, getters: any }) {
            axios({
                url: rest.Feed.recommendTheme(),
                method: 'get',
                headers: getters.authHeader,
            })
                .then((res) => {
                commit("SET_FEED_RECOMMEND_THEME_List1",res.data)
                commit("SET_FEED_RECOMMEND_THEME_List2",res.data)
            })
        },



    }
}