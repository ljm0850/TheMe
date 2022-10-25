import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";
import accounts from "./accounts";
import articles from "./articles";
import follow_like from "./follow_like";
import report from "./report";
import themes from "./themes";

export default createStore({
    modules: { accounts,articles,follow_like,report,themes },
    plugins: [createPersistedState()],
  });

