package src.main.java.crm.model;

import java.util.ArrayList;
import java.util.List;

import src.test.java.crm.model.Relatorio;

public class Main {
    public static void main(String[] args) {
        List<Franquia> franquias = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        List<Venda> vendas = new ArrayList<>();
        List<Lead> leads = new ArrayList<>();

        franquias.add(new Franquia(001, "Selfit", "Joao Pessoa", "Ativa"));
        franquias.add(new Franquia(002, "Selfit", "Cabedelo", "Ativa"));
        franquias.add(new Franquia(003, "Selfit", "Bayeux", "Ativa"));

        clientes.add(new Cliente(123, "Rebeca", "4002-8922", "Mensal", 002));
        clientes.add(new Cliente(1357, "Pedro", "0800-0800", "Trimestral", 001));
        clientes.add(new Cliente(12345, "Juscelino", "1234-5678", "Mensal", 003));

        vendas.add(new Venda(111, 123, "pagamento plano mensal", 200.00));
        vendas.add(new Venda(222, 1357, "pagamento plano trimestral", 500.00));
        vendas.add(new Venda(333, 12345, "pagamento plano mennsal", 200.00));
        
        leads.add(new Lead("Bruno", "2323-4545", "Interessado"));

        Relatorio relatorio = new Relatorio();

        System.out.println("Relatorio:");
        relatorio.relatorioGeral(franquias, clientes, vendas, leads);

    }
}
