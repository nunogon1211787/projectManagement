import React, { useContext } from 'react';
import AppContext from '../context/AppContext';

function TodosList() {
    const {state} = useContext(AppContext);
    const {todos} = state;
    const { loading, error, data } = todos; 
    if (loading === true) {
        return (<h1>Loading ....</h1>);
    }
    else {
        if (error !== null) {
            return (<h1>Error ....</h1>);
        } else {
            if (data.length > 0) {
                return (
                    <div>
                        <div>
                           <ul>
                               {data.map((todo) => (
                                   <li>{todo.title}</li>
                               ))}
                           </ul>
                       </div>
                    </div>
                );
            } else {
                return (<h1>No data ....</h1>);
            }
        }
    }
}
export default TodosList;