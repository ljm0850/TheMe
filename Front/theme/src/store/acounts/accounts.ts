// import router from '@/router'
import axios from 'axios'
import rest from '@/API/rest'
import _ from "lodash"
import { Commit, Dispatch } from 'vuex';

export default {
    state: {
        // token: localStorage.getItem('token') || '', 
        token :"",
        loginUser: {},
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
        kakaoLogin({dispatch}:{dispatch:Dispatch},_code:string){
            axios({
                url: rest.kakao.login(),
                method: 'post',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                data: {
                    grant_type:'authorization_code',
                    client_id: 'bab0a08f8b68900521759c285635e38a',
                    redirect_uri: rest.kakao.redirect(),
                    code:_code,
                }
            })
            .then((res)=>{
                console.log(res.data)
            })
            .catch((err)=>{
                console.log(err)
            })

        },


        login({ commit }: { commit: Commit },_kakaoCode:string) {
            axios({
                url: rest.User.login(),
                method: 'post',
                data: {kakaoId: _kakaoCode }
            })
                .then((res) => {
                commit('SET_TOKEN',res.data)
                commit('SET_LOGIN_USER',res.data)
            })
        }
        
        }
    }