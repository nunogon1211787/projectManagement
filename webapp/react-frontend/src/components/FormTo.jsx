import React, {useState} from "react";
import AddInput from "./AddInput";
import ButtonBasic from "./ButtonBasic";

function SimpleForm() {
    const [userName, email, funct, photo] = useState("");

    const setInitialState = {
        userName,
        email,
        funct,
        photo,
    }


    const handleInputChange = (event) => {
        event.preventDefault();
    }

    return (
        <form onSubmit={handleInputChange}>
            <AddInput onChange={userName} children={"User Name"} />
            <AddInput onChange={email} children={"Email"} />
            <AddInput onChange={funct} children={"Function"} />
            <AddInput onChange={photo} children={"Photo"} />
            <ButtonBasic children={"Submit"} onClick={setInitialState} />
        </form>
    )
}
 export default SimpleForm;