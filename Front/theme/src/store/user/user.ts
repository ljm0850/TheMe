// import router from '@/router'
import axios from 'axios'
import rest from '@/API/rest'
import _, { method } from "lodash"
import { Commit, Dispatch } from 'vuex';

export default {
    state: {
        token :"",
        loginUser: {},
        selectedUser: {},
        searchPersonInfo : {},
        duplicationnickname : false,
        liveSearchPerson : [],
        recommandPersonList: [],
        isProfileMine : false,
    },
    
    getters: {
        isLoggedIn: (state: { loginUser: Object }) => !_.isEmpty(state.loginUser),
        authHeader: (state: { token: string }) => ({ Authorization: state.token }),
        loginUser: (state: {loginUser:Object}) => state.loginUser,
        selectedUser: (state: { selectedUser: Object }) => state.selectedUser,
        postCnt : (state : { postCnt : number}) => state.postCnt,
        searchPersonInfo : (state: { searchPersonInfo: Object }) => state.searchPersonInfo,
        duplicationnickname : (state: { duplicationnickname : boolean}) => state.duplicationnickname,
        liveSearchPerson: (state: { liveSearchPerson: Array<String> }) => state.liveSearchPerson,
        recommandPersonList: (state: { recommandPersonList: Array<String> }) => state.recommandPersonList,
    },
    mutations: {
        SET_TOKEN: (state: { token: string; }, _token:string) => state.token = _token,
        SET_LOGIN_USER: (state: { loginUser: Object }, _user: Object) => state.loginUser = _user,
        SET_SELECTED_USER: (state: { selectedUser:Object},_user:Object) => state.selectedUser = _user,
        SET_SEARCH_PERSON_INFO :  (state: { searchPersonInfo:Object},_searchPersonInfo:Object) => state.searchPersonInfo = _searchPersonInfo,
        SET_DUPLICATIONNICKNAME : (state: { duplicationnickname:boolean}, _duplicationnickname:boolean) => state.duplicationnickname = _duplicationnickname ,
        LIVE_SEARCH_PERSON_LIST: (state: { liveSearchPerson: Array<String> }, _liveSearchPerson: Array<String>) => state.liveSearchPerson = _liveSearchPerson,
        SET_RECOMMAND_PERSON_LIST: (state: { recommandPersonList: Array<Object> }, _recommandPersonList: Array<Object>) => state.recommandPersonList = _recommandPersonList,
    },
    actions: {
        isSame({getters}:{getters:any},_themeUserIdx : number) {
            return getters.loginUser.userIdx == _themeUserIdx;
        },
        isProfileMine({ getters }: { getters: any }) {
            console.log(getters.loginUser.userIdx)
            console.log(getters.selectedUser.userIdx)
            console.log(getters.selectedUser.userIdx == getters.loginUser.userIdx)
            return getters.loginUser.userIdx == getters.selectedUser.userIdx
        },
        getRecommendPersonList({ commit,getters }:{commit:Commit,getters:any}) {
            axios({
                url: rest.User.recommandPersonList(),
                method: 'get',
                headers:getters.authHeader
            })
                .then((res) => {
                    console.log(res.data)
                    commit('SET_RECOMMAND_PERSON_LIST',res.data)
            })  .catch((err) => {
                console.log(err)
            })
        },
        liveSearchPerson({ commit, getters }: {commit:Commit,getters:any},_target:string) {
            axios({
                url: rest.User.liveSearchUser(),
                params:{value:_target},
                method: 'get',
                headers: getters.authHeader
            })
                .then((res) => {
                console.log(res.data)
                commit("LIVE_SEARCH_PERSON_LIST",res.data.userList)
            })
        },

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
        // 확인, 추후 토큰 처리 추가 필요
        login({ commit }: { commit: Commit }, _accessToken: string) {
            axios.defaults.withCredentials = true;
            axios({
                url: rest.User.login(),
                method: 'post',
                data: { kakaoToken: _accessToken }
            })
                .then((res) => {
                    console.log(res.headers)
                    console.log(res.data.userInfo)
                    commit('SET_TOKEN',res.headers.authorization)
                    commit('SET_LOGIN_USER',res.data.userInfo)
                })
                .catch((err) => {
                    console.log("실패")
                    console.log(err)
            })
        },
        logout({commit}:{ commit:Commit}){
            commit('SET_TOKEN','')
            commit('SET_LOGIN_USER',{})
        },

        // 확인
        getUserInfoByNickname({ commit,getters }: { commit: Commit,getters:any }, _userNickname: string) {
            axios({
                url: rest.User.userInfo(_userNickname),
                method: 'get',
                headers: getters.authHeader
            })
                .then((res) => {
                    console.log("유저정보")
                    console.log(res.data)
                    commit('SET_SELECTED_USER',res.data.userInfo)
                    //commit('SET_LOGIN_USER',res.data.userInfo)
                })
        },
        duplicationnickname({ commit,getters }: { commit: Commit,getters:any }, _newNickname: string) {
            axios({
                url: rest.User.duplicationNickname(_newNickname),
                method: 'get',
                headers: getters.authHeader
            })
                .then((res) => {
                    console.log(res);
                    commit("SET_DUPLICATIONNICKNAME",res.data.isPossible);
                })
        },

        updateUserInfo({ dispatch, getters }: { dispatch: Dispatch, getters: any }, _data: { description: string,  nickname: string, picture: string}) {
            if (_data.nickname==="")
            _data.nickname = getters.loginUser.nickname
            if (_data.description==="")
            _data.description = getters.loginUser.description
            axios({
                url: rest.User.userInfo(getters.loginUser.nickname),
                method: 'put',
                data: _data,
                headers:getters.authHeader
            })
                .then((res) => {
                    dispatch('getUserInfoByNickname',_data.nickname)
                    
                    console.log(res)
                })
        },
        
        deleteUser({ commit, getters }: { commit: Commit, getters: any }) {
            axios({
                url: rest.User.deleteUser(getters.loginUser.nickname),
                method: 'delete',
                headers: getters.authHeader
            })
                .then((res) => {
                    console.log(res.data)
                    commit('SET_TOKEN', "")
                    commit('SET_LOGIN_USER', {})
                })
        },
        searchPersonInfo({ commit,getters }: {commit:Commit, getters:any},_value:string) {
            axios({
                url: rest.User.getSerchPerson(),
                method: 'get',
                headers: getters.authHeader,
                params: {
                    value: _value
                }
            })
                .then((res) => {
                    if(res.data.isSame) {//true 라면
                        res.data.userList[0].isSame = true
                    }else{
                        res.data.userList[0].isSame = false
                    }
                commit('SET_SEARCH_PERSON_INFO',res.data.userList)
                })
                .catch((err) => {
                console.log(err)
            })
        },
        followTheme({ commit, getters }: { commit: Commit, getters: any }, _params: { themeId: string, targetUserId: string }) {
            return axios({
                url: rest.User.followtheme(_params.themeId, _params.targetUserId),
                method: 'post',
                headers: getters.authHeader
            })
                .then((res) => {
                    // 뭔가 패치를 해야겠는데 확인해 봐야겠다.
                    console.log("팔로우하기")
                    console.log(res)
                    return(res.data.followIdx)
                })
        },

        cancelFollow({ dispatch,getters }: { dispatch: Dispatch,getters:any }, _userThemeIdx: string) {
            axios({
                url: rest.User.cancelFollow(_userThemeIdx),
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
        isFollow({ commit,getters }: { commit: Commit,getters:any }, _data : {userIdx : number, themeIdx:number} ) {
            return axios({
                url :rest.User.isFollow(getters.loginUser.userIdx, _data.userIdx, _data.themeIdx),
                method:'get',
                headers:getters.authHeader
            })
                .then((res) => {
                    
                    return res.data
                })
            }
        }
    }