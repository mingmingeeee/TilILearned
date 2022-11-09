<template>
  <div>
    <h1>SSAFY 도서 삭제</h1>
    <div>삭제중...</div>
  </div>
</template>

<script>
export default {
  created: function () {
    // const params = new URL(document.location).searchParams;
    // const isbn = params.get("isbn");
    const isbn = this.$route.params.isbn;

    const booklist = JSON.parse(localStorage.getItem("booklist"));
    booklist.books = booklist.books.filter((book) => {
      return isbn != book.isbn;
    });

    localStorage.setItem("booklist", JSON.stringify(booklist));

    alert("삭제가 완료 되었습니다.");

    this.$router.push({ name: "BookList" });
  },
};
</script>
