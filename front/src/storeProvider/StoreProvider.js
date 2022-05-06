import React, { useReducer } from "react";
import reducer from "../reducer/reducer"

import { initialState, Store } from "../App";

const StoreProvider = ({ children }) => {
  const [state, dispatch] = useReducer(reducer, initialState);

  return (
    <Store.Provider value={{ state, dispatch }}>{children}</Store.Provider>
  );
};

export default StoreProvider;