
import {
  FETCH_USERS_STARTED,
  FETCH_USERS_SUCCESS,
  FETCH_USERS_FAILURE,
  FETCH_USER_TODOS_STARTED,
  FETCH_USER_TODOS_SUCCESS,
  FETCH_USER_TODOS_FAILURE,
  DELETE_USER_TODOS,
  ADD_USER_SUCCESS,
  ADD_USER_FAILURE
} from './Actions'




function reducer(state, action) {
  switch (action.type) {
    case FETCH_USERS_STARTED:
      return {
        ...state,
        users: {
          loading: true,
          error: null,
          data: []
        }
      }
    case FETCH_USERS_SUCCESS:
      return {
        ...state,
        users: {
          loading: false,
          error: null,
          data: [...action.payload.data]
        }
      }
    case FETCH_USERS_FAILURE:
      return {
        ...state,
        users: {
          loading: false,
          error: action.payload.error,
          data: [],
        }
      }
    case FETCH_USER_TODOS_STARTED:
      return {
        ...state,
        todos: {
          loading: true,
          error: null,
          data: [],
          userid: action.payload.userid,
        }
      }
    case FETCH_USER_TODOS_SUCCESS:
      return {
        ...state,
        todos: {
          loading: false,
          error: null,
          data: [...action.payload.data],
          userid: state.todos.userid,
        }
      }
    case FETCH_USER_TODOS_FAILURE:
      return {
        ...state,
        todos: {
          loading: false,
          error: action.payload.error,
          data: [],
          userid: 0,
        }
      }
      case DELETE_USER_TODOS:
        return {
          ...state,
          todos: {
            loading: false,
            error: null,
            data: [],
            userid: 0,
          }
      }
    case ADD_USER_SUCCESS:
      return {
        ...state,
        users: {
          loading: false,
          error: null,
          data: []
        }
      }

    case ADD_USER_FAILURE:
      return {
        ...state,
        users: {
          loading: false,
          error: null,
          data: []
        }
      }
    default:
      return state
  }
}


export default reducer;
