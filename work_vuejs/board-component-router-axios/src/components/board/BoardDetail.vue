<template>
  <div class="container regist">
    <h1 class="underline">SSAFY 글 정보</h1>
    <div class="regist_form">
      <label for="articleno">글번호</label>
      <div class="view">{{ article.articleNo }}</div>
      <label for="subject">제목</label>
      <div class="view">{{ article.subject }}</div>
      <label for="userid">작성자</label>
      <div class="view">{{ article.userId }}</div>
      <label for="price">작성일</label>
      <div class="view">{{ article.registerTime }}</div>
      <label for="content">내용</label>
      <div class="view" v-html="article.content"></div>
      <div style="padding-top: 15px">
        <!-- router-link: to={name: router에 명시한 이름}-->
        <router-link
          :to="{
            // 비동기로 값 받아오기 전에 페이지부터 뜨니까~ 처음 페이지 들어갔을 때는 경고 뜸 = life-cycle: created일 때
            name: 'BoardModify',
            params: { articleno: article.articleNo ? article.articleNo : 0 },
          }"
          class="btn"
          >수정</router-link
        >
        <router-link
          :to="{
            name: 'BoardDelete',
            params: { articleno: article.articleNo ? article.articleNo : 0 },
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
import http from "@/util/http-common";

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

    const articleno = this.$route.params.articleno;

    http.get(`/board/${articleno}`).then((response) => {
      switch (response.status) {
        case 200:
          this.article = response.data;
          break;
        case 500:
          alert("서버 오류 !!!");
          break;
      }
    });
  },
};
</script>

<style scoped></style>
