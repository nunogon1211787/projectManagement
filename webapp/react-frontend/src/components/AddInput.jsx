import React from "react";
import "./AddInput.css";

const Input = ({children}) => {

    return (
        <div>
        <label htmlFor="inputName">{children}</label>
        <input
            className="inputBasic"/>
        </div>
    );
};

export default Input;