import request from '@/utils/request'

// 总数数据
export const getDashboardTotalData = data => {
  return request({
    url: '/dashboard/total_data',
    method: 'get',
  })
}

// 趋势数据 - 证书
export const getCertificationData = data => { 
  return request({
    url: '/dashboard/certification_trend',
    method: 'get',
  })
}

// 趋势数据 - 笔试
export const getWrittenScoreData = data => {
  return request({
    url: '/dashboard/writtenScore_trend',
    method: 'get'
  })
}

// 趋势数据 - 面试
export const getInterviewScoreData = data => {
  return request({
    url: '/dashboard/interviewScore_trend',
    method: 'get'
  })
}

// 趋势数据 - 认定
export const getIdentificationData = data => {
  return request({
    url: '/dashboard/identification_trend',
    method: 'get'
  })
}