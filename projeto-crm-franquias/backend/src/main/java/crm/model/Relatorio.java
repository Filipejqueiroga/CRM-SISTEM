package crm.model;

import java.util.List;

import crm.model.Cliente;
import crm.model.Franquia;
import crm.model.Venda;
import crm.model.Lead;
import crm.model.Franquia;
import crm.dao.CheckinDAO;
import crm.dao.ClienteDAO;
import crm.dao.FranquiaDAO;
import crm.dao.LeadDAO;
import crm.dao.VendaDAO;

public class Relatorio {

    private ClienteDAO clienteDAO = new ClienteDAO();
    private VendaDAO vendaDAO = new VendaDAO();
    private LeadDAO leadDAO = new LeadDAO();
    private FranquiaDAO franquiaDAO = new FranquiaDAO();
    private CheckinDAO checkinDAO = new CheckinDAO();

    public Relatorio(){}

    //relatorio geral de todas as franquias
    public void relatorioGeral(String dataVenda) {
        double faturamentoTotal = 0;
        int clientesTotal = 0;
        List<Lead> leads = leadDAO.listar_leads();
        int leadsTotal = leads.size();

        List<Franquia> franquias = franquiaDAO.listar_franquias();
        int franquiasTotal = franquias.size();

        System.out.println("Relatorio Geral: ");
        for(Franquia f : franquias){
            int idFranquia = f.getId();
            
            List<Cliente> clientesFranquia = clienteDAO.listar_clientes_franquia(idFranquia);
            int clientesTotalFranquia = clientesFranquia.size();

            List<Venda> vendasFranquia = vendaDAO.listar_vendas_franquia(idFranquia, dataVenda);
            double faturamentoFranquia = 0;

            for(Venda venda : vendasFranquia){
                faturamentoFranquia += venda.getValor();
            }

            faturamentoTotal += faturamentoFranquia;
            clientesTotal += clientesTotalFranquia;

            //mostra dados de cada franquia existente
            System.out.println();
            System.out.println("Franquia " + f.getNome() + " (" + f.getCidade() + "): ");
            System.out.println(" - Clientes: " + clientesTotalFranquia);
            System.out.println(" - Faturamento: R$ " + faturamentoFranquia);
        }

        // resumo geral
        System.out.println();
        System.out.println("---------------------------");
        System.out.println("Total de franquias: "+ franquiasTotal);
        System.out.println("Total de Clientes: " + clientesTotal);
        System.out.println("Total de leads cadastrados: "+ leadsTotal);
        System.out.println("Faturamento total em "+dataVenda+": R$ " + faturamentoTotal);
        System.out.println();
    }

    //relatorio de uma franquia específica
    public void relatorioFranquia(int id_franquia, String dataVenda) {
        List<Cliente> clientesFranquia = clienteDAO.listar_clientes_franquia(id_franquia);
        List<Checkin> chekinsFranquia = checkinDAO.listar_checkins_franquia(id_franquia);

        Franquia franquia = franquiaDAO.buscar_franquia_por_id(id_franquia);
        String nomeFranquia = (franquia != null) ? franquia.getNome() : "Franquia Desconhecida";

        int clientesTotal = clientesFranquia.size();
        int checkinsTotal = chekinsFranquia.size();

        List<Venda> vendasFranquia = vendaDAO.listar_vendas_franquia(id_franquia, dataVenda);

        double faturamentoFranquia = 0;

        for(Venda venda : vendasFranquia){
            faturamentoFranquia += venda.getValor();
        }

        System.out.println("Relatorio da franquia " + nomeFranquia + ":");
        System.out.println();
        System.out.println(" - Total de clientes: " + clientesTotal);       
        System.out.println(" - Total de checkins: "+ checkinsTotal);
        System.out.println(" - Faturamento em "+ dataVenda +": R$ " + faturamentoFranquia);
    }
}