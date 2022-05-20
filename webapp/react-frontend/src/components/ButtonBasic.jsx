import React from "react";
import "./ButtonBasic.css";

const Button = ({ children, onClick }) => {
    return (
        <button onClick={onClick} className="buttonBasic">
            {children}
        </button>
    );
};

export default Button;