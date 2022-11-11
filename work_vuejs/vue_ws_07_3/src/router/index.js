import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

// Dynamic module loading을 통해 component를 불러옴
const routes = [
  {
    path: "/",
    name: "HomeView",
    component: () => import("@/views/HomeView.vue"),
  },
  {
    path: "/book",
    name: "BookView",
    component: () => import("@/views/BookView.vue"),
    redirect: "/book/list",
    children: [
      {
        path: "list",
        name: "BookList",
        component: () => import("@/components/book/BookList.vue"),
      },
      {
        path: "write",
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
