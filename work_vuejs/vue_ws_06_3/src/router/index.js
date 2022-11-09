import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "HomeView",
    component: () => import("@/views/HomeView.vue"),
  },
  {
    path: "/book",
    name: "BookView",
    redirect: "/book/list", // "/book"을 요청하더라도 바로 "/book/list"로 이동 !
    component: () => import("@/views/BookView.vue"),
    children: [
      {
        path: "list",
        name: "BookList",
        component: () => import("@/components/book/BookList.vue"),
      },
      {
        path: "create",
        name: "BookCreate",
        component: () => import("@/components/book/BookCreate.vue"),
      },
      {
        path: "detail/:isbn",
        name: "BookDetail",
        component: () => import("@/components/book/BookDetail.vue"),
      },
      {
        path: "modify/:isbn",
        name: "BookModify",
        component: () => import("@/components/book/BookModify.vue"),
      },
      {
        path: "delete/:isbn",
        name: "BookDelete",
        component: () => import("@/components/book/BookDelete.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
