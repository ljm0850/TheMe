const HOST = "http://localhost:8080"
const HOST1 = "http://k7c203.p.ssafy.io:8010/v2/api-docs"
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
const REST_API_KEY = "bab0a08f8b68900521759c285635e38a"
const REDIRECT_URI = "http://localhost:8080"
// const REDIRECT_URI = "http://k7c2031.p.ssafy.io:8080"
const KAKAO_GET_AUTHORIZATION = `/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`
// https://kauth.kakao.com/oauth/authorize?client_id=bab0a08f8b68900521759c285635e38a&redirect_uri=http://localhost:8080&response_type=code HTTP/1.1
export default {
    kakao: {
        getAuth: () => KAKAO_Host + KAKAO_GET_AUTHORIZATION,
        login: () => KAKAO_Host,
    },

    User: {
        login:() => HOST + USER + '/login',
        userInfo:(_user_id:string) => HOST + USER + `/info/${_user_id}`,
        userTheme:(_user_id:string) => HOST + USER + THEME + `/${_user_id}`,
        userBoard:(_theme_id:string,_user_id:string) => HOST + USER + BOARD + `/${_theme_id}/${_user_id}`,
        signOut:(_user_id:string) => HOST + USER + `/${_user_id}`,
        themeFollow:(_theme_id:string, _user_id:string, _target_user_id:string) => HOST + USER + FOLLOW + `/${_theme_id}/${_user_id}/${_target_user_id}`,
        cancelThemeFollow:(_follow_id:string) => HOST + USER + FOLLOW + `/${_follow_id}`,
        themeFollowList:(_user_id:string) => HOST + USER + `/following/${_user_id}`,
        followerList:() => HOST + USER + '/follower',
        followingList:() => HOST + USER + '/following',
        unfollow:() => HOST + USER + '/unfollow',
    },

    Board: {
        createArticle:() => HOST + FEED + BOARD,
        fetchArticle:(_board_id:string) => HOST + FEED + BOARD + `/${_board_id}`,
        LikeArticle:(_board_id:string) => HOST + FEED + BOARD + `/like/${_board_id}`,
        reportArticle:(_board_id:string,_reporter_id:string) => HOST + BOARD + `/alert/${_board_id}/${_reporter_id}`,
        detailArticle:(_board_id:string) => HOST + BOARD + `/${_board_id}`
    },

    Comment: {
        commentInfo:(_board_id:string) => HOST + FEED + COMMENT +`/${_board_id}`,
        reportComment:(_board_id:string,_reporter_id:string) => HOST + FEED + COMMENT + `/alert/${_board_id}/${_reporter_id}`
    },

    Feed: {
        recommendTheme:() => HOST + FEED + '/recommend', 
        feedList:()=> HOST + FEED  //params로 region 넘겨야함(0:전국 1:서울 2:대전 3:광주 4:구미 5:부울경)
    },

    Map: {
        publicThemeList:() => HOST + THEME + MAP + THEME,   //params로 isMarked, sort 넘겨야함(0은 인기순, 1은 최신순)
        themeArticleList:(_theme_id:string) => HOST + THEME + MAP + `/${_theme_id}`, // params로 sort 넘겨야함(0은 인기순 1은 최신순)
        placeArticleList:() => HOST + THEME + MAP + '/place'    // params로 value (주소)
    },

    Search: {
        recommendUser:()=> HOST + THEME + SEARCH,
        userAutoComplete:()=>HOST + THEME + SEARCH + USER,  // params로 value = input값
        themeAutoComplete:()=>HOST + THEME + SEARCH + THEME,    //params로 value = input값
        userSearch:() => HOST + THEME + SEARCH + USER + '/info',    //params로 value= input값
        themeSearch:() => HOST + THEME + SEARCH + THEME + '/info'   //params로 value = input값
    },
    Theme: {
        createTheme:()=> HOST + THEME,
        recommendTheme:()=> HOST + THEME + '/recommand',
        isTheme:()=>HOST + THEME + SEARCH,
    }
}