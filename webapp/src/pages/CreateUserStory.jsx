import Button from "../components/Button";
import {useContext, useEffect} from "react";
import Form from "../components/Form";
import Table from "../components/Table";
import AppContext from "../context/AppContext";
import {initNavPage, navToForm, navToTable} from "../context/Actions";
import { Box, Grid, Heading } from "grommet";

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
    const {navigation} = state;
    const {table, form, details} = navigation;

    useEffect(() => {
        initNavPage(dispatch);

    }, [])


    const buttonNavigate = () => {
        navToForm(dispatch);
    };

    const buttonNavigateEdit = () => {
        navToForm(dispatch);
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
          <Table collections="userstories" />
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
        if (details) {
            return (
                <>
                    <h1>User Story</h1>
                    {/*{<Details details={usID}/>}*/}
                    <Button name="Edit User Story" function={buttonNavigateEdit}/>
                    <Button name="Back to user stories list" function={buttonNavigateBack}/>
                </>
            )

        }
    }
  }
}