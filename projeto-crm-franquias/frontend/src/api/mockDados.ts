import type { Franquia, Cliente, LeadFranqueadora, Venda, LeadFranquia, Checkin } from '../types';

export const FRANQUIAS_MOCK: Franquia[] = [
  { id: 1, nome: 'Franquia A', cidade: 'São Paulo', status: 'ativa', totalClientes: 230, totalVendas: 15000, leadsEmAberto: 8 },
  { id: 2, nome: 'Franquia B', cidade: 'Recife', status: 'em implantação', totalClientes: 150, totalVendas: 9000, leadsEmAberto: 4 },
];

export const CLIENTES_FRANQUIA_A_MOCK: Cliente[] = [
    { id: 101, nome: 'Maria Silva', telefone: '(11)99999-0000', plano: 'mensal', statusPlano: 'Ativo', historicoCompras: [{id: 1, clienteId: 101, descricao: 'Plano Mensal', valor: 100, data: '01/09/2025'}] },
    { id: 102, nome: 'João Santos', telefone: '(11)98888-0000', plano: 'anual', statusPlano: 'Ativo', historicoCompras: [{id: 2, clienteId: 102, descricao: 'Plano Anual', valor: 900, data: '15/08/2025'}] },
];

export const VENDAS_FRANQUIA_A_MOCK: Venda[] = [
    { id: 1, clienteId: 101, descricao: 'Plano Mensal', valor: 100, data: '01/09/2025' },
    { id: 2, clienteId: 102, descricao: 'Plano Anual', valor: 900, data: '15/08/2025' },
];

export const LEADS_FRANQUEADORA_MOCK: LeadFranqueadora[] = [
  { id: 1, nome: 'João', localidade: 'SP', descricao: 'Quer abrir franquia' },
  { id: 2, nome: 'Ana', localidade: 'MG', descricao: 'Interessada no modelo de negócio' },
];

export const LEADS_FRANQUIA_A_MOCK: LeadFranquia[] = [
    { id: 201, nome: 'Pedro Costa', telefone: '(11) 98888-1111', status: 'Interessado' },
];

export const CHECKINS_FRANQUIA_A_MOCK: Checkin[] = [
    { id: 1, nomeCliente: 'Maria Silva', data: '11/09/2025 às 15h' },
];