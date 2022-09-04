import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

import userStore from  './modules/userStore.js'

export const store = new Vuex.Store({
    modules:{
        userStore : userStore
    }, // 비동기
    plugins: [createPersistedState({
        paths: ["userStore"]
    })]
});