import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { useAuth } from '../AuthContext';

interface ProtectedRouteProps {
  allowedRoles: ('franqueador' | 'franqueado')[];
}

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ allowedRoles }) => {
  const { user } = useAuth();

  if (!user) {
    // Se não há usuário, redireciona para o login
    return <Navigate to="/login" replace />;
  }

  if (!allowedRoles.includes(user.tipo_usuario)) {
    // Se o tipo de usuário não é permitido, redireciona para uma página de "não autorizado" ou para o login
    return <Navigate to="/login" />; 
  }

  // Se o usuário está logado e tem a permissão, renderiza a página solicitada
  return <Outlet />;
};

export default ProtectedRoute;