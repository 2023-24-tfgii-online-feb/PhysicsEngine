import React from "react";
import "./Header.css";
import logoImage from "../assets/ubu.png";

const Header = () => (
  <header>
    <img src={logoImage} alt="Logo" id="ubuLogo" />
    <div>
      <p>Motor de físicas basado en Java y SpringBoot</p>
    </div>
  </header>
);

export default Header;
