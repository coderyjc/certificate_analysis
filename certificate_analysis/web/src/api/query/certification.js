import request from '@/utils/request'

// 获取面试成绩
export const listCertification = data => {
  const {current, size} = { ...data }
  return request({
    url: '/certification/all',
    method: 'get',
    params: {
      current: current,
      size: size,
      searchCondition: data,
    },
  })
}

// 添加面试成绩
export const addCertification = data => {
  return request({
    url: '/certification/add',
    method: 'post',
    params: {
      param: JSON.stringify(data),
    },
  })
}

// 添加面试成绩
export const updateCertification = data => {
  return request({
    url: '/certification/update',
    method: 'post',
    params: {
      param: JSON.stringify(data),
    },
  })
}

// 删除面试成绩
export const deleteCertification = data => {
  return request({
    url: '/certification/delete',
    method: 'post',
    params: {
      id: data,
    },
  })
}

// 选择性导出面试成绩
export const exportCertification = (exportColumn, exportId, searchCondition) => {

  let baseStr = import.meta.env.VITE_BASE_URL + '/certification/export?'

  let exportStr = ''
  if(exportColumn && exportColumn.length > 0){
    let i = 0;
    exportColumn.forEach(element => {
      if(i == 0) 
        exportStr = 'exportColumn[]=' + element
      else 
        exportStr += '&exportColumn[]=' + element
      i += 1
    });
  }

  let columnStr = ''
  if(exportId && exportId.length > 0){
    let i = 0;
    exportId.forEach(element => {
      if(i == 0) 
        columnStr = 'exportId[]=' + element
      else 
        columnStr += '&exportId[]=' + element
      i += 1
    });
  }

  let conditionStr = 'searchCondition=' + searchCondition

  let result = baseStr + exportStr + '&' + columnStr + '&' + conditionStr
  
  return result
}

// 获取所有的面试年份
export const listInterviewYear = data => {
  return request({
    url: '/certification/years',
    method: 'get',
  })
}

// 获取所有的有效期数据
export const listValidateYear = data => {
  return request({
    url: '/certification/validate_years',
    method: 'get',
  })
}

// 获取统计数据
export const statisticCertification = data => {
  return request({
    url: '/certification/statistic',
    method: 'get',
    params:{
      condition: data
    }
  })
}

// 导出统计数据
export const exportStatisticCertification = data => {
  return import.meta.env.VITE_BASE_URL + '/certification/statistic/export?condition=' + JSON.stringify(data)
}

