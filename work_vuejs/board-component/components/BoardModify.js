export default {
  template: `
    <div class="regist">
        <h1 class="underline">SSAFY 글 수정</h1>
        <div class="regist_form">
            <label for="userid">작성자</label>
            <input type="text" id="userid" name="userid" v-model="userid" ref="userid" /><br />
            <label for="subject">제목</label>
            <input type="text" id="subject" name="subject" v-model="subject" ref="subject" /><br />
            <label for="content">내용</label><br />
            <textarea id="content" name="content" v-model="content" ref="content" cols="35" rows="5"></textarea><br />
            <button @click="checkValue">수정</button>
            <button @click="moveList">목록</button>
        </div>
    </div>
    `,
  data() {
    return {
      articleno: 0,
      userid: "",
      subject: "",
      content: "",
    };
  },
  created: function () {
    const params = new URL(document.location).searchParams;
    const articleno = params.get("articleno");

    const boardlist = JSON.parse(localStorage.getItem("boardlist"));
    for (let article of boardlist.articles) {
      if (articleno == article.articleno) {
        // articleno가 일치하는 게시물의 정보 수정
        this.articleno = article.articleno;
        this.userid = article.userid;
        this.subject = article.subject;
        this.content = article.content;
        break;
      }
    }
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
      const boardlist = JSON.parse(localStorage.getItem("boardlist"));

      // 전통적인 for문 이용하는 예시
      for (let i = 0; i < boardlist.articles.length; i++) {
        if (this.articleno == boardlist.articles[i].articleno) {
          // articleno가 일치하는 게시물의 정보 수정
          boardlist.articles[i] = {
            articleno: this.articleno,
            userid: this.userid,
            subject: this.subject,
            content: this.content,
          };
          break;
        }
      }

      // ES6 향상된 for문 (of)
      // for (let article of boardlist.articles) {
      //   if (this.articleno == article.articleno) {
      //     // articleno가 일치하는 게시물의 정보 수정
      //     article.articleno = this.articleno;
      //     article.userid = this.userid;
      //     article.subject = this.subject;
      //     article.content = this.content;
      //     break;
      //   }
      // }

      // ES6 이전 for-in 반복문
      // for (let index in boardlist.articles) {
      //   if (this.articleno == boardlist.articles[index].articleno) {
      //     articlelist.articles[index] = {
      //       articleno: this.articleno,
      //       userid: this.userid,
      //       subject: this.subject,
      //       content: this.content,
      //     };
      //     break;
      //   }
      // }

      // 수정된 게시물이 들어있는 리스트 로컬 스토리지에 저장
      localStorage.setItem("boardlist", JSON.stringify(boardlist));

      // 수정 성공 메시지 출력
      alert("수정이 완료되었습니다.");

      // 수정 후 목록 페이지로 이동
      location.href = "./list.html";
    },
    moveList() {
      // 수정 후 목록 페이지로 이동
      location.href = "./list.html";
    },
  },
};
