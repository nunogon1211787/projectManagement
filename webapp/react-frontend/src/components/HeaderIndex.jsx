import React from "react";
import "./HeaderIndex.css";
import img from "../image/logo.png";

const headerIndex = () => {
    return (
        <div className={"header"}>
            <div className={"logo"}>
                <img src="../image/logo.png"/>
            </div>
            <div className={"columns"}>
                <ul>HOME</ul>
                <ul>ABOUT US</ul>
                <ul>FAQs</ul>
                <ul>CONTACT</ul>
            </div>
        </div>
    )
}

export default headerIndex;

