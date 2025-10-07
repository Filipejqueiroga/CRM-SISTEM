import React from 'react';
// 1. Importe o hook 'useParams' para ler o ID da URL
import { useParams } from 'react-router-dom';
import styles from './DetalhesFranquiaTela.module.css';
import { FRANQUIAS_MOCK, LEADS_FRANQUIA_A_MOCK, CLIENTES_FRANQUIA_A_MOCK } from '../../api/mockDados';
import CaixaResumo from '../../components/CaixaResumo';
import Cartao from '../../components/Cartao';
import ItemLista from '../../components/ItemLista';

const DetalhesFranquiaTela: React.FC = () => {
  // 2. Use o hook para extrair os parâmetros da URL.
  // O nome 'id' deve corresponder ao que foi definido na sua rota (ex: /franquia/:id)
  const { id } = useParams<{ id: string }>();

  // 3. Encontre a franquia correta no mock usando o ID da URL.
  // Usamos 'parseInt' para converter o ID da URL (que é texto) para número.
  const franquia = FRANQUIAS_MOCK.find(f => f.id === parseInt(id || ''));

  // Por enquanto, os leads e clientes ainda são fixos do mock da Franquia A.
  // Em um cenário real, você também buscaria esses dados com base no ID.
  const leads = LEADS_FRANQUIA_A_MOCK;
  const clientes = CLIENTES_FRANQUIA_A_MOCK;

  // 4. Adicione uma verificação para o caso de a franquia não ser encontrada
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
          <Cartao titulo="Leads (Interessados na Franquia)">
            {/* Se houver leads específicos para outras franquias, a lógica aqui também precisaria mudar */}
            {leads.map(lead => (
              <ItemLista key={lead.id}>
                <span>{lead.nome} ({lead.telefone})</span>
                <span className={styles.tag}>{lead.status}</span>
              </ItemLista>
            ))}
          </Cartao>
        </div>
        <div className={styles.coluna}>
          <Cartao titulo="Amostra de Clientes">
             {/* Se houver clientes específicos para outras franquias, a lógica aqui também precisaria mudar */}
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