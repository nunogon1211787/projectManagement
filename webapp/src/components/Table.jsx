import { useContext, useEffect, useState } from "react";
import AppContext from "../context/AppContext";
import { URL_API } from "../services/Service";
import {fetchCollections} from "../context/Actions";
import {navToDetails} from "../context/Actions";
import Button from "../components/Button";

export default function Table(props) {
  const { state, dispatch } = useContext(AppContext);
  const { collection, details } = state;
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
    console.log(singleId)

    navToDetails(dispatch, singleId);
  };

  const buttonOpen = (id) =>{

    return  <Button name="Open Project" singleId = {id} function={buttonNavigateD} />
}


  // if (props.collections !== 'projects') {
  //  buttonOpen = null;
  // }

    // let buttonOpenUS = <Button name="Open" function={buttonNavigateUS}/>;
    // if (props.collections !== 'userstories') {
    //     buttonOpenUS = null;
    // }

    const handleOnClick = (id) => {
    };

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
            <div className="card bg-light">
              <table
                className="card-body table table-primary table-hover"
                style={{ margin: "1%", borderRadius: "10px" }}
              >
                <thead>
                  <tr style={{ textTransform: "uppercase" }} key="headers">
                    {
                      // eslint-disable-next-line
                      header.map((key, idx) => {
                        if (key !== "_links") {
                          return (
                            <th
                              key={idx}
                              style={{ textTransform: "uppercase" }}
                            >
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
                            {buttonOpen(id)}
                         </tr>
                    );
                  })}                  
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

