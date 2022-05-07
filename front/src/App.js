import React, { createContext, Fragment } from "react";
import StoreProvider from "./storeProvider/StoreProvider";
import FormNewList from "./components/FormNewList";
import ListTodos from "./components/ListTodos";

/**
 * constante  guardar API
 */
export const HOST_API = "http://localhost:8080/api";

/**
 * constante guardar un estado inicial de objetos
 */
export const initialState = {
  todo: { list: [], item: {} },
  todoList: { list: [], item: {} },
};
export const Store = createContext(initialState);

function App() {
  return (
    <Fragment>
      <div className="container">
        <h1 className="text-center">Bienvenido a la Lista de ToDos</h1>
        <StoreProvider>
          <FormNewList />
          <div id="createAlert"></div>
          <div className="row" id="rowList">
            <ListTodos />
          </div>
        </StoreProvider>
      </div>
      </Fragment>
  );
}

export default App;
