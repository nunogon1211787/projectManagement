import React from "react";
import "./AddInput.css";

const Input = (props) => {

    return (
        <div>
        <label htmlFor="inputName">{props.children}</label>
        <input
            className="inputBasic" onChange={(e) => props.onChange(e.target.value)}/>
        </div>
    );
};

export default Input;