import {
  FETCH_COLLECTIONS_SUCCESS,
  FETCH_COLLECTIONS_FAILURE,
  FETCH_DETAILS_FAILURE,
  FETCH_DETAILS_STARTED,
  FETCH_DETAILS_SUCCESS,
  DELETE_DETAILS,
} from "./Actions";

export default function reducer(state, action) {
  // debugger
  switch (action.type) {
    case FETCH_COLLECTIONS_SUCCESS:
      return {
        ...state,
        collection: {
          loading: false,
          error: null,
          data: [action.payload.data],
        },
      };
    case FETCH_COLLECTIONS_FAILURE:
      return {
        ...state,
        collection: {
          loading: false,
          error: action.payload.error,
          data: [],
        },
      };
    case FETCH_DETAILS_STARTED:
      return {
        ...state,
        details: {
          loading: true,
          error: null,
          data: [],
          userid: action.payload.userid,
        },
      };
    case FETCH_DETAILS_SUCCESS:
      return {
        ...state,
        details: {
          loading: false,
          error: null,
          data: [...action.payload.data],
          userid: state.details.userid,
        },
      };
    case FETCH_DETAILS_FAILURE:
      return {
        ...state,
        details: {
          loading: false,
          error: action.payload.error,
          data: [],
          userid: 0,
        },
      };
    case DELETE_DETAILS:
      return {
        ...state,
        details: {
          loading: false,
          error: null,
          data: [],
          userid: 0,
        },
      };
    default:
      return state;
  }
}
