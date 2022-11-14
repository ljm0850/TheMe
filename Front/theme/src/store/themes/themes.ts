// import router from '@/router'
import rest from '@/API/rest'
import axios from "axios";
import { Commit, Dispatch } from 'vuex';
import _ from "lodash"


export default {
    state: {
        searchThemeList: [],
        selectedThemeIdxForCreate: 0,
        selectedThemeNameForCreate: "",
        selectedThemeEmoticonForCreate: "",
        getRecommendThemeList:[],
        liveSearchTheme: [],
        selectedUserThemeList: [],
        publicThemeList :[],
        publicThemeDetail :[],
       },
    getters: {
        searchThemeList: (state: { searchThemeList: Array<object> }) => state.searchThemeList,
        isSearchThemeList: (state: { liveSearchTheme: Array<object> }) => !_.isEmpty(state.liveSearchTheme),
        selectedThemeIdxForCreate: (state: { selectedThemeIdxForCreate: number}) => state.selectedThemeIdxForCreate,
        isSelectedThemeIdxForCreate: (state: { selectedThemeIdxForCreate: number}) => !(state.selectedThemeIdxForCreate==0),
        selectedThemeNameForCreate: (state: { selectedThemeNameForCreate: string}) => state.selectedThemeNameForCreate,
        selectedThemeEmoticonForCreate: (state: { selectedThemeEmoticonForCreate: string }) => state.selectedThemeEmoticonForCreate,
        getRecommendThemeList: (state: {getRecommendThemeList:Array<Object> }) => state.getRecommendThemeList,
        liveSearchTheme: (state: { liveSearchTheme: Array<String> }) => state.liveSearchTheme,
        selectedUserThemeList: (state: { selectedUserThemeList:Array<Object> }) => state.selectedUserThemeList,
        publicThemeList : (state: {publicThemeList:Array<Object>}) => state.publicThemeList,
        publicThemeDetail:(state: {publicThemeDetail:Object}) => state.publicThemeDetail,
    },
    mutations: {
        SET_SEARCH_THEME_LIST: (state: { searchThemeList: Array<object> }, _searchThemeList: Array<object>) => state.searchThemeList = _searchThemeList,
        SET_SELECTED_THEME_IDX_FOR_CREATE: (state: {selectedThemeIdxForCreate:number},_idx:number)=> state.selectedThemeIdxForCreate = _idx ,    
        SET_SELECTED_THEME_NAME_FOR_CREATE: (state: {selectedThemeNameForCreate:string},_name:string)=> state.selectedThemeNameForCreate = _name ,    
        SET_SELECTED_THEME_EMOTICON_FOR_CREATE: (state: { selectedThemeEmoticonForCreate: string }, _name: string) => state.selectedThemeEmoticonForCreate = _name,
        SET_RECOMMEND_THEME_LIST: (state: {getRecommendThemeList:Array<Object>},_themeList:Array<Object> ) => state.getRecommendThemeList = _themeList,
        LIVE_SEARCH_THEME_LIST: (state: { liveSearchTheme: Array<String> }, _liveThemeList: Array<String>) => state.liveSearchTheme = _liveThemeList,
        SET_SELECTED_USER_THEME_LIST:(state: {selectedUserThemeList:Array<Object>}, _themeList:Array<Object>)=> state.selectedUserThemeList = _themeList,
        SET_PUBLIC_THEME_LIST: (state:{ publicThemeList: Array<object>}, _publicThemeList:Array<Object>) => state.publicThemeList = _publicThemeList,
        SET_PUBLIC_THEME_DETAIL : (state:{ publicThemeDetail: Object}, _publicThemeDetail:Object) => state.publicThemeDetail = _publicThemeDetail,
    },
    actions: {
        getPublicThemeList({ commit,getters }:{commit:Commit,getters:any},_params:object) {
            axios({
                url: rest.Theme.getPublicThemeList(),
                method: 'get',
                headers: getters.authHeader,
                params: _params
            })
                .then((res) => {
                commit("SET_PUBLIC_THEME_LIST", res.data.themeList)
                
            })
        },
        liveSearchTheme({ commit, getters }: {commit:Commit,getters:any},_target:string) {
            axios({
                url: rest.Theme.liveSearchTheme(),
                params:{value:_target},
                method: 'get',
                headers: getters.authHeader
                })
                .then((res) => {
                    commit("LIVE_SEARCH_THEME_LIST",res.data.themeList)
                })
            
            
            
        },
        searchThemeInfo({ commit,getters }: {commit:Commit, getters:any},_value:string) {
            axios({
                url: rest.Theme.searchThemeInfo(),
                method: 'get',
                headers: getters.authHeader,
                params: {
                    value: _value
                }
            })
                .then((res) => {
                    if(res.data.isSame) {//true 라면
                        res.data.themeList[0].isSame = true
                    }else{
                        res.data.themeList[0].isSame = false
                    }
                    commit("SET_SEARCH_THEME_LIST",res.data.themeList)
                })
                .catch((err) => {
                console.log(err)
            })
        },
        
        registTheme({ dispatch,commit, getters }: { dispatch:Dispatch,commit: Commit, getters: any }, _data:{emoticon:string, name:string, openType:number, challenge:boolean}) {
            axios({
                url: rest.Theme.registTheme(),
                method: 'post',
                headers: getters.authHeader,
                data: {
                    emoticon: _data.emoticon,
                    name: getters.selectedThemeNameForCreate
                }
            })
                .then((res) => {
                    console.log("공용테마 등록",res.data)
                    const themeIdx = res.data.idx
                    commit('SET_SELECTED_THEME_IDX_FOR_CREATE',themeIdx)
                    dispatch('createUserTheme', { openType:_data.openType, challenge:_data.challenge })
            })
        },
        createUserTheme({ getters }: { getters: any }, _data: { openType:number, challenge:boolean}) {
            const data = {
                challenge: _data.challenge,
                createTime: new Date(),
                description: "",
                modifyTime: "",
                openType: _data.openType,
                themeIdx: getters.selectedThemeIdxForCreate,
                userIdx: getters.loginUser.userIdx
            }
            axios({
                url: rest.Theme.createUserTheme(),
                method: 'post',
                headers: getters.authHeader,
                data: data
            })
                .then((res) => {
                    console.log("유저 테마 생성 완료")
                    console.log(res.data)
            })
        },
        getUserThemeList({ commit, getters }: { commit: Commit, getters: any }, _userIdx:string) {
            axios({
                url: rest.Theme.getUerThemeList(_userIdx),
                method: 'get',
                headers: getters.authHeader
            })
                .then((res) => {
                    commit('SET_SELECTED_USER_THEME_LIST',res.data.themeList)
                    console.log(res.data)
                    // console.log(getters.selectedUserThemeList)
            })
        },
        getMyThemeList({ dispatch, getters}: { dispatch: Dispatch, getters: any }) {
            console.log("머임")
            console.log(getters.loginUser)
            console.log(getters.loginUser.userIdx)
            console.log(typeof(getters.loginUser))
            dispatch("getUserThemeList",getters.loginUser.userIdx)
        },
        getRecommendThemeList({ commit,getters }:{commit:Commit,getters:any}) {
            axios({
                url: rest.Theme.recommendThemeList(),
                method: 'get',
                headers:getters.authHeader
            })
                .then((res) => {
                    console.log("추천 테마 : ",res.data)
                    commit('SET_RECOMMEND_THEME_LIST',res.data.recommendList)
            })
        },
        searchTheme({ commit, getters }: { commit: Commit, getters: any },_target:string) {
            axios({
                url: rest.Theme.searchTheme(_target),
                method: 'get',
                headers: getters.authHeader
            })
                .then((res) => {
                    commit('SET_SEARCH_THEME_LIST',res.data.themeDtos)
            })
        },
        scrapTheme({ commit, getters }: { commit: Commit, getters: any },_themeIdx:string) {
            axios({
                url: rest.Theme.scrapTheme(_themeIdx),
                method: 'post',
                headers: getters.authHeader
            })
            .then((res) => {
                console.log(res)
            })
        },
        unScrapTheme({ dispatch, getters }: { dispatch: Dispatch, getters: any }, _themeIdx: string) {
            axios({
                url: rest.Theme.scrapTheme(_themeIdx),
                method: 'delete',
                headers: getters.authHeader
            })
            .then((res) => {
                console.log(res)
            })
        },
        detailTheme({ commit, getters }: { commit: Commit, getters: any }, _themeIdx: string) {
            axios({
                url: rest.Theme.getThemeDetail(_themeIdx),
                method: 'get',
                headers: getters.authHeader
            })
                .then((res) => {
                commit("SET_PUBLIC_THEME_DETAIL", res.data.themeDetail)
            })
        },
        selectedThemeIdxForCreate({ commit }: { commit: Commit },_idx:number) {
            commit('SET_SELECTED_THEME_IDX_FOR_CREATE',_idx)
        },

        selectedThemeNameForCreate({ commit,dispatch,getters }: { commit: Commit, dispatch:Dispatch, getters:any },_name:string) {
            commit('SET_SELECTED_THEME_NAME_FOR_CREATE', _name)
            axios({
                url: rest.Theme.searchTheme(_name),
                method: 'get',
                headers: getters.authHeader
            })
                .then((res) => {
                    console.log("선택 검색 결과: ", res.data.themeDtos)
                    for (const data of res.data.themeDtos) {
                        if (data.name == _name) {
                            dispatch('selectedThemeIdxForCreate',data.themeIdx)
                            dispatch('selectedThemeEmoticonForCreate', data.emoticon)
                            console.log("정확한 데이터: ",data)
                        }
                    }
            })
        },
        selectedThemeEmoticonForCreate({ commit }: { commit: Commit }, _emoticon: string) {
            commit('SET_SELECTED_THEME_EMOTICON_FOR_CREATE',_emoticon)
        },

    }
}