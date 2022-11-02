// import router from '@/router'
import axios from 'axios'
import rest from '@/API/rest'
import _, { method } from "lodash"
import { Commit, Dispatch } from 'vuex';

export default {
    state: {
        // token: localStorage.getItem('token') || '', 
        token :"",
        loginUser: {t:"T"},
    },
    getters: {
        isLoggedIn: (state: { loginUser: Object; }) => !_.isEmpty(state.loginUser),
        authHeader: (state: { token: string}) => ({ Authorization: state.token }),
        
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
                    client_id: 'bab0a08f8b68900521759c285635e38a',
                    redirect_uri: rest.kakao.redirect(),
                    code: _code,
                }
            })
                .then((res) => {
                    console.log(res.data)
                })
                .catch((err) => {
                    console.log(err)
                })

        },

        login({ commit }: { commit: Commit }, _kakaoCode: string) {
            axios({
                url: rest.User.login(),
                method: 'post',
                data: { kakaoId: _kakaoCode }
            })
                .then((res) => {
                    console.log(res.data)
                    // commit('SET_TOKEN',res.data)
                    // commit('SET_LOGIN_USER',res.data)
                })
        },

        signout({ commit }: { commit: Commit }, _userNickname: string) {
            axios({
                url: rest.User.signOut(_userNickname),
                method: 'delete',
            })
                .then((res) => {
                    console.log(res.data)
                    // commit('SET_TOKEN', "")
                    // commit('SET_LOGIN_USER', {})
                })
        },
        // 정보조회
        getUserInfoByNickname({ commit }: { commit: Commit }, _userNickname: string) {
            axios({
                url: rest.User.userInfo(_userNickname),
                method: 'get',
            })
                .then((res) => {
                    console.log(res.data)
                })
        },
        getUserInfoByIdx({ commit }: { commit: Commit }, _userIdx: string) {
            axios({
                url: rest.User.userInfo2(_userIdx),
                method: 'get'
            })
            
        },
        updateUser({ commit }: { commit: Commit }, _userNickname: string, _data: Object) {
            axios({
                url: rest.User.userInfo(_userNickname),
                method: 'put',
                data: _data,
            })
                .then((res) => {
                    console.log(res)
                })
        },
        // 이름 중복 검사(업데이트시 사용)
        nicknameDuplicationCheck({ commit }: { commit: Commit }, _newNickname: string) {
            axios({
                url: rest.User.duplication(_newNickname),
                method: 'get',
            })
                .then((res) => {
                    console.log(res)
                })
        },

        themeFollow({ commit }: { commit: Commit }, _themeId: string, _userId: string, _targetUserId: string) {
            axios({
                url: rest.User.themeFollow(_themeId, _userId, _targetUserId),
                method: 'post',
            })
                .then((res) => {
                    console.log(res)
                })
        },
        cancelFollow({ dispatch }: { dispatch: Dispatch }, _followId: string) {
            axios({
                url: rest.User.cancelThemeFollow(_followId),
                method: 'delete',
            })
                .then((res) => {
                    console.log(res)
                })
        },
        getFollowerList({ commit }: { commit: Commit }, _userId: string) {
            axios({
                url: rest.User.followerList(_userId),
                method: 'get'
            })
                .then((res) => {
                    console.log(res)
                })
        },
        getFollowingList({ commit }: { commit: Commit }, _userId: string) {
            axios({
                url: rest.User.followingList(_userId),
                method: 'get'
            })
                .then((res) => {
                    console.log(res)
                })
        },
        followingThemeIdxList({ commit }: { commit: Commit }, _userId: string) {
            axios({
                url: rest.User.followingThemeIdxList(_userId),
                method: 'get',
            })
                .then((res) => {
                    console.log(res)
                })
        },
        userUnfollow({ commit }: {commit : Commit},_targetUserIdx:string,_userIdx:string) {
            axios({
                url: rest.User.unfollow(_targetUserIdx, _userIdx),
                method: 'delete',
            })
        },





        

        
        }
    }