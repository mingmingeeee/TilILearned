export default {
  template: `
    <div class="regist">
        <h1>SSAFY 글 삭제</h1>
        <div>삭제중...</div>
    </div>
    `,
  created() {
    setTimeout(function () {
      const params = new URL(document.location).searchParams;
      const articleno = params.get("articleno");

      const boardlist = JSON.parse(localStorage.getItem("boardlist"));
      boardlist.articles = boardlist.articles.filter((article) => {
        return articleno != article.articleno;
      });

      localStorage.setItem("boardlist", JSON.stringify(boardlist));

      alert("삭제가 완료 되었습니다.");

      location.href = "./list.html";
    }, 3000);
  },
};
