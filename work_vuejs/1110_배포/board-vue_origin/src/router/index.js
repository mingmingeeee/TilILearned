import Vue from "vue";
import VueRouter from "vue-router";
import AppMain from "@/views/AppMain.vue";
import AppUser from "@/views/AppUser.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: AppMain,
  },
  {
    path: "/user",
    name: "main",
    component: AppUser,
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/AppBoard.vue"),
    redirect: "/board/list",
    children: [
      {
        path: "list", // list pgno 등은 쿼리로 넘김 파람이 아니라!
        name: "boardlist",
        component: () => import("@/components/board/BoardList"),
      },
      {
        path: "write",
        name: "boardwrite",
        component: () => import("@/components/board/BoardWrite"),
      },
      {
        path: "view/:articleno", // 파람으로~
        name: "boardview",
        component: () => import("@/components/board/BoardView"),
      },
      {
        path: "modify/:articleno",
        name: "boardmodify",
        component: () => import("@/components/board/BoardModify"),
      },
      {
        path: "delete/:articleno",
        name: "boarddelete",
        component: () => import("@/components/board/BoardDelete"),
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
