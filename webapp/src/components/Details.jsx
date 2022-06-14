import { useContext, useEffect } from "react";
import AppContext from "../context/AppContext";
import { URL_API } from "../services/Service";
import { fetchDetails } from "../context/Actions";
import Button from "./Button";
import { navToEditDetails } from "../context/Actions";
import { useNavigate } from "react-router-dom";
import {Box, Grid, Heading} from "grommet";

export default function Details(props) {
  const { state, dispatch } = useContext(AppContext);
  const { details } = state;
  const { userid, loading, error, data } = details;
  const navigate = useNavigate();

  useEffect(() => {
    let url = `${URL_API}/${props.details}`;

    if(props.query !== undefined){
      url = `${URL_API}/${props.details}?${props.query}`
    }
    const request = {};
    fetchDetails(url, request, dispatch);
  }, []);
  

  if (loading === true) {
    return <h1>Loading ....</h1>;
  } else {
    if (error !== null && error !== undefined) {
      console.log (error)
      return <h1>{error}</h1>;
    } else {
        if(data !== undefined){
          const headers = Object.keys(data[0]);
          const body = Object.values(data[0]);

          const buttonNavigateToProjectTeam = () => navigate("/resources",{state: {projId: body[0]}, replace: true})

          const buttonNavigateToProductBacklog = () => navigate("/userstories",{state: {projId: body[0]}, replace: true})

          const buttonNavigateToSprints = () => navigate("/sprints",{state: {projId: body[0]}, replace: true})

          const buttonNavigateE = () => {
            navToEditDetails(dispatch);
          };

        const setHeading = () => {
          switch (window.location.pathname) {
            case "/projects":
              return "Project";
            case "/userstories":
              return "User Story";
            case "/users":
              return "User";
            default:
              return "";
          }
        }

          const setButtons = () => {
            switch(window.location.pathname){
              case "/projects":
                return (
                    <Box gridArea="buttons" >
                      <Button name="Project Team" function={buttonNavigateToProjectTeam}/>
                      <Button name="Product Backlog" function={buttonNavigateToProductBacklog}/>
                      <Button name="Sprints" function={buttonNavigateToSprints}/>
                      <Button name="Edit Project" function={buttonNavigateE}/>
                    </Box>
                );
              case "/userstories":
                return (
                    <Box gridArea="buttons" >
                      <Button name="Edit User Story" function={buttonNavigateE}/>
                    </Box>
                );
              default:
                return "";
            }
          }

        const setName = () => {
          switch (window.location.pathname) {
            case "/projects":
              return body[1];
            case "/userstories":
              return body[0].split("&")[1];
              case "/users":
                return body[0];
            default:
              return "";
          }
        }

          return (
            <>
              <Grid
                  rows={["any CSS size", "any CSS size"]}
                  columns={["any CSS size", "any CSS size"]}
                  gap="small"
                  areas={[
                    { name: "header", start: [0, 0], end: [3, 0] },
                    { name: "buttons", start: [0, 1], end: [0, 1] },
                    { name: "main", start: [1, 1], end: [3, 1] },
                  ]}
              >
                <Box gridArea="header" align="center" justify="center">
                  <Heading>{setHeading()}: {setName()}</Heading>
                </Box>
                  {setButtons()}
                <Box gridArea="main">
                  <div className="card bg-light">
                    <table
                        className="card-body table table-primary table-hover"
                        style={{ margin: "1%", borderRadius: "10px" }}>
                        {
                          // eslint-disable-next-line
                          headers.map((key, idx) => {
                            if (key !== "_links") {
                              const result = key.replace(/[A-Z]/g, ' $&').trim();
                              return (
                                  <tr style={{ textTransform: "uppercase" }} key={idx}>
                                  <th
                                      key={idx}
                                      style={{ textTransform: "uppercase" }}>
                                    {result}
                                  </th>
                                  <td>{body[idx]}</td>
                                  </tr>
                              );
                            }
                          })
                        }
                    </table>


                  </div>
                </Box>
              </Grid>

            </>
        );
      } else {
        return <h1>No data ....</h1>;
      }
    }
  }

}