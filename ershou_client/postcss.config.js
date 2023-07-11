module.exports = {
    "plugins": {
        'postcss-pxtorem': {
            rootValue: 37.5,
            propList: ['*']
        }
    }
}
//rootValue根据设计稿宽度除以10进行设置，这边假设设计稿为375，即rootValue设为37.5；
//propList是设置需要转换的属性，这边*为所有都进行转换。