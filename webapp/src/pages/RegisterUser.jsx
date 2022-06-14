import Button from "../components/Button";
import { useContext, useEffect } from "react";
import Form from "../components/Form";
import Table from "../components/Table";
import Details from "../components/Details";
import AppContext from "../context/AppContext";
import { initNavPage, navToForm, navToTable } from "../context/Actions";
import { Box, Grid, Heading } from "grommet";

const postBody = {
  userName: "",
  email: "",
  function: "",
  password: "",
  passwordConfirmation: "",
  photo: "",
};

const inputTypes = ["text", "text", "text", "text", "text", "text"];

export default function RegisterUser() {
  const { state, dispatch } = useContext(AppContext);
  const { navigation, details } = state;
  const { table, form,  single } = navigation;
  const {userid} = details;

  useEffect(() => {
    initNavPage(dispatch);

  }, [])

  const buttonNavigate = () => {
    navToForm(dispatch);
  };

  const buttonNavigateT = () => {
    navToTable(dispatch);
  };

  let userID = `users/${userid}`;

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
          <Heading>Users</Heading>
          <Button name="Register User" function={buttonNavigate} />
        </Box>
        <Box gridArea="main">
          <Table collections="users" />
        </Box>
      </Grid>
    );
  } else {
    if (form) {
      return (
        <Box flex justify="center" align="center">
          <Form label={postBody} rules={inputTypes} collections="users" />
        </Box>
      );
    } else {
      if (single) {
        return (
          <>
            <Details details = {userID} />
            <Button name="Back to table" function={buttonNavigateT} />
          </>
        );
      } 
    }
  }
}
