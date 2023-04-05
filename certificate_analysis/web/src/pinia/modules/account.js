import { defineStore } from 'pinia'

export const useAccount = defineStore('account', {
  state: () => ({
    userinfo: null,
    permissionList: [],
  }),
  actions: {
    // 清除用户信息
    clearUserinfo() {
      this.userinfo = null
    },
    // 设置用户信息
    setUserinfo(userName) {
      this.userinfo = {
        userName: userName,
      }
    },
    // 获取用户信息
    getUserInfo() {
      return this.userinfo
    },
  },
})
