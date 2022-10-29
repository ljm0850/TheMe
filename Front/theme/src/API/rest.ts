const HOST = "http://localhost:8080"
const ACCOUNT = "/"


// 카카오
const KAKAO_Host = "https://kauth.kakao.com"
const REST_API_KEY = "bab0a08f8b68900521759c285635e38a"
const REDIRECT_URI = "http://localhost:8080"
const KAKAO_GET_AUTHORIZATION = `/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`
// https://kauth.kakao.com/oauth/authorize?client_id=bab0a08f8b68900521759c285635e38a&redirect_uri=http://localhost:8080&response_type=code HTTP/1.1
export default {
    kakao: {
        getAuth: () => KAKAO_Host + KAKAO_GET_AUTHORIZATION,
        login: () => KAKAO_Host,
    },

    accounts: {
        login:()=> HOST + ACCOUNT
    }
}