import Button from "../components/Button";
import {useContext, useEffect} from "react";
import ProjectForm from "../components/ProjectForm";
import Table from "../components/Table";
import AppContext from "../context/AppContext";
import {initNavPage, navToForm, navToTable} from "../context/Actions";
import {Box, Grid, Heading} from "grommet";
import Details from "../components/Details";
import EditDetails from "../components/EditDetails";

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
    "number",
];

export default function CreateProject() {
    const {state, dispatch} = useContext(AppContext);
    const {navigation, details} = state;
    const {table, form, single, editDetails} = navigation;
    const {userid} = details;

    useEffect(() => {
        initNavPage(dispatch);

    }, [])

    const buttonNavigateT = () => {
        navToTable(dispatch);
    };

    const buttonNavigate = () => {
        navToForm(dispatch);
    };

    let projID = `projects/${userid}`;


    if (table) {
        return (
            <Grid
                rows={["any CSS size", "any CSS size"]}
                columns={["any CSS size", "any CSS size"]}
                gap="small"
                areas={[
                    {name: "header", start: [0, 0], end: [1, 0]},
                    {name: "main", start: [0, 1], end: [1, 1]},
                ]}
            >

                <Box gridArea="header" align="center" justify="center">
                    <Heading>Projects</Heading>
                    <Button name="Create Project" function={buttonNavigate}/>
                </Box>
                <Box gridArea="main">
                    <Table collections="projects"/>
                </Box>
            </Grid>
        );
    } else {
        if (form) {
            return (
                //<Form className="form" label={postBody} rules={inputTypes} collections="projects" />
                <ProjectForm className="form" label={postBody}
                             rules={inputTypes} collections="projects"/>
            );
        } else {
            if (single) {
                return (
                    <>
                        <Details details={projID}/>
                        <Button name="Back to table"
                                function={buttonNavigateT}/>
                    </>
                );
            } else {
                if (editDetails) {
                    return (
                        <>
                            <h1>Edit Project</h1>
                            <EditDetails label={postBody} rules={inputTypes}
                                         details={projID} httpMethod="PUT"/>
                            <Button name="Back to table"
                                    function={buttonNavigateT}/>
                        </>
                    );
                }
            }
        }
    }
}
