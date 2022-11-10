const HOST = "https://k7c203.p.ssafy.io:8000"
const USER = "/user"
const THEME = '/theme'
const BOARD = '/board'
const FOLLOW = '/follow'
const FEED = '/feed'
const COMMENT = '/comment'
const MAP = '/map'
const SEARCH = '/search'

// 카카오
const KAKAO_Host = "https://kauth.kakao.com"
// const REDIRECT_URI = "http://localhost:8080"
const REDIRECT_URI = "https://k7c2031.p.ssafy.io"
const KAKAO_GET_AUTHORIZATION = `/oauth/authorize?client_id=${process.env.VUE_APP_KAKAO_REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`
const KAKAO_TOKEN = "/oauth/token"
export default {
    kakao: {
        redirect:()=> REDIRECT_URI,
        getAuth: () => KAKAO_Host + KAKAO_GET_AUTHORIZATION,
        login: () => KAKAO_Host + KAKAO_TOKEN
    },

    User: {
        login:() => HOST + USER + '/login',
        userInfo: (_nickname: string) => HOST + USER + `/info/${_nickname}`,
        duplicationNickname: (_nickname:string) => HOST + USER + `/duplication/${_nickname}`,
        deleteUser:(_nickname:string) => HOST + USER + `/${_nickname}`,
        followtheme:(_theme_idx:string, _user_idx:string, _target_user_idx:string) => HOST + USER + FOLLOW + `/${_theme_idx}/${_user_idx}/${_target_user_idx}`,
        cancelFollow:(_follow_id:string) => HOST + USER + FOLLOW + `/${_follow_id}`,
        getFollowingThemeIdxList: (_user_idx:string) => HOST + USER + '/following' + THEME + `/${_user_idx}`,
        getFollowerList: (_user_idx: string) => HOST + USER + `/follower/${_user_idx}`,
        getFollowingList:(_user_idx:string) => HOST + USER + `/following/${_user_idx}`,
        unfollowUser: (_target_user_idx:string,_user_idx:string) => HOST + USER + `/unfollow/${_target_user_idx}/${_user_idx}`,
        userInfoByIdx: (_user_idx: string) => HOST + USER + `/info/id/${_user_idx}`,
        liveSearchUser: ()=> HOST + USER + '/live/search',
        getSerchPerson:() => HOST + USER + '/search/user/info',
        recommandPersonList : () => HOST + USER + `/search/recommend`,
    },
    
    Theme: {
        getPublicThemeList:() => HOST + THEME + MAP + THEME,
        liveSearchTheme:()=>HOST + THEME + '/live' + SEARCH,    
        searchThemeInfo:() => HOST + THEME + SEARCH + THEME + '/info',   
        registTheme: () => HOST + THEME,
        createUserTheme: () => HOST + THEME +'/userTheme',
        getUerThemeList: (_user_idx: string) => HOST + THEME + `/${_user_idx}`,
        recommendThemeList:()=> HOST + THEME + '/recommend',    // api 미완
        searchTheme: (_target:string)=>HOST + THEME + SEARCH + `/${_target}`,
        scrapTheme: (_user_idx:string, _theme_idx:string)=>HOST + THEME + `/bookmark/${_user_idx}/${_theme_idx}`
    },
    
    Feed: {
        createArticle: () => HOST + FEED + BOARD,
        fetchArticle:(_board_idx:string) => HOST + FEED + BOARD + `/${_board_idx}`,
        LikeArticle:(_board_id:string) => HOST + FEED + BOARD + `/like/${_board_id}`,
        reportArticle: (_board_idx: string,) => HOST + FEED + BOARD + `/alert/${_board_idx}`,
        themeArticleList:(_theme_idx:string) => HOST + FEED + MAP + THEME + `${_theme_idx}`,
        placeArticleList:() => HOST + FEED + MAP + '/place',    
        comment: (_board_idx: string) => HOST + FEED + COMMENT + `/${_board_idx}`,
        deleteComment: (_comment_idx: string) => HOST + COMMENT + `${_comment_idx}`,
        reportComment:(_comment_idx:string) => HOST + FEED + COMMENT + `/alert/${_comment_idx}`,
        recommendTheme:() => HOST + FEED + '/recommend', 
        feedList:()=> HOST + FEED + '/region'  
    },

}
