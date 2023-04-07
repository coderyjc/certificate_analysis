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

// 选择性导出笔试成绩
export const exportWrittenScore = (exportColumn, exportId, searchCondition) => {

  let baseStr = 'http://localhost:8080/writtenScore/export?'

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


// --------------Deprecated--------------------------------------------------------

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
