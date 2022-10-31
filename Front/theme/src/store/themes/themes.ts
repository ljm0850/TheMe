// import router from '@/router'
import rest from '@/API/rest'
import axios from "axios";
// import { ActionContext } from 'vuex';
import { Commit, Dispatch } from 'vuex';

export default {
    state: {
        feedTheme: [],
        feedRecommendTheme: []
    },
    getters: {
        getFeedTheme: (state: { feedTheme: Array<object>; }) => state.feedTheme,
        getFeedRecommendTheme: (state: { feedRecommendTheme : Array<object>}) => state.feedRecommendTheme
    },
    mutations: {
        SET_FEED_THEME: (state: { feedTheme: Array<object>; }, _feedTheme: Array<object>) => state.feedTheme = _feedTheme,
        SET_FEED_RECOMMEND_THEME: (state: { feedRecommendTheme: Array<object>}, _feedRecommendTheme: Array<object>) => state.feedRecommendTheme = _feedRecommendTheme
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
                commit("SET_FEED_RECOMMEND_THEME",res.data)
            })
        },



    }
}