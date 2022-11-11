<template>
  <div>
    <h1 class="underline">SSAFY 글 등록</h1>
    <div>
      <label for="userid">작성자</label>
      <input
        type="text"
        id="userid"
        name="userid"
        v-model="userid"
        ref="userid" /><br />
      <label for="subject">제목</label>
      <input
        type="text"
        id="subject"
        name="subject"
        v-model="subject"
        ref="subject" /><br />
      <label for="content">내용</label><br />
      <textarea
        id="content"
        name="content"
        v-model="content"
        ref="content"
        cols="35"
        rows="5"></textarea
      ><br />
      <button @click="checkValue">등록</button>
      <button @click="moveList">목록</button>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  data() {
    return {
      userid: "",
      subject: "",
      content: "",
    };
  },
  methods: {
    // 입력값 체크하기 - 체크가 성공하면 registArticle 호출
    checkValue() {
      // 사용자 입력값 체크하기
      // isbn, 제목, 저자, 가격, 설명이 없을 경우 각 항목에 맞는 메세지를 출력
      let err = true;
      let msg = "";
      !this.userid &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      // 만약, 내용이 다 입력되어 있다면 registArticle 호출
      else this.writeArticle();
    },

    writeArticle() {
      // 서버로 보낼 객체 작성
      const article = {
        userId: this.userid,
        subject: this.subject,
        content: this.content,
      };

      http.post("/board", article).then((response) => {
        switch (response.status) {
          case 200:
            alert("등록이 완료되었습니다.");
            this.moveList();
            break;

          case 400:
            alert("잘못된 요청입니다.");
            break;

          case 500:
            alert("서버 오류!!!");
            break;
        }
      });
    },
    moveList() {
      this.$router.push({ path: "/board/list" });
    },
  },
};
</script>

<style scoped></style>
