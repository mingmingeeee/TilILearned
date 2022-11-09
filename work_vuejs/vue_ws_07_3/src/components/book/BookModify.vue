<template>
  <div class="container regist">
    <h1 class="underline">SSAFY 도서 수정</h1>
    <div class="regist_form">
      <label for="isbn">도서번호</label>
      <div class="view">{{ isbn }}</div>
      <label for="title">도서명</label>
      <input type="text" id="title" name="title" v-model="title" /><br />
      <label for="author">저자</label>
      <input type="text" id="author" name="author" v-model="author" /><br />
      <label for="price">가격</label>
      <input
        type="number"
        id="price"
        name="price"
        v-model.number="price" /><br />
      <label for="content">설명</label><br />
      <textarea
        id="content"
        name="content"
        rows="5"
        v-model="content"></textarea
      ><br />
      <button @click="modifyBook">수정</button>
      <button @click="deleteBook">목록</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isbn: "",
      title: "",
      author: "",
      price: 0,
      content: "",
    };
  },
  methods: {
    modifyBook: function () {
      const booklist = JSON.parse(localStorage.getItem("booklist"));

      // 전통적인 for문 이용하는 예시
      for (let i = 0; i < booklist.books.length; i++) {
        if (this.isbn == booklist.books[i].isbn) {
          // isbn 번호가 일치하는 책의 정보 수정
          booklist.books[i] = {
            isbn: this.isbn,
            title: this.title,
            author: this.author,
            price: this.price,
            content: this.content,
          };
          break;
        }
      }

      // ES6 향상된 for문 (of)
      // for (let book of booklist.books) {
      //   if (this.isbn == book.isbn) {
      //     // isbn 번호가 일치하는 책의 정보 수정
      //     book.isbn = this.isbn;
      //     book.title = this.title;
      //     book.author = this.author;
      //     book.price = this.price;
      //     book.content = this.content;
      //     break;
      //   }
      // }

      // ES6 이전 for-in 반복문
      // for (let index in booklist.books) {
      //   if (this.isbn == booklist.books[index].isbn) {
      //     booklist.books[index] = {
      //       isbn: this.isbn,
      //       title: this.title,
      //       author: this.author,
      //       price: this.price,
      //       content: this.content,
      //     };
      //     break;
      //   }
      // }

      console.log(booklist.books);

      // 수정된 도서가 들어있는 리스트 로컬 스토리지에 저장
      localStorage.setItem("booklist", JSON.stringify(booklist));

      // 수정 성공 메시지 출력
      alert("수정이 완료되었습니다.");

      // 수정 후 목록 페이지로 이동
      this.deleteBook();
    },
    deleteBook() {
      this.$router.push({ name: "BookList" });
    },
  },
  created: function () {
    // const params = new URL(document.location).searchParams;
    // const isbn = params.get("isbn");
    const isbn = this.$route.params.isbn;

    const booklist = JSON.parse(localStorage.getItem("booklist"));
    for (let book of booklist.books) {
      if (isbn == book.isbn) {
        this.isbn = book.isbn;
        this.title = book.title;
        this.author = book.author;
        this.price = book.price;
        this.content = book.content;
        break;
      }
    }
  },
};
</script>
