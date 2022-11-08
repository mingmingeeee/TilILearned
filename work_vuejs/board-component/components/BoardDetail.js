export default {
  template: `
    <div class="regist">
        <h1 class="underline">SSAFY 글 정보</h1>
        <div class="regist_form">
          <label for="articleno">글번호</label>
          <div class="view">{{article.articleno}}</div>
          <label for="subject">제목</label>
          <div class="view">{{article.subject}}</div>
          <label for="userid">작성자</label>
          <div class="view">{{article.userid}}</div>
          <label for="price">작성일</label>
          <div class="view">{{article.regtime}}</div>
          <label for="content">내용</label>
          <div class="view" v-html="article.content"></div>
          <div style="padding-top: 15px">
          <a :href="'./modify.html?articleno=' + article.articleno" class="btn">수정</a>
          <a :href="'./delete.html?articleno=' + article.articleno" class="btn">삭제</a>
          <a href="./list.html" class="btn">목록</a>
          </div>
        </div>
    </div>
    `,
  data: function () {
    return {
      article: {},
    };
  },
  created() {
    // URL에서 ISBN 번호 얻기
    const params = new URL(document.location).searchParams;
    let articleno = params.get("articleno");

    const boardlist = JSON.parse(localStorage.getItem("boardlist"));
    for (let article of boardlist.articles) {
      if (articleno == article.articleno) {
        this.article = article;
        break;
      }
    }
  },
};
