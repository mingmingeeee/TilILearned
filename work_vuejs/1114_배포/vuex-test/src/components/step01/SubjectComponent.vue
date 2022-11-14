<template>
  <button @click="addCount">{{ title }} - {{ count }}</button>
</template>

<script>
export default {
  name: "SubjectComponent",
  props: ["title"],
  data() {
    return {
      count: 0,
    };
  },
  methods: {
    addCount: function () {
      this.count += 1;
      // this.$emit("addtotcount");
      this.$store.state.count++; // store의 data에 접근!
    },
  },
};
</script>

<style></style>
