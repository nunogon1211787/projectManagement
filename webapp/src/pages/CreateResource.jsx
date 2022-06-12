import { useContext } from "react";
import Form from "../components/Form";
import Table from "../components/Table";
import Button from "../components/Button";
import AppContext from "../context/AppContext";
import { navToForm } from "../context/Actions";
import { Box, Grid, Heading } from "grommet";

const postBody = {
  projectID: "",
  userID: "",
  projectRole: "",
  startDate: "",
  endDate: "",
  costPerHour: "",
  percentageOfAllocation: "",
};

const inputTypes = ["text", "text", "text", "date", "date", "number", "number"];

export default function CreateResource() {
  const { state, dispatch } = useContext(AppContext);
  const { navigation } = state;
  const { table, form } = navigation;

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
          <Box gridArea="header" align="center" justify="center">
            <Heading>Resources ? Project Team</Heading>
            <Button name="Create Resource" function={buttonNavigate} />
          </Box>
          <Box gridArea="main">
            <Table
              collections="resources"
              query="project=Project_2022_1&date=2022-12-13"
            />
          </Box>
        </Grid>
      </>
    );
  } else {
    if (form) {
      return (
        <>
          <h1>Resources ? Project Team</h1>
          <Form label={postBody} rules={inputTypes} collections="resources" />
        </>
      );
    }
  }
}
