export default {
  template: `
    <div class="regist">
        <h1 class="underline">SSAFY 글 목록</h1>
        <div style="text-align: right">
            <button @click="movePage">글 등록</button>
        </div>
        <div v-if="articles.length">
            <table id="article-list">
                <colgroup>
                    <col style="width: 5%" />
                    <col style="width: 45%" />
                    <col style="width: 25%" />
                    <col style="width: 25%" />
                </colgroup>
                <thead>
                <tr>
                    <th>글번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="(article, index) in articles" :key="index">
                    <td>{{article.articleno}}</td>
                    <td><a :href="'detail.html?articleno=' + article.articleno">{{article.subject}}</a></td>
                    <td>{{article.userid}}</td>
                    <td>{{article.regtime}}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div v-else class="text-center">게시글이 없습니다.</div>
    </div>
    `,
  data() {
    return {
      articles: [],
    };
  },
  created() {
    // localStorage에 저장된 게시물 목록 불러오기
    const boardlist = localStorage.getItem("boardlist");

    let newBoard = {
      articles: [],
    };

    if (boardlist) {
      newBoard = JSON.parse(boardlist);
    } else {
      localStorage.setItem("boardlist", JSON.stringify(newBoard));
    }

    this.articles = newBoard.articles;
  },
  methods: {
    movePage: function () {
      location.href = "./write.html";
    },
  },
};
