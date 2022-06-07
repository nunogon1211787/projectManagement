import Button from "../components/Button";
import { useContext } from "react";
import Form from "../components/Form";
import Table from "../components/Table";
import AppContext from "../context/AppContext";
import { navToForm } from "../context/Actions";
import { Box } from "grommet";

const postBody = {
  projectID: "",
  name: "",
};

const inputTypes = ["text", "text"];

export default function CreateSprint() {
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
          <h1>Sprints</h1>
          <Table collections="sprints" />
          <Button name="Create Sprint" function={buttonNavigate} />
        </Box>
      </>
    );
  } else {
    if (form) {
      return (
        <>
          <Box fill align="center" justify="center">
            <Form label={postBody} rules={inputTypes} collections="sprints" />
          </Box>
        </>
      );
    }
  }
}
