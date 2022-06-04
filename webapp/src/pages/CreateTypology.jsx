import Button from "../components/Button";
import { useContext } from "react";
import Form from "../components/Form";
import Table from "../components/Table";
import AppContext from "../context/AppContext";
import { navToForm } from "../context/Actions";

const postBody = {
  description: "",
};

const inputTypes = ["text"];

export default function CreateTypology() {
  const { state, dispatch } = useContext(AppContext);
  const { navigation } = state;
  const { table, form } = navigation;

  const buttonNavigate = () => {
    navToForm(dispatch);
  };

  if (table) {
    return (
      <>
        <h1>Typologies</h1>
        <Table collections="typologies" />
        <Button name="Create Typology" function={buttonNavigate} />
      </>
    );
  } else {
    if (form) {
      return (
        <>
          <Form label={postBody} rules={inputTypes} collections="typologies" />
        </>
      );
    }
  }
}
