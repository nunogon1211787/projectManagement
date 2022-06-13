import Button from "../components/Button";
import {useContext, useEffect} from "react";
import Form from "../components/Form";
import Table from "../components/Table";
import AppContext from "../context/AppContext";
import {initNavPage, navToEditDetails, navToForm, navToTable} from "../context/Actions";
import { Box, Grid, Heading } from "grommet";
import {useLocation} from "react-router-dom";
import EditDetails from "../components/EditDetails";
import Details from "../components/Details";

const postBody = {
    projectID: "",
    title: "",
    priority: "",
    description: "",
    timeEstimate: "",
};

const inputTypes = ["text", "text", "number", "text", "number"];

export default function CreateUserStory() {
    const {state, dispatch} = useContext(AppContext);
    const {navigation, details} = state;
    const {table, form, single, editDetails} = navigation;
    const {userid} =  details;

    const location = useLocation();
    const path = `userstories/productBacklog/${location.state.projId}`;

    let usID = `userstories/${userid}` ;

    useEffect(() => {
        initNavPage(dispatch);

    }, [])


    const buttonNavigate = () => {
        navToForm(dispatch);
    };

    const buttonNavigateEdit = () => {
        navToEditDetails(dispatch);
    };

    const buttonNavigateBack = () => {
        navToTable(dispatch);
    };

  if (table) {
    return (
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
          <Heading>User Stories</Heading>
          <Button name="Create User Story" function={buttonNavigate} />
        </Box>
        <Box gridArea="main">
          <Table collections={path} />
        </Box>
      </Grid>
    );
  } else {
    if (form) {
      return (
        <>
          <Box fill align="center" justify="center">
            <Form
              label={postBody}
              rules={inputTypes}
              collections="userstories"
            />
          </Box>
        </>
      );
    }else {
        if (single) {
            return (
                <>
                    <Details details={usID}/>
                    <Button name="Back to user stories list" function={buttonNavigateBack}/>
                </>
            )

        }else {
            if (editDetails) {
                return (
                    <>
                        <h1>Edit User Story</h1>
                        <EditDetails label={postBody} rules={inputTypes} details={usID} httpMethod="PUT"/>
                        <Button name="Back to table" function={buttonNavigateBack}/>
                    </>
                );
            }
        }
    }
  }
}