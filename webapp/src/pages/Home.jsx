import Login from "./Login";
import { Box } from "grommet";
import { useContext } from "react";
import AppContext from "../context/AppContext";

export function Home() {
  const { state, dispatch } = useContext(AppContext);
  const { auth } = state;
  const { data, error } = auth;

  const errorMessage = () => {
    if (error !== null) {
      return <p style={{ color: "red" }}>{error}</p>;
    }
  };

  if (data.token === "") {
    return (
      <Box flex align="center" justify="center">
        <Login />
        <br></br>
        {errorMessage()}
      </Box>
    );
  } else {
    return (
      <Box fill align="center" justify="center">
        <h1>Welcome to Beaver APP</h1>
      </Box>
    );
  }
}

}