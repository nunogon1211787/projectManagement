import { useEffect, useState, useContext } from "react";
import { fetchCollections, fetchDetails } from "../context/Actions";
import AppContext from "../context/AppContext";
import { URL_API } from "../services/Service";
import { navToDetails } from "../context/Actions";
import Button from "./Button";
import { useNavigate } from "react-router-dom";

const initialFormData = {};


export default function EditDetails(props) {
  const { dispatch } = useContext(AppContext);
  const [formData, setFormData] = useState(initialFormData);

  const inputLabel = Object.keys(props.label);

  const defaultValues = Object.values(props.label);

  const inputRules = props.rules;

  const navigate = useNavigate();

  //GET REQUEST TO API
  // useEffect(() => {
  //   const url = `${URL_API}/${props.details}`;
  //   const request = {};
  //   fetchDetails(url, request, dispatch);
  //   // eslint-disable-next-line
  // }, []);

  function makePutRequest() {
    const url = `${URL_API}/${props.details}`;

    const putRequest = {
      method: props.httpMethod,
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };

    fetchCollections(url, putRequest, dispatch);
    navigate("/resources", {state: {projId: props.projID}})
  }

  const buttonNavigate = () => {
    navToDetails(dispatch);
  };

  const inputs = inputLabel.map((txt, idx) => {
    if (defaultValues[idx] !== "") {
      return (
          <>
            <div className="mb-3">
              <label
                  className="form-label"
                  style={{
                    display: "inline-block",
                    textTransform: "capitalize",
                  }}
              >
                {" "}
                {txt}{" "}
              </label>
              <input
                  key={idx}
                  className="form-control"
                  value={defaultValues[idx]}
                  type={inputRules[idx]}
                  name={txt}
                  readOnly="true"
              />
            </div>
          </>
      );
    } else {
      return (
          <>
            <div className="mb-3">
              <label
                  className="form-label"
                  style={{
                    display: "inline-block",
                    textTransform: "capitalize",
                  }}
              >
                {" "}
                {txt}{" "}
              </label>
              <input
                  key={idx}
                  className="form-control"
                  type={inputRules[idx]}
                  name={txt}
                  onChange={(e) =>
                      setFormData({ ...formData, [e.target.name]: e.target.value })
                  }
              />
            </div>
          </>
      );
    }
  });

  return (
      <div className="card bg-light">
        <form className="card-body" onSubmit={() => makePutRequest()}>
          {inputs}
          <br />
          <Button name="save" type="submit" />
          {/* <Button name="return" function={buttonNavigate} /> */}
        </form>
      </div>
  );
}
