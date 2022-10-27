const HOST = ""


// 카카오
const KAKAO_Host = "https://kauth.kakao.com"
const REST_API_KEY = "bab0a08f8b68900521759c285635e38a"
const REDIRECT_URI = "http://localhost:8080/"
const KAKAO_GET_AUTHORIZATION = `/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}`
export default {
    kakao: {
        get_auth: () => KAKAO_Host + KAKAO_GET_AUTHORIZATION,
        login: () => KAKAO_Host,
    },

    accounts: {
        
    }
}