export default [
  // 请求用户列表
  {
    url: '/api/query/written',
    method: 'get',
    timeout: 1000,
    response: () => {
      // 响应内容
      return {
        code: 200,
        message: '获取成功',
        data: {
          'list|10': [
            {
              'id|+1': 1,
              name: '@cname()',
              examId:  /\d{10,10}/,
              identificationId:  /\d{18,18}/,
              interviewYear: /201\d{1,1}年/,
              qualificationId:  /\d{14,14}/,
              major: '专业',
              validateDate: /201\d{1,1}-01-01/,
            },
          ],
          'total|100-1000': 1,
        },
      }
    },
  },
]
