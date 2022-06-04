import Button from "../components/Button";
import { useContext } from "react";
import Form from "../components/Form";
import Table from "../components/Table";
import AppContext from "../context/AppContext";
import { navToForm } from "../context/Actions";

const postBody = {
  projectID: "",
  title: "",
  priority: "",
  description: "",
  timeEstimate: "",
};

const inputTypes = ["text", "text", "number", "text", "number"];


export default function CreateUserStory() {
  const { state, dispatch } = useContext(AppContext);
  const { navigation } = state;
  const { table, form } = navigation;

  const buttonNavigate = () => {
    navToForm(dispatch);
  };

  if (table) {
    return (
      <>
        <h1>User Story</h1>
        <Table collections="userstories" />
        <Button name="Create User Story" function={buttonNavigate} />
      </>
    );
  } else {
    if (form) {
      return (
        <>
          <Form label={postBody} rules={inputTypes} collections="userstories" />
        </>
      );
    }
  }
}
