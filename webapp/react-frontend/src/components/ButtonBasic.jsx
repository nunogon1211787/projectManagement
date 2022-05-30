import React from "react";
import "./ButtonBasic.css";

const Button = ({ children, onClick }) => {
    return (
        <button type="submit" onClick={onClick} className="buttonBasic">
            {children}
        </button>
    );
};

export default Button;