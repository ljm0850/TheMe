// import router from '@/router'
import rest from '@/API/rest'
import axios from "axios";
import { Commit, Dispatch } from 'vuex';

export default {
    state: {
       },
    getters: {
        },
    mutations: {
        },
    actions: {
        // 테마등록
        getPublicThemeList({ commit,getters }:{commit:Commit,getters:any}) {
            axios({
                url: rest.Theme.getPublicThemeList(),
                method: 'get',
                headers: getters.authHeader
            })
                .then((res) => {
                console.log(res)
            })
        },
        liveSearchTheme({ commit, getters }: {commit:Commit,getters:any}) {
            axios({
                url: rest.Theme.liveSearchTheme(),
                method: 'get',
                headers: getters.authHeader
            })
                .then((res) => {
                console.log(res)
            })
        },
        searchThemeInfo({ commit,getters }: {commit:Commit, getters:any}) {
            axios({
                url: rest.Theme.searchThemeInfo(),
                method: 'get',
                headers: getters.authHeader
            })
        },
        
        registTheme({ commit, getters }: { commit: Commit, getters: any }, _emoticon: string, _themeName: string) {
            axios({
                url: rest.Theme.registTheme(),
                method: 'post',
                headers: getters.authHeader,
                data: {
                    emoticon: _emoticon,
                    name: _themeName
                }
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
        recommendThemeList({ getters }:{getters:any}) {
            axios({
                url: rest.Theme.recommendThemeList(),
                method: 'get',
                headers:getters.authHeader
            })  
        },
        searchTheme({ commit, getters }: { commit: Commit, getters: any },_target:string) {
            axios({
                url: rest.Theme.searchTheme(_target),
                method: 'get',
                headers: getters.authHeader
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