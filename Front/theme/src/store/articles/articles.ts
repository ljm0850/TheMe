// import router from '@/router'
// import axios from 'axios'
import rest from '@/API/rest'

import axios from "axios"

export default {
    state: {
        feedArticleList: [{ themeName: "임시 테마 이름" }, { themeName: "임시2"}]
    },
    getters: {
        getFeedArticleList: (state: {feedArticleList : Array<object>}) => state.feedArticleList,
        
    },
    mutations: {
        SET_FEED_ARTICLE_LIST: (state: {feedArticleList : Array<object>}, _list:Array<object>) => state.feedArticleList = _list

    },
    actions: {
        getFeedArticleList({ getters }: {getters:any},_region:number) {
            axios({
                url: rest.Feed.feedList(),
                method: 'get',
                headers: getters.authHeader,
                params: { region: _region}  
            })
        }

    }
}