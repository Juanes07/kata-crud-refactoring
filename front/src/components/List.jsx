import React, { useContext, useEffect } from "react";
import { HOST_API } from "../App";
import { Store } from "../App";

/**
 * Componente List
 *
 * @author Juan Esteban Velasquez P.
 * @version 1.0.0
 * @since 1.0.0
 */

const List = ({ id }) => {
  const {
    dispatch,
    state: { todo },
  } = useContext(Store);
  const currentList = todo.list;

  /**
   *  filtro de listas por medio del id de TodoList
   */

  const show = currentList.filter(
    (event) => event.id_groupList === id.id_groupList
  );

  useEffect(() => {
    fetch(HOST_API + "/todos")
      .then((res) => res.json())
      .then((list) => {
        dispatch({ type: "update-list", list });
      });
  }, [dispatch]);

  /**
   * funcion para eliminar ToDo
   * @param  id_todo del ToDo
   */
  const onDelete = (id_todo) => {
    fetch(HOST_API + "/" + id_todo + "/todo", {
      method: "DELETE",
    }).then((list) => {
      dispatch({ type: "delete-item", id_todo });
    });
  };

  /**
   * funcion para editar ToDo
   * @param  id_todo del ToDo
   */

  const onEdit = (todo) => {
    dispatch({ type: "edit-item", item: todo });
  };

  /**
   * funcion para cambiar el estado completed de ToDo
   * @param event
   * @param todo objeto ToDo
   */
  const onChange = (event, todo) => {
    const request = {
      name: todo.name,
      id_todo: todo.id_todo,
      completed: event.target.checked,
      id_groupList: todo.id_groupList,
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
      });
  };

  /**
   *  funcion para desactivar el boton editar en cuando este listo el ToDo
   */

  const editOff = (param) => (param ? true : false);

  /**
   * funcion para hacer efecto de tachado al ToDo
   */
  const decorationDone = {
    textDecoration: "line-through",
  };

  return (
    <div className="m-2">
      <table className="table table-striped table-hover">
        <thead>
          <tr>
            <td>ID</td>
            <td>Tarea</td>
            <td>¿Completado?</td>
            <td>Acciones</td>
          </tr>
        </thead>
        <tbody>
          {show.map((todo) => {
            return (
              <tr
                key={todo.id_todo}
                style={todo.completed ? decorationDone : {}}
              >
                <th scope="row">{todo.id_todo}</th>
                <td>{todo.name}</td>
                <td>
                  <input
                    className="form-check-input"
                    type="checkbox"
                    defaultChecked={todo.completed}
                    onChange={(event) => onChange(event, todo)}
                  ></input>
                </td>
                <td>
                  <div className="m-1">
                    {" "}
                    <button
                      className="btn btn-danger"
                      id="buttonDelete"
                      onClick={() => onDelete(todo.id_todo)}
                    >
                      Eliminar
                    </button>
                  </div>
                </td>
                <td>
                  <div className="m-1">
                    <button
                      disabled={editOff(todo.completed)}
                      onClick={() => onEdit(todo)}
                      className="btn btn-success"
                      id="buttonEdit"
                    >
                      Editar
                    </button>
                  </div>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

export default List;
