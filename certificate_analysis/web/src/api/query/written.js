import request from '@/utils/request'

// 获取笔试成绩
export const listWrittenScore = data => {
  return request({
    url: '/writtenScore/all',
    method: 'get',
    params: {
      ...data,
    },
  })
}

// 添加笔试成绩
export const addWrittenScore = data => {
  return request({
    url: '/writtenScore/add',
    method: 'post',
    params: {
      ...data,
    },
  })
}

// 删除笔试成绩
export const deleteWrittenScore = data => {
  return request({
    url: '/writtenScore/delete',
    method: 'post',
    params: {
      id: data,
    },
  })
}

// 获取所有工作地点
export const listWorkAddress = () => {
  return request({
    url: '/writtenScore/work_address',
    method: 'get',
  })
}

// 获取所有年份
export const listExamYear = () => {
  return request({
    url: '/writtenScore/exam_year',
    method: 'get',
  })
}
