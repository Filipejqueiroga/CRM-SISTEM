import React from 'react';

import { useParams } from 'react-router-dom';
import styles from './DetalhesFranquiaTela.module.css';
import { FRANQUIAS_MOCK, LEADS_FRANQUIA_A_MOCK, CLIENTES_FRANQUIA_A_MOCK } from '../../api/mockDados';
import CaixaResumo from '../../components/CaixaResumo';
import Cartao from '../../components/Cartao';
import ItemLista from '../../components/ItemLista';

const DetalhesFranquiaTela: React.FC = () => {
  
  const { id } = useParams<{ id: string }>();
  const franquia = FRANQUIAS_MOCK.find(f => f.id === parseInt(id || ''));

  const leads = LEADS_FRANQUIA_A_MOCK;
  const clientes = CLIENTES_FRANQUIA_A_MOCK;

  if (!franquia) {
    return <div>Franquia não encontrada!</div>;
  }

  const resumoInfos = [
    { label: 'Receita Total', value: franquia.totalVendas.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' }) },
    { label: 'Total de Clientes', value: franquia.totalClientes },
    { label: 'Leads em Aberto', value: franquia.leadsEmAberto },
  ];

  return (
    <div className={styles.container}>
      <h1>Detalhes da {franquia.nome}</h1>
      <p className={styles.subtitulo}>{franquia.cidade} | Status: <span className={styles.status}>{franquia.status}</span></p>

      <CaixaResumo titulo="Resumo da Unidade" infos={resumoInfos} />

      <div className={styles.colunas}>
        <div className={styles.coluna}>
          <Cartao titulo="Leads">
            {leads.map(lead => (
              <ItemLista key={lead.id}>
                <span>{lead.nome} ({lead.telefone})</span>
                <span className={styles.tag}>{lead.status}</span>
              </ItemLista>
            ))}
          </Cartao>
        </div>
        <div className={styles.coluna}>
          <Cartao titulo="Clientes">
            {clientes.slice(0, 5).map(cliente => (
              <ItemLista key={cliente.id}>
                <span>{cliente.nome}</span>
                <span className={styles.tagPlano}>{cliente.plano}</span>
              </ItemLista>
            ))}
          </Cartao>
        </div>
      </div>
    </div>
  );
};

export default DetalhesFranquiaTela;