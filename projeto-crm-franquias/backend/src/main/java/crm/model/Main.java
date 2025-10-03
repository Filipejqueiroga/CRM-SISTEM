package crm.model;

import crm.dao.ClienteDAO;
import crm.dao.FranquiaDAO;
import crm.dao.LeadDAO;
import crm.dao.VendaDAO;
import crm.dao.CheckinDAO;

public class Main {
    public static void main(String[] args) {

        Relatorio relatorio = new Relatorio();

        ClienteDAO clienteDAO = new ClienteDAO();
        LeadDAO leadDAO = new LeadDAO();
        VendaDAO vendaDAO = new VendaDAO();

        int idFranquiaTeste = 2;

        String dataTeste = "03/06/25";

        // teste do relatorio de uma franquia especifica
        System.out.println("Relatorio da franquia "+idFranquiaTeste+ ":");

        relatorio.relatorioFranquia(idFranquiaTeste, dataTeste);

        //teste do relatorio geral
        System.out.println("Relatorio Geral: ");
        relatorio.relatorioGeral(dataTeste);

    }
}
