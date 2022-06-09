import React, { useContext } from "react";
import { Box, Text } from "grommet";
import { UserMenu } from "./UserMenu";
import MenuButton from "./MenuButton";
import { useNavigate } from "react-router-dom";
import AppContext from "../context/AppContext";

export default function Sidebar(props) {
  const { state, dispatch } = useContext(AppContext);
  const { auth } = state;
  const { data } = auth;

  const { appName, appIcon, items, userSession } = props;
  const navigate = useNavigate();

  // const showPermittedMenuButton = (Icon, label, path) => {
  //   if (Object.keys(data.token._links).includes(path)) {
  //     return <MenuButton Icon={Icon} path={path} label={label} key={label} />;
  //   }
  // };

  return (
    <>
      <Box
        fill="vertical"
        width="sidebar"
        background="dark-2"
        elevation="medium"
      >
        <Box
          flex={false}
          align="center"
          gap="xsmall"
          pad={{ vertical: "small" }}
          onClick={() => navigate("/", { replace: true })}
        >
          {appIcon}
          {/* <Text size="xsmall">{appName}</Text> */}
        </Box>
        <Box flex overflow="auto">
          {items.map(({ Icon, label, path }) => {
            /* showPermittedMenuButton(Icon, label, path) */
            return (
              <MenuButton Icon={Icon} path={path} label={label} key={label} />
            );
          })}
        </Box>
        {props.userSession && (
          <UserMenu
            alignSelf="center"
            user={userSession.user}
            items={userSession.items}
          />
        )}
      </Box>
    </>
  );
}
