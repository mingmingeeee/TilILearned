<template>
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
            <td>{{ article.articleNo }}</td>
            <td>
              <router-link
                :to="{
                  name: 'BoardDetail', // router(index.js) 작성한 이름 중에서 BoardDetail로 가겠다는 의미
                  params: { articleno: article.articleNo }, // articleno router로 전달
                }"
                >{{ article.subject }}</router-link
              >
            </td>
            <td>{{ article.userId }}</td>
            <td>{{ article.registerTime }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-else class="text-center">게시글이 없습니다.</div>
  </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  data() {
    return {
      articles: [],
    };
  },
  created() {
    // GET /api/board 요청
    http.get("/board").then((response) => {
      switch (response.status) {
        case 200:
          this.articles = response.data;
          break;
        case 500:
          alert("서버 오류");
          break;
      }
    });
  },
  methods: {
    movePage: function () {
      this.$router.push({ path: "/board/write" });
    },
  },
};
</script>

<style scoped>
.regist {
  padding: 10px;
  text-align: center;
}

.underline {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, cyan 30%);
}

#article-list {
  border-collapse: collapse;
  width: 100%;
}

#article-list thead {
  background-color: #ccc;
  font-weight: bold;
}

#article-list th {
  text-align: center;
  border-bottom: 1px solid #ddd;
  height: 50px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
</style>
