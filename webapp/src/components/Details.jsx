import { useContext, useEffect } from "react";
import AppContext from "../context/AppContext";
import { URL_API } from "../services/Service";
import { fetchDetails } from "../context/Actions";
import Button from "./Button";
import { navToEditDetails } from "../context/Actions";
export default function Details(props) {
  const { state, dispatch } = useContext(AppContext);
  const { details } = state;
  const { loading, error, data } = details;
  const buttonNavigateD = () => {
    navToEditDetails(dispatch);
  };
  useEffect(() => {
    const url = `${URL_API}/${props.details}`;
    const request = {};
    fetchDetails(url, request, dispatch);
  }, []);
  const handleOnClick = (id) => {};
  if (loading === true) {
    return <h1>Loading ....</h1>;
  } else {
    if (error !== null) {
      return <h1>Error:{error}</h1>;
    } else {
      if(data.length !== 0){
        const headers = Object.keys(data[0]);
        const body = Object.values(data[0]);
        return (
            <>
              <div className="card bg-light">
                <table
                    className="card-body table table-primary table-hover"
                    style={{ margin: "1%", borderRadius: "10px" }}>
                  <thead>
                  <tr style={{ textTransform: "uppercase" }} key="headers">
                    {
                      // eslint-disable-next-line
                      headers.map((key, idx) => {
                        if (key !== "_links") {
                          return (
                              <th
                                  key={idx}
                                  style={{ textTransform: "uppercase" }}>
                                {key}
                              </th>
                          );
                        }
                      })
                    }
                  </tr>
                  </thead>
                  <tbody>
                  <tr
                      key= "coisa"
                      //   onClick={() => handleOnClick(id)}
                      style={{
                        cursor: "pointer",
                        textTransform: "capitalize",
                      }}>
                    {body.map((row, index) => {

                      // eslint-disable-next-line
                      if (index !== body.length - 1) {
                        return <td key={index}>{row}</td>;
                      }
                    })}
                    <Button name="Edit Project" function={buttonNavigateD} />
                    <Button name="Edit Project Team" function={buttonNavigateD} />;

                  </tr>
                  </tbody>
                </table>
              </div>
            </>
        );
      } else {
        return <h1>No data ....</h1>;
      }
    }
  }

}