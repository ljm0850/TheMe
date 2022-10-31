import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";
import accounts from "./acounts/accounts";
import articles from "./articles/articles";
import follow_like from "./follow_like/follow_like";
import report from "./report/report";
import themes from "./themes/themes";

export default createStore({
    modules: { accounts,articles,follow_like,report,themes },
    plugins: [createPersistedState()],
  });

