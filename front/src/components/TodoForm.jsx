import React, { useRef, useContext, useState } from "react";
import { HOST_API } from "../App";
import { Store } from "../App";

/**
 * Componente ToDoForm
 *
 * @author Juan Esteban Velasquez P.
 * @version 1.0.0
 * @since 1.0.0
 */

const TodoForm = ({ id }) => {
  const formRef = useRef(null);
  const {
    dispatch,
    state: { todo },
  } = useContext(Store);
  const item = todo.item;
  const [state, setState] = useState(item);

  /**
   * funcion para Agregar el ToDo
   * @param event
   */

  const onAdd = (event) => {
    /**
     * se previene una recarga al presionar el boton
     */
    event.preventDefault();

    const request = {
      name: state.name,
      id_todo: null,
      completed: false,
      id_groupList: id.id_groupList,
    };

    /**
     * valida que el ToDo tenga como minimo 4 caracteres
     */
    
    if (state.name.length > 3) {
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
    }
  };

  /**
   * funcion para Actualizar el ToDo
   * @param event
   */

  const onUpdate = (event) => {
    /**
     * se previene una recarga al presionar el boton
     */
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
        <button
          className="btn btn-success ms-3"
          disabled={wihtOutTodo(state.name)}
          onClick={onUpdate}
        >
          Actualizar
        </button>
      )}
      {!item.id_todo && (
        <button
          className="btn btn-success ms-3"
          disabled={wihtOutTodo(state.name)}
          onClick={onAdd}
        >
          Crear
        </button>
      )}
    </form>
  );
};

export default TodoForm;