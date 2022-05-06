import React, { useContext, useRef, useState } from "react";
import { HOST_API, Store } from "../App";

const FormNewList = () => {
  const formRef = useRef(null);
  const {
    dispatch,
    state: { todoList },
  } = useContext(Store);
  const item = todoList.item;
  const [state, setState] = useState(item);

  const onAdd = (event) => {
    event.preventDefault();
    const request = {
      name: state.name,
      id_List: null,
    };
    switch (state.name) {
      case "" && undefined:
        let createAlert = document.getElementById("createAlert");
        createAlert.innerHTML = `<div class="alert alert-warning">
              <h2>Ingresa porfavor un nombre a la lista</h2></div>`;
        break;
      case state.name.length === 0:
        let createAlert2 = document.getElementById("createAlert");
        createAlert2.innerHTML = `<div class="alert alert-warning">
                <h2>Ingresa porfavor un nombre a la lista</h2></div>`;
        break;
      default:
    }
    fetch(HOST_API + "/", {
      method: "POST",
      body: JSON.stringify(request),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((res) => res.json)
      .then((todoList) => {
        dispatch({ type: "add-listTodo", item: todoList });
        setState({ name: "" });
        formRef.current.reset();
      });
  };
  return (
    <div className="row">
      <div className="col-2"></div>
      <div className="col-8">
        <form ref={formRef}>
          <div className="input-group mb-2">
            <input
              type="text"
              name="name"
              placeholder="Ingresa una nueva Lista de ToDo"
              className="form-control"
              onChange={(event) => {
                setState({ ...state, name: event.target.value });
              }}
            ></input>
            <button onClick={onAdd} className="btn btn-primary">Agregar Nueva Lista</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default FormNewList;
