<template>
  <div class="container regist">
    <h1>SSAFY 글 삭제</h1>
    <div>삭제중...</div>
  </div>
</template>

<script>
export default {
  created() {
    const thiz = this;
    setTimeout(function () {
      // const params = new URL(document.location).searchParams;
      // const articleno = params.get("articleno");

      const articleno = thiz.$route.params.articleno; // 여기 this는 view가 아닌 window임
      // setTimeout때문엥..

      const boardlist = JSON.parse(localStorage.getItem("boardlist"));
      boardlist.articles = boardlist.articles.filter((article) => {
        return articleno != article.articleno;
      });

      localStorage.setItem("boardlist", JSON.stringify(boardlist));

      alert("삭제가 완료 되었습니다.");

      // location.href = "./list.html";

      thiz.$router.push({ path: "/board/list" });
    }, 3000);
  },
};
</script>

<style scoped></style>
