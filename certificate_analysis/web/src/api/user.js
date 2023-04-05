import request from '@/utils/request'

// 登录接口
export const Login = data => {
  return request({
    url: '/user/login',
    method: 'post',
    params: {
      ...data
    },
  })
}
