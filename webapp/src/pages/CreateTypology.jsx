import Table from "../components/Table";
import Form from "../components/Form";

const url = "http://localhost:8080/typologies";

const postBody = {
  description: "",
};

const inputTypes = ["text"];

export default function CreateTypology() {
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
      <h1>Typologies</h1>
      <Table collections="typologies" />
      <Form label={postBody} rules={inputTypes} request={makePostRequest} />
    </>
  );
}
