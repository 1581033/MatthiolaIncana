import { createStore } from 'vuex'
import getters from './getters'
import user from './modules/user'
import permission from './modules/async-router'
import app from "./modules/app";

export default createStore({
    modules: {
        app,
        user,
        permission
    },
    state: {},
    mutations: {},
    actions: {},
    getters
})
