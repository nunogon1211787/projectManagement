import React, { useContext, useEffect } from 'react';
import AppContext from '../context/AppContext';
import {fetchUsers} from '../context/Actions';
import TableHeader from './TableHeader';
import TableBody from './TableBody';
import { URL_API } from '../services/Service';

function Table() {
    const { state, dispatch } = useContext(AppContext);
    const { users } = state;
    const { loading, error, data } = users;

    useEffect(() => {
        const url = `${URL_API}users`;
        const request = {};
        fetchUsers(url, request, dispatch);
        // eslint-disable-next-line
    }, []);

    if (loading === true) {
        return (<h1>Loading ....</h1>);
    }
    else {
        if (error === null) {
            return (<h1>Error ....</h1>);
        } else {
            if (data.length > 0) {
                return (
                    <div>
                        <table border="1">
                            <TableHeader />
                            <TableBody />
                        </table>
                    </div>
                );
            } else {
                return (<h1>No data ....</h1>);
            }
        }
    }
}
export default Table;