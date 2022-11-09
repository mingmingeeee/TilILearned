<template>
  <div>
    <h1 class="underline">도서 목록</h1>
    <div style="text-align: right">
      <button @click="movePage">도서 등록</button>
    </div>

    <div v-if="books.length > 0">
      <table id="book-list">
        <colgroup>
          <col style="width: 5%" />
          <col style="width: 20%" />
          <col style="width: 40%" />
          <col style="width: 20%" />
          <col style="width: 15%" />
        </colgroup>
        <thead>
          <tr>
            <th>번호</th>
            <th>ISBN</th>
            <th>제목</th>
            <th>저자</th>
            <th>가격</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(book, index) in books" :key="index">
            <td>{{ index + 1 }}</td>
            <td>{{ book.isbn }}</td>
            <td>
              <router-link
                :to="{ name: 'BookDetail', params: { isbn: book.isbn } }"
                >{{ book.title }}</router-link
              >
            </td>
            <td>{{ book.author }}</td>
            <td>{{ book.price | filterPrice }}원</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div v-else class="text-center">게시글이 없습니다.</div>
  </div>
</template>

<script>
export default {
  data() {
    // 도서 목록을 저장할 배열
    return {
      books: [],
    };
  },
  methods: {
    movePage: function () {
      this.$router.push({ name: "BookCreate" });
    },
  },
  created() {
    // localStorage에 저장된 도서 목록 불러오기
    const booklist = localStorage.getItem("booklist");

    let newBook = {
      books: [],
    };

    if (booklist) {
      newBook = JSON.parse(booklist);
    } else {
      localStorage.setItem("booklist", JSON.stringify(newBook));
    }

    this.books = newBook.books;
  },
  filters: {
    filterPrice(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>
