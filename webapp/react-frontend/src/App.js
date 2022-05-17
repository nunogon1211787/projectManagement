import TodosPage from './pages/TodosPage';
import Form from "./components/Form";
import {Component} from "react";
import {addUser} from "./context/Actions";

// const people = [{
// }];


class App extends Component{
    // constructor(props) {
    //     super(props);
    //     this.state = {
    //         userNames: people,
    //     };
    // }
    state = {userNames :[]};

    handleSubmit = userName => {
        this.setState({userNames: [...this.state.userNames, userName]  })
    }

    render() {

    return (<div>
      <h1>ProjectG2</h1>
    <TodosPage />
            <h3>Add New</h3>
          <Form handleSubmit={this.handleSubmit} />
  </div>
  );
    }
}

export default App;
