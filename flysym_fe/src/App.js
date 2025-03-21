import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import HomePage from "./Component/Home";
import Navbar from "./Component/Mynavbar";
import MyFooter from "./Component/Myfooter";
import Login from "./Component/Login";
import Registrati from "./Component/Registrati";
import Promozioni from "./Component/Promozioni";
import PromozioniLineaFissa from "./Component/PromozioniLineaFissa";
import Profilo from "./Component/DatiUtenti";
import Pagamento from "./Component/Pagamento";
function App() {
  return (
    <Router>
      <>
        <header>
          <Navbar />
        </header>
        <main>
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/login" element={<Login />} />
            <Route path="/registrati" element={<Registrati />} />
            <Route path="/Promozioni" element={<Promozioni />} />
            <Route path="/promozioniLineaFissa" element={<PromozioniLineaFissa />} />
            <Route path="/Profilo" element={<Profilo />} />
            <Route path="/pagamento" element={<Pagamento />} />
          </Routes>
        </main>
        <footer>
          <MyFooter />
        </footer>
      </>
    </Router>
  );
}

export default App;
