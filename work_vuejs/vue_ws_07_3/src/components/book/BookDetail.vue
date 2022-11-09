<template>
  <div class="container regist">
    <h1 class="underline">SSAFY 도서 정보</h1>
    <div class="regist_form">
      <label for="isbn">도서번호</label>
      <div class="view">{{ book.isbn }}</div>
      <label for="title">도서명</label>
      <div class="view">{{ book.title }}</div>
      <label for="author">저자</label>
      <div class="view">{{ book.author }}</div>
      <label for="price">가격</label>
      <div class="view">{{ book.price | filterPrice }}원</div>
      <label for="content">설명</label><br />
      <div class="view" :inner-html.prop="book.content | filterEnterToBr"></div>
      <div style="padding-top: 15px">
        <router-link
          class="btn"
          :to="{ name: 'BookModify', params: { isbn: book.isbn } }"
          >수정</router-link
        >
        <router-link
          class="btn"
          :to="{ name: 'BookDelete', params: { isbn: book.isbn } }"
          >삭제</router-link
        >
        <router-link class="btn" :to="{ name: 'BookList' }">목록</router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      book: {},
    };
  },
  created() {
    // URL에서 ISBN 번호 얻기
    // const params = new URL(document.location).searchParams;
    // let isbn = params.get("isbn");
    let isbn = this.$route.params.isbn;

    const booklist = JSON.parse(localStorage.getItem("booklist"));
    for (let book of booklist.books) {
      if (isbn == book.isbn) {
        this.book = book;
        break;
      }
    }
  },
};
</script>
