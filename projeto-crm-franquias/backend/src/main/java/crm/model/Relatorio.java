package crm.model;

import java.util.List;

import crm.model.Cliente;
import crm.model.Franquia;
import crm.model.Venda;
import crm.model.Lead;
import crm.model.Franquia;
import crm.dao.ClienteDAO;
import crm.dao.FranquiaDAO;
import crm.dao.LeadDAO;
import crm.dao.VendaDAO;

public class Relatorio {

    private ClienteDAO clienteDAO = new ClienteDAO();
    private VendaDAO vendaDAO = new VendaDAO();
    private LeadDAO leadDAO = new LeadDAO();
    private FranquiaDAO franquiaDAO = new FranquiaDAO();

    public Relatorio(ClienteDAO clienteDAO, VendaDAO vendaDAO, LeadDAO leadDAO) {
        this.clienteDAO = clienteDAO;
        this.vendaDAO = vendaDAO;
        this.leadDAO = leadDAO;
    }

    //relatorio geral de todas as franquias
    public void relatorioGeral(String dataVenda) {
        double faturamentoTotal = 0;
        int clientesTotal = 0;
        List<Lead> leads = this.leadDAO.listar_leads();
        int leadsTotal = leads.size();

        List<Franquia> franquias = franquiaDAO.listar_franquias();

        for(Franquia f : franquias){
            int idFranquia = f.getId();
            
            List<Cliente> clientesFranquia = this.clienteDAO.listar_clientes_franquia(idFranquia);
            int clientesTotalFranquia = clientesFranquia.size();

            List<Venda> vendasFranquia = this.vendaDAO.listar_vendas_franquia(idFranquia, dataVenda);
            double faturamentoFranquia = 0;

            for(Venda venda : vendasFranquia){
                faturamentoFranquia += venda.getValor();
            }

            faturamentoTotal += faturamentoFranquia;
            clientesTotal += clientesTotalFranquia;

            //mostra dados de cada franquia existente
            System.out.println();
            System.out.println("Franquia " + f.getNome() + ", " + f.getCidade() + ": ");
            System.out.println("Clientes: " + clientesTotalFranquia);
            System.out.println("Faturamento: R$ " + faturamentoFranquia);
        }

        // resumo geral
        System.out.println();
        System.out.println("Total de Clientes: " + clientesTotal);
        System.out.println("Faturamento total em "+dataVenda+": R$ " + faturamentoTotal);
        System.out.println("Leads Castrados: " + leadsTotal);
    }

    //relatorio de uma franquia específica
    public void relatorioFranquia(int id_franquia, String dataVenda) {
        List<Cliente> clientesFranquia = clienteDAO.listar_clientes_franquia(id_franquia);

        int clientesTotal = clientesFranquia.size();

        List<Venda> vendasFranquia = vendaDAO.listar_vendas_franquia(id_franquia, dataVenda);

        double faturamentoFranquia = 0;

        for(Venda venda : vendasFranquia){
            faturamentoFranquia += venda.getValor();
        }

        System.out.println("Total de clientes: " + clientesTotal);
        System.out.println("Faturamento em "+ dataVenda +": R$ " + faturamentoFranquia);
        System.out.println();
    }
}
