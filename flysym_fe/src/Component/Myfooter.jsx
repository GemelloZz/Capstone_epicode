import { Container, Row, Col } from "react-bootstrap";

const MyFooter = () => {
  return (
    <>
      <Container fluid className="bg-black text-white">
        <Row>
          <Col xs={6} md={6} lg={3}>
            <p className="mt-3">Copyright &copy; 2025</p>
            <p>Powered by FlySim</p>
            <p> Creato e Pensato da Gabriele Lucarelli </p>
          </Col>
          <Col xs={6} md={6} lg={3}>
            <p className="mt-3">P.IVA123456789</p>
            <p>Numero di telefono 123456789</p>
            <p> Indirizzo via 123, 12345, Roma</p>
          </Col>
          <Col xs={6} md={6} lg={3}>
            <p className="mt-3">Chi siamo? </p>
            <p>Vuoi lavorare con noi?</p>
            <p>Progetto Pianta un albero</p>
          </Col>
          <Col xs={6} md={6} lg={3}>
            <p className="mt-3">Sede Legale</p>
            <p>Partnertship Apple</p>
            <p>ontrolla il sito</p>
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default MyFooter;
