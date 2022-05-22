import TodosPage from './pages/TodosPage';
import FormTo from "./components/FormTo";
import {Component} from "react";
import "./App.css";
import Footer from "./components/Footer";
import HeaderIndex from "./components/HeaderIndex";


class App extends Component {
    state = {userNames: []};

    handleSubmit = userName => {
        this.setState({userNames: [...this.state.userNames, userName]})
    }

    render() {
        return (<div className={"container"}>
                <HeaderIndex />
                <div>
                    <h1>ProjectG2 - BEAVER</h1>
                    <TodosPage/>
                </div>
                <div>
                    <h3>Add New</h3>
                    <FormTo />
                </div>
                <Footer />
            </div>
        );
    }
}

export default App;
