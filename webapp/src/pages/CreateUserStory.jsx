import Form from "../components/Form";
import Table from "../components/Table";

const postBody = {
  projectID: "",
  title: "",
  priority: "",
  description: "",
  timeEstimate: "",
};

const inputTypes = ["text", "text", "number", "text", "number"];

export default function CreateUserStory() {
  return (
    <>
      <h1>User Story</h1>
      <Table collections="userstories" />
      <Form label={postBody} rules={inputTypes} collections="userstories" />
    </>
  );
}
