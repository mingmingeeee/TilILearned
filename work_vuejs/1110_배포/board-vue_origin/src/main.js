import Vue from "vue";
import App from "./App.vue";
import router from "./router"; // 라우터 불러왔으니 (vue create할 때 불러왔음)
import store from "./store";

Vue.config.productionTip = false;

new Vue({
  router, // 라우터 등록시켜라~
  store,
  render: (h) => h(App),
}).$mount("#app");
