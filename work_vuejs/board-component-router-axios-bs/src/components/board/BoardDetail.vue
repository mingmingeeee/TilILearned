<template>
  <div>
    <h1 class="underline">SSAFY 글 정보</h1>
    <div>
      <label for="articleno">글번호</label>
      <div>{{ article.articleNo }}</div>
      <label for="subject">제목</label>
      <div>{{ article.subject }}</div>
      <label for="userid">작성자</label>
      <div>{{ article.userId }}</div>
      <label for="price">작성일</label>
      <div>{{ article.registerTime }}</div>
      <label for="content">내용</label>
      <div v-html="article.content"></div>
      <div style="padding-top: 15px">
        <router-link
          :to="{
            name: 'BoardModify',
            params: { articleno: article.articleNo ? article.articleNo : 0 },
          }"
          >수정</router-link
        >
        <router-link
          :to="{
            name: 'BoardDelete',
            params: { articleno: article.articleNo ? article.articleNo : 0 },
          }"
          >삭제</router-link
        >
        <router-link to="/board/list">목록</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";

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

    // GET /api/board/[글번호]
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
