import Table from "../components/Table";
import Form from "../components/Form";

const url = "http://localhost:8080/users";

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
  //POST REQUEST TO API

  const makePostRequest = (data) => {
    const postRequest = {
      method: "POST",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(data),
    };

    fetch(url, postRequest)
      .then((res) => res.json())
      .then()
      .catch((err) => console.log(err));
  };

  return (
    <>
      <h1>Users</h1>
      <Table collections="users" />
      <Form label={postBody} rules={inputTypes} request={makePostRequest} />
    </>
  );
}
