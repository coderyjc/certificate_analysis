import { createRouter, createWebHashHistory } from 'vue-router'

import redirect from './modules/redirect'
import error from './modules/error'
import login from './modules/login'
import home from './modules/home'

import query from './modules/query'
import analysis from './modules/analysis'
import user from './modules/user'


/* 菜单栏的路由 */
export const fixedRoutes = [...home, ...query, ...analysis, ...user]

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    { path: '/', redirect: '/home', },
    ...redirect,
    ...login,
    ...fixedRoutes,
    ...error,
  ],
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { left: 0, top: 0 }
    }
  },
})

export default router
