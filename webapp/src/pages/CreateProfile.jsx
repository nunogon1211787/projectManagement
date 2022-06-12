import Button from "../components/Button";
import { useContext } from "react";
import Form from "../components/Form";
import Table from "../components/Table";
import AppContext from "../context/AppContext";
import { navToForm } from "../context/Actions";
import { Box, Grid, Heading } from "grommet";

const postBody = {
  description: "",
};

const inputTypes = ["text"];

export default function CreateUserProfile() {
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
            <Heading>Profiles</Heading>
            <Button name="Create Profile" function={buttonNavigate} />
          </Box>
          <Box gridArea="main">
            <Table collections="profiles" />
          </Box>
        </Grid>
      </>
    );
  } else {
    if (form) {
      return (
        <>
          <Box fill align="center" justify="center">
            <Form label={postBody} rules={inputTypes} collections="profiles" />
          </Box>
        </>
      );
    }
  }
}
