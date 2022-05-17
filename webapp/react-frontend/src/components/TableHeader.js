import React, { useContext } from 'react';
import AppContext from '../context/AppContext';

function TableHeader() {
    const {headers} = useContext(AppContext);
   
    return(
        <thead>
                <tr>
                    <th style={{width:"50px"}}>{headers.username}</th>
                    <th style={{width:"200px"}}>{headers.email}</th>
                    <th style={{width:"200px"}}>{headers.function1}</th>
                    <th style={{width:"300px"}}>{headers.photo}</th>
                </tr>
            </thead>
    );
}
export default TableHeader;