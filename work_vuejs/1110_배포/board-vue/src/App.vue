<template>
  <div id="app">
    <the-header></the-header>
    <router-view></router-view>
    <!-- 바뀔 때마다 보이게 할거양 ^^ -->
  </div>
</template>

<script>
// 불, 등, 보 하나라도 안 하면 에러남 !!!
import TheHeader from "@/components/TheHeader";
// import AppMain from "@/views/AppMain";

export default {
  components: {
    TheHeader,
    // AppMain,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

a {
  text-decoration: none;
}
</style>
