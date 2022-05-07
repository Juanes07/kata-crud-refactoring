import React, { useRef, useContext, useState } from "react";
import { HOST_API } from "../App";
import { Store } from "../App";

const TodoForm = ({ id }) => {
  const formRef = useRef(null);
  const {
    dispatch,
    state: { todo },
  } = useContext(Store);
  const item = todo.item;
  const [state, setState] = useState(item);

  const onAdd = (event) => {
    event.preventDefault();

    const request = {
      name: state.name,
      id_todo: null,
      completed: false,
      id_groupList: id.id_groupList,
    };

    // if (state.name.length > 15) {
    //   let createAlert = document.getElementById("createAlert");
    //   createAlert.innerHTML = `<div class="alert alert-warning" role="alert">
    //   <h2>La tarea no debe sobrepasar los 15 caracteres</h2>
    // </div>`;
    //   setTimeout(() => {
    //     createAlert.innerHTML = "";
    //   }, 3000);
    // }
    fetch(HOST_API + "/todo", {
      method: "POST",
      body: JSON.stringify(request),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((todo) => {
        dispatch({ type: "add-item", item: todo });
        setState({ name: "" });
        formRef.current.reset();
      });
  };

  const onEdit = (event) => {
    event.preventDefault();

    const request = {
      name: state.name,
      id_todo: item.id_todo,
      isCompleted: item.isCompleted,
      id_groupList: id.id_groupList,
    };
    fetch(HOST_API + "/todo", {
      method: "PUT",
      body: JSON.stringify(request),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((todo) => {
        dispatch({ type: "update-item", item: todo });
        setState({ name: "" });
        formRef.current.reset();
      });
  };

  /**
   * funcion para desactivar el boton en caso de estar el Todo Vacio
   * @param {} name
   */
  const wihtOutTodo = (name) =>
    name === undefined || name.lenght === 0 ? true : false;

  return (
    <form ref={formRef}>
      <input
        type="text"
        name="name"
        placeholder="¿Qué piensas hacer hoy?"
        onChange={(event) => {
          setState({ ...state, name: event.target.value });
        }}
        defaultValue={item.name}
      ></input>
      {item.id_todo && (
        <button disabled={wihtOutTodo(state.name)} onClick={onEdit}>
          Actualizar
        </button>
      )}
      {!item.id_todo && (
        <button disabled={wihtOutTodo(state.name)} onClick={onAdd}>
          Crear
        </button>
      )}
    </form>
  );
};

export default TodoForm;
