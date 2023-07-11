<template>
    <div class="SelectListOne" @click="handleClick">
      <van-icon name="search" size="20px"/>
      <span style="margin-left: 20px">{{MySelectBeforeName}}</span>
      <span style="color: orange;">{{MySelectKey}}</span>
      <span>{{MySelectLastName}}</span>
    </div>
</template>

<script>

export default {
  name: "SelectListOne",
  props:["selectName","selectKey"],
  data(){
    return{
      MySelectBeforeName:'',//匹配key前面的字符串
      MySelectKey:this.selectKey,//key
      MySelectLastName:this.selectName,//key后面的字符串
    }

  },
  methods:{
    handleClick() {
      this.$emit("click");
    },
    getThreeSliceSrting(){
      console.log("获取的item的name="+this.selectName)
      console.log("当前的key="+this.selectKey)
      let start = this.selectName.indexOf(this.selectKey); // 找到匹配子字符串的起始位置
      if(start!=-1){
        let end = start + this.selectKey.length; // 计算匹配子字符串的结束位置
        let before = this.selectName.substring(0, start); // 截取匹配子字符串之前的部分
        let matched = this.selectName.substring(start, end); // 截取匹配子字符串
        let after = this.selectName.substring(end); // 截取匹配子字符串之后的部分
        this.MySelectBeforeName=before
        this.MySelectKey=matched
        this.MySelectLastName=after
        // console.log(this.MySelectBeforeName)
        // console.log( this.MySelectKey)
        // console.log(this.MySelectLastName)
      }
      else{
        this.MySelectKey=this.selectName.substring(0,this.selectKey.length)
        this.MySelectLastName=this.selectName.substring(this.selectKey.length)
      }

    },
  },
  watch:{
    'selectKey':function (newVal,oldVal){
      setTimeout(()=>{
        this.getThreeSliceSrting()
      },750)
    }

  },
  created() {
    this.getThreeSliceSrting()
  },
}
</script>

<style scoped lang="less">
    span{
      font-size: 17px;

    }
  .SelectListOne{
    border-bottom: 1px solid #f5f5f5;
    width: 390px;
    display: flex;
    justify-content:flex-start;
    font-size: 17px;
    align-items: center;
    padding:15px 0px 10px 15px;

  }
</style>