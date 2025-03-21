import React from "react";
import { Nav, Navbar } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";

const NavBar = () => {
  const navigate = useNavigate();

  const isAuthenticated = localStorage.getItem("authToken");

  return (
    <>
      <Navbar expand="lg bg-warning">
        <Navbar.Brand href="#home" className=""></Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar">
          <Nav className="me-auto text-center">
            <Nav.Link as={Link} to={"/"} className="text-white fw-bold fs-1">
              FlySim
            </Nav.Link>
            <Nav.Link href="#home" className="text-white fw-bold fs-4 mt-3">
              Dove siamo ?
            </Nav.Link>
            <Nav.Link href="#home" className="text-white fw-bold fs-4 mt-3">
              Chi siamo?
            </Nav.Link>
            {isAuthenticated ? (
              <Nav.Link onClick={() => navigate("/Profilo")} className="text-danger fw-bold fs-4 mt-3">
                Profilo
              </Nav.Link>
            ) : (
              <Nav.Link onClick={() => navigate("/login")} className="text-white fw-bold fs-4 mt-3">
                Login
              </Nav.Link>
            )}
            {isAuthenticated ? (
              <Nav.Link className="text-danger fw-bold fs-4 mt-3"></Nav.Link>
            ) : (
              <Nav.Link onClick={() => navigate("/Registrati")} className="text-white fw-bold fs-4 mt-3">
                Registrati
              </Nav.Link>
            )}
          </Nav>
          <Nav className="ms-auto text-center"></Nav>
        </Navbar.Collapse>
      </Navbar>
    </>
  );
};

export default NavBar;
