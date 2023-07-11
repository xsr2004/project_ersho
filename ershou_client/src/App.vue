<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: 'App',
  components: {
    
  },
  mounted() {
    document.body.style.overflow = 'hidden';
  }
}
</script>

<style>
  body, html {
    height: 100%;
    width: 100%;
  }
  body {
    margin: 0;
    padding: 0;
  }
</style>
