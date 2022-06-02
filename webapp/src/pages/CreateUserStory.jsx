// import { useEffect, useState } from "react";
import Form from "../components/Form";
import Table from "../components/Table";

const url = "http://localhost:8080/userstories";

const postBody = {
  projectID: "",
  title: "",
  priority: "",
  description: "",
  timeEstimate: "",
};

const inputTypes = ["text", "text", "number", "text", "number"];

export default function CreateUserStory() {
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
      <h1>User Story</h1>
      <Table collections="userstories" />
      <Form label={postBody} rules={inputTypes} request={makePostRequest} />
    </>
  );
}
