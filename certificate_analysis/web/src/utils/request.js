import axios from 'axios'
import { ElMessage } from 'element-plus'

const service = axios.create({
  baseURL: import.meta.env.VITE_BASE_URL,
  timeout: 10000,
  withCredentials: true,
})

// 拦截请求
service.interceptors.request.use(
  config => {
    return config
  },
  error => {
    // console.log(error);
    return Promise.reject(error)
  }
)

// 拦截响应
service.interceptors.response.use(
  // 响应成功进入第1个函数，该函数的参数是响应对象
  response => {
    // console.log(response)
    
    return response.data
  },
  // 响应失败进入第2个函数，该函数的参数是错误对象
  error => {
    // console.log("error: ", error);

    // console.dir(error) // 可在此进行错误上报
    // ElMessage.closeAll()

    // try {
    //   ElMessage.error(error.response.data.msg)
    // } catch (err) {
    //   ElMessage.error(error.message)
    // }

    return Promise.reject(error)
  }
)

export default service
