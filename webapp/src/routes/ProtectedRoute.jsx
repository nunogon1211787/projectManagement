import React, { useContext } from "react";
import { Navigate, useLocation } from "react-router-dom";
import AppContext from "../context/AppContext";
const ProtectedRoute = (props) => {
  const { state } = useContext(AppContext);
  const { token } = state.auth.data;
  const location = useLocation();
  if (!token) {
    return <Navigate to="/home" replace state={{ from: location }} />;
  }

  return props.children;
};
export default ProtectedRoute;