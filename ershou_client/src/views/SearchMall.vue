<template>
  <div>
    <div class="nav" ref="nav">

      <van-search
          v-model="value"
          :showError="showError"
          shape="round"
          placeholder="请输入搜索关键词"
          @search="onSearch"
          @input="debounceMethod"
          autofocus
      >
        <template #left>
          <van-icon name="arrow-left" @click="goBack()" style="margin:0 15px"/>

        </template>
        <template #left-icon>
          <van-icon name=""/>
        </template>
        <template #right-icon>
          <van-button type="primary" @click="onSearch">搜索</van-button>
        </template>
        <template #action>

        </template>

      </van-search>
    </div>

    <div v-if="showHistory" class="HistoryAndRecommend">
<!--        <div class="history">-->
<!--            <span>历史搜索</span>-->
<!--            <van-button type="info" plain icon="browsing-history" >消除记录</van-button>-->
<!--            <div class="histroyCard">-->
<!--              <van-tag v-for="(item,index) in HistoryTagList"-->
<!--                       :key="index"-->
<!--                       @click="clickTag(item)"-->
<!--                       color="rgba(227,255,236)"-->
<!--                       text-color="rgb(34,139,34)">-->
<!--                  {{item}}-->
<!--              </van-tag>-->
<!--            </div>-->
<!--        </div>-->
        <div class="recommend">
          <span>大家都在找的类型</span>
          <div class="histroyCard">
            <van-tag v-for="(item,index) in RecommendTagList"
                     :key="index"
                     @click="clickTag(item)"
                     color="rgba(227,255,236)"
                     text-color="rgb(34,139,34)">
              {{item}}
            </van-tag>
          </div>
        </div>
    </div>
    <div v-if="showSelect&&canMounted">
      <SelectListOne    @click="onclickSelect(item.name)"
                        :selectName="item.name"
                        :selectKey="selectKey"
                        v-if="true"
                        v-for="(item,index) in SearchLike" :key="index">
      </SelectListOne>
      <p v-if="showError">换个搜索词吧</p>
    </div>
    <div v-if="!showError&&showCard">
      <van-dropdown-menu active-color="#4fc08d" style="height: 60px" >
<!--        综合     时间-->
        <van-dropdown-item v-model="zonghe.value" :options="zonghe.zongheSortStyle"/>
        <van-dropdown-item v-model="time.value" :options="time.TimeSortStyle" />
<!--        筛选    表单-->
        <van-dropdown-item title="筛选" ref="item">
          <van-cell center title="区域">
            <template #right-icon>
              <van-radio-group v-model="selectForm.radio" direction="horizontal" @change="handleRadioChange">
                <van-radio name="全校" checked-color="#4fc08d">全校(默认)</van-radio>
                <van-radio name="西区" checked-color="#4fc08d">西区</van-radio>
                <van-radio name="东区" checked-color="#4fc08d">东区</van-radio>
              </van-radio-group>
            </template>
          </van-cell>

        </van-dropdown-item>
      </van-dropdown-menu>

      <div class="card" :style="{height: cardHeight+'px'}">
        <van-card
            :price="item.price"
            :desc="item.description"
            :title="item.name"
            :origin-price="item.oldprice"
            @click="goDetailCard(item)"
            v-for="(item,index) in SearchLike" :key="index"
        >
          <template #num>
              <span>{{item.address}}</span>
          </template>
          <template #thumb>
            <van-image :src="getUrl(item)">
              <template v-slot:loading>
                <van-loading  size="25" color="#4fc08d" />
              </template>
            </van-image>
          </template>
        </van-card>
      </div>
      <p v-if="showNUll" style="position: absolute;top: 200px">此筛选结果为空</p>

    </div>
  </div>

</template>

<script>
import {GetSearchRes} from "@/api";
import {Search, Toast} from "vant";

