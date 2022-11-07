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
       },
    getters: {
        searchThemeList: (state: { searchThemeList: Array<object> }) => state.searchThemeList,
        isSearchThemeList: (state: { searchThemeList: Array<object> }) => !_.isEmpty(state.searchThemeList),
        selectedThemeIdxForCreate: (state: { selectedThemeIdxForCreate: number}) => state.selectedThemeIdxForCreate,
        isSelectedThemeIdxForCreate: (state: { selectedThemeIdxForCreate: number}) => !(state.selectedThemeIdxForCreate==0),
        selectedThemeNameForCreate: (state: { selectedThemeNameForCreate: string}) => state.selectedThemeNameForCreate,
        selectedThemeEmoticonForCreate: (state: { selectedThemeEmoticonForCreate: string }) => state.selectedThemeEmoticonForCreate,
        getRecommendThemeList: (state: {getRecommendThemeList:Array<Object> }) => state.getRecommendThemeList,
    },
    mutations: {
        SET_SEARCH_THEME_LIST: (state: { searchThemeList: Array<object> }, _searchThemeList: Array<object>) => state.searchThemeList = _searchThemeList,
        SET_SELECTED_THEME_IDX_FOR_CREATE: (state: {selectedThemeIdxForCreate:number},_idx:number)=> state.selectedThemeIdxForCreate = _idx ,    
        SET_SELECTED_THEME_NAME_FOR_CREATE: (state: {selectedThemeNameForCreate:string},_name:string)=> state.selectedThemeNameForCreate = _name ,    
        SET_SELECTED_THEME_EMOTICON_FOR_CREATE: (state: { selectedThemeEmoticonForCreate: string }, _name: string) => state.selectedThemeEmoticonForCreate = _name,
        SET_RECOMMEND_THEME_LIST: (state: {getRecommendThemeList:Array<Object>},_themeList:Array<Object> ) => state.getRecommendThemeList = _themeList,
    },
    actions: {
        getPublicThemeList({ commit,getters }:{commit:Commit,getters:any},_params:object) {
            // _params
            // {
            //         isMarked: 0,
            //         sort: 1,
            //         pageSize: 5,
            //         pageIdx: 0
            //     }
            axios({
                url: rest.Theme.getPublicThemeList(),
                method: 'get',
                headers: getters.authHeader,
                params: _params
            })
                .then((res) => {
                console.log(res)
            })
        },
        liveSearchTheme({ commit, getters }: {commit:Commit,getters:any}, _value:string) {
            axios({
                url: rest.Theme.liveSearchTheme(),
                method: 'get',
                headers: getters.authHeader,
                params: {
                    value: _value
                }
            })
                .then((res) => {
                console.log(res)
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
                console.log(res.data)
                })
                .catch((err) => {
                console.log(err)
            })
        },
        
        registTheme({ dispatch, getters }: { dispatch:Dispatch,commit: Commit, getters: any }, _data:object) {
            // {
            //     emoticon: _emoticon,
            //     name: _themeName
            // }
            axios({
                url: rest.Theme.registTheme(),
                method: 'post',
                headers: getters.authHeader,
                data: {

                }
            })
                .then((res) => {
                    const themeIdx = res.data.idx
                    dispatch('createUserTheme',_data)
                    
            })
        },
        createUserTheme({ getters }: { getters: any }, _data: object) {
        //  {
        //   "challenge": true,
        //   "createTime": "2022-11-07T04:55:45.963Z",
        //   "description": "string",
        //   "modifyTime": "2022-11-07T04:55:45.963Z",
        //   "openType": 0,
        //   "themeIdx": 0,
        //   "userIdx": 0
        // }
            axios({
                url: rest.Theme.createUserTheme(),
                method: 'post',
                headers: getters.authHeader,
                data: _data
            })
                .then((res) => {
                console.log(res)
            })
        },

        getUserThemeList({ commit, getters }: { commit: Commit, getters: any }, _userIdx:string) {
            axios({
                url: rest.Theme.getUerThemeList(_userIdx),
                method: 'get',
                headers: getters.authHeader
            })
                .then((res) => {
                console.log(res)
            })
        },
        bookmarkTheme({ dispatch, getters }: { dispatch: Dispatch, getters: any }, _userIdx:string, _themeIdx:string) {
            axios({
                url: rest.Theme.scrapTheme(_userIdx, _themeIdx),
                method: 'post',
                headers: getters.authHeader
            })
            .then((res) => {
                console.log(res)
            })
        },
        getRecommendThemeList({ commit,getters }:{commit:Commit,getters:any}) {
            axios({
                url: rest.Theme.recommendThemeList(),
                method: 'get',
                headers:getters.authHeader
            })
                .then((res) => {
                    console.log(res.data.recommendList)
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
        scrapTheme({ commit, getters }: { commit: Commit, getters: any },_userIdx:string,_themeIdx:string) {
            axios({
                url: rest.Theme.scrapTheme(_userIdx, _themeIdx),
                method: 'post',
                headers: getters.authHeader
            })
            .then((res) => {
                console.log(res)
            })
        },
        unScrapTheme({ dispatch, getters }: { dispatch: Dispatch, getters: any }, _userIdx: string, _themeIdx: string) {
            axios({
                url: rest.Theme.scrapTheme(_userIdx, _themeIdx),
                method: 'delete',
                headers: getters.authHeader
            })
            .then((res) => {
                console.log(res)
            })
        },
        selectedThemeIdxForCreate({ commit }: { commit: Commit },_idx:number) {
            commit('SET_SELECTED_THEME_IDX_FOR_CREATE',_idx)
        },
        selectedThemeNameForCreate({ commit }: { commit: Commit },_name:string) {
            commit('SET_SELECTED_THEME_NAME_FOR_CREATE',_name)
        },
        selectedThemeEmoticonForCreate({ commit }: { commit: Commit }, _emoticon: string) {
            commit('SET_SELECTED_THEME_EMOTICON_FOR_CREATE',_emoticon)
        },

    }
}