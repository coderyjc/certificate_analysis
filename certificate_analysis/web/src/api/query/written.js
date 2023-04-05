import request from '@/utils/request'

// 获取笔试成绩
export const listWrittenScore = (data) => {
  return request({
    url: '/writtenScore/all',
    method: 'get',
    params: {
      ...data
    }
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

