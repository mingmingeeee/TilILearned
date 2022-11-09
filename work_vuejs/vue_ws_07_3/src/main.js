import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

Vue.config.productionTip = false;

// 전역 필터
Vue.filter("filterPrice", (value) => {
  if (!value) return value;
  return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
});

Vue.filter("filterEnterToBr", (value) => {
  if (!value) return value;
  return value.replace(/(?:\r\n|\r|\n)/g, "<br />");
});

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
