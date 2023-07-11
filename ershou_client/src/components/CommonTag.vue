<template>
  <div class="tag">
      <van-tabbar v-model="active"
                  @change="onChange"
                  active-color="#4fc08d"
                  inactive-color="#000"
                  :style="{ width: windowWidth + 'px' }"
      >
        <van-tabbar-item name="Recommend" icon="goods-collect-o">主页</van-tabbar-item>
        <van-tabbar-item name="SaleGood" icon="add-o">发表</van-tabbar-item>
        <van-tabbar-item name="MyHome" icon="manager-o">我的</van-tabbar-item>
      </van-tabbar>
  </div>
</template>

<script>
export default {
  name: "CommonTag",
  computed:{
    active:{
      get(){
        return this.$route.path.replace('/','')
      },
      set(){

      }
    }
  },
  data(){
    return {
      windowWidth:document.documentElement.clientWidth,//获取用户设备高度

    }
  },
  methods:{
    onChange(name){
      this.$router.push(`/${name}`)
    }
  },
  mounted() {

  }
}
</script>

<style scoped lang="less">
    .tag{
      overflow: hidden;
      .van-tabbar{
        position: fixed;
        height: 50px;
        bottom: 0;
      }

    }
    /deep/ .van-icon{
      font-size: 30px;
    }
    /deep/ .van-tabbar-item__text{
      font-size: 17px;
    }
</style>