import React from "react";
import { Box, Text } from "grommet";
import { useNavigate } from "react-router-dom";
export default function MenuButton(props) {
  let navigate = useNavigate();
  const navTo = () => {
    navigate(props.path, { replace: true });
  };
  return (
      <Box
          pad={{ vertical: "small" }}gap="xsmall"
          align="center"
          justify="center"
          hoverIndicator="dark-4"
          onClick={() => navTo()}
      >
        <props.Icon color="light-5" />
        <Text size="xsmall" color="white">
          {props.label}
        </Text>
      </Box>
  );
}