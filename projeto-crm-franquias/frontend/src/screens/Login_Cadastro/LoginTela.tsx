import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import styles from './LoginTela.module.css';
import { useAuth } from '../../AuthContext';


const LoginTela: React.FC = () => {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const { login } = useAuth(); // Você usará isso quando a API estiver pronta
  const navigate = useNavigate();

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    // --- LÓGICA DE LOGIN (SIMULADA COM API) ---
    // const { user, token } = await api.post('/login', { email, senha });
    // login(user, token); // Salva no Contexto

    if (email === 'admin@gmail' && senha === '123') {
      const userSimulado = { id: 1, nome: 'Admin Franqueador', email: email, tipo_usuario: 'franqueador' as const };
      login(userSimulado, 'fake-token-franqueador');
      
      alert('Login como Franqueador bem-sucedido!');
      navigate('/franqueador');

    } else if (email === 'franquia@gmail' && senha === '123') {
      const userSimulado = { id: 2, nome: 'Usuário Franqueado', email: email, tipo_usuario: 'franqueado' as const };
      login(userSimulado, 'fake-token-franqueado');

      alert('Login como Franqueado bem-sucedido!');
      navigate('/franqueado');

    } else {
      alert('Email ou senha inválidos!');
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.formContainer}>
        <h1>Entrar no Sistema</h1>
        <form className={styles.form} onSubmit={handleSubmit}>
          <div className={styles.formGroup}>
            <label htmlFor="email">Email</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div className={styles.formGroup}>
            <label htmlFor="senha">Senha</label>
            <input
              type="password"
              id="senha"
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
              required
            />
          </div>
          <button type="submit" className={styles.botao}>Entrar</button>
        </form>
        <p className={styles.linkCadastro}>
          Não tem uma conta? <Link to="/cadastro">Cadastre-se</Link>
        </p>
      </div>
    </div>
  );
};

export default LoginTela;