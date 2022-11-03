import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";
import user from "./user/user";
import feed from "./feed/feed";
import themes from "./themes/themes";

export default createStore({
    modules: { user,feed,themes },
    plugins: [createPersistedState()],
  });

