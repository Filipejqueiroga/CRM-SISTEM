import React from 'react';
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
        <h2>Cadastrar Cliente</h2>
        <form className={styles.formCadastro}>
          <input type="text" placeholder="Nome" />
          <input type="text" placeholder="Telefone" />
          <select>
            <option value="mensal">Plano Mensal</option>
            <option value="semestral">Plano Semestral</option>
            <option value="anual">Plano Anual</option>
          </select>
          <button type="submit">Cadastrar Cliente</button>
        </form>
      </div>

      <div className={styles.secao}>
        <h2>Registrar Venda</h2>
        <form className={styles.formCadastro}>
          <input type="text" placeholder="Nome do Cliente" />
          <input type="text" placeholder="Descrição da Venda" />
          <input type="number" placeholder="Valor (R$)" />
          <button type="submit">Registrar Venda</button>
        </form>
      </div>

      <div className={styles.secao}>
        <h2>Lista de Clientes</h2>
        <div className={styles.lista}>
          {CLIENTES_FRANQUIA_A_MOCK.map(cliente => (
            <div key={cliente.id} className={styles.itemLista}>
              <span>{cliente.nome}</span>
              <span>Plano: {cliente.plano} ({cliente.statusPlano})</span>
              <span>Última Compra: {cliente.historicoCompras[0]?.data || 'N/A'}</span>
            </div>
          ))}
        </div>
      </div>

      <div className={styles.secao}>
        <h2>Check-ins Recentes</h2>
        <div className={styles.lista}>
            {CHECKINS_FRANQUIA_A_MOCK.map(checkin => (
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