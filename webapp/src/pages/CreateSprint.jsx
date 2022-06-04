import Button from "../components/Button";
import { useContext } from "react";
import Form from "../components/Form";
import Table from "../components/Table";
import AppContext from "../context/AppContext";
import { navToForm } from "../context/Actions";

const postBody = {
  projectID: "",
  name: ""
};

const inputTypes = ["text"];

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
        <h1>Sprints</h1>
        <Table collections="sprints" />
        <Button name="Create Sprint" function={buttonNavigate} />
      </>
    );
  } else {
    if (form) {
      return (
        <>
          <Form label={postBody} rules={inputTypes} collections="sprints" />
        </>
      );
    }
  }
}