import Table from "../components/Table";
import Form from "../components/Form";

const url = "http://localhost:8080/sprints";

const postBody = {
  projectID: "",
  name: "",

};

const inputTypes = ["text", "text"];

export default function CreateSprint() {
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
      <h1>Sprints</h1>
      <Table collections="sprints" />
      <Form label={postBody} rules={inputTypes} request={makePostRequest} />
    </>
  );
}
