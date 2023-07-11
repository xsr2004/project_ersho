<template>
  <div class="MyHome-Container">
    <div class="bgc-Container">

    </div>
    <van-image
        round
        width="100"
        height="100"
        src="https://img01.yzcdn.cn/vant/cat.jpeg"
    />
    <span class="UserName">15191273200</span>
    <van-grid>
      <van-grid-item icon="bag-o" text="我买的" to="/MyBuy"/>
      <van-grid-item icon="after-sale" text="我卖的" to="/MySale"/>
      <van-grid-item icon="location-o" text="管理地址" to="/MyAddress"/>
      <van-grid-item icon="setting-o" text="设置" to="/MyConfiguration"/>
    </van-grid>
<!--    //分享-->
    <van-cell title="显示分享面板" @click="showShare = true" is-link icon="share-o"/>
    <van-share-sheet
        v-model="showShare"
        title="立即分享给好友"
        :options="options"
        @select="onSelect()"
    />

    <van-cell title="回收" @click="OnCellClick()" is-link icon="send-gift-o" style="top: 450px"/>
    <van-cell title="关于我们" @click="OnCellClick()" is-link icon="smile-o" style="top: 500px"/>
  </div>
</template>

<script>
import {Toast} from "vant";

export default {
  name: "MyHome",
  components:{

  },
  data(){
    return{
      showShare: false,
      options: [
        { name: '微信', icon: 'wechat' },
        { name: '微博', icon: 'weibo' },
        { name: '复制链接', icon: 'link' },
        { name: '分享海报', icon: 'poster' },
        { name: '二维码', icon: 'qrcode' },
      ],
    }
  },
  methods:{
    onSelect(option) {
      Toast(option.name);
      this.showShare = false;
    },

  }
}
</script>

<style scoped lang="less">
    .MyHome-Container{

      /deep/ .van-image{
        position: absolute;
        top: 30px;
        left: 10px;
      }
      /deep/ .van-image--round img{

      }
      .bgc-Container{
        width: 375px;
        height: 123px;
        background-color: #4fc08d;
      }
      .UserName{
        display: inline-block;
        position: absolute;
        top: 60px;
        left: 110px;
      }
      /deep/ .van-grid-item__icon{
        font-size: 40px;
      }
      /deep/ .van-grid-item__content{
        height: 110%;
      }
      /deep/ .van-grid-item__text{
        font-size: 18px;
      }
      /deep/ .van-cell{
        position: absolute;
        top: 300px;
        font-size: 20px;
        background-color: rgb(250, 250, 247);
        //box-shadow: 3px 2px;
      }
      /deep/ .van-cell__right-icon{
        font-size: 20px;
      }
      /deep/ .van-cell__left-icon{
        font-size: 20px;
      }

    }
</style>