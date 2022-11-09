<template>
  <tr>
    <td>{{ no }}</td>
    <td>{{ isbn }}</td>
    <td>
      <router-link :to="{ name: 'BookDetail', params: { isbn } }">{{
        title
      }}</router-link>
    </td>
    <td>{{ author }}</td>
    <td>{{ price | filterPrice }}원</td>
  </tr>
</template>

<script>
export default {
  props: {
    no: String,
    isbn: String,
    title: String,
    author: String,
    price: Number,
  },
};
</script>

<style></style>
