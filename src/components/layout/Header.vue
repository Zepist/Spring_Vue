<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="info">
            <b-navbar-brand href="http://localhost:8081/">Home</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
                <b-nav-item href="http://localhost:8081/boardList">자유게시판</b-nav-item>
                <b-nav-item href="http://localhost:8081/weather">날씨검색</b-nav-item>
                <b-nav-item v-show="fn_isLogin"  href="http://localhost:8081/member">회원정보</b-nav-item>
                <b-nav-item v-show="fn_isLogOut" href="http://localhost:8081/login">로그인</b-nav-item>
                <b-nav-item-dropdown v-show="fn_isLogin" text="주식">
                    <b-dropdown-item href="http://localhost:8081/">거래소</b-dropdown-item>
                    <b-dropdown-item href="http://localhost:8081/stockTest">주식테스트</b-dropdown-item>
                </b-nav-item-dropdown>
                <b-nav-item-dropdown v-show="fn_isLogin" text="로스트아크">
                    <b-dropdown-item href="http://localhost:8081/Character">정보 검색</b-dropdown-item>
                </b-nav-item-dropdown>

                <b-nav-item-dropdown v-show="fn_isAdmin" text="관리자용">
                    <b-dropdown-item href="http://localhost:8081/memberList">회원목록</b-dropdown-item>
                    <b-dropdown-item href="http://localhost:8081/commonCode">공통코드 관리</b-dropdown-item>
                    <!-- 
                    <b-dropdown-item href="#">RU</b-dropdown-item>
                    <b-dropdown-item href="#">FA</b-dropdown-item> 
                    -->
                </b-nav-item-dropdown>

                <b-nav-item href="http://localhost:8081/test">테스트페이지</b-nav-item>
                <div v-show="fn_isLogin" class="text-light pt-2 mx-3">
                       환영합니다. {{this.$store.getters.getUserNm}}님
                </div>
                <b-button @click="logOut" v-show="fn_isLogin" size="sm" class="my-2 my-sm-0"  type="button">로그아웃</b-button>
                <!-- <b-nav-item href="#" disabled>Disabled</b-nav-item> -->
            </b-navbar-nav>
            
            <!--
            <b-navbar-nav class="ml-auto">
                <b-nav-form>
                <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
                <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
                </b-nav-form>

                <b-nav-item-dropdown text="Lang" right>
                <b-dropdown-item href="#">EN</b-dropdown-item>
                <b-dropdown-item href="#">ES</b-dropdown-item>
                <b-dropdown-item href="#">RU</b-dropdown-item>
                <b-dropdown-item href="#">FA</b-dropdown-item>
                </b-nav-item-dropdown>

                <b-nav-item-dropdown right>

                <template #button-content>
                    <em>User</em>
                </template>
                <b-dropdown-item href="#">Profile</b-dropdown-item>
                <b-dropdown-item href="#">Sign Out</b-dropdown-item>
                </b-nav-item-dropdown>
            </b-navbar-nav>
            -->
            

            </b-collapse>
        </b-navbar>
    </div>
</template>

<script>
export default {
    computed:{
        fn_isLogin(){
            return this.$store.getters.getLoginStat;
        },
        fn_isLogOut(){
            return (this.fn_isLogin == true ? false : true);
        },
        fn_isAdmin(){
            return (this.$store.getters.getUserNm == 'ADMIN' ? true : false);
        } 
    },
    methods:{
        logOut(){
            this.$store.commit('logOut');
            window.alert("로그아웃 하였습니다.");
            this.$router.push({name: 'Home'});
        }
    },
    name : "header"
};
</script>