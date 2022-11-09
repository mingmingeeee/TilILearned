<template>
  <div class="container regist">
    <h1 class="underline">SSAFY 글 정보</h1>
    <div class="regist_form">
      <label for="articleno">글번호</label>
      <div class="view">{{ article.articleno }}</div>
      <label for="subject">제목</label>
      <div class="view">{{ article.subject }}</div>
      <label for="userid">작성자</label>
      <div class="view">{{ article.userid }}</div>
      <label for="price">작성일</label>
      <div class="view">{{ article.regtime }}</div>
      <label for="content">내용</label>
      <div class="view" v-html="article.content"></div>
      <div style="padding-top: 15px">
        <!-- router-link: to={name: router에 명시한 이름}-->
        <router-link
          :to="{
            name: 'BoardModify',
            params: { articleno: article.articleno },
          }"
          class="btn"
          >수정</router-link
        >
        <router-link
          :to="{
            name: 'BoardDelete',
            params: { articleno: article.articleno },
          }"
          class="btn"
          >삭제</router-link
        >
        <!-- /board/list: 리터럴 자체이기 때문에 ":" 안 붙여줘도 됨 (내부에 있는 변수가 아니니까!!!) -->
        <router-link to="/board/list" class="btn">목록</router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: function () {
    return {
      article: {},
    };
  },
  created() {
    // URL에서 글 번호 얻기
    // const params = new URL(document.location).searchParams;
    // let articleno = params.get("articleno");

    const articleno = this.$route.params.articleno; // route 객체에서 param 얻어올 수 있음

    const boardlist = JSON.parse(localStorage.getItem("boardlist"));
    for (let article of boardlist.articles) {
      if (articleno == article.articleno) {
        this.article = article;
        break;
      }
    }
  },
};
</script>

<style scoped></style>
