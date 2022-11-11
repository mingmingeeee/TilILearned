<template>
  <div>
    <h1>SSAFY 글 삭제</h1>
    <div>삭제중...</div>
  </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  created() {
    // const params = new URL(document.location).searchParams;
    // const articleno = params.get("articleno");
    const articleno = this.$route.params.articleno;

    http.delete(`/board/${articleno}`).then((response) => {
      switch (response.status) {
        case 200:
          alert("삭제가 완료되었습니다.");
          this.$router.push({ path: "/board/list" });
          break;

        case 400:
          alert("삭제 요청이 잘못되었습니다.");
          break;

        case 500:
          alert("서버 오류!!!");
          break;
      }
    });
  },
};
</script>

<style scoped></style>
