import {
  FETCH_COLLECTIONS_SUCCESS,
  FETCH_COLLECTIONS_FAILURE,
  FETCH_DETAILS_FAILURE,
  FETCH_DETAILS_STARTED,
  FETCH_DETAILS_SUCCESS,
  DELETE_DETAILS,
  NAV_TO_FORM,
  NAV_TO_TABLE,
  FETCH_AUTH_FAILURE,
  FETCH_AUTH_SUCCESS,
  NAV_TO_DETAILS,
  NAV_TO_EDITDETAILS,
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
    case NAV_TO_FORM:
      return {
        ...state,
        navigation: {
          table: false,
          form: true,
          details: false,
          editDetails: false,
        },
      };
    case NAV_TO_TABLE:
      return {
        ...state,
        navigation: {
          table: true,
          form: false,
          details: false,
          editDetails: false,
        },
      };
    case FETCH_AUTH_SUCCESS:
      return {
        ...state,
        auth: {
          loading: false,
          error: null,
          data: {
            token: action.payload.data,
          },
        },
      };
    case FETCH_AUTH_FAILURE:
      return {
        ...state,
        auth: {
          loading: false,
          error: action.payload.error,
          data: {
            token: "",
          },
        },
      };
      case NAV_TO_DETAILS:
        return {
          ...state,
          navigation: {
            table: false,
            form: false,
            details: true,
            editDetails: false,
          },
        };
        case NAV_TO_EDITDETAILS:
        return {
          ...state,
          navigation: {
            table: false,
            form: false,
            details: false,
            editDetails: true,
          },
        };
    default:
      return state;
  }
}
