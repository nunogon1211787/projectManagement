import Button from "../components/Button";
import {useContext} from "react";
import Form from "../components/Form";
import Table from "../components/Table";
import AppContext from "../context/AppContext";
import {navToForm, navToTable} from "../context/Actions";
import {Box} from "grommet";

const postBody = {
    projectID: "",
    title: "",
    priority: "",
    description: "",
    timeEstimate: "",
};

const inputTypes = ["text", "text", "number", "text", "number"];

export default function CreateUserStory() {
    const {state, dispatch} = useContext(AppContext);
    const {navigation} = state;
    const {table, form, details} = navigation;

    const buttonNavigate = () => {
        navToForm(dispatch);
    };

    const buttonNavigateEdit = () => {
        navToForm(dispatch);
    };

    const buttonNavigateBack = () => {
        navToTable(dispatch);
    };

    if (table) {
        return (
            <Box fill align="center" justify="center">
                <h1>User Story</h1>
                <Table collections="userstories"/>
                <Button name="Create User Story" function={buttonNavigate}/>
            </Box>
        );
    } else {
        if (form) {
            return (
                <>
                    <Box fill align="center" justify="center">
                        <Form
                            label={postBody}
                            rules={inputTypes}
                            collections="userstories"
                        />
                    </Box>
                </>
            );
        } else {
            if (details) {
                return (
                    <>
                        <h1>User Story</h1>
                        {/*{<Details details={usID}/>}*/}
                        <Button name="Edit User Story" function={buttonNavigateEdit}/>
                        <Button name="Back to user stories list" function={buttonNavigateBack}/>
                    </>
                )

            }
        }
    }
}