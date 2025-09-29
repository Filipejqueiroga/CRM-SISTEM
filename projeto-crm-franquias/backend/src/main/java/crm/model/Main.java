package crm.model;

import java.util.ArrayList;
import java.util.List;

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
    }
}
