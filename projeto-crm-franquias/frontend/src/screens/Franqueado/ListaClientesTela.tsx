import React from 'react';
import styles from './ListaClientesTela.module.css';
import { CLIENTES_FRANQUIA_A_MOCK } from '../../api/mockDados';
import type { Cliente } from '../../types';
import Cartao from '../../components/Cartao';
import LinhaInfo from '../../components/LinhaInfo';

const ListaClientesTela: React.FC = () => {
  const clientes = CLIENTES_FRANQUIA_A_MOCK; 

  return (
    <div className={styles.container}>
      <h1>Meus Clientes</h1>
      <p>Lista detalhada de todos os clientes da sua franquia.</p>

      <div className={styles.lista}>
        {clientes.map((cliente: Cliente) => (
          <Cartao key={cliente.id} titulo={cliente.nome}>
            <LinhaInfo label="Telefone" value={cliente.telefone} />
            <LinhaInfo label="Plano" value={cliente.plano} />
            <LinhaInfo label="Status do Plano" value={cliente.statusPlano} />
            <div className={styles.historico}>
              <h4>Últimas Compras:</h4>
              {cliente.historicoCompras.length > 0 ? (
                <ul>
                  {cliente.historicoCompras.map(compra => (
                    <li key={compra.id}>
                      {compra.data} - {compra.descricao} - {compra.valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}
                    </li>
                  ))}
                </ul>
              ) : (
                <p>Nenhuma compra registrada.</p>
              )}
            </div>
          </Cartao>
        ))}
      </div>
    </div>
  );
};

export default ListaClientesTela;