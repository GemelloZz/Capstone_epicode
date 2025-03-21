import { useState } from "react";
import { FloatingLabel, Form } from "react-bootstrap";

const Registrati = () => {
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
  const [telefonoLineaFissa, setTelefonoLineaFissa] = useState("");
  const [cap, setCap] = useState("");
  const [telefono, setTelefono] = useState("");
  const [indirizzo, setIndirizzo] = useState("");
  const [citta, setCitta] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();

    if (!username || !password || !firstName || !lastName || !email || !telefonoLineaFissa || !cap || !telefono || !indirizzo || !citta) {
      setError("Tutti i campi sono obbligatori.");
      return;
    }

    setLoading(true);
    try {
      const response = await fetch("http://localhost:9090/api/auth/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          firstName,
          lastName,
          email,
          password,
          username,
          telefonoLineaFissa,
          cap,
          telefono,
          indirizzo,
          citta,
          roles: ["ROLE_USER"], // Aggiungi i ruoli
        }),
      });

      if (!response.ok) {
        throw new Error("Registrazione effettuata con successo");
      }

      const data = await response.json();
      console.log("Registrazione avvenuta con successo", data);

      // Reset dei campi
      setFirstName("");
      setLastName("");
      setEmail("");
      setPassword("");
      setUsername("");
      setTelefonoLineaFissa("");
      setCap("");
      setTelefono("");
      setIndirizzo("");
      setCitta("");
    } catch (error) {
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="d-flex justify-content-center vh-100 mt-5">
      <div>
        <h2 className="text-center fs-1 fw-bold">Registrati</h2>
        {error && <div className="alert alert-danger">{error}</div>}
        <form onSubmit={handleSubmit}>
          <FloatingLabel className="mb-3" label="Nome">
            <Form.Control type="text" value={firstName} onChange={(e) => setFirstName(e.target.value)} required />
          </FloatingLabel>
          <FloatingLabel className="mb-3" label="Cognome">
            <Form.Control type="text" value={lastName} onChange={(e) => setLastName(e.target.value)} required />
          </FloatingLabel>
          <FloatingLabel className="mb-3" label="Email">
            <Form.Control type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
          </FloatingLabel>
          <FloatingLabel className="mb-3" label="Username">
            <Form.Control type="text" value={username} onChange={(e) => setUsername(e.target.value)} required />
          </FloatingLabel>
          <FloatingLabel className="mb-3" label="Password">
            <Form.Control type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
          </FloatingLabel>
          <FloatingLabel className="mb-3" label="Telefono Linea Fissa">
            <Form.Control type="text" value={telefonoLineaFissa} onChange={(e) => setTelefonoLineaFissa(e.target.value)} required />
          </FloatingLabel>
          <FloatingLabel className="mb-3" label="CAP">
            <Form.Control type="text" value={cap} onChange={(e) => setCap(e.target.value)} required />
          </FloatingLabel>
          <FloatingLabel className="mb-3" label="Telefono">
            <Form.Control type="text" value={telefono} onChange={(e) => setTelefono(e.target.value)} required />
          </FloatingLabel>
          <FloatingLabel className="mb-3" label="Indirizzo">
            <Form.Control type="text" value={indirizzo} onChange={(e) => setIndirizzo(e.target.value)} required />
          </FloatingLabel>
          <FloatingLabel className="mb-3" label="Città">
            <Form.Control type="text" value={citta} onChange={(e) => setCitta(e.target.value)} required />
          </FloatingLabel>
          <button type="submit" className="btn btn-warning w-100" disabled={loading}>
            {loading ? "Registrazione in corso..." : "Registrati"}
          </button>
        </form>
      </div>
    </div>
  );
};

export default Registrati;
