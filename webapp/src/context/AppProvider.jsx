import React, { useReducer } from "react";
import { Provider } from "./AppContext";
import reducer from "./Reducer";

const initialState = {
  auth: {
    loading: false,
    error: null,
    data: {
      token: "1",
    },
  },
  login: {
    user: null,
    profile: null,
    role: null,
  },
  collection: {
    loading: true,
    error: null,
    data: [],
  },
  details: {
    userid: "",
    loading: false,
    error: null,
    data: [],
  },
  navigation: {
    table: true,
    form: false,
    single: false,
    editDetails: false,
  },
};

const AppProvider = (props) => {
  const [state, dispatch] = useReducer(reducer, initialState);

  const value = {
    state: state,
    dispatch: dispatch,
  };

  return <Provider value={value}>{props.children}</Provider>;
};

export default AppProvider;
