
import React, { useReducer } from 'react';
import PropTypes from "prop-types";
import { Provider } from './AppContext';
import reducer from './Reducer';

const initialState = {
  users: {
    loading: true,
    error: null,
    data: [],
  },
  todos: {
    userid: 0,
    loading: false,
    error: null,
    data: [],
  },
};

const headers = {
  userName: "Name",
  email: "Email",
  function1: "Function",
  photo: "Photo",
};

const AppProvider = (props) => {
  const [state, dispatch] = useReducer(reducer, initialState);
  return (
    <Provider value={{
      state,
      headers,
      dispatch
    }}>
      {props.children}
    </Provider>
  );
};
AppProvider.propTypes = {
  children: PropTypes.node,
};


export default AppProvider;