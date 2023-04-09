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

  let baseStr = 'http://localhost:8080/certification/export?'

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
