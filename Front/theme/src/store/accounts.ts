// import router from '@/router'
import axios from 'axios'
import rest from '@/API/rest'

export default {
    state: {
        // token: localStorage.getItem('token') || '', 
        token : "test",
        loginUser: {},
    },
    getters: {
        isLoggedIn: (state: { token: string; }) => !!state.token,
    },
    mutations: {
        SET_TOKEN: (state: { token: string; }, token:string) => state.token = token,
    },
    actions: {
        kakaoLogin(){
            axios({
                url: rest.kakao.get_auth(),
                method: "get",
            })
            .then((res)=>{
                console.log(res)
            })
            .catch((err)=>{
                console.log(err)
            })

            }
        }
    }