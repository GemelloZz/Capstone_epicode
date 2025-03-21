import React, { useState, useEffect } from "react";
import { Button, Container, Row, Col, Form, Alert } from "react-bootstrap";
import { useNavigate, useLocation } from "react-router-dom";

const Pagamento = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const [paymentSuccess, setPaymentSuccess] = useState(false);
  const [paymentError, setPaymentError] = useState(null);
  const [userId, setUserId] = useState("");

  // Recupera l'ID promozione dalla navigazione o dal localStorage
  const promotionId = location.state?.idPromozione || localStorage.getItem("idPromozione") || null;
  console.log("ID promozione:", promotionId);

  // Funzione per estrarre l'ID utente dal token JWT
  const getUserNameFromToken = () => {
    const token = localStorage.getItem("authToken");
    if (!token) return null;

    try {
      const payload = JSON.parse(atob(token.split(".")[1])); // Decodifica il payload del token
      console.log("Payload del token:", payload);
      return payload.sub || null; // `sub` contiene il nome dell'utente
    } catch (error) {
      console.error("Errore nella decodifica del token:", error);
      return null;
    }
  };

  useEffect(() => {
    const userIdFromToken = getUserNameFromToken();
    if (userIdFromToken) {
      setUserId(userIdFromToken);
    }
  }, []);

  const handlePayment = async () => {
    setPaymentError(null);
    setPaymentSuccess(false);

    const userName = getUserNameFromToken(); // Ottieni il nome utente dal token

    if (!promotionId || !userName) {
      throw new Error("ID promozione o nome utente non validi");
    }

    try {
      // Simuliamo una risposta di pagamento (da sostituire con la logica reale)
      const paymentResponse = {
        success: true, // Simuliamo un pagamento riuscito
        amount: 100,
        promotionId: promotionId,
        userName: userName, // Passiamo il nome utente invece dell'ID
      };

      if (paymentResponse.success) {
        setPaymentSuccess(true);

        const token = localStorage.getItem("authToken");
        if (token) {
          const response = await fetch(`http://localhost:9090/api/auth/user/${userName}/promozioni/${promotionId}`, {
            method: "POST",
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          });

          const responseData = await response.json();
          if (!response.ok) {
            throw new Error(responseData.error || "Errore nell'aggiornamento della promozione");
          }

          console.log("Promozione aggiornata:", responseData);
        }
      } else {
        throw new Error("Pagamento non riuscito");
      }
    } catch (error) {
      console.error("Errore nel pagamento:", error);
      setPaymentError(error.message);
    }
  };

  return (
    <Container>
      <Row>
        <Col>
          <h1>Pagina di Acquisto</h1>
          <h2>Dettagli Promozione</h2>
          <p>
            <strong>ID Promozione:</strong> {promotionId || "Nessuna promozione selezionata"}
            <br />
          </p>
          <p>
            <strong>ID utente :</strong> {userId || "Nessun utente loggato"}
          </p>

          <Form>
            <Form.Group controlId="formPayment">
              <Form.Label>Metodo di pagamento</Form.Label>
              <Form.Control type="text" placeholder="Inserisci i dettagli del pagamento" />
            </Form.Group>

            <Button variant="primary" className="mt-3" onClick={handlePayment}>
              Completa il pagamento
            </Button>
          </Form>

          {paymentSuccess && (
            <Alert variant="success" className="mt-3">
              <h3>Pagamento effettuato con successo!</h3>
            </Alert>
          )}

          {paymentError && (
            <Alert variant="danger" className="mt-3">
              <h3>Errore nel pagamento:</h3>
              <p>{paymentError}</p>
            </Alert>
          )}
        </Col>
      </Row>
    </Container>
  );
};

export default Pagamento;
