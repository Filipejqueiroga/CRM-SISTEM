import React from 'react';
import { Routes, Route, Link, Navigate, Outlet, useNavigate } from 'react-router-dom';
import { useAuth } from '../AuthContext';

import PainelTela from '../screens/Franqueador/PainelTela';
import PainelFranqueadoTela from '../screens/Franqueado/PainelFranqueadoTela';
import DetalhesFranquiaTela from '../screens/Franqueador/DetalhesFranquiaTela';
import ListaClientesTela from '../screens/Franqueado/ListaClientesTela';
import AdicionarClienteTela from '../screens/Franqueado/AdicionarClienteTela';
import CheckInTela from '../screens/Franqueado/CheckInTela';
import LoginTela from '../screens/Login_Cadastro/LoginTela';
import CadastroTela from '../screens/Login_Cadastro/CadastroTela';
import ProtectedRoute from './ProtectedRoute';

// Layout de página privada
const AppLayout = () => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <div>
      <nav style={{ background: '#333', padding: '1rem', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <div>
          {user?.tipo_usuario === 'franqueador' && (
            <Link to="/franqueador" style={{ color: 'white', textDecoration: 'none', marginRight: '1rem' }}>
              Painel do Franqueador
            </Link>
          )}

          {user?.tipo_usuario === 'franqueado' && (
            <>
              <Link to="/franqueado" style={{ color: 'white', textDecoration: 'none', marginRight: '1rem' }}>
                Meu Painel
              </Link>
              <Link to="/franqueado/clientes" style={{ color: 'white', textDecoration: 'none', marginRight: '1rem' }}>
                Meus Clientes
              </Link>
              <Link to="/franqueado/adicionar-cliente" style={{ color: 'white', textDecoration: 'none', marginRight: '1rem' }}>
                Novo Cliente
              </Link>
              <Link to="/franqueado/checkin" style={{ color: 'white', textDecoration: 'none', marginRight: '1rem' }}>
                Check-in
              </Link>
            </>
          )}
        </div>
        <button onClick={handleLogout} style={{ background: '#d9534f', color: 'white', border: 'none', padding: '0.5rem 1rem', borderRadius: '4px', cursor: 'pointer', fontWeight: 'bold' }}>
          Sair
        </button>
      </nav>
      <main>
        <Outlet />
      </main>
    </div>
  );
};

const AuthLayout = () => {
  // Este layout não tem nenhuma barra de navegação, apenas renderiza a página.
  return (
    <main>
      <Outlet />
    </main>
  );
};


function AppRoutes() {
  return (
    <Routes>
      //rotas públicas
      <Route element={<AuthLayout />}>
        <Route path="/login" element={<LoginTela />} />
        <Route path="/cadastro" element={<CadastroTela />} />
      </Route>

      // rotas protegidas
      <Route element={<ProtectedRoute allowedRoles={['franqueador', 'franqueado']} />}>
        <Route element={<AppLayout />}>

          <Route path="/franqueador" element={<PainelTela />} />
          <Route path="/franqueador/franquia/:id" element={<DetalhesFranquiaTela />} />

          <Route path="/franqueado" element={<PainelFranqueadoTela />} />
          <Route path="/franqueado/clientes" element={<ListaClientesTela />} />
          <Route path="/franqueado/adicionar-cliente" element={<AdicionarClienteTela />} />
          <Route path="/franqueado/checkin" element={<CheckInTela />} />
        </Route>
      </Route>
      
      <Route path="*" element={<Navigate to="/login" replace />} />
    </Routes>
  );
}

export default AppRoutes;