import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import BoardList from '../views/board/BoardList.vue'
import Board from '../views/board/Board.vue'
import Member from '../views/member/Member.vue'
import Login from '../views/member/Login.vue'
import Signup from '../views/member/Signup.vue'
import MemberList from '../views/admin/MemberList.vue'
import CommonCode from '../views/admin/CommonCode.vue'
import StockTest from '../views/stock/StockTest.vue'
import Character from '../views/lostArk/Character.vue'
import Test from '../views/Test.vue'
import Weather from '../views/api/Weather.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/boardList',
    name: 'BoardList',
    component: BoardList
  },
  {
    path: '/member',
    name: 'Member',
    component: Member
  },
  {
    path: '/board',
    name: 'Board',
    component: Board
  },
  {
    path: '/memberList',
    name: 'MemberList',
    component: MemberList
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },
  {
    path: '/test',
    name: 'Test',
    component: Test
  },
  {
    path: '/commonCode',
    name: 'CommonCode',
    component: CommonCode
  },
  {
    path: '/stockTest',
    name: 'StockTest',
    component: StockTest
  },
  {
    path: '/weather',
    name: 'Weather',
    component: Weather
  },
  {
    path: '/character',
    name: 'Character',
    component: Character
  }               
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
