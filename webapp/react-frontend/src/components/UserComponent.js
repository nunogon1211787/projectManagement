import React from 'react';
import UserService from '../services/UserService';

class UserComponent extends React.Component {

    constructor(props){
        super(props)
        this.state = {
            systemUsers:[]
        }
    }

    componentDidMount(){
        UserService.getUsers().then((response) => {
            this.setState({ systemUsers: response.data})
        });
    }

    render (){
        return (
            <div>
                <h1 className = "text-center"> Users List</h1>
                <table className = "table table-striped">
                    <thead>
                    <tr>
                        <td> User Name</td>
                        <td> User Email</td>
                        <td> User Function</td>
                        <td> User Photo</td>
                    </tr>

                    </thead>
                    <tbody>
                    {
                        this.state.systemUsers.map((systemUser, index) =>
                                <tr key = {index}>
                                    <td> {systemUser.userName}</td>
                                    <td> {systemUser.email}</td>
                                    <td> {systemUser.function}</td>
                                    <td> {systemUser.photo}</td>
                                </tr>
                        )
                    }

                    </tbody>
                </table>

            </div>

        )
    }
}

export default UserComponent