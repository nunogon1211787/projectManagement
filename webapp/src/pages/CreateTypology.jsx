import Table from "../components/Table";
import Form from "../components/Form";

const postBody = {
  description: "",
};

const inputTypes = ["text"];

export default function CreateTypology() {
  return (
    <>
      <h1>Typologies</h1>
      <Table collections="typologies" />
      <Form label={postBody} rules={inputTypes} collections="typologies" />
    </>
  );
}
