import React from "react";
import { Menu, Text } from "grommet";
import { Avatar } from "./Avatar";
import { Logout } from "grommet-icons";

export const UserMenu = ({ user = {}, items = [] }) => (
  <Menu
    dropAlign={{ bottom: "top" }}
    icon={false}
    items={items.map((item) => ({
      ...item,
      label: <Text size="small">{item.label}</Text>,
      onClick: () => {}, // no-op
    }))}
    label={<Avatar name={user.name} url={user.thumbnail} />}
  />
);
