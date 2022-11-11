<template>
  <div>
    <h1>SSAFY 도서 삭제</h1>
    <div>삭제중...</div>
  </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  created: function () {
    /*
    const thiz = this;
    setTimeout(function () {
      // const params = new URL(document.location).searchParams;
      // const isbn = params.get("isbn");
      const isbn = thiz.$route.params.isbn;

      const booklist = JSON.parse(localStorage.getItem("booklist"));
      booklist.books = booklist.books.filter((book) => {
        return isbn != book.isbn;
      });

      localStorage.setItem("booklist", JSON.stringify(booklist));

      alert("삭제가 완료 되었습니다.");

      thiz.$router.push({ name: "BookList" });
    }, 3000);
    */

    if (confirm("정말로 삭제?")) {
      const isbn = this.$route.params.isbn;
      http.delete(`/${isbn}`).then(({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "삭제가 완료되었습니다.";
        }
        alert(msg);
        this.$router.push({ name: "BookList" });
      });
    }
  },
};
</script>
