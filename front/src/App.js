import React, {
  createContext,
} from "react";

import TodoForm from "./components/TodoForm";
import List from "./components/List";
import StoreProvider from "./storeProvider/StoreProvider"
import FormNewList from "./components/FormNewList";

export const HOST_API = "http://localhost:8080/api";
export const initialState = {
  todo: { list: [], item: {} },
  todoList: {list: [], item: {}}
};
export const Store = createContext(initialState);

function App() {
  return (
    <StoreProvider>
      <FormNewList/>
      <div id="createAlert"></div>
      <h3>To-Do List</h3>
      <TodoForm />
      <List />
    </StoreProvider>
  );
}

export default App;
