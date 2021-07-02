import React from "react";
import { render } from "react-dom";
import Routes from "../Routes";

const styles = {
  fontFamily: "sans-serif",
  textAlign: "center"
};

const Root = () => (
  <div style={styles}>
    <Routes />
  </div>
);

render(<Root />, document.getElementById("root"));
