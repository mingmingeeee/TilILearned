<template>
  <div>
    <b-container fluid="xl">
      <!-- <h1 class="underline">SSAFY 글 목록</h1> -->
      <b-row>
        <b-col class="text-center">
          <h1 class="underline">SSAFY 글 목록</h1>
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col class="text-right">
          <b-button variant="primary" @click="movePage">글 등록</b-button>
        </b-col>
      </b-row>

      <!-- 테이블 작성 -->
      <div>
        <b-table
          v-if="articles.length"
          class="text-center"
          striped
          hover
          head-variant="dark"
          :items="articles"
          :fields="fields"
          @row-clicked="rowClickListener">
        </b-table>

        <div v-else class="text-center">게시글이 없습니다.</div>
      </div>

      <!-- <b-row>
      <b-col v-if="articles.length">
        <b-table-simple class="text-center">
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <b-tbody>
            <b-tr v-for="(article, index) in articles" :key="index">
              <b-td>{{ article.articleNo }}</b-td>
              <b-td>
                <router-link
                  :to="{
                    name: 'BoardDetail',
                    params: { articleno: article.articleNo },
                  }"
                  >{{ article.subject }}</router-link
                >
              </b-td>
              <b-td>{{ article.userId }}</b-td>
              <b-td>{{ article.registerTime }}</b-td>
            </b-tr>
          </b-tbody>
        </b-table-simple>
      </b-col>
      <div v-else class="text-center">게시글이 없습니다.</div>
    </b-row> -->
    </b-container>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import moment from "moment";

export default {
  data() {
    return {
      articles: [],
      fields: [
        {
          key: "articleNo",
          lable: "글번호",
          sortable: true,
        },
        {
          key: "subject",
          lable: "제목",
          sortable: true,
        },
        {
          key: "userId",
          lable: "작성자",
          sortable: true,
        },
        {
          key: "registerTime",
          lable: "작성일",
          sortable: true,
        },
      ],
    };
  },
  created() {
    // GET /api/board
    http.get("/board").then((response) => {
      switch (response.status) {
        case 200:
          this.articles = response.data;
          this.articles.map((article) => {
            const obj = moment(article.registerTime, "YYYY-MM-DD hh:mm:ss");
            article.registerTime = obj.format("MM-DD HH:mm");
            return article;
          });
          break;

        case 500:
          alert("서버 오류!!!");
          break;
      }
    });
  },
  methods: {
    rowClickListener(row, index) {
      console.log(index, row);
      this.$router.push({
        name: "BoardDetail",
        params: { articleno: row.articleNo },
      });
    },
    movePage: function () {
      this.$router.push({ path: "/board/write" });
    },
  },
};
</script>

<style scoped></style>
