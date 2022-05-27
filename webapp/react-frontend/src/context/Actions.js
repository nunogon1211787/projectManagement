  import { makeHTTPRequest } from '../services/Service';



export const FETCH_USERS_STARTED = 'FETCH_USERS_STARTED';
export const FETCH_USERS_SUCCESS = 'FETCH_USERS_SUCCESS';
export const FETCH_USERS_FAILURE = 'FETCH_USERS_FAILURE';

export const ADD_USER_SUCCESS = 'ADD_USER_SUCCESS';
export const ADD_USER_FAILURE = 'FAILURE';

export function fetchUsers(url, request, dispatch) {
  //função ser executado em caso de sucesso
  const success = (res) => dispatch(fetchUsersSuccess(res));
  //função ser executado em caso de falha
  const failure = (err) => dispatch(fetchUsersFailure(err.message));
  makeHTTPRequest(url, request, success, failure);
}
export function fetchUserTodos(url, request, dispatch) {
  //função ser executado em caso de sucesso
  const success = (res) => dispatch(fetchUserTodosSuccess(res));
  //função ser executado em caso de falha
  const failure = (err) => dispatch(fetchUserTodosFailure(err.message));
  makeHTTPRequest(url, request, success, failure);
}

export function addUser(url, request, dispatch){
  const success = (res) => dispatch(addUserSuccess(res));
  const failure = (err) => dispatch(addUserFailure(err.message));
  makeHTTPRequest(url, request, success, failure);
}




export function fetchUsersStarted() {
  return {
    type: FETCH_USERS_STARTED,

  }
}

export function fetchUsersSuccess(users) {
  return {
    type: FETCH_USERS_SUCCESS,
    payload: {
      data:
        [...users]
    }

  }
}

export function addUserSuccess(userName, email, function1, photo) {
  return {
    type: ADD_USER_SUCCESS,
    payload: {
      data:
          [...userName, email, function1, photo]
    }
  }
}


export function fetchUsersFailure(message) {
  return {
    type: FETCH_USERS_FAILURE,
    payload: {
      error: message
    }
  }
}

export function addUserFailure(message) {
  return {
    type: ADD_USER_FAILURE,
    payload: {
      error: message
    }
  }
}


export const FETCH_USER_TODOS_STARTED = 'FETCH_USER_TODOS_STARTED';
export const FETCH_USER_TODOS_SUCCESS = 'FETCH_USER_TODOS_SUCCESS';
export const FETCH_USER_TODOS_FAILURE = 'FETCH_USER_TODOS_FAILURE';
export const DELETE_USER_TODOS = 'DELETE_USER_TODOS';

export function fetchUserTodosStarted(id) {
  return {
    type: FETCH_USER_TODOS_STARTED,
    payload: {
      userid: id
    }
  }
}

export function fetchUserTodosSuccess(todos) {
  return {
    type: FETCH_USER_TODOS_SUCCESS,
    payload: {
      data: [...todos]
    }

  }
}

export function fetchUserTodosFailure(message) {
  return {
    type: FETCH_USER_TODOS_FAILURE,
    payload: {
      error: message
    }
  }
}

export function deleteUserTodos() {
  return {
    type: DELETE_USER_TODOS,
  }
}
