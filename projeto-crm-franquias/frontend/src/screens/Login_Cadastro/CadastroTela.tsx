import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import styles from './CadastroTela.module.css';

const CadastroTela: React.FC = () => {
  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [tipoUsuario, setTipoUsuario] = useState<'franqueado' | 'franqueador'>('franqueado');
  const navigate = useNavigate();

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    // substituir por chamada API: POST /api/cadastro
    console.log('Tentativa de cadastro com:', { nome, email, senha, tipoUsuario });
    
    alert('Cadastro realizado com sucesso! Você será redirecionado para o login.');
    navigate('/login');
  };

  return (
    <div className={styles.container}>
      <div className={styles.formContainer}>
        <h1>Criar Conta</h1>
        <form className={styles.form} onSubmit={handleSubmit}>
          <div className={styles.formGroup}>
            <label htmlFor="nome">Nome Completo</label>
            <input
              type="text"
              id="nome"
              value={nome}
              onChange={(e) => setNome(e.target.value)}
              required
            />
          </div>
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

          <div className={styles.formGroup}>
            <label htmlFor="tipoUsuario">Eu sou</label>
            <select 
              id="tipoUsuario" 
              value={tipoUsuario} 
              onChange={(e) => setTipoUsuario(e.target.value as 'franqueado' | 'franqueador')}
            >
              <option value="franqueado">Franqueado</option>
              <option value="franqueador">Franqueador</option>
            </select>
          </div>
          <button type="submit" className={styles.botao}>Cadastrar</button>
        </form>
        <p className={styles.linkLogin}>
          Já tem uma conta? <Link to="/login">Faça o login</Link>
        </p>
      </div>
    </div>
  );
};

export default CadastroTela;