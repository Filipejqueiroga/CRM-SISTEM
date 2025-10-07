import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { useAuth } from '../AuthContext';

interface ProtectedRouteProps {
  allowedRoles: ('franqueador' | 'franqueado')[];
}

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ allowedRoles }) => {
  const { user } = useAuth();

  if (!user) {
    return <Navigate to="/login" replace />;
  }

  if (!allowedRoles.includes(user.tipo_usuario)) {
    return <Navigate to="/login" />; 
  }

  return <Outlet />;
};

export default ProtectedRoute;