import React, { useEffect, useState } from "react";
import { ListGroup, Button, Container, Row, Col, ProgressBar } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const Profilo = () => {
  const [user, setUser] = useState({
    firstName: "",
    lastName: "",
    email: "",
    telefono: "",
    telefonoLineaFissa: "",
    indirizzo: "",
    cap: "",
    avatar: null,
    città: "",
  });

  const [promozione, setPromozione] = useState(null);

  useEffect(() => {
    const fetchUserData = async () => {
      const token = localStorage.getItem("authToken");
      if (!token) {
        console.error("Token non trovato");
        return;
      }

      try {
        const response = await fetch("http://localhost:9090/api/auth/user", {
          method: "GET",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        });

        if (!response.ok) {
          throw new Error(`Errore HTTP: ${response.status}`);
        }

        const data = await response.json();
        setUser({
          firstName: data.firstName,
          lastName: data.lastName,
          email: data.email,
          telefono: data.telefono,
          telefonoLineaFissa: data.telefonoLineaFissa,
          indirizzo: data.indirizzo,
          cap: data.cap,
          avatar: data.avatar,
          città: data.città,
        });

        fetchPromozione(data.id, token);
      } catch (error) {
        console.error("Errore nel recupero dei dati utente", error);
      }
    };

    const fetchPromozione = async (userName, token) => {
      try {
        const response = await fetch(`http://localhost:9090/api/auth/user/${userName}/promozioni`, {
          method: "GET",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        });

        if (!response.ok) {
          throw new Error("Nessuna promozione trovata");
        }

        const data = await response.json();
        if (data.length > 0) {
          setPromozione(data[0]);
        }
      } catch (error) {
        console.warn("Nessuna promozione disponibile", error);
        setPromozione(null);
      }
    };

    fetchUserData();
  }, []);

  const navigate = useNavigate();
  const handleLogout = () => {
    localStorage.removeItem("authToken");
    navigate("/login");
  };

  return (
    <>
      <Container className="mt-3">
        <Row>
          <h1 className="text-center my-5 fw-bolder">Benvenuto, {user.firstName}</h1>
          <Col xs={12} md={6}>
            <ListGroup>
              <ListGroup.Item>
                <strong>Nome: </strong> {user.firstName}
              </ListGroup.Item>
              <ListGroup.Item>
                <strong>Cognome: </strong> {user.lastName}
              </ListGroup.Item>
              <ListGroup.Item>
                <strong>Email: </strong> {user.email}
              </ListGroup.Item>
              <ListGroup.Item>
                <strong>Telefono: </strong> {user.telefono}
              </ListGroup.Item>
              <ListGroup.Item>
                <strong>Telefono Linea Fissa: </strong> {user.telefonoLineaFissa}
              </ListGroup.Item>
              <ListGroup.Item>
                <strong>Indirizzo: </strong> {user.indirizzo}, <strong>CAP: </strong> {user.cap}, <strong>Città: </strong> {user.città}
              </ListGroup.Item>
            </ListGroup>
          </Col>
          <Col xs={12} md={6}>
            <div className="mt-5">
              {promozione ? (
                <>
                  <ProgressBar striped variant="success" now={(promozione.minuti / 100) * 100} />
                  <p className="fw-bold">Minuti: {promozione.minuti}</p>
                  <ProgressBar striped variant="info" now={(promozione.messaggi / 100) * 100} />
                  <p className="fw-bold">Messaggi: {promozione.messaggi}</p>
                  <ProgressBar striped variant="warning" now={(promozione.giga / 100) * 100} />
                  <p className="fw-bold">Giga: {promozione.giga}</p>
                </>
              ) : (
                <p className="text-center fw-bold text-danger">Nessuna promozione disponibile</p>
              )}
            </div>
          </Col>
        </Row>
      </Container>
      <Row className="text-center">
        <Col>
          <Button variant="danger" className="my-5" onClick={handleLogout}>
            Logout
          </Button>
        </Col>
      </Row>
    </>
  );
};

export default Profilo;
