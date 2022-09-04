

const userStore = {
    state:{
        ID : '',
        UserNm : '',
        UserID : '',
        //UserNickNm : '',
        LoginStat : false,
        Authority : ''
    },
    getters:{
        getID : function(state){
            return state.ID;
        },
        getUserNm : function(state){
            return state.UserNm;
        },
        getUserID : function(state){
            return state.UserID;
        },
        getLoginStat : function(state){
            return state.LoginStat;
        },
        //getUserNickNm : function(state){
        //    return state.UserNickNm;
        //},
        getAuthority : function(state){
            return state.Authority;
        }
    },
    mutations:{
        loginInfo : function(state,information){
            state.ID = information.id;
            state.UserID = information.userid;            
            state.UserNm = information.name;
            //state.UserNickNm = information.nickname;
            state.LoginStat = true;
            state.Authority = (information.nickname == 'ADMIN' ? 'ADMIN' : '');
        },
        logOut : function(state){
            state.ID = '';
            state.UserID = '';            
            state.UserNm = '';
            //state.UserNickNm = '';
            state.LoginStat = false;
            state.Authority = '';
        }
    }, // 동기
    actions:{
        
    } // 비동기
};

export default userStore