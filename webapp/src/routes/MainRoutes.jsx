import React, { useContext } from "react";
import { Route, Routes } from "react-router-dom";
import {
  Projects,
  OrderedList,
  UserAdd,
  Tasks,
  History,
  FingerPrint,
  Services,
  Group,
} from "grommet-icons";
import CreateProfile from "../pages/CreateProfile";
import CreateProject from "../pages/CreateProject";
import CreateSprint from "../pages/CreateSprint";
import CreateUserStory from "../pages/CreateUserStory";
import CreateTypology from "../pages/CreateTypology";
import { Home } from "../pages/Home";
import RegisterUser from "../pages/RegisterUser";

import { Box, Grommet } from "grommet";

import { theme } from "../theme";
import Sidebar from "../components/Sidebar";
import { ReactComponent as LogoIcon } from "../routes/beaver-svgrepo.svg";
import AppContext from "../context/AppContext";
import CreateResource from "../pages/CreateResource";

const userSession = {
  user: {
    name: "Alan Souza",
    thumbnail: "//s.gravatar.com/avatar/b226da5c619b18b44eb95c30be393953?s=80",
  },
  items: [
    {
      label: "Logout",
      href: "#",
    },
  ],
};

const items = [
  {
    label: "Projects",
    Icon: Projects,
    path: "projects",
  },
  {
    label: "Users",
    Icon: Group,
    path: "users",
  },
  {
    label: "User Stories",
    Icon: OrderedList,
    path: "userstories",
  },
  {
    label: "Resources",
    Icon: UserAdd,
    path: "resources",
  },
  {
    label: "Sprints",
    Icon: History,
    path: "sprints",
  },
  {
    label: "Tasks",
    Icon: Tasks,
    path: "tasks",
  },
  {
    label: "Profiles",
    Icon: FingerPrint,
    path: "profiles",
  },
  {
    label: "Typologies",
    Icon: Services,
    path: "typologies",
  },
];

function MainRoute() {
  const { state } = useContext(AppContext);
  const { auth } = state;
  const { data } = auth;

  if (data.token === "") {
    return (
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="register" element={<RegisterUser />} />
        </Routes>
    );
  } else {
    return (
        // <Router>
        <Grommet theme={theme} full>
          <Box direction="row" fill>
            <Sidebar
                appIcon={<LogoIcon />}
                appName="Beaver App"
                items={items}
                userSession={userSession}
            />
            <Box flex>
              <Routes>
                <Route path="/" element={<Home />} />
                <Route path="userstories" element={<CreateUserStory />} />
                <Route path="profiles" element={<CreateProfile />} />
                <Route path="typologies" element={<CreateTypology />} />
                <Route path="projects" element={<CreateProject />} />
                <Route path="sprints" element={<CreateSprint />} />
                <Route path="users" element={<RegisterUser />} />
                <Route path="resources" element={<CreateResource />} />

              </Routes>
            </Box>
          </Box>
        </Grommet>
        //  </Router>
    );
  }
}

export default MainRoute;