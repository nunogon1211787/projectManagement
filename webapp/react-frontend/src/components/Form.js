import React, {Component} from 'react';

class Form extends Component {
    constructor(props) {
        super(props)

        this.initialState = {
            userName: '',
            email: '',
            function1: '',
            photo: '',
        }

        this.state = this.initialState
    }


    handleChange = (event) => {
        const { name, value } = event.target;

        this.setState({
            [name] : value,        });
    }

    onFormSubmit = (event) => {
        event.preventDefault();

        this.props.handleSubmit(this.state);
        this.setState(this.initialState);
    }

    render() {
        const {userName, email, function1, photo} = this.state;

        return (
            <form onSubmit={this.onFormSubmit}>
                <label htmlFor="userName">Name</label>
                <input
                    type="text"
                    name="userName"
                    id="userName"
                    value={userName}
                    onChange={this.handleChange} />
                <label htmlFor="email">Email</label>
                <input
                    type="text"
                    name="email"
                    id="email"
                    value={email}
                    onChange={this.handleChange} />
                <label htmlFor="function1">Function</label>
                <input
                    type="text"
                    name="function1"
                    id="function1"
                    value={function1}
                    onChange={this.handleChange}/>
                <label htmlFor="photo">Photo</label>
                <input
                    type="text"
                    name="photo"
                    id="photo"
                    value={photo}
                    onChange={this.handleChange}/>
                <input type="button" value="Submit" onClick={this.onFormSubmit} />

            </form>
        );
    }
}

export default Form;