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

// 检查用户名是否重复
export const checkRepeat = data => {
  return request({
    url: '/user/check',
    method: 'get',
    params: {
      username: data
    },
  })
}

// 获取所有用户
export const listUser = data => {

  const {current, size} = { ...data }

  return request({
    url: '/user/all',
    method: 'get',
    params: {
      current: current,
      size: size,
      searchCondition: data
    },
  })
}

// 修改密码
export const changePwd = data => {
  return request({
    url: '/user/change_password',
    method: 'post',
    params: {
      username: data.username,
      oldPassword: data.oldPassword,
      newPassword: data.password
    },
  })
}

// 添加用户
export const addUser = data => {
  return request({
    url: '/user/add',
    method: 'post',
    params: {
      username: data.username,
      password: data.password
    },
  })
}

// 删除用户
export const deleteUser = data => {
  return request({
    url: '/user/delete',
    method: 'post',
    params: {
      id: data
    },
  })
}

