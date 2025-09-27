export interface Franquia {
  id: number;
  nome: string;
  cidade: string;
  status: 'ativa' | 'em implantação';
  totalClientes: number;
  totalVendas: number;
  leadsEmAberto: number;
}

export interface Cliente {
  id: number;
  nome: string;
  telefone: string;
  plano: 'mensal' | 'semestral' | 'anual';
  historicoCompras: Venda[];
  statusPlano: 'Ativo' | 'Inativo';
}

export interface Venda {
  id: number;
  clienteId: number;
  descricao: string;
  valor: number;
  data: string;
}

export interface LeadFranqueadora {
  id: number;
  nome: string;
  localidade: string;
  descricao: string;
}

export interface LeadFranquia {
  id: number;
  nome: string;
  telefone: string;
  status: 'Interessado' | 'Contatado';
}

export interface Checkin {
    id: number;
    nomeCliente: string;
    data: string;
}