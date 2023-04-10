import request from '@/utils/request'

// 获取面试成绩
export const listIdentification = data => {
  const {current, size} = { ...data }
  
  return request({
    url: '/identification/all',
    method: 'get',
    params: {
      current: current,
      size: size,
      searchCondition: data,
    },
  })
}

// 添加面试成绩
export const addIdentification = data => {
  return request({
    url: '/identification/add',
    method: 'post',
    params: {
      param: JSON.stringify(data),
    },
  })
}

// 删除面试成绩
export const deleteIdentification = data => {
  return request({
    url: '/identification/delete',
    method: 'post',
    params: {
      id: data,
    },
  })
}

// 选择性导出面试成绩
export const exportIdentification = (exportColumn, exportId, searchCondition) => {

  let baseStr = 'http://localhost:8080/identification/export?'

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
