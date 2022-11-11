<template>
  <tr>
    <td>{{ no }}</td>
    <td>{{ isbn }}</td>
    <td>
      <router-link
        :to="{
          name: 'BookDetail',
          params: { isbn: isbn },
        }"
        >{{ title }}</router-link
      >
    </td>
    <td>{{ author }}</td>
    <td>{{ price | filterPrice }}원</td>
  </tr>
</template>

<script>
export default {
  // 부모 component로 부터 전달받은 도서정보
  props: {
    no: String,
    isbn: String,
    title: String,
    author: String,
    price: Number,
  },
};
</script>

<style scope>
td {
  text-align: center;
  border-bottom: 1px solid #ddd;
  height: 50px;
}
</style>
