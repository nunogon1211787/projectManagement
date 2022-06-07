import Button from "../components/Button";
import { useContext } from "react";
import Form from "../components/Form";
import Table from "../components/Table";
import AppContext from "../context/AppContext";
import { navToForm } from "../context/Actions";
import { Box } from "grommet";

const postBody = {
  description: "",
};

const inputTypes = ["text"];

export default function CreateUserProfile() {
  const { state, dispatch } = useContext(AppContext);
  const { navigation } = state;
  const { table, form } = navigation;

  const buttonNavigate = () => {
    navToForm(dispatch);
  };

  if (table) {
    return (
      <>
        <Box fill align="center" justify="center">
          <h1>Profiles</h1>
          <Table collections="profiles" />
          <Button name="Create Profile" function={buttonNavigate} />
        </Box>
      </>
    );
  } else {
    if (form) {
      return (
        <>
          <Box fill align="center" justify="center">
            <Form label={postBody} rules={inputTypes} collections="profiles" />
          </Box>
        </>
      );
    }
  }
}
