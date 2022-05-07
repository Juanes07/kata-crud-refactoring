import React, { useReducer } from "react";
import { initialState, Store } from "../App";
import reducer from "../reducer/reducer"

/**
 * funcion Store provider
 *
 * @author Juan Esteban Velasquez P.
 * @version 1.0.0
 * @since 1.0.0
 */
const StoreProvider = ({ children }) => {
  const [state, dispatch] = useReducer(reducer, initialState);

  return (
    <Store.Provider value={{ state, dispatch }}>{children}</Store.Provider>
  );
};

export default StoreProvider;