<template>
  <h2>{{ total }}</h2>
</template>

<script>
export default {
  // props: ["total"],
  computed: {
    total() {
      return this.$store.state.count; // 미리 store에 있던 거 들고 와서, 값 바뀌면 재계산
    },
  },
};
</script>

<style></style>