import _ from 'lodash'
import '../style/common.less'
import SelectListOne from "@/views/sonofviews/SelectListOne";
import DetailCard from "@/views/sonofviews/DetailCard";
export default {
  name: "SearchMall",
  components: {DetailCard, SelectListOne},

  data(){
    return {
      HistoryTagList:[
        '二手','angular','shit','module','noodles'
      ],
      RecommendTagList:[
          '二手','angular','shit','module','noodles'
      ],
      value:'',
      showSelect:Boolean,
      showError:Boolean,
      showCard:Boolean,
      showHistory:Boolean,
      showDetailCard:false,
      showNUll:Boolean,
      itemName:'',
      SearchLike:[

      ],

      canMounted:Boolean,//控制selectlistone组件渲染时机
      zonghe:{
        value: 0,//“综合”value
        zongheSortStyle: [
          { text: '综合', value: 0 },
          { text: '价格升序', value: 1 },
          { text: '价格降序', value: 2 },
        ],

      },
      time:{
        value:3,
        TimeSortStyle: [
          { text: '时间', value: 3 },
          { text: '最近一周', value: 4 },
          { text: '最近一年', value: 5 },
        ],
      },
      selectForm:{
        switch1: false,// 包邮/品保，默认不
        radio:'全校',//区域，默认全校不分区域
      },
      DetailCard:{
        name:'',
      },
      params:{
        zongheValue:0,
        timeValue:3,
        switch1: false,
        radio:'全校',
      },
      windowHeight: document.documentElement.clientHeight,
      cardHeight:0,
    }
  },
  computed:{
    selectKey(){
      return this.value
    },

  },
  methods:{
    //当radio改变，关闭dropmenu的该item
    handleRadioChange(){
      this.$refs.item.toggle();
      this.params.radio=this.selectForm.radio
      this.onSearch()
    },
    //初始化参数
    initAllParams(){
      this.selectForm.radio='全校'
      this.selectForm.switch1='false'
      this.zonghe.value=0
      this.time.value=3
    },
    //将图片url处理，向服务端发请求并加载图片
    getUrl(item){
      const url = new URL(`${JSON.parse(item.media)[0]||item.media}`, 'http://example.com')
      return url.toString()
    },

    clickTag(item){
      console.log(item)
      this.value=item
      this.onSearch()
    },
    onSearch(){
      //请求成功后，将返回数据给SearchLike，showSelect为false，showCard为true
      if(this.value!=''){
        Toast.loading({
          message: '加载中...',
          forbidClick: true,
        });
        console.log("showcard++++"+this.showCard)
        GetSearchRes(this.value,this.params).then((res)=>{
          Toast.clear()
          console.log(res.data.data)
          this.SearchLike=res.data.data
          this.showNUll=this.SearchLike.length==0?true:false

          this.showSelect=false
          this.showCard=true

          console.log(this.SearchLike)
        }).catch((error)=>{
          console.log(error)
        })
      }else{
        Toast.fail("请输入值")
        this.SearchLike=[]
      }

    },

    ListSelect() {
      this.canMounted=false
      if(this.value!=''){
        if(this.SearchLike.length==0){
          Toast.loading({
            message: '加载中...',
            forbidClick: true,
          });
        }
        GetSearchRes(this.value,this.params).then((res)=>{
          Toast.clear()
          this.showSelect=true
          this.SearchLike=res.data.data
          //需要在Searchlike完全更新以后再去挂载selectlistone
          this.canMounted=true
          console.log(this.SearchLike)
          if(this.SearchLike.length==0){
            this.showError=true
          }
        }).catch((error)=>{
          console.log(error)
        })
      }else{
        this.SearchLike=[]
      }
    },
    debounceMethod(){
      //将showCardfalse，调自定义的防抖
      this.showCard=false
      this.showHistory=false
      this.initAllParams();
      this.debounce()
    },
    debounce:

        _.debounce(function (){
          console.log("debounceMethod的value="+this.value)
          this.ListSelect()
        }, 300),
    onclickSelect(name){
        this.value=name
        Toast.loading({
          message: '加载中...',
          forbidClick: true,
        });
        GetSearchRes(name,this.params).then((res)=>{
          Toast.clear()
          this.showCard=true
          this.showSelect=false
          this.SearchLike=res.data.data
          console.log(this.SearchLike)
        }).catch((error)=>{
          console.log(error)
        })


    },
    goBack(){
      //SearchMall的goback返回的是首页
      if(this.value!=''){
        this.value='';
        return;
      }
      this.$router.push('/Recommend');
    },
    goDetailCard(item) {
      // console.log(JSON.parse(item.media)[0])
        this.$router.push(
            {
              path:'/DetailCard',
              query:
                  {
                    name:item.name,
                    fatherLike:this.value,
                    id:item.id
                  }

            })
    },
    getCardHeight(){
      const navHeight = this.$refs.nav.clientHeight;
      console.log(this.$refs.menu)
      const pageHeight = document.documentElement.clientHeight;
      this.cardHeight = pageHeight - navHeight - 60;
    }
 },
  watch:{
    value:function (newVal,oldVal){
      console.log("newval="+newVal)
      console.log("oldval是"+oldVal)
      // 一旦新旧不同，card就消失
      if(newVal==''){//加个推荐搜索和搜索记录标签
          this.showHistory=true
          //从local取item的name
          this.showCard=false
          this.showSelect=false
      }else{
        this.showHistory=false
      }
    },
    'zonghe.value':function (newVal,oldVal){
      if(newVal!=oldVal){
        this.params.zongheValue=newVal
        this.onSearch()
      }
    },
    'time.value':function (newVal,oldVal){
      if(newVal!=oldVal){
        this.params.timeValue=newVal
        this.onSearch()
      }
    },
    'selectForm.switch1':function (newVal,oldVal){
      if(newVal!=oldVal){
        this.params.switch1=newVal
        this.onSearch()
      }
    },
    'selectForm.radio':function (newVal,oldVal){

    },
    'showCard':function (newVal,oldVal){
      if(newVal==true){
        console.log(this.showNUll+"GUIDANCE")
        this.getCardHeight()
      }

    },


  },
  mounted() {
    // 是不是DetailCard点进来的，不是就默认，是就接收参数，因为不会缓存...
    this.canMounted=false

    this.showError=false
    this.value=this.$route.query.value || ''
    this.showCard=this.$route.query.showCard || false
    this.showSelect=this.$route.query.showSelect || false
    this.showNUll=false
    if(this.showCard!=false){
      this.onSearch()
    }
    this.showHistory=(this.showSelect || this.showCard)?false:true
    console.log("searchmall挂载")
  },
  created() {
    console.log("searchmall创建")
  },
  destroyed() {
    console.log("searchmall销毁")
  }

}
</script>

