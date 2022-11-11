<template>
  <div class="header">
    <a href="./index.html">
      <img src="@/assets/ssafy_logo.png" class="ssafy_logo" />
    </a>
    <p class="logo">게시판</p>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.ssafy_logo {
  width: 150px;
}
.header {
  text-align: center;
  box-shadow: 0px 1px 10px rgba(0, 0, 0, 0.3);
}
.header img {
  vertical-align: middle;
}
.logo {
  display: inline-block;
  font-size: 30pt;
  font-weight: bold;
}
</style>
