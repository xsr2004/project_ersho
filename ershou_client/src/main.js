import Vue from 'vue'
import App from './App.vue'
import './plugins/Fastclick'//fastclick插件
import './style/common.less'
import router from './router'
import './plugins/vant'//vant库按需引入，在vant.js配置
import 'amfe-flexible'//移动端适配

import axios from 'axios'

Vue.config.productionTip = false

new Vue({
  el:'#app',
  render: h => h(App),
  router,
}).$mount('#app')
