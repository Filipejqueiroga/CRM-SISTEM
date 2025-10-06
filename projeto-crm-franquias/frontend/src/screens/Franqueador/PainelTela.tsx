import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import type { Franquia, LeadFranqueadora } from '../../types';
// 1. Garanta que os serviços mocados estão sendo importados
import { getFranquias, getLeadsFranqueadora } from '../../api/servicos';
import styles from './PainelTela.module.css';
import ItemLista from '../../components/ItemLista';

const PainelTela: React.FC = () => {
  const [franquias, setFranquias] = useState<Franquia[]>([]);
  const [leads, setLeads] = useState<LeadFranqueadora[]>([]);
  // 2. O estado de loading começa como 'true'
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const carregarDados = async () => {
      // 3. Busca os dados dos serviços de simulação
      const [franquiasData, leadsData] = await Promise.all([
        getFranquias(),
        getLeadsFranqueadora(),
      ]);
      
      setFranquias(franquiasData);
      setLeads(leadsData);
      
      // 4. ESSA É A LINHA CRUCIAL: Finaliza o estado de carregamento
      setLoading(false);
    };

    carregarDados();
  }, []); // O array vazio garante que isso rode apenas uma vez

  const receitaTotal = franquias.reduce((acc, f) => acc + f.totalVendas, 0);
  const totalClientes = franquias.reduce((acc, f) => acc + f.totalClientes, 0);

  const handleFranquiaClick = (id: number) => {
    navigate(`/franqueador/franquia/${id}`);
  };

  // 5. Se loading for true, mostra a mensagem. Quando for false, mostra o resto.
  if (loading) {
    return <p style={{ padding: '2rem' }}>Carregando...</p>;
  }

  return (
    <div className={styles.container}>
      <h1>Painel do Franqueador</h1>

      <div className={styles.resumoGeral}>
        <h2>Relatório Geral da Rede</h2>
        <p>Receita Total: {receitaTotal.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</p>
        <p>Total de Clientes: {totalClientes}</p>
        <p>Leads em Aberto: {leads.length}</p>
      </div>

      <div className={styles.lista}>
        <h2>Lista de Franquias</h2>
        {franquias.map(franquia => (
          <ItemLista key={franquia.id} onClick={() => handleFranquiaClick(franquia.id)}>
            <span>{franquia.nome} - {franquia.cidade} ({franquia.status})</span>
            <span>Clientes: {franquia.totalClientes} / Vendas: {franquia.totalVendas.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</span>
          </ItemLista>
        ))}
      </div>
      
      <div className={styles.lista}>
        <h2>Leads (Interessados em Abrir Franquia)</h2>
        {leads.map(lead => (
            <ItemLista key={lead.id}>
                <span>{lead.nome} - {lead.localidade}</span>
                <span>{lead.descricao}</span>
            </ItemLista>
        ))}
      </div>
    </div>
  );
};

export default PainelTela;



/*import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import type { Franquia, LeadFranqueadora } from '../../types';
import { getFranquias, getLeadsFranqueadora } from '../../api/servicos';
import styles from './PainelTela.module.css';
import axios from 'axios';
import ItemLista from '../../components/ItemLista'; // Importe o componente ItemLista

const PainelTela: React.FC = () => {
  const [franquias, setFranquias] = useState<Franquia[]>([]);
  const [leads, setLeads] = useState<LeadFranqueadora[]>([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

useEffect(() => {
  const carregarDados = async () => {
    const token = localStorage.getItem('authToken'); // Pega o token salvo
    try {
      const response = await axios.get('http://localhost:3001/api/franquias', {
        headers: {
          'Authorization': `Bearer ${token}` // Envia o token para a API
        }
      });
      setFranquias(response.data);
    } catch (error) {
      console.error("Erro ao buscar franquias:", error);
      // Aqui você pode redirecionar para o login se o token for inválido
    }
  };
  carregarDados();
}, []); 

  const receitaTotal = franquias.reduce((acc, f) => acc + f.totalVendas, 0);
  const totalClientes = franquias.reduce((acc, f) => acc + f.totalClientes, 0);

  const handleFranquiaClick = (id: number) => {
    navigate(`/franqueador/franquia/${id}`);
  };

  if (loading) return <p>Carregando...</p>;

  return (
    <div className={styles.container}>
      <h1>Painel do Franqueador</h1>

      <div className={styles.resumoGeral}>
        <h2>Relatório Geral da Rede</h2>
        <p>Receita Total: {receitaTotal.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</p>
        <p>Total de Clientes: {totalClientes}</p>
        <p>Leads em Aberto: {leads.length}</p>
      </div>

      <div className={styles.lista}>
        <h2>Lista de Franquias</h2>
        {franquias.map(franquia => (
          // Usando o componente ItemLista com onClick
          <ItemLista key={franquia.id} onClick={() => handleFranquiaClick(franquia.id)}>
            <span>{franquia.nome} - {franquia.cidade} ({franquia.status})</span>
            <span>Clientes: {franquia.totalClientes} / Vendas: {franquia.totalVendas.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</span>
          </ItemLista>
        ))}
      </div>
      
      <div className={styles.lista}>
        <h2>Leads (Interessados em Abrir Franquia)</h2>
        {leads.map(lead => (
            <ItemLista key={lead.id}>
                <span>{lead.nome} - {lead.localidade}</span>
                <span>{lead.descricao}</span>
            </ItemLista>
        ))}
      </div>
    </div>
  );
};

export default PainelTela; */