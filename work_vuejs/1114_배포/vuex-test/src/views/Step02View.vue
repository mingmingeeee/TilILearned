<template>
  <div class="text-center">
    <h2>당신이 좋아하는 파트를 선택하세요</h2>
    <result-component />
    <subject-component title="코딩"></subject-component>
    <subject-component title="알고리즘"></subject-component>
  </div>
</template>

<script>
import ResultComponent from "@/components/step02/ResultComponent";
import SubjectComponent from "@/components/step02/SubjectComponent";

export default {
  components: {
    ResultComponent,
    SubjectComponent,
  },
};
</script>
<style>
.text-center {
  text-align: center;
}
</style>
