import Form from "../components/Form";
import Table from "../components/Table";

const url = "http://localhost:8080/profiles";

const postBody = {
  description: "",
};

const inputTypes = ["text"];

export default function CreateUserProfile() {
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
      <h1>Profiles</h1>
      <Table collections="profiles" />
      <Form label={postBody} rules={inputTypes} request={makePostRequest} />
    </>
  );
}
