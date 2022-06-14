import { useState, useContext } from "react";
import {fetchAddToCollections, navToTable} from "../context/Actions";
import AppContext from "../context/AppContext";
import { URL_API } from "../services/Service";
import Button from "./Button";

const initialFormData = {};

export default function Form(props) {
  const { dispatch } = useContext(AppContext);
  const [formData, setFormData] = useState(initialFormData);

  const inputLabel = Object.keys(props.label);

  const defaultValues = Object.values(props.label);

  const inputRules = props.rules;

  function makePostRequest() {
    const url = `${URL_API}/${props.collections}`;

    const postRequest = {
      method: "POST",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };

    fetchAddToCollections(url, postRequest, dispatch);
  }

  const buttonNavigate = () => {
    navToTable(dispatch);
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
        <form className="card-body" onSubmit={(e) => {
            e.preventDefault()
            makePostRequest()
        }}>
          {inputs}
          <br />
          <Button name="create" type="submit" />
          <Button name="return" function={buttonNavigate} />
        </form>
      </div>
  );
}
