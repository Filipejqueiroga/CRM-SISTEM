package src.test.java.crm.model;
import java.util.List;

import src.main.java.crm.model.Cliente;
import src.main.java.crm.model.Franquia;
import src.main.java.crm.model.Lead;
import src.main.java.crm.model.Venda;

public class Relatorio {

    //relatorio geral de todas as franquias
    public void relatorioGeral(List<Franquia> franquias, List<Cliente> clientes, List<Venda> vendas, List<Lead> leads) {
        double faturamentoTotal = 0;
        int totalClientes = 0;

        for (Franquia franquia : franquias) {
            int clientesFranquia = 0;
            double faturamentoFranquia = 0;
            
            //conta todos os clientes
            for (Cliente cliente : clientes) {
                if (cliente.getId_franquia() == franquia.getId()) {
                    clientesFranquia++;

                    //somando todas as vendas
                    for (Venda venda : vendas) {
                        if (venda.getId_cliente() == cliente.getId()) {
                            faturamentoFranquia += venda.getValor();
                        }
                    }
                }
            }
            //mostra dados por franquia
            System.out.println("Franquia " + franquia.getNome() + ", " + franquia.getCidade() + ": ");
            System.out.println("Clientes: " + clientesFranquia);
            System.out.println("Faturamento: R$ " + faturamentoFranquia);
            System.out.println();

            totalClientes += clientesFranquia;
            faturamentoTotal += faturamentoFranquia;
        }

        // resumo geral
        System.out.println("Total de Clientes: " + totalClientes);
        System.out.println("Faturamento Total: R$ " + faturamentoTotal);
        System.out.println("Leads Castrados: " + leads.size());
    }

    //relatorio de uma franquia específica
    public void relatorioFranquia(int id_franquia, List<Cliente> clientes, List<Venda> vendas, List<Lead> leads) {
        int clientesFranquia = 0;
        double faturamentoFranquia = 0;

        System.out.println("Clientes:");
        for (Cliente cliente : clientes) {
            //contando os clientes da franquia
            if (cliente.getId_franquia() == id_franquia) {
                clientesFranquia++;

                //calculando o faturamento
                for (Venda venda : vendas) {
                    if (venda.getId_cliente() == cliente.getId()) {// "se o id do cliente que comprou for igual esse id"
                        faturamentoFranquia += venda.getValor();
                    }
                }
                System.out.println("Nome: "+cliente.getNome());
                System.out.println("Id: "+cliente.getId());
                System.out.println("Telefone: "+cliente.getNumero_telefone());
                System.out.println("Plano: "+cliente.getTipo_plano());
            }
        }

        System.out.println("Total de clientes: " + clientesFranquia);
        System.out.println("Faturamento: R$ " + faturamentoFranquia);
        System.out.println("Leads Castrados: " + leads.size());
    }
}
