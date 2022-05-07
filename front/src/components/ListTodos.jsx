import React, { Fragment, useContext, useEffect } from "react";
import TodoForm from "./TodoForm";
import { HOST_API, Store } from "../App";
import List from "./List";

/**
 * Componente ListToDos
 *
 * @author Juan Esteban Velasquez P.
 * @version 1.0.0
 * @since 1.0.0
 */

const ListTodos = () => {
  const {
    dispatch,
    state: { todoList },
  } = useContext(Store);
  const lista = todoList.list;

  useEffect(() => {
    fetch(HOST_API + "/groupLists")
      .then((response) => response.json())
      .then((list) => {
        dispatch({ type: "update-groupList", list });
      });
  }, [dispatch]);

  /**
   *  funcion para eliminar una Listas de ToDos
   * @param  id_groupList
   */

  const onDelete = (id_groupList) => {
    fetch(HOST_API + "/" + id_groupList + "/groupList", {
      method: "DELETE",
    }).then((list) => {
      dispatch({ type: "delete-groupList", id_groupList });
    });

    /**
     * para eliminar la columna creada por la lista de ToDo guardada
     */

    const deleteDiv = document.getElementById("rowList");
    deleteDiv.removeChild(document.getElementById(id_groupList));
  };

  return lista.map((todoList) => {
    return (
      <Fragment key={todoList.id_groupList}>
        <div className="col-md-6 mb-2" id={todoList.id_groupList}>
          <div className="card custom-card">
            <div className="m-2 rounded bkGrey" key={todoList.id_groupList}>
              <div className="m-2">
                <h2>{todoList.name}</h2>
                <button
                  className="btn btn-outline-danger"
                  onClick={() => onDelete(todoList.id_groupList)}
                >
                  Borrar Lista
                </button>
              </div>
              <TodoForm id={todoList} />
              <List id={todoList} />
            </div>
          </div>
        </div>
      </Fragment>
    );
  });
};

export default ListTodos;
