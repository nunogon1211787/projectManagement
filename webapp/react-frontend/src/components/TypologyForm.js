import React, {Component} from 'react'

class Form extends Component {
    initialState = { //object with empty properties
        description: '',
    }

    state = this.initialState //assign that to this.state

    handleChange = (event) => {
        const {name, value} = event.target

        this.setState({
            [name]: value,
        })
    }
    submitForm = () => {
        this.props.handleSubmit(this.state)
        this.setState(this.initialState)
    }

    render() {
        const {name} = this.state;

        return (
            <form>
                <label htmlFor="name">Description</label>
                <input
                    type="text"
                    name="name"
                    id="name"
                    value={name}
                    onChange={this.handleChange}/>
                <label htmlFor="job">Job</label>
                <input type="button" value="Submit" onClick={this.submitForm}/>
            </form>
        );
    }
}

export default Form;