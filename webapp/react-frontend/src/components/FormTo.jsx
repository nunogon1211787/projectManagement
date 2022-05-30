import React, {useState} from "react";
import AddInput from "./AddInput";
import ButtonBasic from "./ButtonBasic";

const data = {
    userName:"",
    email:"",
    func:"",
    photo:""
}

function SimpleForm() {
    const [state, setState] = useState(data);



    const handleInputChange = (event) => {
        event.preventDefault();

        if(event)
        setState(state.userName = event)


    }

    return (
        <form onSubmit={handleInputChange}>
            <AddInput onChange={handleInputChange} children="User Name" />
            <AddInput onChange={handleInputChange} children="Email" />
            <AddInput onChange={handleInputChange} children="Function" />
            <AddInput onChange={handleInputChange} children="Photo" />
            <ButtonBasic children={"Submit"} onClick={setInitialState} />
        </form>
    )
}
 export default SimpleForm;