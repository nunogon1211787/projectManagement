import { useContext, useEffect } from "react";
import AppContext from "../context/AppContext";
import { URL_API } from "../services/Service";
import { fetchCollections } from "../context/Actions";

export default function Table(props) {
  const { state, dispatch } = useContext(AppContext);
  const { collection } = state;
  const { loading, error, data } = collection;

  //GET REQUEST TO API
  useEffect(() => {
    const url = `${URL_API}/${props.collections}`;
    const request = {};
    fetchCollections(url, request, dispatch);
    // eslint-disable-next-line
  }, []);

  const handleOnClick = (id) => {};

  if (loading === true) {
    return <h1>Loading ....</h1>;
  } else {
    if (error !== null) {
      return <h1>Error:{error}</h1>;
    } else {
      if (Object.keys(data[0])[0] === "_embedded") {
        const collect = Object.keys(data[0]._embedded)[0];
        const header = Object.keys(data[0]._embedded[collect][0]);
        const response = data[0]._embedded[collect];

        return (
          <>
            <table className="table table-primary table-hover">
              <thead>
                <tr style={{ textTransform: "uppercase" }} key="headers">
                  {
                    // eslint-disable-next-line
                    header.map((key, idx) => {
                      if (key !== "_links") {
                        return (
                          <th key={idx} style={{ textTransform: "uppercase" }}>
                            {key}
                          </th>
                        );
                      }
                    })
                  }
                </tr>
              </thead>
              <tbody>
                {response.map((row, index) => {
                  const id = row[Object.keys(row)[0]];
                  return (
                    <tr
                      key={index}
                      onClick={() => handleOnClick(id)}
                      style={{
                        cursor: "pointer",
                        textTransform: "capitalize",
                      }}
                    >
                      {
                        // eslint-disable-next-line
                        Object.keys(row).map((attr, idx) => {
                          if (attr !== "_links") {
                            return <td key={idx}>{row[attr]}</td>;
                          }
                        })
                      }
                    </tr>
                  );
                })}
              </tbody>
            </table>
          </>
        );
      } else {
        return <h1>No data ....</h1>;
      }
    }
  }
}
