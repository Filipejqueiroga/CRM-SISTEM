package crm.model;

import crm.dao.ClienteDAO;
import crm.dao.FranquiaDAO;
import crm.dao.LeadDAO;
import crm.dao.VendaDAO;

public class Main {
    public static void main(String[] args) {


        Franqueador franqueador = new Franqueador(1, "gabriela@empresa.com", "Gabi123", "senha123", "Gabix");
        Franqueado franqueado = new Franqueado(2, "filipe@email.com", "Filipe", "senha456", "Unidade centro", 101);
        Cliente cliente = new Cliente(10, "Rebeca", "+5583999999999", "Mensal", 101);
        Academia academia = new Academia(101, "Academia Central", "Cabedelo", "Ativa", "Fit", "");

        System.out.println(franqueador);
        System.out.println(franqueado);
        System.out.println(cliente);

        academia.registrarCheckin(cliente);

        Venda venda = new Venda(1, cliente.getId(), "Plano Mensal", 120.0, "01-09-2025");
        System.out.println(venda);

        Lead lead = new Lead("Pedro", "+5583988888888", "Novo");
        System.out.println(lead);

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
