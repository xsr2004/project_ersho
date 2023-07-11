import {instance1} from "./request";

//搜索数据
export const GetSearchRes=(search,params)=>{
    return instance1.get(`/SearchMall/${search}`,{params})
}
//商品详情
export const GetDetailCard=(id)=>{
    return instance1.get(`/SearchMall/DetailCard?id=${id}`)
}
//提交表单
export const SubmitForm=(data)=>{
    return instance1.post(`/SaleGood/submit`,data)
}
