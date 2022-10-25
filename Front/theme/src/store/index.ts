import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";
import accounts from "./accounts";

export default createStore({
    modules: { accounts },
    plugins: [createPersistedState()],
  });

