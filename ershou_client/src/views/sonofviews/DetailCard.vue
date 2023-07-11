<template>

  <div class="detailCard">
    <div class="nav">
      <van-search
          v-model="dataBag.name"
          shape="round"
          placeholder="请输入搜索关键词"
          @focus="onMyfocus"
          show-action
      >
        <template #left>
          <van-icon name="arrow-left" @click="goBack()" style="margin:0 20px 0 20px"/>
        </template>
        <template #action>

        </template>

      </van-search>
    </div>
    <div class="swipe">
      <van-swipe class="my-swipe" indicator-color="#4fc08d" :stop-propagation="false">
        <van-swipe-item v-for="(image, index) in dataBag.media" :key="index" @click="clickImage(image,index)">
          <van-image v-lazy="image" :src="image">
            <template v-slot:loading>
              <van-loading size="25" color="#4fc08d" vertical>
                 努里加载中...
              </van-loading>
            </template>
          </van-image>
        </van-swipe-item>
      </van-swipe>
    </div>
    <div class="detail">
      <p>{{dataBag.name}}</p>
      <p class="priceBold">￥{{ dataBag.price }}</p>
      <p>种类：{{dataBag.type}}</p>
      <p>描述：{{dataBag.description}}</p>
      <p>出货人地址：{{dataBag.address}}</p>
      <p>联系qq/vx：{{dataBag.contactway}}</p>
    </div>
    <div class="footer">
      <van-button icon="plus" type="primary">我要发布</van-button>
      <van-button icon="https://img01.yzcdn.cn/vant/user-active.png" type="info" >
        联系他
      </van-button>
    </div>
  </div>
</template>

<script>

import {GetDetailCard} from "@/api";
import {ImagePreview} from "vant";

export default {
  name: "DetailCard",
  data() {
    return {

      dataBag:{
        // name: '鬼知道',//名字
        // description: '没描述.....单走一个6',//描述
        // type:'种类总得有吧',//种类
        // address:'地址地址',//地址
        // price:Number,//价格
        // contactway:'qq/vx?',//联系qq/vx
        // images: [
        //   'https://img01.yzcdn.cn/vant/apple-1.jpg',
        //   'https://img01.yzcdn.cn/vant/apple-2.jpg',
        //   'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2F49d22d04-3927-4585-9c42-4dc1cb629333%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1685083104&t=5aa280f16025c1df0c1a80cc24a81954',
        // ],
      },


    }
  },
  methods: {
    onMyfocus() {//返回值的搜索结果selectList
      console.log(1)
      this.$router.push({
        path: '/SearchMall',
        query:
            {value: this.name}
      })

    },
    clickImage(image,index){
      ImagePreview(
          {
            images:[image],
            showIndex:false,
            swipeDuration:0,
          }
      )
    },
    goBack(){
      this.$router.push(
          {
            path:'/SearchMall',
            query:{
              value:this.$route.query.fatherLike || '',
              showCard:true,
              showSelect:false,
            }
          }
      )
    }


  },
  mounted() {
    this.name = this.$route.query.name
    this.innerWidth=window.innerWidth+'px'//获取当前用户设备宽度
    GetDetailCard(this.$route.query.id) .then((response)=>{
      console.log(response.data.data)
      this.dataBag=response.data.data
      this.dataBag.media=JSON.parse(response.data.data.media)
    })
                                        .catch((error)=>{
          console.log(error)
        })


  }
}
</script>

<style scoped lang="less">
  p{
    font-size: 15px;
  }
  .detailCard {
    color:#1C1C1E;
    //letter-spacing:0px;
    .nav {
      margin-bottom: 20px;
    }

    .swipe {
      .my-swipe {
        height: 300px;
        /deep/ .van-swipe-item {
          .van-image{
            width: 100%;
            height: 100%;
            .van-image__img{
              object-fit: contain;
            }
            .van-image__loading{
                background-color: #fff;
            }
          }

        }
        /deep/ .van-swipe__indicators{
          .van-swipe__indicator{
            width: 10px;
            height: 10px;
            background-color: #ebedf0;
            border-radius: 100%;
            transition: opacity 0.2s, background-color 0.2s;
          }
        }
      }

    }

    .detail {
      display: flex;
      flex-direction: column;
      letter-spacing: 3px;
      .priceBold{
          color: #8b0000;
          font-weight: 700;
          font-size: 25px;
      }
    }
    .footer{
      position: fixed;
      display: flex;
      justify-content: space-between;
      bottom: 0;
      //height: 50px;
      width: v-bind(innerWidth);
      background-color: pink;
      /deep/ .van-button--normal{
        width: 200px;
      }
    }

  }
</style>