<template>
  <div class="container regist">
    <h1 class="underline">SSAFY 도서 등록</h1>
    <div class="regist_form">
      <label for="isbn">도서번호</label>
      <input
        type="text"
        id="isbn"
        name="isbn"
        v-model="isbn"
        ref="isbn" /><br />
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
        v-model="price"
        ref="price" /><br />
      <label for="content">설명</label><br />
      <textarea
        id="content"
        name="content"
        rows="5"
        v-model="content"
        ref="content"></textarea
      ><br />
      <button @click="validate">등록</button>
      <button>목록</button>
    </div>
  </div>
</template>

<script>
export default {
  data: function () {
    return {
      isbn: "",
      title: "",
      author: "",
      price: "",
      content: "",
    };
  },
  methods: {
    validate() {
      let isValid = true;
      let errMsg = "";

      // 삼항연산자 이용해서 값이 제대로 입력됐는지 확인
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
        this.registBook();
      }
    },
    registBook: function () {
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
      this.$router.push({ name: "BookList" });
    },
  },
  updated() {},
};
</script>