<style scoped lang="less">
  //.card{
  //  display: flex;
  //  //flex-wrap: wrap;
  //  //justify-content: space-between;
  //  //flex-direction: column;
  //}
  .nav{
    .van-search{
      width:330px;
      /deep/ .van-cell{
        padding: 2px 5px;
      }
      /deep/ .van-field__right-icon{
          .van-button{
            height: 30px;
            border-radius: 20px;
            .van-button--normal{
              padding: 0 15px;
              font-size: 15px;
            }
          }


      }
    }

  }
  .HistoryAndRecommend{
    display: flex;
    flex-direction: column;
    line-height: 40px;
    font-size: 20px;
    padding: 20px 10px;
    .history{
      display: flex;
      justify-content: space-between;
      flex-wrap: wrap;
      align-items: center;
      .van-button{
        height: 20px;
        color: #4fc08d;

        border: 0;
        font-size: 15px;
        border-bottom: 1px solid #4fc08d;
        padding: 0;
      }
      .histroyCard{
          //width: 300px;
          display: flex;
          flex-direction: row;
          flex-wrap: wrap;
          padding-top: 10px;
          padding-bottom: 20px ;
          margin-bottom: 20px;
          border-bottom: 1px solid rgba(48,48,49,0.1);
          /deep/ .van-tag{
            //width: 80px;
            //height: 30px;
            padding: 5px 12px;
            margin-right: 10px;
            margin-top: 10px;
            font-size: 14px;

          }
      }
    }
    .recommend{
      .histroyCard{
        //background-color: rgb(250, 250, 247);
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        //padding: 10px 10px 20px;
        padding-top:10px ;
        padding-bottom:20px ;
        margin-bottom: 20px;
        border-bottom: 1px solid rgba(48,48,49,0.1);
        /deep/ .van-tag{
          //width: 80px;
          //height: 30px;
          padding: 5px 12px;
          margin-right: 10px;
          margin-top: 10px;
          font-size: 14px;

        }
      }
    }

  }

  .card{
    overflow: auto;
  }
    /deep/ .van-card{
      font-size: 15px;
      background:none;
      .van-card__thumb{
        width: 130px;
        height: 110px;
        margin-right: 20px;
        .van-image{
          width: 100%;
          height: 100%;

          .van-image__img{
            object-fit: cover;
          }
          .van-image__loading{
            background-color: #fff;
          }
          .van-image__error{
            background-color:#4fc08d;
          }
        }
    }



    /deep/ .van-image__error, .van-image__loading{
      background-color:#4fc08d;

    }

  }
  ::-webkit-scrollbar {//隐藏滚动条
    width: 0;
    height: 0;
    background-color: transparent;
  }





</style>