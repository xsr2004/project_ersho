//1. 导入 Vue 和 VueRouter 的包

import Vue from 'vue'
import VueRouter from 'vue-router'
import Recommend from '../views/Recommend'
import SaleGood from '../views/SaleGood'
//2. 调用Vue.use()函数，把VueRouter安装为Vue的插件
import MyHome from "@/views/MyHome";//我的
import MyBuy from "@/views/sonofviews/MyBuy"//我买的
import MySale from "@/views/sonofviews/MySale";//我卖的
import MyAddress from "@/views/sonofviews/MyAddress";//我的地址
import MyConfiguration from "@/views/sonofviews/MyConfiguration";
import MainView from "@/views/MainView";
import SearchMall from "@/views/SearchMall";
import SelectListOne from "@/views/sonofviews/SelectListOne";
import DetailCard from "@/views/sonofviews/DetailCard";
//设置
Vue.use(VueRouter)
 
//3. 创建路由的实例对象
const router = new VueRouter({
    routes:[
        {
            path:'/',
            component:MainView,
            redirect:'/Recommend',

            children:[
                {
                    path:'Recommend',
                    component:Recommend,
                    name:'Recommend',
                },
                {
                    path:'SaleGood',
                    component:SaleGood,
                    name:'SaleGood'
                },
                {
                    path:'MyHome',
                    component:MyHome,
                    name:'MyHome',
                },
                {
                    path:'MyBuy',
                    component:MyBuy,
                    name:'MyBuy',
                },
                {
                    path:'MySale',
                    component:MySale,
                    name:'MySale',
                },
                {
                    path: 'MyAddress',
                    component: MyAddress,
                    name: 'MyAddress',
                },
                {
                    path:'MyConfiguration',
                    component:MyConfiguration,
                    name:'MyConfiguration',
                }
            ]
        },
                {
                    path:'/SearchMall',
                    component:SearchMall,
                    name:'SearchMall',
                },
                {
                    path:'/DetailCard',
                    component:DetailCard,
                    name:'DetailCard',

                },




    ]
})
 
//4. 向外共享路由的实例对象
export default router