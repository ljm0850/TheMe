// import router from '@/router'
import axios from 'axios'
import rest from '@/API/rest'
import _, { method } from "lodash"

export default {
    state: {
        // token: localStorage.getItem('token') || '', 
        token :"",
        loginUser: {},
    },
    getters: {
        isLoggedIn: (state: { loginUser: Object; }) => !_.isEmpty(state.loginUser),
    },
    mutations: {
        SET_TOKEN: (state: { token: string; }, _token:string) => state.token = _token,
        SET_LOGIN_USER: (state:{ loginUser:Object},_user:Object) => state.loginUser = _user
    },
    actions: {
        // login({commit,dispatch},_kakaoToken:string){
        // login(_kakaoToken:string){
        //     axios({
        //         url:rest.accounts.login(),
        //         method: 'post',
        //         data: _kakaoToken
        //     })
        //     .then(res=>{
        //         // const token = res.data.
        //         // dispatch('saveToken',token)
        //     })
        // }
        
        }
    }