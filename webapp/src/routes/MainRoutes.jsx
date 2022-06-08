import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import CreateProject from "../pages/CreateProject";
import CreateUserStory from "../pages/CreateUserStory";
import CreateProfile from "../pages/CreateProfile";
import CreateTypology from "../pages/CreateTypology";
import { Home } from "../pages/Home";
import CreateSprint from "../pages/CreateSprint";
import RegisterUser from "../pages/RegisterUser";
import CreateResource from '../pages/CreateResource';

import {
  Projects,
  OrderedList,
  UserAdd,
  Tasks,
  History,
  FingerPrint,
  Services,
  Group,
  Grommet as GrommetIcon,
} from "grommet-icons";

import { Box, Grommet } from "grommet";

import { theme } from "../theme";
import { Sidebar } from "../components";

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
    path: "/projects",
  },
  {
    label: "Users",
    Icon: Group,
    path: "/users",
  },
  {
    label: "User Stories",
    Icon: OrderedList,
    path: "/userstories",
  },
  {
    label: "Resources",
    Icon: UserAdd,
    path: "/resources",
  },
  {
    label: "Sprints",
    Icon: History,
    path: "/sprints",
  },
  {
    label: "Tasks",
    Icon: Tasks,
    path: "/tasks",
  },
  {
    label: "Profiles",
    Icon: FingerPrint,
    path: "/profiles",
  },
  {
    label: "Typologies",
    Icon: Services,
    path: "/typologies",
  },
];

function MainRoute() {
  return (
    <Router>
      <Grommet theme={theme} full>
        <Box direction="row" fill>
          <Sidebar
            appIcon={<GrommetIcon color="brand" />}
            appName="Beaver App"
            items={items}
            userSession={userSession}
          />
          <Box flex>
            <Switch>
              <Route path="/" exact component={Home} />
              <Route path="/userstories" component={CreateUserStory} />
              <Route path="/profiles" component={CreateProfile} />
              <Route path="/typologies" component={CreateTypology} />
              <Route path="/projects" component={CreateProject} />
              <Route path="/sprints" component={CreateSprint} />
              <Route path="/users" component={RegisterUser} />
              <Route path="/resources" component={CreateResource} />

            </Switch>
          </Box>
        </Box>
      </Grommet>
    </Router>
  );
}

export default MainRoute;
