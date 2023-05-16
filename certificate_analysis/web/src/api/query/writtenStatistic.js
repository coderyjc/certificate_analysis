import request from '@/utils/request'

// 获取笔试成绩
export const listWrittenStatistic = (start, end) => {
  return request({
    url: '/writtenStatistic/all',
    method: 'get',
    params: {
      start: start,
      end: end
    },
  })
}

// 修改笔试成绩
export const updateWrittenStatistic = data => {
  return request({
    url: '/writtenStatistic/update',
    method: 'post',
    params: {
      ...data,
    },
  })
}

// 导出统计数据
export const exportStatisticWrittenScore = (start, end) => {
  return import.meta.env.VITE_BASE_URL + '/writtenStatistic/export?start=' + start + '&end=' + end 
}

