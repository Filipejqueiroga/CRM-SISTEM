import React from 'react';
import { Link } from 'react-router-dom';
import styles from './PainelFranqueadoTela.module.css';
import { CLIENTES_FRANQUIA_A_MOCK, CHECKINS_FRANQUIA_A_MOCK } from '../../api/mockDados';

const PainelFranqueadoTela: React.FC = () => {
  const resumoFranquia = {
    totalClientes: 120,
    vendasTotais: 7500,
    leads: 8,
  };

  return (
    <div className={styles.container}>
      <h1>Painel do Franqueado: Franquia A</h1>

      <div className={styles.resumo}>
        <h2>Resumo da Franquia</h2>
        <p>Total de Clientes: {resumoFranquia.totalClientes}</p>
        <p>Vendas Totais: {resumoFranquia.vendasTotais.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</p>
        <p>Leads em Aberto: {resumoFranquia.leads}</p>
      </div>

      <div className={styles.secao}>
        <h2>Ações Rápidas</h2>
        <div className={styles.acoes}>
          <Link to="/franqueado/adicionar-cliente" className={styles.botaoAcao}>
            Cadastrar Novo Cliente
          </Link>
          <Link to="/franqueado/clientes" className={styles.botaoAcao}>
            Ver Meus Clientes
          </Link>
          <Link to="/franqueado/checkin" className={styles.botaoAcao}>
            Registrar Check-in
          </Link>
        </div>
      </div>

      <div className={styles.secao}>
        <h2>Lista de Clientes (Recentes)</h2>
        <div className={styles.lista}>
          {CLIENTES_FRANQUIA_A_MOCK.slice(0, 3).map(cliente => (
            <div key={cliente.id} className={styles.itemLista}>
              <span>{cliente.nome}</span>
              <span>Plano: {cliente.plano} ({cliente.statusPlano})</span>
            </div>
          ))}
        </div>
      </div>

      <div className={styles.secao}>
        <h2>Check-ins Recentes</h2>
        <div className={styles.lista}>
            {CHECKINS_FRANQUIA_A_MOCK.slice(0, 3).map(checkin => (
                <div key={checkin.id} className={styles.itemLista}>
                    <span>{checkin.nomeCliente}</span>
                    <span>{checkin.data}</span>
                </div>
            ))}
        </div>
      </div>
    </div>
  );
};

export default PainelFranqueadoTela;