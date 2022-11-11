<template>
  <div class="regist">
    <h1 class="underline">{{ writeFormTitle }}</h1>
    <div class="regist_form">
      <label for="isbn">도서번호</label>
      <template v-if="type == 'create'">
        <input
          type="text"
          id="isbn"
          name="isbn"
          v-model="isbn"
          ref="isbn" /><br />
      </template>
      <div v-else class="view">{{ isbn }}</div>
      <label for="title">도서명</label>
      <input
        type="text"
        id="title"
        name="title"
        v-model="title"
        ref="title" /><br />
      <label for="author">저자</label>
      <input
        type="text"
        id="author"
        name="author"
        v-model="author"
        ref="author" /><br />
      <label for="price">가격</label>
      <input
        type="number"
        id="price"
        name="price"
        v-model.number="price"
        ref="price" /><br />
      <label for="content">설명</label><br />
      <textarea
        id="content"
        name="content"
        rows="5"
        v-model="content"
        ref="content"></textarea
      ><br />
      <button @click="validate">
        <span v-if="type == 'create'">등록</span>
        <span v-else>수정</span>
      </button>
      <button @click="moveList">목록</button>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  props: {
    // 부모 component로 부터 전달받은 type 정보를 받아옴
    type: { type: String },
  },
  data: function () {
    return {
      isbn: "",
      title: "",
      author: "",
      price: 0,
      content: "",
    };
  },
  computed: {
    writeFormTitle: function () {
      return this.type == "create" ? "SSAFY 도서 등록" : "SSAFY 도서 수정";
    },
  },
  methods: {
    validate() {
      let isValid = true;
      let errMsg = "";

      !this.isbn
        ? ((isValid = false),
          (errMsg = "isbn을 입력해주세요."),
          this.$refs.isbn.focus())
        : !this.title
        ? ((isValid = false),
          (errMsg = "도서명을 입력해주세요."),
          this.$refs.title.focus())
        : !this.author
        ? ((isValid = false),
          (errMsg = "저자를 입력해주세요."),
          this.$refs.author.focus())
        : !this.price
        ? ((isValid = false),
          (errMsg = "가격을 입력해주세요."),
          this.$refs.price.focus())
        : !this.content
        ? ((isValid = false),
          (errMsg = "설명을 입력해주세요."),
          this.$refs.content.focus())
        : (isValid = true);

      if (!isValid) {
        alert(errMsg);
      } else {
        if (this.type == "create") {
          this.registBook();
        } else {
          this.modifyBook();
        }
      }
    },
    registBook: function () {
      /*
      // 데이터 선언
      let newBook = {
        books: [],
      };

      // 로컬 스토리지에 저장된 데이터 가져오기
      const booklist = localStorage.getItem("booklist");

      // 기존 로컬 스토리지에 저장된 내용이 있다면 newBook 객체를 변경
      if (booklist) {
        newBook = JSON.parse(booklist);
      }

      // 입력한 도서 내용을 가지고 객체 생성
      let book = {
        isbn: this.isbn,
        title: this.title,
        author: this.author,
        price: this.price,
        content: this.content,
      };

      // 배열에 저장
      newBook.books.push(book);

      // 로컬 스토리지에 newBook 객체 저장
      localStorage.setItem("booklist", JSON.stringify(newBook));

      // 등록 성공 메시지 출력
      alert("등록이 완료되었습니다.");

      // 목록 페이지로 이동하기
      this.moveList();
      */
      http
        .post("", {
          isbn: this.isbn,
          title: this.title,
          author: this.author,
          price: this.price,
          content: this.content,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    modifyBook: function () {
      /*
      const booklist = JSON.parse(localStorage.getItem("booklist"));

      // ES6 향상된 for문 (of)
      for (let book of booklist.books) {
        if (this.isbn == book.isbn) {
          // isbn 번호가 일치하는 책의 정보 수정
          book.isbn = this.isbn;
          book.title = this.title;
          book.author = this.author;
          book.price = this.price;
          book.content = this.content;
          break;
        }
      }

      console.log(booklist.books);

      // 수정된 도서가 들어있는 리스트 로컬 스토리지에 저장
      localStorage.setItem("booklist", JSON.stringify(booklist));

      // 수정 성공 메시지 출력
      alert("수정이 완료되었습니다.");

      // 수정 후 목록 페이지로 이동
      this.moveList();
      */

      http
        .put(`/${this.isbn}`, {
          isbn: this.isbn,
          title: this.title,
          author: this.author,
          price: this.price,
          content: this.content,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    moveList() {
      this.$router.push({ name: "BookList" });
    },
  },
  created: function () {
    if (this.type == "modify") {
      /*
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
      */

      http.get(`/${this.$route.params.isbn}`).then(({ data }) => {
        this.isbn = data.isbn;
        this.title = data.title;
        this.author = data.author;
        this.price = data.price;
        this.content = data.content;
      });
    }
  },
};
</script>

<style scoped></style>
