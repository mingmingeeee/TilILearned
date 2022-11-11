<template>
  <div class="regist">
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
        <!-- 서버로부터 isbn 번호 받기 전에는 0으로 설정 -->
        <router-link
          :to="{
            name: 'BookModify',
            params: { isbn: book.isbn ? book.isbn : 0 },
          }"
          class="btn"
          >수정</router-link
        >
        <router-link
          :to="{
            name: 'BookDelete',
            params: { isbn: book.isbn ? book.isbn : 0 },
          }"
          class="btn"
          >삭제</router-link
        >
        <router-link :to="{ name: 'BookList' }" class="btn">목록</router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  // 부모 component로 부터 전달받은 도서정보
  props: {
    book: Object,
  },
};
</script>

<style scoped></style>
