import { useContext } from "react";
import Form from "../components/Form";
import Table from "../components/Table";
import Button from "../components/Button";
import AppContext from "../context/AppContext";
import { navToForm } from "../context/Actions";

const postBody = {
  projectID: "",
  userID: "",
  projectRole: "",
  startDate: "",
  endDate: "",
  costPerHour: "",
  percentageOfAllocation: "",
};

const inputTypes = [
  "text",
  "text",
  "text",
  "date",
  "date",
  "number",
  "number",
];

export default function CreateResource() {
  const { state, dispatch } = useContext(AppContext);
  const { navigation } = state;
  const { table, form } = navigation;

  const buttonNavigate = () => {
    navToForm(dispatch);
  }
  if (table) {
    return (
      <>
        <h1>Resources ? Project Team</h1>
        <Table collections="resources" query="project=Project_2022_1&date=2022-12-13"/>
        <Button name="Create Resource" function={buttonNavigate} />
      </>
    );
  } else {
    if (form){
      return (
        <>
          <h1>Resources ? Project Team</h1>
          <Form label={postBody} rules={inputTypes} collections="resources" />
        </>
      );
    }
  } 
}
