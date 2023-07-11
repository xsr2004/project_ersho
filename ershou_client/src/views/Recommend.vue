<template>
  <div class="Search">
      <div class="nav">
        <van-image src="https://img01.yzcdn.cn/vant/cat.jpeg" round
                   class="nav_img"/>
        <van-search
            v-model="value"
            shape="round"
            placeholder="来这搜西农！"
            @search="onSearch"
            @focus="onfocus"
            @clear="onClear"
            @blur="onblur"
        >
          <template #left-icon>
            <van-icon name=""/>
          </template>
          <template #action>
            <van-icon name=""/>
          </template>
        </van-search>
      </div>
    <van-pull-refresh v-model="isLoading" @refresh="onRefresh" loosing-text="">
      <div class="main"  :style="{ width: windowWidth + 'px' }">

        <h3>体验最便捷的二手交易平台</h3>
        <ul>
          <li>这会是一个信息平台<br>它会有俩个核心功能（猜的）：
          </li>
          <li> 1.同学们可以卖掉想卖的，多余的东西</li>
          <li>2.同学们可以通过搜索的方式买到校内的所有东西</li>
          <li><br>从4月15日开始在idea建立这个工程，这个小网页从前端到后端已经基本实现了功能（大佬勿喷）</li>
          <li>主打一个小成本试错的态度，目前进入收集商品的阶段</li>
          <li>平台绝对不会泄露大家的信息，因为本人就是西农的信工大二学生，并且也根本没人访问(……)</li>
          <li>这东西没钱可赚纯纯怨种，但如果能帮得到你，那可真是泰裤啦！</li>
          <li>这里放个qq：2751247482，欢迎各位同学提意见，提想法</li>
        </ul>

      </div>
    </van-pull-refresh>



  </div>
</template>

<script>
import {Toast} from "vant";
import '../style/common.less'
export default {
    name:'Recommend',

    data(){
        return {
            value:'',//搜索框
          //刷新器
            isLoading: false,
          //页面高度
          windowWidth:document.documentElement.clientWidth,
          // windowHeight:document.documentElement.clientHeight
        }
    },
    methods: {
      onSearch() {
      },
      onfocus() {
        this.$router.push('/SearchMall')
      },
      onClear() {
      },
      onblur() {

      },
      onRefresh(){//回弹时出现isLoading，onRefresh则消失
          this.isLoading=false
      },

    },
    mounted(){
      // this.onRefresh()
      // this.windowHeight = document.documentElement.clientHeight;//获取用户设备高度
      // document.body.style.overflow = 'hidden';
      // window.addEventListener("scroll",this.scrollFn)
      // window.innerHeight=this.windowHeight
      // window.innerWidth=this.windowWidth
      // console.log(window.innerHeight)
      // console.log(window.innerWidth)
    },

}
</script>

<style lang="less" scoped>

   .Search{
     .nav {
       justify-content: space-between;
       .nav_img{
         width: 50px;
         height: 50px;
       }
     }
     .main{
       background-color: #fff;
       box-sizing: border-box;
       height: 500px;
       overflow-y: scroll;
       ul{
         font-size: 18px;
         color: grey;
       }
     }

   }

</style>