import request from '@/utils/request'

// 获取备份列表
export const listBackupAll = data => {
  const {current, size, name} = { ...data }
  return request({
    url: '/backup/all',
    method: 'get',
    params: {
      current: current,
      size: size,
      table: name,
    },
  })
}

// 添加备份
export const addBackup = (tableName, description) => {
  return request({
    url: '/backup/add',
    method: 'post',
    params: {
      table_name: tableName,
      description: description
    },
  })
}

// 删除备份
export const deleteBackup = data => {
  return request({
    url: '/backup/delete',
    method: 'post',
    params: {
      id: data,
    },
  })
}

// 恢复数据
export const recoverBackup = data => {
  return request({
    url: '/backup/recover',
    method: 'post',
    params: {
      id: data,
    },
  })
}

