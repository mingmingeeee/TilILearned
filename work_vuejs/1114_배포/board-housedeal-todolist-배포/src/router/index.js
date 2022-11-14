import Vue from "vue";
import VueRouter from "vue-router";
import AppMain from "@/views/AppMain";
import AppInstagram from "@/views/AppInstagram";
import AppHouse from "@/views/AppHouse";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: AppMain,
  },
  {
    path: "/instagram",
    name: "insta",
    component: AppInstagram,
  },
  {
    path: "/house",
    name: "house",
    component: AppHouse,
  },
  {
    path: "/todo",
    name: "todo",
    component: () => import("@/views/AppTodo"),
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/AppUser"),
    children: [
      {
        path: "join",
        name: "join",
        component: () => import("@/components/user/UserRegister"),
      },
      {
        path: "login",
        name: "login",
        component: () => import("@/components/user/UserLogin"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/AppBoard"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardlist",
        component: () => import("@/components/board/BoardList"),
      },
      {
        path: "write",
        name: "boardwrite",
        component: () => import("@/components/board/BoardWrite"),
      },
      {
        path: "view/:articleno",
        name: "boardview",
        component: () => import("@/components/board/BoardView"),
      },
      {
        path: "modify",
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
