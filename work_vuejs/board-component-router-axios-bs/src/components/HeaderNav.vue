<template>
  <div>
    <!-- toggleable="lg" 화면에 따라 토글 버튼이 보이게 설정 sm ~ lg -->
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-brand href="#">
        <router-link to="/">
          <img
            src="@/assets/ssafy_logo.png"
            class="d-inline-block align-middle"
            width="90px" />
        </router-link>
      </b-navbar-brand>

      <!-- 햄버거 버튼 -->
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <!-- 메뉴 -->
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#">
            <router-link to="/" class="link">HOME</router-link>
          </b-nav-item>

          <b-nav-item href="#">
            <router-link to="/board/list" class="link">게시판</router-link>
          </b-nav-item>

          <b-nav-item href="#">
            <router-link to="/about" class="link">About</router-link>
          </b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="gear-fill" font-scale="3"></b-icon>
            </template>
            <b-dropdown-item href="#">회원가입</b-dropdown-item>
            <b-dropdown-item href="#">로그인</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.link {
  color: black;
  font-weight: bold;
}

.link:hover {
  color: white;
  text-decoration-line: none;
}
</style>
