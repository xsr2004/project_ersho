<template>
    <div :style="{height:windowHeight+'px'},{width:windowWidth+'px'}">
      <keep-alive>
        <router-view></router-view>
      </keep-alive>
      <common-tag></common-tag>
    </div>
</template>

<script>
import CommonTag from "@/components/CommonTag";

export default {
  name: "MainView",
  data(){
    return{
      windowHeight:document.documentElement.clientHeight,
      windowWidth:document.documentElement.clientWidth,//获取用户设备高度
    }
  },
  components:{
      CommonTag
  },
  mounted() {
    // document.body.style.overflow = 'auto';
  }
}
</script>

<style scoped>

</style>