import Form from "../components/Form";
import Table from "../components/Table";

const postBody = {
  projectName: "",
  description: "",
  businessSector: "",
  typology: "",
  customer: "",
  startDate: "",
  endDate: "",
  numberOfSprints: "",
  budget: "",
  projectStatus: "",
  sprintDuration: "",
};

const inputTypes = [
  "text",
  "text",
  "text",
  "text",
  "text",
  "date",
  "date",
  "number",
  "number",
  "text",
  "number",
];

export default function CreateProject() {
  return (
    <>
      <h1>Projects</h1>
      <Table collections="projects" />
      <Form label={postBody} rules={inputTypes} collections="projects" />
    </>
  );
}
