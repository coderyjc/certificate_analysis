import request from '@/utils/request'

// 获取笔试成绩
export const listWrittenScore = () => {
  return request({
    url: '/api/query/written',
    method: 'get',
  })
}
