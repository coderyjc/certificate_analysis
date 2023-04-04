import { createRouter, createWebHashHistory } from 'vue-router'

import redirect from './modules/redirect'
import error from './modules/error'
import login from './modules/login'
import home from './modules/home'
import test from './modules/test'

import visualization from './modules/visualization'
import query from './modules/query'
import predict from './modules/predict'

/* 菜单栏的路由 */
// 只有固定菜单
export const fixedRoutes = [...home, ...visualization, ...query, ...predict, ...test]

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    { path: '/', redirect: '/home', },
    ...redirect, // 统一的重定向配置
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
