// import router from '@/router'
// import axios from 'axios'
// import rest from '@/api/rest'

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

    }
}