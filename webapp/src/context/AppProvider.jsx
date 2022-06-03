import React, { useReducer } from "react";
import { Provider } from "./AppContext";
import reducer from "./Reducer";

const initialState = {
  collection: {
    loading: true,
    error: null,
    data: [],
  },
  details: {
    userid: 0,
    loading: false,
    error: null,
    data: [],
  },
  navigation: {
    table: true,
    form: false,
  },
};

// const initialState = {
//     loading: false,
//     data: {
//         token: ""
//     }
// };

const AppProvider = (props) => {
  const [state, dispatch] = useReducer(reducer, initialState);

  const value = {
    state: state,
    dispatch: dispatch,
  };

  return <Provider value={value}>{props.children}</Provider>;
};

export default AppProvider;
