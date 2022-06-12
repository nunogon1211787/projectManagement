import Button from "../components/Button";
import { useContext } from "react";
import Form from "../components/Form";
import Table from "../components/Table";
import AppContext from "../context/AppContext";
import { Box } from "grommet";
import { navToEditDetails, navToDetails, navToForm, navToTable, navToProjectTeam, fetchCollections } from "../context/Actions";
import Details from "../components/Details";
import EditDetails from "../components/EditDetails";

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
  const { navigation, details } = state;
  const { table, form, single, editDetails } = navigation;
  const {userid} = details;

  const buttonNavigateT = () => {
    navToTable(dispatch);
  };

  const buttonNavigate = () => {
    navToForm(dispatch);
  };

  const buttonNavigateE = () => {
    navToEditDetails(dispatch);
  };

  let projID = `projects/${userid}` ;

  if(table){
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
    } else {
      if (single) {
        return (
          <>
            <h1>Project XPTO</h1>
            <Details details = {projID} />
            <Button name="Edit Project" function={buttonNavigateE} />
            <Button name="Sprints"  />
            <Button name="Product Backlog"  />
            <Button name="Back to table" function={buttonNavigateT} />
          </>
        )
      } else {
        if (editDetails) {
          return (
            <>
              <h1>Edit Project</h1>
              <EditDetails label={postBody} rules={inputTypes} details={projID} httpMethod="PUT" />
              <Button name="Back to table" function={buttonNavigateT} />
            </>
          )
        }
    }
  }
  }
}
