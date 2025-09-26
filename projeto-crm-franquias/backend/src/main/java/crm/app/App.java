package crm.app;

import crm.dao.FranquiaDAO;
import crm.model.Franquia;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        // Instancia os DAOs que serão usados
        FranquiaDAO franquiaDAO = new FranquiaDAO();
        // Outros DAOs...

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n========= MENU PRINCIPAL =========");
            System.out.println("1. Gerenciar Franquias");
            System.out.println("2. Gerenciar Clientes");
            System.out.println("3. Gerenciar Vendas");
            System.out.println("4. Gerenciar Leads");
            System.out.println("0. Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1:
                    gerenciarFranquias(scanner, franquiaDAO);
                    break;
                case 2:
                    System.out.println("FUNCIONALIDADE AINDA NÃO IMPLEMENTADA.");
                    break;
                case 3:
                    System.out.println("FUNCIONALIDADE AINDA NÃO IMPLEMENTADA.");
                    break;
                case 4:
                     System.out.println("FUNCIONALIDADE AINDA NÃO IMPLEMENTADA.");
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
        scanner.close();
    }

    private static void gerenciarFranquias(Scanner scanner, FranquiaDAO dao) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Franquias ---");
            System.out.println("1. Listar todas");
            System.out.println("2. Adicionar nova");
            System.out.println("3. Atualizar existente");
            System.out.println("4. Excluir");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1: // LISTAR - ATUALIZADO
                    System.out.println("\nListando franquias...");
                    var lista = dao.listar_franquias();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhuma franquia cadastrada.");
                    } else {
                        // Formato de exibição atualizado para incluir o tipo de negócio
                        for (var f : lista) {
                            System.out.printf("ID: %d | Nome: %s | Cidade: %s | Status: %s | Tipo: %s\n",
                                    f.getId(), f.getNome(), f.getCidade(), f.getStatus(), f.getTipo_negocio());
                        }
                    }
                    break;
                case 2: // ADICIONAR - ATUALIZADO
                    System.out.println("\nAdicionando nova franquia...");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Cidade: ");
                    String cidade = scanner.nextLine();
                    System.out.print("Status (Ativa/Inativa): ");
                    String status = scanner.nextLine();
                    System.out.print("Tipo de Negócio (ex: Academia): "); // NOVO CAMPO
                    String tipoNegocio = scanner.nextLine();              // NOVO CAMPO

                    // Construtor atualizado
                    Franquia novaFranquia = new Franquia(0, nome, cidade, status, tipoNegocio);
                    dao.adicionar_franquias(novaFranquia);
                    System.out.println("Franquia adicionada com sucesso!");
                    break;
                case 3: // ATUALIZAR - ATUALIZADO
                    System.out.println("\nAtualizando franquia...");
                    System.out.print("Digite o ID da franquia para atualizar: ");
                    int idAtualizar = Integer.parseInt(scanner.nextLine());
                    System.out.print("Novo Nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Nova Cidade: ");
                    String novaCidade = scanner.nextLine();
                    System.out.print("Novo Status: ");
                    String novoStatus = scanner.nextLine();
                    System.out.print("Novo Tipo de Negócio: "); // NOVO CAMPO
                    String novoTipoNegocio = scanner.nextLine();  // NOVO CAMPO

                    // Construtor atualizado
                    Franquia franquiaAtualizada = new Franquia(idAtualizar, novoNome, novaCidade, novoStatus, novoTipoNegocio);
                    dao.atualizar_franquias(franquiaAtualizada);
                    System.out.println("Franquia atualizada com sucesso!");
                    break;
                case 4: // EXCLUIR
                    System.out.println("\nExcluindo franquia...");
                    System.out.print("Digite o ID da franquia para excluir: ");
                    int idExcluir = Integer.parseInt(scanner.nextLine());
                    dao.excluir_franquias(idExcluir);
                    System.out.println("Franquia excluída com sucesso!");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}