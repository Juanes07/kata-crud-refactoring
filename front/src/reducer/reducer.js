/**
 * Reducer
 *
 * @author Juan Esteban Velasquez P.
 * @version 1.0.0
 * @since 1.0.0
 */

/**
 *  funcion reducer 
 * @param {*} state 
 * @param {*} action 
 * @returns 
 */

function reducer(state, action) {
  switch (action.type) {
    case "update-item":
      const todoUpItem = state.todo;
      const listUpdateEdit = todoUpItem.list.map((item) => {
        if (item.id_todo === action.item.id_todo) {
          return action.item;
        }
        return item;
      });
      todoUpItem.list = listUpdateEdit;
      todoUpItem.item = {};
      return { ...state, todo: todoUpItem };
    case "delete-item":
      const todoUpDelete = state.todo;
      const listUpdate = todoUpDelete.list.filter((item) => {
        return item.id_todo !== action.id_todo;
      });
      todoUpDelete.list = listUpdate;
      return { ...state, todo: todoUpDelete };

    case "update-list":
      const todoUpList = state.todo;
      todoUpList.list = action.list;
      return { ...state, todo: todoUpList };

    case "edit-item":
      const todoUpEdit = state.todo;
      todoUpEdit.item = action.item;
      return { ...state, todo: todoUpEdit };
      
    case "add-item":
      const todoUp = state.todo.list;
      todoUp.push(action.item);
      return { ...state, todo: { list: todoUp, item: {} } };

    case "add-todoList":
      const gruopList = state.todoList.list;
      gruopList.push(action.item);
      return { ...state, todoList: { list: gruopList, item: {} } };
      
    case "update-groupList":
      const listTodos = state.todoList;
      listTodos.list = action.list;
      return { ...state, todoList: listTodos };

    case "delete-groupList":
      const groupListDelete = state.todo;
      const listUpdater = groupListDelete.list.filter((item) => {
        return item.id_groupList !== action.id_groupList;
      });
      groupListDelete.list = listUpdater;
      return { ...state, todo: groupListDelete };

    default:
      return state;
  }
}

export default reducer;

