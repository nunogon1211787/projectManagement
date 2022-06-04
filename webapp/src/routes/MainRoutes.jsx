import React from 'react';
import { Route, Routes } from "react-router-dom";
import CreateUserStory from '../pages/CreateUserStory';
import CreateProfile from '../pages/CreateProfile';
import CreateTypology from '../pages/CreateTypology';
import CreateProject from '../pages/CreateProject';
import CreateSprint from '../pages/CreateSprint';
import RegisterUser from '../pages/RegisterUser';
import { Home } from '../pages/Home';

function MainRoute() {
  return (
    // <Router>
      <div>
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="userstories" element={<CreateUserStory />} />
            <Route path="profiles" element={<CreateProfile />} />
            <Route path="typologies" element={<CreateTypology />} />
            <Route path="projects" element={<CreateProject />} />
            <Route path="sprints" element={<CreateSprint />} />
            <Route path="users" element={<RegisterUser />} />
        </Routes>
      </div>
    // </Router>

  );
}

export default MainRoute;
