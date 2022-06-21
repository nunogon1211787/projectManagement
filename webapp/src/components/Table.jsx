import { useContext, useEffect, useState } from "react";
import AppContext from "../context/AppContext";
import { URL_API } from "../services/Service";
import { fetchCollections, navToEditDetails } from "../context/Actions";
import { navToDetails } from "../context/Actions";
import Button from "../components/Button";
import { Box, Pagination } from "grommet";

export default function Table(props) {
  const { state, dispatch } = useContext(AppContext);
  const { collection } = state;
  const { loading, error, data } = collection;

  //GET REQUEST TO API
  useEffect(() => {
    let url = `${URL_API}/${props.collections}`;

    //  if(props.query !== undefined){
    //    url = `${URL_API}/${props.collections}/${props.query}`
    //  }
    const request = {};
    fetchCollections(url, request, dispatch);
    // eslint-disable-next-line
  }, []);

  const buttonNavigateD = (singleId) => {
    navToDetails(dispatch, singleId);
  };

  const buttonNavigateEdit = (singleId) => {
    navToEditDetails(dispatch, singleId);
  };

  const buttonOpen = (id) => {
    if (props.collections === 'projects') {
      return <Button name="Open Project" singleId={id} function={buttonNavigateD} />
    }
    if (window.location.pathname === '/resources') {
      return <Button name="Change Role" singleId={id} function={buttonNavigateEdit} />
    }
    if (window.location.pathname === '/userstories') {
      return <Button name="Open" singleId={id} function={buttonNavigateD} />
    }
    if (props.collections === 'users') {
      return <Button name="Open" singleId={id} function={buttonNavigateD} />
    }
  }
  // if(props.collections === 'userstories'){
  //   return <Button name="Open" function={buttonNavigateUS}/>
  // }





  // let buttonOpenUS = <Button name="Open" function={buttonNavigateUS}/>;
  // if (props.collections !== 'userstories') {
  //     buttonOpenUS = null;
  // }

  const handleOnClick = (id) => {
    if (props.collections === 'projects' || window.location.pathname === '/userstories' || window.location.pathname === '/users') {
      return buttonNavigateD(id)
    } else {
      if (window.location.pathname === '/resources') {
        return buttonNavigateEdit(id)
      }
    }
  }

  let dataLimit = 2;
  const [sIndex, setSIndex] = useState(0)
  const [eIndex, setEIndex] = useState(2)

  if (loading === true) {
    return <h1>Loading ....</h1>;
  } else {
    if (error !== null) {
      return <h1 style={{ color: "red" }}>{error}</h1>;
    } else {
      if (Object.keys(data[0])[0] === "_embedded") {
        const collect = Object.keys(data[0]._embedded)[0];
        const header = Object.keys(data[0]._embedded[collect][0]);
        const response = data[0]._embedded[collect];

        let dataPage = response.slice(sIndex, eIndex);
        const changePage = ({ startIndex, endIndex }) => {
          setSIndex(startIndex);
          setEIndex(endIndex);
        }
        return (
          <>
            <div className="card bg-light">
              {/* <DataTable columns={headerTable} data={dataPage}/> */}
              {<table
                className="card-body table table-primary table-hover"
                style={{ margin: "1%", borderRadius: "10px" }}
              >
                <thead>
                  <tr style={{ textTransform: "uppercase" }} key="headers">
                    {
                      // eslint-disable-next-line
                      header.map((key, idx) => {
                        if (key !== "_links" && key !== "customer" && key !== "businessSector"
                          && key !== "numberOfSprints" && key !== "budget" && key !== "sprintDuration"
                          && key !== "endDate" && key !== "typo" && key !== "photo") {
                          const result = key.replace(/[A-Z]/g, ' $&').trim();
                          return (
                            <th
                              key={idx}
                            // style={{ textTransform: "uppercase" }}
                            >
                              {result}
                            </th>
                          );
                        }
                      })
                    }
                  </tr>
                </thead>
                <tbody>
                  {dataPage.map((row, index) => {
                    let id = row[Object.keys(row)[0]];

                    if (window.location.pathname === "/resources") {
                      id = row[Object.keys(row)[0]] + "&" + row[Object.keys(row)[1]] + "&" + row[Object.keys(row)[3]];
                    }
                    if (props.collections === "users") {
                      id = row[Object.keys(row)[1]];
                    }


                    return (
                      <tr
                        key={index}
                        onClick={() => handleOnClick(id)}
                        style={{
                          cursor: "pointer",
                          // textTransform: "capitalize",
                        }}
                      >
                        {
                          // eslint-disable-next-line
                          Object.keys(row).map((attr, idx) => {
                            if (attr !== "_links" && attr !== "customer" && attr !== "businessSector"
                              && attr !== "numberOfSprints" && attr !== "budget" && attr !== "sprintDuration"
                              && attr !== "endDate" && attr !== "typo") {
                              return (
                                <td key={idx}>{Array.isArray(row[attr]) ? row[attr].join(", ") : row[attr]}</td>
                              );
                            }
                          })
                        }
                        {buttonOpen(id)}
                      </tr>
                    );
                  })}
                </tbody>
              </table>}
              <div>
                <Box>
                  <Pagination step={dataLimit} numberItems={response.length} onChange={changePage} alignSelf="center" />
                </Box>
              </div>
            </div>
          </>
        );
      } else {
        return <h1>No data ....</h1>;
      }
    }
  }
}
