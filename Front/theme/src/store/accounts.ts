// import router from '@/router'
import axios from 'axios'
import rest from '@/API/rest'

export default {
    state: {
        // token: localStorage.getItem('token') || '', 
        token : "",
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
            // axios({
            //     // url: rest.kakao.get_auth(),
            //     url: "https://kauth.kakao.com/oauth/authorize?client_id=bab0a08f8b68900521759c285635e38a&redirect_uri=http://localhost:8080&response_type=code",
            //     method: "get",
            // })
            // .then((res)=>{
            //     console.log(res)
            // })
            // .catch((err)=>{
            //     console.log("에러발생")
            //     console.log(err)
            // })
            // Kakao.Auth.authorize()

            }
        }
    }