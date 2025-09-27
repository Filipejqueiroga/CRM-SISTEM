// src/navigation/AppRoutes.tsx

import React from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';

import PainelTela from '../screens/Franqueador/PainelTela';
import PainelFranqueadoTela from '../screens/Franqueado/PainelFranqueadoTela';
//não sei se vai ter: import LeadFranqueadoTela from '../screens/Franqueado/LeadFranqueadoTela';

const Layout: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  return (
    <div>
      <nav style={{ 
        background: '#333', 
        padding: '1rem', 
        display: 'flex', 
        gap: '1rem' 
      }}>
        <Link to="/franqueador" style={{ color: 'white', textDecoration: 'none' }}>
          Visão do Franqueador
        </Link>
        <Link to="/franqueado" style={{ color: 'white', textDecoration: 'none' }}>
          Visão do Franqueado (Painel)
        </Link>
        <Link to="/franqueado/leads" style={{ color: 'white', textDecoration: 'none' }}>
          Visão do Franqueado (Leads)
        </Link>
      </nav>
      <main>
        {children}
      </main>
    </div>
  );
};

function AppRoutes() {
  return (
    <BrowserRouter>
      <Layout>
        <Routes>
          <Route path="/franqueador" element={<PainelTela />} />

          <Route path="/franqueado" element={<PainelFranqueadoTela />} />

          <Route path="/" element={<PainelTela />} />
        </Routes>
      </Layout>
    </BrowserRouter>
  );
}


export default AppRoutes;