import axios from 'axios'

// 表单提交，查询主页，商品展示
const instance1 = axios.create({
    // baseURL: 'http://localhost:8083',//开发环境

    baseURL:'http://47.106.211.118:8082',//真环境

    timeout: 5000,
    headers: {'X-Custom-Header': 'foobar','Content-Type':'multipart/form-data'}
  });
// instance1添加响应拦截器
instance1.interceptors.request.use(function (config) {

    return config;
}, function (error) {
    return Promise.reject(error);
});
// instance1添加响应拦截器
instance1.interceptors.response.use(function (response) {
    return response;
  }, function (error) {
    return Promise.reject(error);
  });



export {instance1}