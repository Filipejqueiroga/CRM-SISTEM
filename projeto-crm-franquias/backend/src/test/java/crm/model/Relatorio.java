package src.test.java.crm.model;
import java.util.List;

import Cliente;
import Franquia;
import Venda;

public class Relatorio {

    public void resumoFranquia(List<Franquia> franquias){
        for(Franquia f : franquias){
            double totalVendas = calcularTotalVendas(f.getVendas());
            int totalClientes = contarClientes(f.getClientes());

            System.out.print("\n");
            System.out.println("Franquia "+ f.getNome()+ ": ");
            System.out.println("Total de vendas: "+totalVendas);
            System.out.println("Total de clientes: "+totalClientes);
            System.out.println("Clientes: ");
            for(Cliente c : f.getClientes()){
                System.out.println(c.getNome());
            }
        }
    }
    public double calcularTotalVendas(List<Venda> vendas){
        double total = 0;

        for(Venda v : vendas){
            total += v.getValor();
        }
        return total;
    }
    public int contarClientes(List<Cliente> clientes){
        return clientes.size();
    }
}
