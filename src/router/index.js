import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import BoardList from '../views/board/BoardList.vue'
import Board from '../views/board/Board.vue'
import MemberList from '../views/member/MemberList.vue'
import Member from '../views/member/Member.vue'
import Login from '../views/member/Login.vue'

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
    path: '/loginInfo',
    name: 'LoginInfo',
    component: Login
  }        
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
