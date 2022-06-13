import Button from "../components/Button";
import {useContext, useEffect} from "react";
import Form from "../components/Form";
import Table from "../components/Table";
import AppContext from "../context/AppContext";
import {initNavPage, navToForm} from "../context/Actions";
import { Box, Grid, Heading } from "grommet";
import {useLocation} from "react-router-dom";

const postBody = {
  projectID: "",
  name: "",
};

const inputTypes = ["text", "text"];

export default function CreateSprint() {
  const { state, dispatch } = useContext(AppContext);
  const { navigation } = state;
  const { table, form } = navigation;

    const location = useLocation();
    const path = `sprints/sprintList/${location.state.projId}`;

    useEffect(() => {
        initNavPage(dispatch);

    }, [])

  const buttonNavigate = () => {
    navToForm(dispatch);
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
          <Box gridArea="header" direction="row" align="center" justify="center" gap="medium">
            <Heading>Sprints</Heading>
            <Button name="Create Sprint" function={buttonNavigate} />
          </Box>
          <Box gridArea="main">
            <Table collections={path} />
          </Box>
        </Grid>
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
