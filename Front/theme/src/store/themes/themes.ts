// import router from '@/router'
import rest from '@/API/rest'
import axios from "axios";
import { Commit, Dispatch } from 'vuex';

export default {
    state: {
        liveSearchTheme : []
       },
    getters: {
        liveSearchTheme: (state: {liveSearchTheme:Array<String>}) => state.liveSearchTheme,
        
        },
    mutations: {
        LIVE_SEARCH_THEME_LIST: (state:{ liveSearchTheme:Array<String>},_liveThemeList:Array<String>) => state.liveSearchTheme = _liveThemeList
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
        liveSearchTheme({ commit, getters }: {commit:Commit,getters:any},_target:string) {
            axios({
                url: rest.Theme.liveSearchTheme(),
                params:{value:_target},
                method: 'get',
                headers: getters.authHeader
            })
                .then((res) => {
                commit("LIVE_SEARCH_THEME_LIST",res.data.themeList)
                console.log(res.data.themeList)

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
                console.log(res)
                })
                .catch((err) => {
                console.log(err)
            })
        },
        
        registTheme({ commit, getters }: { commit: Commit, getters: any }, _data:object) {
            // {
            //     emoticon: _emoticon,
            //     name: _themeName
            // }
            axios({
                url: rest.Theme.registTheme(),
                method: 'post',
                headers: getters.authHeader,
                data: _data
            })
                .then((res) => {
                console.log(res)
            })
        },
        createUserTheme({ getters }: { getters: any },_data:object) {
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
        getrecommendThemeList({ getters }:{getters:any}) {
            axios({
                url: rest.Theme.recommendThemeList(),
                method: 'get',
                headers:getters.authHeader
            })
                .then((res) => {
                console.log(res)
            })
        },
        searchTheme({ commit, getters }: { commit: Commit, getters: any },_target:string) {
            axios({
                url: rest.Theme.searchTheme(_target),
                method: 'get',
                headers: getters.authHeader
            })
            .then((res) => {
                console.log(res)
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
    }
}