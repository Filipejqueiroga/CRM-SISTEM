import React from 'react';
import { Routes, Route, Link, Navigate, Outlet, useNavigate } from 'react-router-dom';
import { useAuth } from '../AuthContext';

// Importe todas as suas telas
import PainelTela from '../screens/Franqueador/PainelTela';
import PainelFranqueadoTela from '../screens/Franqueado/PainelFranqueadoTela';
import DetalhesFranquiaTela from '../screens/Franqueador/DetalhesFranquiaTela';
import ListaClientesTela from '../screens/Franqueado/ListaClientesTela';
import AdicionarClienteTela from '../screens/Franqueado/AdicionarClienteTela';
import CheckInTela from '../screens/Franqueado/CheckInTela';
import LoginTela from '../screens/Login_Cadastro/LoginTela';
import CadastroTela from '../screens/Login_Cadastro/CadastroTela';
import ProtectedRoute from './ProtectedRoute';

// LAYOUT 1: PARA PÁGINAS PRIVADAS (APÓS O LOGIN)
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
          {/* Mostra links apenas se o usuário for um franqueador */}
          {user?.tipo_usuario === 'franqueador' && (
            <Link to="/franqueador" style={{ color: 'white', textDecoration: 'none', marginRight: '1rem' }}>
              Painel do Franqueador
            </Link>
          )}
          {/* Mostra links apenas se o usuário for um franqueado */}
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
        {/* As rotas filhas (protegidas) serão renderizadas aqui */}
        <Outlet />
      </main>
    </div>
  );
};

// LAYOUT 2: PARA PÁGINAS PÚBLICAS (TELA DE LOGIN E CADASTRO)
const AuthLayout = () => {
  // Este layout não tem nenhuma barra de navegação, apenas renderiza a página.
  return (
    <main>
      <Outlet />
    </main>
  );
};

// COMPONENTE PRINCIPAL DE ROTAS, AGORA REESTRUTURADO
function AppRoutes() {
  return (
    <Routes>
      {/* Grupo 1: Rotas Públicas (usando o AuthLayout) */}
      <Route element={<AuthLayout />}>
        <Route path="/login" element={<LoginTela />} />
        <Route path="/cadastro" element={<CadastroTela />} />
      </Route>

      {/* Grupo 2: Rotas Privadas (protegidas e usando o AppLayout) */}
      <Route element={<ProtectedRoute allowedRoles={['franqueador', 'franqueado']} />}>
        <Route element={<AppLayout />}>
          {/* Rotas do Franqueador */}
          <Route path="/franqueador" element={<PainelTela />} />
          <Route path="/franqueador/franquia/:id" element={<DetalhesFranquiaTela />} />

          {/* Rotas do Franqueado */}
          <Route path="/franqueado" element={<PainelFranqueadoTela />} />
          <Route path="/franqueado/clientes" element={<ListaClientesTela />} />
          <Route path="/franqueado/adicionar-cliente" element={<AdicionarClienteTela />} />
          <Route path="/franqueado/checkin" element={<CheckInTela />} />
        </Route>
      </Route>
      
      {/* Rota padrão: redireciona qualquer caminho não encontrado para o login */}
      <Route path="*" element={<Navigate to="/login" replace />} />
    </Routes>
  );
}

export default AppRoutes;