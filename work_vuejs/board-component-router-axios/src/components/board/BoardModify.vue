<template>
  <div class="container regist">
    <h1 class="underline">SSAFY 글 수정</h1>
    <div class="regist_form">
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
      <button @click="checkValue">수정</button>
      <button @click="moveList">목록</button>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  data() {
    return {
      articleno: 0,
      userid: "",
      subject: "",
      content: "",
    };
  },
  created: function () {
    // const params = new URL(document.location).searchParams;
    // const articleno = params.get("articleno");

    const articleno = this.$route.params.articleno;

    http.get(`/board/${articleno}`).then((response) => {
      switch (response.status) {
        case 200:
          this.articleno = response.data.articleNo;
          this.userid = response.data.userId;
          this.subject = response.data.subject;
          this.content = response.data.content;
          break;
        case 300:
          alert("서버 오류!!!");
          break;
      }
    });
  },
  methods: {
    // 입력값 체크하기 - 체크가 성공하면 registArticle 호출
    checkValue() {
      // 사용자 입력값 체크하기
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
      else this.modifyArticle();
    },

    modifyArticle() {
      // 서버로 보낼 JSON 객체 작성
      // 서버에서는 카멜 표기법으로 변수명이 작성되어 있기 때문에
      // 아래와 같이 Key는 카멜 표기법으로 작성
      // articleno은 주소로 요청할 때 보낼거니까 여기에 안 해도 됨
      const article = {
        userId: this.userid,
        subject: this.subject,
        content: this.content,
      };

      http.put(`/board/${this.articleno}`, article).then((response) => {
        switch (response.status) {
          case 200:
            alert("수정이 완료되었습니다.");
            // 수정 후 목록 페이지로 이동
            this.moveList();
            break;

          case 400:
            alert("잘못된 요청을 보냈습니다.");
            break;

          case 500:
            alert("서버 오류!!!");
            break;
        }
      });
    },
    moveList() {
      // 수정 후 목록 페이지로 이동
      this.$router.push({ path: "/board/list" });
    },
  },
};
</script>

<style></style>
