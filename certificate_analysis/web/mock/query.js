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
              id:  /\d{18,18}/,
              "pedagogyScore|10-100": 100,
              "educationalPsychologyScore|10-100": 100, 
              "qualityScore|10-100": 100,
              "pedagogyStatus|1": [0, 1],
              "educationalPsychologyStatus|1": [0, 1], 
              "qualityStatus|1": [0, 1],
              "workAddress|1-10": '★',
              testDate: '2013',
            },
          ],
          'total|50-1000': 1,
        },
      }
    },
  },
]
