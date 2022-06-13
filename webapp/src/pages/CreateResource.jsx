import { useContext, useEffect } from "react";
import Form from "../components/Form";
import Table from "../components/Table";
import Button from "../components/Button";
import AppContext from "../context/AppContext";
import { navToForm, initNavPage, navToTable } from "../context/Actions";
import { useLocation } from "react-router-dom";
import EditDetails from "../components/EditDetails";
import { Box, Grid, Heading } from "grommet";

const postBody = {
  systemUserID: "",
  projectId: "",
  projectRole: "",
  startDate: "",
  endDate: "",
  costPerHour: "",
  percentageOfAllocation: "",
};

const patchBody = {
  role: "",
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
  "numeric",
  "numeric",
];

const inputTypesPatch = [
  "text",
  "date",
  "date",
  "numeric",
  "numeric",
];

export default function CreateResource() {
  const { state, dispatch } = useContext(AppContext);
  const { navigation, details } = state;
  const { table, form, editDetails } = navigation;
  const {userid} = details;

  const location = useLocation();

  useEffect(() => {
    initNavPage(dispatch);

  }, [])


  const resId = `resources/${userid}`;

  const path = `resources/currentProjectTeam/${location.state.projId}`;

  const buttonNavigate = () => {
    navToForm(dispatch);
  }

  const buttonNavigateT = () => {
    navToTable(dispatch);
  };

  if (table) {
    return (
      <>
        <Grid
          rows={["any CSS size", "any CSS size"]}
          columns={["any CSS size", "any CSS size"]}
          gap="small"
          areas={[
            { name: "header", start: [0, 0], end: [1, 0] },
            { name: "main", start: [0, 1], end: [1, 1] },
          ]}
        >
          <Box gridArea="header" align="center" justify="center">
            <Heading>Project Team</Heading>
            <Button name="Create Resource" function={buttonNavigate} />
          </Box>
          <Box gridArea="main">
            <Table
                collections={path}
                query="project=Project_2022_1&date=2022-12-13"
            />
          </Box>
        </Grid>
      </>
    );
  } else {
    if (form){
      return (
        <>
          <h1>Associate Resource</h1>
          <Form label={postBody} rules={inputTypes} collections="resources" />
        </>
      );
    } else {
      if(editDetails){
        return(
          <>
              <h1>Change Role</h1>
              <EditDetails label={patchBody} rules={inputTypesPatch} details={resId} httpMethod="PATCH" projID={location.state.projId} />

              <Button name="Back to table" function={buttonNavigateT} />
          </>
        )
      }
    }
  } 
}
