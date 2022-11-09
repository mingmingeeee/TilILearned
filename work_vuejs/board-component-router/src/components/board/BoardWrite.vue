<template>
  <div class="regist">
    <h1 class="underline">SSAFY 글 등록</h1>
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
      <button @click="checkValue">등록</button>
      <button @click="moveList">목록</button>
    </div>
  </div>
</template>

<script>
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
      // 데이터 선언
      let newBoard = {
        articles: [],
      };

      // 로컬 스토리지에 저장된 데이터 가져오기
      const boardlist = localStorage.getItem("boardlist");

      // 기존 로컬 스토리지에 저장된 내용이 있다면 newBoard 객체를 변경
      if (boardlist) {
        newBoard = JSON.parse(boardlist);
      }

      // 입력한 게시물 내용을 가지고 객체 생성
      let article = {
        articleno:
          newBoard && newBoard.articles && newBoard.articles.length > 0
            ? newBoard.articles[newBoard.articles.length - 1].articleno + 1
            : 1,
        userid: this.userid,
        subject: this.subject,
        content: this.content,
      };

      // 배열에 저장
      newBoard.articles.push(article);

      // 로컬 스토리지에 newArticle 객체 저장
      localStorage.setItem("boardlist", JSON.stringify(newBoard));

      // 등록 성공 메시지 출력
      alert("등록이 완료되었습니다.");

      // 목록 페이지로 이동하기
      this.moveList();
    },
    moveList() {
      this.$router.push({ name: "BoardList" });
    },
  },
};
</script>

<style scoped>
input,
textarea,
.view {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  color: #787878;
  font-size: medium;
}

label {
  display: inline-block;
  width: 80px;
}

button,
.btn {
  width: 8%;
  background-color: #d0d3d0;
  color: rgb(80, 82, 79);
  padding: 14px 20px;
  margin: 8px 0;
  border: 1px solid #787878;
  border-radius: 4px;
  font-size: large;
  cursor: pointer;
}

.regist {
  padding: 10px;
  text-align: center;
}

.regist_form {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
  text-align: justify;
}

.underline {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, cyan 30%);
}
</style>
