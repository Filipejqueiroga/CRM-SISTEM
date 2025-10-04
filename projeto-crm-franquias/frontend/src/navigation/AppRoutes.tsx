import React from 'react';
import { Routes, Route, Link, Navigate, Outlet } from 'react-router-dom';

// Imports de tela
import PainelTela from '../screens/Franqueador/PainelTela';
import PainelFranqueadoTela from '../screens/Franqueado/PainelFranqueadoTela';
import DetalhesFranquiaTela from '../screens/Franqueador/DetalhesFranquiaTela';
import ListaClientesTela from '../screens/Franqueado/ListaClientesTela';
import AdicionarClienteTela from '../screens/Franqueado/AdicionarClienteTela';
import CheckInTela from '../screens/Franqueado/CheckInTela';

const Layout = ({ children }: { children: React.ReactNode }) => {
  return (
    <div>
      <nav style={{ background: '#333', padding: '1rem', display: 'flex', gap: '1rem' }}>
        <Link to="/franqueador" style={{ color: 'white', textDecoration: 'none' }}>Visão do Franqueador</Link>
        <Link to="/franqueado" style={{ color: 'white', textDecoration: 'none' }}>Painel do Franqueado</Link>
      </nav>
      <main>
        {children}
      </main>
    </div>
  );
};

// Layout específico para o Franqueado para navegação interna
const FranqueadoLayout = () => (
  <div>
    {/* Outlet renderiza a rota filha correspondente */}
    <Outlet /> 
  </div>
);

function AppRoutes() {
  return (
      <Layout>
        <Routes>
          <Route path="/" element={<Navigate to="/franqueador" />} />
          
          {/* Rotas do Franqueador */}
          <Route path="/franqueador" element={<PainelTela />} />
          {/* Adicionamos o :id para futuramente tornar a rota dinâmica */}
          <Route path="/franqueador/franquia/:id" element={<DetalhesFranquiaTela />} />

          {/* Rotas do Franqueado (agora aninhadas) */}
          <Route path="/franqueado" element={<FranqueadoLayout />}>
            <Route index element={<PainelFranqueadoTela />} />
            <Route path="clientes" element={<ListaClientesTela />} />
            <Route path="adicionar-cliente" element={<AdicionarClienteTela />} />
            <Route path="checkin" element={<CheckInTela />} />
          </Route>
          
          <Route path="*" element={<h1>Página não encontrada!</h1>} />
        </Routes>
      </Layout>
  );
}

export default AppRoutes;