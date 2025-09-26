package crm.testeDao; 

import crm.dao.ClienteDAO;
import crm.model.Cliente;
import java.util.List;

public class TesteClienteDAO {

    public static void main(String[] args) {
        
        // 🚨 IMPORTANTE: Rode a classe DatabaseSetup pelo menos uma vez antes de executar este teste.
        
        ClienteDAO dao = new ClienteDAO();
        int primeiroIdExistente = 1; // Baseado no seu INSERT inicial: Filipe, Pedro, Gabriela...

        System.out.println("----------------------------------------");
        System.out.println("    INICIANDO TESTES DO CLIENTE DAO     ");
        System.out.println("----------------------------------------");

        // ----------------------------------------------------
        // TESTE A: LISTAR (READ) - Verifica dados iniciais
        // ----------------------------------------------------
        System.out.println("\n--- TESTE A: LISTAR CLIENTES INICIAIS ---");
        List<Cliente> clientesIniciais = dao.listar_clientes();
        System.out.println("Total de clientes encontrados: " + clientesIniciais.size());
        clientesIniciais.forEach(c -> 
            System.out.printf("ID: %d | Nome: %s | Plano: %s\n", c.getId(), c.getNome(), c.getTipo_plano())
        );

        // ----------------------------------------------------
        // TESTE B: ADICIONAR (CREATE)
        // ----------------------------------------------------
        System.out.println("\n--- TESTE B: ADICIONAR NOVO CLIENTE ---");
        // ID 0 é um placeholder, o banco deve gerar o ID real.
        // Usamos id_franquia 2 (Franquia B), que foi criada no DatabaseSetup.
        Cliente novoCliente = new Cliente(0, "Novo Teste", "99999-9999", "Mensal", 2);
        dao.adicionar_clientes(novoCliente);
        System.out.println("Novo cliente adicionado: " + novoCliente.getNome());

        // ----------------------------------------------------
        // TESTE C: ATUALIZAR (UPDATE)
        // ----------------------------------------------------
        System.out.println("\n--- TESTE C: ATUALIZAR CLIENTE (ID: " + primeiroIdExistente + ") ---");
        
        // Crio um objeto com o ID existente e as informações para atualizar
        Cliente clienteParaAtualizar = new Cliente(
            primeiroIdExistente, // Usamos o ID do Filipe (ID=1)
            "Filipe **VIP**",    // Nome Alterado
            "83 91111-2222",     // Novo Telefone
            "PLANO PLATINUM",    // Novo Plano
            1
        );
        
        dao.atualizar_clientes(clienteParaAtualizar);
        System.out.println("Cliente ID " + primeiroIdExistente + " atualizado.");


        // ----------------------------------------------------
        // TESTE D: LISTAR (READ) - Verifica alterações e novo registro
        // ----------------------------------------------------
        System.out.println("\n--- TESTE D: LISTAR APÓS ADICIONAR/ATUALIZAR ---");
        dao.listar_clientes().forEach(c -> 
            System.out.printf("ID: %d | Nome: %s | Plano: %s\n", c.getId(), c.getNome(), c.getTipo_plano())
        );


        // ----------------------------------------------------
        // TESTE E: EXCLUIR (DELETE)
        // ----------------------------------------------------
        // Precisamos do ID do cliente que acabamos de inserir para excluí-lo.
        // Assumindo que ele é o último da lista, pegamos o ID dele.
        List<Cliente> clientesAtuais = dao.listar_clientes();
        int idParaExcluir = clientesAtuais.get(clientesAtuais.size() - 1).getId(); 

        System.out.println("\n--- TESTE E: EXCLUIR CLIENTE (ID: " + idParaExcluir + ") ---");
        dao.exluir_clientes(idParaExcluir);
        System.out.println("Cliente ID " + idParaExcluir + " excluído.");
        

        // ----------------------------------------------------
        // TESTE F: LISTAR (READ) - Confirma exclusão
        // ----------------------------------------------------
        System.out.println("\n--- TESTE F: LISTAR APÓS EXCLUSÃO ---");
        System.out.println("Total de clientes remanescentes: " + dao.listar_clientes().size());
        
        System.out.println("----------------------------------------");
    }
}