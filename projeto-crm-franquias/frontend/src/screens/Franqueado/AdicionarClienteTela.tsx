import React, { useState } from 'react';
import styles from './AdicionarClienteTela.module.css';

const AdicionarClienteTela: React.FC = () => {
  const [nome, setNome] = useState('');
  const [telefone, setTelefone] = useState('');
  const [plano, setPlano] = useState<'mensal' | 'semestral' | 'anual'>('mensal');

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    alert(`Cliente cadastrado!\nNome: ${nome}\nTelefone: ${telefone}\nPlano: ${plano}`);
    setNome('');
    setTelefone('');
    setPlano('mensal');
  };

  return (
    <div className={styles.container}>
      <h1>Cadastrar Novo Cliente</h1>
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
          <label htmlFor="telefone">Telefone</label>
          <input
            type="tel"
            id="telefone"
            value={telefone}
            onChange={(e) => setTelefone(e.target.value)}
            required
          />
        </div>
        <div className={styles.formGroup}>
          <label htmlFor="plano">Plano</label>
          <select id="plano" value={plano} onChange={(e) => setPlano(e.target.value as 'mensal' | 'semestral' | 'anual')}>
            <option value="mensal">Mensal</option>
            <option value="semestral">Semestral</option>
            <option value="anual">Anual</option>
          </select>
        </div>
        <button type="submit" className={styles.botao}>Cadastrar</button>
      </form>
    </div>
  );
};

export default AdicionarClienteTela;