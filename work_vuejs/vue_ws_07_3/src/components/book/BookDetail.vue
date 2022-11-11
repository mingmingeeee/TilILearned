<template>
  <div>
    <view-detail :book="book"></view-detail>
  </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  // Dynamic module loading을 통해 component를 불러옴
  components: {
    "view-detail": () => import("@/components/book/include/ViewDetail.vue"),
  },
  data: function () {
    return {
      book: {},
    };
  },
  created() {
    /*
    // URL에서 ISBN 번호 얻기
    // const params = new URL(document.location).searchParams;
    // let isbn = params.get("isbn");
    const isbn = this.$route.params.isbn;

    const booklist = JSON.parse(localStorage.getItem("booklist"));
    for (let book of booklist.books) {
      if (isbn == book.isbn) {
        this.book = book;
        break;
      }
    }
    */

    const isbn = this.$route.params.isbn;
    http.get(`/${isbn}`).then(({ data }) => {
      this.book = data;
    });
  },
};
</script>
