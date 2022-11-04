// import router from '@/router'
import axios from 'axios'
import rest from '@/API/rest'
import _, { method } from "lodash"
import { Commit, Dispatch } from 'vuex';

export default {
    state: {
        // token: localStorage.getItem('token') || '', 
        token :"",
        loginUser: {
            // userIdx: 0,
            // email: null,
            // description:null,
            // kakaoId: "0000000",
            // nickname : "닉네임",
            // picture : "https://firebasestorage.googleapis.com/v0/b/theme-b8677.appspot.com/o/article%2Ftest?alt=media&token=301ac89b-60a3-4314-9b11-945f104c91f6",
            // createTime : "2022-11-02T01:27:07"
        },
        selectedUser: {
            userIdx: 0,
            email: null,
            description:"C203의 프론트를 맡고있는 이재민입니다",
            kakaoId: "0000000",
            nickname : "닉네임",
            picture : "https://firebasestorage.googleapis.com/v0/b/theme-b8677.appspot.com/o/article%2Ftest?alt=media&token=301ac89b-60a3-4314-9b11-945f104c91f6",
            createTime : "2022-11-02T01:27:07"
        },
    },
    getters: {
        isLoggedIn: (state: { loginUser: Object; }) => !_.isEmpty(state.loginUser),
        authHeader: (state: { token: string }) => ({ Authorization: state.token }),
        loginUser: (state: {loginUser:Object}) => state.loginUser,
        selectedUser: (state: { selectedUser: Object }) => state.selectedUser,
    },
    mutations: {
        SET_TOKEN: (state: { token: string; }, _token:string) => state.token = _token,
        SET_LOGIN_USER: (state:{ loginUser:Object},_user:Object) => state.loginUser = _user
    },
    actions: {
        kakaoLogin({ dispatch }: { dispatch: Dispatch }, _code: string) {
            axios({
                url: rest.kakao.login(),
                method: 'post',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                data: {
                    grant_type: 'authorization_code',
                    client_id: process.env.VUE_APP_KAKAO_CLIENT_ID,
                    redirect_uri: rest.kakao.redirect(),
                    code: _code,
                }
            })
                .then((res) => {
                    console.log(res.data.access_token)
                    dispatch('login',res.data.access_token)
                })
                .catch((err) => {
                    console.log(err)
                })
        },

        login({ commit }: { commit: Commit }, _accessToken: string) {
            console.log("여긴 도착")
            axios.defaults.withCredentials = true;
            axios({
                url: rest.User.login(),
                method: 'post',
                data: { kakaoToken: _accessToken }
            })
                .then((res) => {
                    console.log("성공")
                    console.log(res)
                    console.log("----------------------")
                    console.log(res.data)
                    // commit('SET_TOKEN',res.data)
                    commit('SET_LOGIN_USER',res.data.userInfo)
                })
                .catch((err) => {
                    console.log("실패")
                    console.log(err)
            })
        },
        
        getUserInfoByNickname({ commit,getters }: { commit: Commit,getters:any }, _userNickname: string) {
            axios({
                url: rest.User.userInfo(_userNickname),
                method: 'get',
                headers: getters.authHeader
            })
                .then((res) => {
                    console.log(res.data)
                })
        },
        Duplicationnickname({ commit,getters }: { commit: Commit,getters:any }, _newNickname: string) {
            axios({
                url: rest.User.duplicationNickname(_newNickname),
                method: 'get',
                headers: getters.authHeader
            })
                .then((res) => {
                    console.log(res)
                })
        },

        updateUserInfo({ commit,getters }: { commit: Commit,getters:any }, _userNickname: string, _data: Object) {
            axios({
                url: rest.User.userInfo(_userNickname),
                method: 'put',
                data: _data,
                headers:getters.authHeader
            })
                .then((res) => {
                    console.log(res)
                })
        },
        
        deleteUser({ commit, getters }: { commit: Commit, getters: any }, _userNickname: string) {
            axios({
                url: rest.User.deleteUser(_userNickname),
                method: 'delete',
                headers: getters.authHeader
            })
                .then((res) => {
                    console.log(res.data)
                    // commit('SET_TOKEN', "")
                    // commit('SET_LOGIN_USER', {})
                })
        },
        followTheme({ commit,getters }: { commit: Commit,getters:any }, _themeId: string, _userId: string, _targetUserId: string) {
            axios({
                url: rest.User.followtheme(_themeId, _userId, _targetUserId),
                method: 'post',
                headers: getters.authHeader
            })
                .then((res) => {
                    console.log(res)
                })
        },
        cancelFollow({ dispatch,getters }: { dispatch: Dispatch,getters:any }, _followId: string) {
            axios({
                url: rest.User.cancelFollow(_followId),
                method: 'delete',
                headers: getters.authHeader
            })
                .then((res) => {
                    console.log(res)
                })
        },
        getFollowingThemeIdxList({ commit,getters }: { commit: Commit,getters:any }, _userId: string) {
            axios({
                url: rest.User.getFollowingThemeIdxList(_userId),
                method: 'get',
                headers: getters.authHeader
            })
                .then((res) => {
                    console.log(res)
                })
        },

        getFollowerList({ commit,getters }: { commit: Commit,getters:any }, _userId: string) {
            axios({
                url: rest.User.getFollowerList(_userId),
                method: 'get',
                headers: getters.authHeader
            })
                .then((res) => {
                    console.log(res)
                })
        },
        getFollowingList({ commit,getters }: { commit: Commit,getters:any }, _userId: string) {
            axios({
                url: rest.User.getFollowingList(_userId),
                method: 'get',
                headers: getters.authHeader
            })
                .then((res) => {
                    console.log(res)
                })
        },
        userUnfollow({ commit,getters }: {commit : Commit,getters:any},_targetUserIdx:string,_userIdx:string) {
            axios({
                url: rest.User.unfollowUser(_targetUserIdx, _userIdx),
                method: 'delete',
                headers:getters.authHeader
            })
                .then((res) => {
                console.log(res)
            })
        },
        getUserInfoByIdx({ commit,getters }: { commit: Commit,getters:any }, _userIdx: string) {
            axios({
                url: rest.User.userInfoByIdx(_userIdx),
                method: 'get',
                headers: getters.authHeader
            })
                .then((res) => {
                console.log(res)
            })
        },
   
        }
    }