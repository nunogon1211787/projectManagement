import Button from "../components/Button";
import { useContext } from "react";
import Form from "../components/Form";
import Table from "../components/Table";
import AppContext from "../context/AppContext";
import { navToForm } from "../context/Actions";
import { Box } from "grommet";

const postBody = {
  userName: "",
  email: "",
  function: "",
  password: "",
  passwordConfirmation: "",
  photo: "",
};

const inputTypes = ["text", "text", "text", "text", "text", "text"];

export default function RegisterUser() {
  const { state, dispatch } = useContext(AppContext);
  const { navigation } = state;
  const { table, form } = navigation;

  const buttonNavigate = () => {
    navToForm(dispatch);
  };

  if (table) {
    return (
      <Box fill align="center" justify="center">
        <h1>User</h1>
        <Table collections="users" />
        <Button name="Register User" function={buttonNavigate} />
      </Box>
    );
  } else {
    if (form) {
      return (
        <Box flex justify="center" align="center">
          <Form label={postBody} rules={inputTypes} collections="users" />
        </Box>
      );
    }
  }
}
