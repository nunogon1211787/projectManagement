import React, { useContext } from 'react';
import AppContext from '../context/AppContext';
import { deleteUserTodos, fetchUserTodos, fetchUserTodosStarted } from '../context/Actions';
import TodosList from './TodosList';
import { URL_API } from '../services/Service';

function TableBody() {
    const { state, dispatch } = useContext(AppContext);
    const { users, todos } = state;
    const { data } = users;
    const { userid } = todos;

    const handleOnClick = (id) => {
        if (userid !== id) {
            dispatch(fetchUserTodosStarted(id));
            const url = `${URL_API}/posts?userId=${id}`;
            const request = {};
            fetchUserTodos(url, request, dispatch);
        } else {
            dispatch(deleteUserTodos());
        }
    };
    const rows = data.map((row, index) => {
        if (userid !== row.userName) {
            return (
                <tr key={index} onClick={() => handleOnClick(row.userName)} style={{ cursor: "pointer" }}>
                    <td>{row.userName}</td>
                    <td>{row.email}</td>
                    <td>{row.function}</td>
                    <td>{row.photo}</td>
                </tr>
            )
        } else {
            return (
                <tr style={{ backgroundColor: "#CCCCCC", cursor: "pointer" }} onClick={() => handleOnClick(row.userName)}>
                    <td colspan="4">
                        <table border="4">
                            <tr key={index}>
                                <td style={{ width: "50px" }}>{row.userName}</td>
                                <td style={{ width: "200px" }}>{row.email}</td>
                                <td style={{ width: "200px" }}>{row.function}</td>
                                <td style={{ width: "300px" }}>{row.photo}</td>
                            </tr>
                            <tr><td colspan="4"><TodosList /></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            )
        }
    });
    return (
        <tbody>{rows}</tbody>
    );
}
export default TableBody;