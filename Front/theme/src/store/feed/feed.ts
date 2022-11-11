// import router from '@/router'
import rest from '@/API/rest'
// import { storage } from '../firebase/firebase'
import { Commit, Dispatch } from 'vuex';

import axios from "axios"
export default {
    state: {
        feedArticleList: [{ themeName: "tttasdgfa" }, { themeName: "임시2" }],
        feedTheme: [],
        feedRecommendThemeList1: [{title:"코딩하기 좋은 까페"},{title:"낮잠자기 좋은 까페"},{title:"짧"}],
        feedRecommendThemeList2: [{ title: "프로젝트 하기 좋은 싸피" }, { title: "수완지구 분위기 있는 레스토랑" }, { title: "다" }],
        searchPlacesList: [],
        selectedPlace: {},
    },
    getters: {
        getFeedArticleList: (state: { feedArticleList: Array<object> }) => state.feedArticleList,
        getFeedTheme: (state: { feedTheme: Array<object>; }) => state.feedTheme,
        getFeedRecommendThemeList1: (state: { feedRecommendThemeList1 : Array<object>}) => state.feedRecommendThemeList1,
        getFeedRecommendThemeList2: (state: { feedRecommendThemeList2: Array<object> }) => state.feedRecommendThemeList2,
        searchPlacesList: (state: { searchPlacesList: Array<Object> }) => state.searchPlacesList,
        selectedPlace: (state: { selectedPlace:Object})=> state.selectedPlace,
    },
    mutations: {
        SET_FEED_ARTICLE_LIST: (state: {feedArticleList : Array<object>}, _list:Array<object>) => state.feedArticleList = _list,
        SET_FEED_THEME: (state: { feedTheme: Array<object>; }, _feedTheme: Array<object>) => state.feedTheme = _feedTheme,
        SET_FEED_RECOMMEND_THEME_List1: (state: { feedRecommendThemeList1: Array<object>}, _feedRecommendThemeList: Array<object>) => state.feedRecommendThemeList1 = _feedRecommendThemeList,
        SET_FEED_RECOMMEND_THEME_List2: (state: { feedRecommendThemeList2: Array<object> }, _feedRecommendThemeList: Array<object>) => state.feedRecommendThemeList2 = _feedRecommendThemeList,
        SET_SEARCH_PLACES_LIST: (state: {searchPlacesList:Array<Object>}, _searchPlacesList:Array<Object>) => state.searchPlacesList = _searchPlacesList,
        SET_SELECTED_PLACE: (state:{selectedPlace:Object}, _selectedPlace:Array<Object>) => state.selectedPlace = _selectedPlace
    },
    actions: {
        // board-controller
        createArticle({ commit, getters }: { commit: Commit, getters: any }, _data: { description:string, pictures:Array<string>,themeIdx:number}) {
            const dt = {
                description: _data.description,
                name: getters.selectedPlace.place_name,
                pictures: _data.pictures,
                // place: getters.selectedPlace.address_name,
                place: getters.selectedPlace.road_address_name,
                themeIdx: _data.themeIdx,
                logitude : getters.selectedPlace.x,
                latitude : getters.selectedPlace.y,
            }
            console.log(dt)

            axios({
                url: rest.Feed.createArticle(),
                method: 'post',
                headers: getters.authHeader,
                data: dt
            })
                .then((res) => {
                console.log(res.data)
            })
        },
        detailArticle({ commit, getters }: { commit: Commit, getters: any }, _boardIdx: string) {
            axios({
                url: rest.Feed.fetchArticle(_boardIdx),
                method: 'get',
                headers: getters.authHeader
            })
                .then((res) => {
                console.log(res)
            })
        },
        updateArticle({ commit, getters }: { commit: Commit, getters: any }, _boardIdx: string, _data:object) {
            axios({
                url: rest.Feed.fetchArticle(_boardIdx),
                method: 'put',
                headers: getters.authHeader,
                data: _data
            })
                .then((res) => {
                console.log(res)
            })
        },
        deleteArticle({ commit, getters }: { commit: Commit, getters: any },_boardIdx:string) {
            axios({
                url: rest.Feed.fetchArticle(_boardIdx),
                method: 'delete',
                headers: getters.authHeader
            })
                .then((res) => {
                console.log(res)
            })
        },
        reportArticle({ commit, getters }: { commit: Commit, getters: any },_boardIdx:string, _content:string) {
            axios({
                url: rest.Feed.reportArticle(_boardIdx),
                method: 'post',
                headers: getters.authHeader,
                params: {
                    content: _content
                }
            })
                .then((res) => {
                console.log(res)
            })
        },
        likeArticle({ dispatch, getters }: { dispatch: Dispatch, getters: any },_boardIdx:string) {
            axios({
                url: rest.Feed.LikeArticle(_boardIdx),
                method: 'post',
                headers: getters.authHeader
            })
                .then((res) => {
                console.log(res)
            })
        },
        unlikeArticle({ dispatch, getters }: { dispatch: Dispatch, getters: any }, _boardIdx: string) {
            axios({
                url: rest.Feed.LikeArticle(_boardIdx),
                method: 'delete',
                headers: getters.authHeader
            })
                .then((res) => {
                console.log(res)
            })
        },


        //comment-controller
        createComment({ dispatch, getters }: { dispatch: Dispatch, getters: any }, _boardIdx: string, _content: string) {
            axios({
                url: rest.Feed.comment(_boardIdx),
                method: 'post',
                headers: getters.authHeader,
                params: {
                    contnet: _content
                }
            })
                .then((res) => {
                console.log(res)
            })
        },
        deleteComment({ dispatch, getters }: { dispatch: Dispatch, getters: any }, _commentIdx:string) {
            axios({
                url: rest.Feed.deleteComment(_commentIdx),
                method: 'delete',
                headers: getters.authHeader,
            })
                .then((res) => {
                console.log(res)
            })
        },
        reportComment({ dispatch, getters }: { dispatch: Dispatch, getters: any }, _commentIdx: string,_content:string) {
            axios({
                url: rest.Feed.reportComment(_commentIdx),
                method: 'post',
                headers: getters.authHeader,
                params: {
                    content:_content
                }
            })
                .then((res) => {
                console.log(res)
            })
        },

        // feed-controller
        themeArticleList({ commit, getters }: { commit: Commit, getters: any },_themeIdx:string,_pageIdx:number,_pageSize:number) {
            axios({
                url: rest.Feed.themeArticleList(_themeIdx),
                method: 'get',
                headers: getters.authHeader,
                params: {
                    pageIdx: _pageIdx,
                    pageSize: _pageSize
                }
            })
                .then((res => {
                console.log(res)
            }))
        },


        // 
        getFeedTheme({ commit, getters }: { commit: Commit, getters: any }, _region: number) {
            axios({
                url: rest.Feed.feedList(),
                method: 'get',
                headers: getters.authHeader,
                params: { region: _region,pageSize : 3,pageIdx:0}
            })
                .then((res) => {
                    commit("SET_FEED_THEME",res.data.data)    
            })
        },
        
        getRecommendFeedTheme({ commit,getters }: { commit: Commit, getters: any }) {
            axios({
                url: rest.Feed.recommendTheme(),
                method: 'get',
                headers: getters.authHeader,
            })
                .then((res) => {
                // commit("SET_FEED_RECOMMEND_THEME_List1",res.data)
                // commit("SET_FEED_RECOMMEND_THEME_List2",res.data)
            })
        },
        searchPlacesList({ commit }: { commit: Commit }, _list: Array<Object>) {
            commit("SET_SEARCH_PLACES_LIST",_list)
        },
        selectedPlace({ commit }: { commit: Commit }, _place: Object) {
            commit("SET_SELECTED_PLACE",_place)
        },

    }
}