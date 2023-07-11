<template>
  <van-image :src="src">
    <template v-slot:loading>
      <van-loading type="spinner" size="25" />
    </template>
    <template v-slot:error>
      <van-loading type="spinner" size="25" />
    </template>
  </van-image>
</template>
<script>

export default {
  name: "MyImage",
  props:{
    src:{
      type:String,
    }
  },
  data() {
    return {

    }
  },
}

</script>
<style scoped lang="less">
  .van-image {
    width: 100%;
    height: 100%;

    .van-image__img {
      object-fit: contain;
    }
  }
</style>