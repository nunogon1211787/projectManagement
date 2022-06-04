import Button from "../components/Button";
import { useContext } from "react";
import Form from "../components/Form";
import Table from "../components/Table";
import AppContext from "../context/AppContext";
import { navToForm } from "../context/Actions";

const postBody = {
  projectName: "",
  description: "",
  businessSector: "",
  typology: "",
  customer: "",
  startDate: "",
  endDate: "",
  numberOfSprints: "",
  budget: "",
  projectStatus: "",
  sprintDuration: "",
};

const inputTypes = [
  "text",
  "text",
  "text",
  "text",
  "text",
  "date",
  "date",
  "number",
  "number",
  "text",
  "number",
];

export default function CreateProject() {
  const { state, dispatch } = useContext(AppContext);
  const { navigation } = state;
  const { table, form } = navigation;

  const buttonNavigate = () => {
    navToForm(dispatch);
  };

  if (table) {
    return (
      <>
        <h1>Projects</h1>
        <Table collections="projects" />
        <Button name="Create Project" function={buttonNavigate} />
      </>
    );
  } else {
    if (form) {
      return (
        <>
          <Form label={postBody} rules={inputTypes} collections="projects" />
        </>
      );
    }
  }
}