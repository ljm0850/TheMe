// import router from '@/router'
import rest from '@/API/rest'
// import { storage } from '../firebase/firebase'
import { Commit, Dispatch } from 'vuex';

import axios from "axios"
import router from '@/router';
export default {
    state: {
        feedArticleList: [{ themeName: "tttasdgfa" }, { themeName: "임시2" }],
        feedTheme: [],
        displayFeedTheme: { num: 0, list: {} },    // feedTheme는 너무 커서 잘라서 사용
        feedRecommendThemeList1: [{title:"코딩하기 좋은 까페"},{title:"낮잠자기 좋은 까페"},{title:"짧"}],
        feedRecommendThemeList2: [{ title: "프로젝트 하기 좋은 싸피" }, { title: "수완지구 분위기 있는 레스토랑" }, { title: "다" }],
        searchPlacesList: [],
        selectedPlace: {},
        publicThemeArticleList : [],
        selectedThemeforArticle: {},
        userThemeArticleList: [],
        placeArtilceList: [], 
        boardInfoByUserTheme: {},
        detailArticle: {},
    },
    getters: {
        getFeedArticleList: (state: { feedArticleList: Array<object> }) => state.feedArticleList,
        getFeedTheme: (state: { feedTheme: Array<object>; }) => state.feedTheme,
        displayFeedTheme: (state: { displayFeedTheme: {list:Object} }) => state.displayFeedTheme.list,
        getFeedRecommendThemeList1: (state: { feedRecommendThemeList1 : Array<object>}) => state.feedRecommendThemeList1,
        getFeedRecommendThemeList2: (state: { feedRecommendThemeList2: Array<object> }) => state.feedRecommendThemeList2,
        searchPlacesList: (state: { searchPlacesList: Array<Object> }) => state.searchPlacesList,
        selectedPlace: (state: { selectedPlace:Object})=> state.selectedPlace,
        publicThemeArticleList: (state: { publicThemeArticleList:Object})=> state.publicThemeArticleList,
        selectedThemeforArticle: (state: { selectedThemeforArticle: Object }) => state.selectedThemeforArticle,
        userThemeArticleList: (state: { userThemeArticleList: Object }) => state.userThemeArticleList,
        placeArtilceList: (state: { placeArtilceList: Array<Object> }) => state.placeArtilceList,
        boardInfoByUserTHeme : (state: {boardInfoByUserTheme : Object}) => state.boardInfoByUserTheme,
        detailArticle: (state: {detailArticle : Object}) => state.detailArticle,
    },
    mutations: {
        SET_FEED_ARTICLE_LIST: (state: {feedArticleList : Array<object>}, _list:Array<object>) => state.feedArticleList = _list,
        SET_FEED_THEME: (state: { feedTheme: Array<object>; }, _feedTheme: Array<object>) => state.feedTheme = _feedTheme,
        SET_DISPLAY_FEED_THEME_LIST: (state: { displayFeedTheme: { list:Object} },_displayFeedTheme:Object)=>state.displayFeedTheme.list = _displayFeedTheme,
        SET_DISPLAY_FEED_THEME_NUM: (state: { displayFeedTheme: { num:number} },_num:number)=>state.displayFeedTheme.num = _num,
        SET_FEED_RECOMMEND_THEME_List1: (state: { feedRecommendThemeList1: Array<object>}, _feedRecommendThemeList: Array<object>) => state.feedRecommendThemeList1 = _feedRecommendThemeList,
        SET_FEED_RECOMMEND_THEME_List2: (state: { feedRecommendThemeList2: Array<object> }, _feedRecommendThemeList: Array<object>) => state.feedRecommendThemeList2 = _feedRecommendThemeList,
        SET_SEARCH_PLACES_LIST: (state: {searchPlacesList:Array<Object>}, _searchPlacesList:Array<Object>) => state.searchPlacesList = _searchPlacesList,
        SET_SELECTED_PLACE: (state:{selectedPlace:Object}, _selectedPlace:Array<Object>) => state.selectedPlace = _selectedPlace,
        SET_PUBLIC_THEME_ARTICLE_LIST: (state:{publicThemeArticleList:Object}, _publicThemeArticleList:Array<Object>) => state.publicThemeArticleList = _publicThemeArticleList,
        SET_SELECTED_THEME_FOR_ARTICLE: (state: { selectedThemeforArticle: Object }, _selectedThemeforArticle: Object) => state.selectedThemeforArticle = _selectedThemeforArticle,
        SET_USER_THEME_ARTICLE_LIST: (state: { userThemeArticleList: Object }, _userThemeArticleList: Object) => state.userThemeArticleList = _userThemeArticleList,
        SET_BOARD_INFO_BY_USERTHEME : (state : {boardInfoByUserTheme:Object}, _boardInfoByUserTheme:Object) => state.boardInfoByUserTheme = _boardInfoByUserTheme,
        SET_PLACE_ARTICLE_LIST :  (state:{placeArtilceList:Array<Object>}, _placeArtilceList:Array<Object>) => state.placeArtilceList = _placeArtilceList,
        SET_DETAIL_ARTICLE : (state : {detailArticle:Object}, _detailArticle:Object) => state.detailArticle = _detailArticle,
    },
    actions: {
        // board-controller
        createArticle({ commit, getters }: { commit: Commit, getters: any }, _data: { description:string, pictures:Array<string>,themeIdx:number, publicThemeIdx:number}) {
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
            // console.log(dt)

            axios({
                url: rest.Feed.createArticle(),
                method: 'post',
                headers: getters.authHeader,
                data: dt
            })
                .then((res) => {
                    // console.log(res.data)
                    router.push({name: 'UserTheme', params: { userThemeIdx:_data.themeIdx, publicThemeIdx:_data.publicThemeIdx }})
            })
        },

        detailArticle({ commit, getters }: { commit: Commit, getters: any }, _boardIdx: string) {
            axios({
                url: rest.Feed.fetchArticle(_boardIdx),
                method: 'get',
                headers: getters.authHeader
            })
                .then((res) => {
                    console.log(res.data.comment)
                    commit("SET_DETAIL_ARTICLE", res.data.comment)
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
        likeArticle({ commit, dispatch, getters }: { commit: Commit, dispatch: Dispatch, getters: any },_boardIdx:string) {
            axios({
                url: rest.Feed.LikeArticle(_boardIdx),
                method: 'post',
                headers: getters.authHeader
            })
                .then((res) => {
                console.log(res)
                commit("SET_LIKE_ARTICLE_MY",true)  
            })
        },
        unlikeArticle({commit, dispatch, getters }: { commit : Commit ,dispatch: Dispatch, getters: any }, _boardIdx: string) {
            axios({
                url: rest.Feed.LikeArticle(_boardIdx),
                method: 'delete',
                headers: getters.authHeader
            })
                .then((res) => {
                console.log(res)
                commit("SET_LIKE_ARTICLE_MY",false)  
            })
        },


        //comment-controller
        createComment({ dispatch, getters }: { dispatch: Dispatch, getters: any }, _data:{ boardIdx: string, content : string }) {
            console.log(_data);
            axios({
                url: rest.Feed.comment(_data.boardIdx),
                method: 'post',
                headers: getters.authHeader,
                params: {
                    content : _data.content 
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
        themeArticleList({ commit, getters }: { commit: Commit, getters: any }, _data:{themeIdx:string, pageIdx:number, pageSize:number}) {
            
            axios({
                url: rest.Feed.themeArticleList(_data.themeIdx),
                method: 'get',
                headers: getters.authHeader,
                params: {
                    pageIdx: _data.pageIdx,
                    pageSize: _data.pageSize
                }
            })
                .then((res => {
                console.log(res.data.data)
                commit("SET_PUBLIC_THEME_ARTICLE_LIST", res.data.data)
            }))
        },


        // 
        getFeedTheme({ commit, getters, dispatch }: { commit: Commit, getters: any, dispatch:Dispatch }, _region: number) {
            console.log("지역 : ", _region)
            commit("SET_DISPLAY_FEED_THEME_LIST", [])
            commit("SET_DISPLAY_FEED_THEME_NUM",0)
            axios({
                url: rest.Feed.feedList(),
                method: 'get',
                headers: getters.authHeader,
                params: { region: 3,  pageIdx:0, pageSize : 2147483647 }
            })
                .then((res) => {
                    console.log("피드 데이터 : ",res.data.data,typeof(res.data.data))
                    commit("SET_FEED_THEME", res.data.data)
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
        getUserThemeArticleList ({ commit,getters }: { commit: Commit, getters: any }, _data:{themeIdx:string, pageIdx:number, pageSize:number}) {
            axios({
                url: rest.Feed.userThemeArticleList(_data.themeIdx),
                method: 'get',
                headers: getters.authHeader,
                params: {
                    pageIdx: _data.pageIdx,
                    pageSize: _data.pageSize
                }
            })
                .then((res) => {
                // console.log("유저테마게시글목록")
                // console.log(res.data)
                commit("SET_USER_THEME_ARTICLE_LIST",res.data.data)
            })
        },
        placeArticleList({ commit, getters }: { commit: Commit, getters: any }, _data:{themeIdx:string, name:string, pageIdx:number, pageSize:number}) {
            axios({
                url: rest.Feed.placeArticleList(_data.themeIdx),
                method: 'get',
                headers: getters.authHeader,
                params: {
                    name: _data.name,
                    pageIdx: _data.pageIdx,
                    pageSize: _data.pageSize
                }
            })
                .then((res => {
                    // console.log(res.data.data)
                    commit("SET_PLACE_ARTICLE_LIST", res.data.data)
            }))
        },
        getBoardInfoByUserTheme({commit, getters} : { commit: Commit, getters: any }, userThemeIdx:string) {
            
            axios({
                url:rest.Feed.boardInfoByUserTheme(userThemeIdx),
                method:'get',
                headers:getters.authHeader,
            })
            .then((res => {
                
                commit("SET_BOARD_INFO_BY_USERTHEME", res.data)
            }))
        },
        searchPlacesList({ commit }: { commit: Commit }, _list: Array<Object>) {
            commit("SET_SEARCH_PLACES_LIST",_list)
        },
        selectedPlace({ commit }: { commit: Commit }, _place: Object) {
            commit("SET_SELECTED_PLACE",_place)
        },
        selectedThemeForArticle({ commit, getters,dispatch }: { commit: Commit, getters: any, dispatch:Dispatch }, _themeDetail: {name:string, idx:number}) {
            commit("SET_SELECTED_THEME_FOR_ARTICLE", _themeDetail)
            // console.log("feed.ts // 선택한 공용 테마 idx",_themeDetail.idx)
            dispatch('selectedThemeIdxForCreate',_themeDetail.idx)  // 공용테마 IDX 저장
            axios({
                url: rest.Theme.liveSearchTheme(),
                params:{value:_themeDetail.name},
                method: 'get',
                headers: getters.authHeader
                })
            .then((res) => {
                const searchList = res.data.themeList
                for (let idx = 0; idx < searchList.length; idx++){
                    if (searchList[idx].name == _themeDetail.name) {
                        // 이미 가지고 있으면
                        if (searchList[idx].my) {
                            dispatch('getMyThemeIdx', _themeDetail.idx)
                            // router.push({name: 'CreateThemeArticle'})
                            
                        }  
                        // 안가지고 있어서 개인테마 생성
                        else {   
                            dispatch('createUserTheme', {
                                openType:0, challenge:false, component:true
                            })
                        }
                    }
                }
            })
            
        },
        displayFeedTheme({ commit, state, getters }: { commit: Commit, state: any, getters: any }) {
            const before = { ...state.displayFeedTheme.list }
            for (let i = state.displayFeedTheme.num; i < state.displayFeedTheme.num + 14; i++){
                if (i < getters.getFeedTheme.length) {
                    before[i] = getters.getFeedTheme[i]
                }
            }
            commit("SET_DISPLAY_FEED_THEME_LIST", before)
            commit("SET_DISPLAY_FEED_THEME_NUM", state.displayFeedTheme.num + 14)
        }

    }
}