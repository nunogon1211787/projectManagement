import React from 'react';
import './App.css';
import UserComponent from './components/UserComponent';
import TypologyForm from "./components/TypologyForm";

function App() {
    return (
        <div className="App">
            <UserComponent/>
            <TypologyForm/>
        </div>
    );
}

export default App;
