import React from 'react';

import { URL_API } from '../services/Service';
import Table from '../components/Table';

function TodosPage() {
  return (
    <div>
      <h1>System Users</h1>
      <p>This web app interacts with REST API available at : {URL_API}</p>
      <p>It display a list of users. At click on user data, it displays user's list of to dos.</p>
      <hr></hr>
      <Table />
    </div>
  );
}

export default TodosPage;
