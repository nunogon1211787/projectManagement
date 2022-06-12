import { useContext, useEffect } from "react";
import Form from "../components/Form";
import Table from "../components/Table";
import Button from "../components/Button";
import AppContext from "../context/AppContext";
import { navToForm, initNavPage, navToTable } from "../context/Actions";
import { useLocation } from "react-router-dom";
import EditDetails from "../components/EditDetails";

const postBody = {
  systemUserID: "",
  projectId: "",
  projectRole: "",
  startDate: "",
  endDate: "",
  costPerHour: "",
  percentageOfAllocation: "",
};

const patchBody = {
  role: "",
  startDate: "",
  endDate: "",
  costPerHour: "",
  percentageOfAllocation: "",
};

const inputTypes = [
  "text",
  "text",
  "text",
  "date",
  "date",
  "numeric",
  "numeric",
];

const inputTypesPatch = [
  "text",
  "date",
  "date",
  "numeric",
  "numeric",
];

export default function CreateResource() {
  const { state, dispatch } = useContext(AppContext);
  const { navigation, details } = state;
  const { table, form, editDetails } = navigation;
  const {userid} = details;

  const location = useLocation();

  useEffect(() => {
    initNavPage(dispatch);
  
  }, [])
  

  const resId = `resources/${userid}`;

  const path = `resources/currentProjectTeam/${location.state.projId}`;

  const buttonNavigate = () => {
    navToForm(dispatch);
  }

  const buttonNavigateT = () => {
    navToTable(dispatch);
  };

  console.log(userid)

  if (table) {
    return (
      <>
        <h1>Project Team</h1>
        <Table collections={path} />
        <Button name="Create Resource" function={buttonNavigate} />
      </>
    );
  } else {
    if (form){
      return (
        <>
          <h1>Associate Resource</h1>
          <Form label={postBody} rules={inputTypes} collections="resources" />
        </>
      );
    } else {
      if(editDetails){
        return(
          <>
              <h1>Change Role</h1>
              <EditDetails label={patchBody} rules={inputTypesPatch} details={resId} httpMethod="PATCH" projID={location.state.projId} />
            
              <Button name="Back to table" function={buttonNavigateT} />
            </>
        )
      }
    }
  } 
}
