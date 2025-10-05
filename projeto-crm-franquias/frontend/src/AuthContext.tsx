// src/context/AuthContext.tsx

import React, { createContext, useState, useContext } from 'react';

// Interface para os dados do usuário
interface User {
  id: number;
  nome: string;
  email: string;
  tipo_usuario: 'franqueador' | 'franqueado';
}

// Interface para o valor do contexto
interface AuthContextType {
  user: User | null;
  login: (userData: User, token: string) => void;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const [user, setUser] = useState<User | null>(null);

  const login = (userData: User, token: string) => {
    setUser(userData);
    localStorage.setItem('authToken', token); // Armazena o token no navegador
  };

  const logout = () => {
    setUser(null);
    localStorage.removeItem('authToken'); // Remove o token
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

// --- ADIÇÃO NECESSÁRIA ABAIXO ---
// Hook customizado para facilitar o uso do contexto nos componentes
export const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth deve ser usado dentro de um AuthProvider');
  }
  return context;
};