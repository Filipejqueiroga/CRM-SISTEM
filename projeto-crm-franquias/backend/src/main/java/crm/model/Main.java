package crm.model;

import crm.dao.ClienteDAO;
import crm.dao.FranquiaDAO;
import crm.dao.LeadDAO;
import crm.dao.VendaDAO;

public class Main {
    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        LeadDAO leadDAO = new LeadDAO();
        VendaDAO vendaDAO = new VendaDAO();

        Relatorio relatorio = new Relatorio(clienteDAO, vendaDAO, leadDAO);

        int idFranquiaTeste = 1;

        String dataTeste = "2025-01-10";

        // teste do relatorio de uma franquia especifica
        System.out.println("Relatorio da franquia id "+idFranquiaTeste+ ":");

        relatorio.relatorioFranquia(idFranquiaTeste, dataTeste);

        //teste do relatorio geral
        System.out.println("Relatorio Geral: ");
        relatorio.relatorioGeral(dataTeste);
    }
}
