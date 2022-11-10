<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png" />
    <!-- 3. 보여주기 -->
    <apt-list></apt-list>
  </div>
</template>

<script>
import AptList from "@/components/AptList"; // 1. 불러오기

export default {
  name: "App",
  components: { AptList }, // 2. 등록하기
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
