package crm.app;

import crm.dao.*;
import crm.model.*;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        FranquiaDAO franquiaDAO = new FranquiaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        LeadDAO leadDAO = new LeadDAO();
        VendaDAO vendaDAO = new VendaDAO();
        CheckinDAO checkinDAO = new CheckinDAO();

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n========= MENU PRINCIPAL DO CRM =========");
            System.out.println("1. Gerenciar Franquias");
            System.out.println("2. Gerenciar Clientes");
            System.out.println("3. Gerenciar Usuários");
            System.out.println("4. Gerenciar Leads");
            System.out.println("5. Gerenciar Vendas");
            System.out.println("6. Gerenciar Check-ins");
            System.out.println("0. Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1: gerenciarFranquias(scanner, franquiaDAO); break;
                case 2: gerenciarClientes(scanner, clienteDAO); break;
                case 3: gerenciarUsuarios(scanner, usuarioDAO); break;
                case 4: gerenciarLeads(scanner, leadDAO); break;
                case 5: gerenciarVendas(scanner, vendaDAO); break;
                case 6: gerenciarCheckins(scanner, checkinDAO); break;
                case 0: System.out.println("Encerrando o sistema..."); break;
                default: System.out.println("Opção inválida! Tente novamente."); break;
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
                
                case 1: 
                    System.out.println("\nListando franquias...");
                    var lista = dao.listar_franquias();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhuma franquia cadastrada.");
                    } else {
                        for (var f : lista) {
                            System.out.printf("ID: %d | Nome: %s | Cidade: %s | Status: %s | Tipo: %s\n",
                                    f.getId(), f.getNome(), f.getCidade(), f.getStatus(), f.getTipoNegocio());
                        }
                    }
                    break;

                case 2: 
                    System.out.println("\nAdicionando nova franquia...");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Cidade: ");
                    String cidade = scanner.nextLine();
                    System.out.print("Status (Ativa/Inativa): ");
                    String status = scanner.nextLine();
                    System.out.print("Tipo de Negócio (ex: Academia): ");
                    String tipoNegocio = scanner.nextLine();

                    Franquia novaFranquia = new Franquia(0, nome, cidade, status, tipoNegocio);
                    dao.adicionar_franquias(novaFranquia);
                    System.out.println("Franquia adicionada com sucesso!");
                    break;

                case 3: 
                    System.out.println("\nAtualizando franquia...");
                    System.out.print("Digite o ID da franquia para atualizar: ");
                    int idAtualizar = Integer.parseInt(scanner.nextLine());
                    System.out.print("Novo Nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Nova Cidade: ");
                    String novaCidade = scanner.nextLine();
                    System.out.print("Novo Status: ");
                    String novoStatus = scanner.nextLine();
                    System.out.print("Novo Tipo de Negócio: ");
                    String novoTipoNegocio = scanner.nextLine();

                    Franquia franquiaAtualizada = new Franquia(idAtualizar, novoNome, novaCidade, novoStatus, novoTipoNegocio);
                    dao.atualizar_franquias(franquiaAtualizada);
                    System.out.println("Franquia atualizada com sucesso!");
                    break;

                case 4: 
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

    private static void gerenciarClientes(Scanner scanner, ClienteDAO dao) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Clientes ---");
            System.out.println("1. Listar todos");
            System.out.println("2. Adicionar novo");
            System.out.println("3. Atualizar existente");
            System.out.println("4. Excluir");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.println("\nListando clientes...");
                    dao.listar_clientes().forEach(c -> System.out.printf(
                        "ID: %d | Nome: %s | Tel: %s | Plano: %s | Franquia ID: %d\n",
                        c.getId(), c.getNome(), c.getNumeroTelefone(), c.getTipoPlano(), c.getIdFranquia()
                    ));
                    break;
                case 2:
                    System.out.println("\nAdicionando novo cliente...");
                    System.out.print("Nome: "); String nome = scanner.nextLine();
                    System.out.print("Telefone: "); String tel = scanner.nextLine();
                    System.out.print("Plano: "); String plano = scanner.nextLine();
                    System.out.print("ID da Franquia: "); int idFranquia = Integer.parseInt(scanner.nextLine());
                    dao.adicionar_clientes(new Cliente(0, nome, tel, plano, idFranquia));
                    System.out.println("Cliente adicionado!");
                    break;
                case 3:
                    System.out.println("\nAtualizando cliente...");
                    System.out.print("ID do cliente a atualizar: "); int idUpd = Integer.parseInt(scanner.nextLine());
                    System.out.print("Novo Nome: "); String nomeUpd = scanner.nextLine();
                    System.out.print("Novo Telefone: "); String telUpd = scanner.nextLine();
                    System.out.print("Novo Plano: "); String planoUpd = scanner.nextLine();
                    System.out.print("Novo ID da Franquia: "); int idFranquiaUpd = Integer.parseInt(scanner.nextLine());
                    dao.atualizar_clientes(new Cliente(idUpd, nomeUpd, telUpd, planoUpd, idFranquiaUpd));
                    System.out.println("Cliente atualizado!");
                    break;
                case 4:
                    System.out.println("\nExcluindo cliente...");
                    System.out.print("ID do cliente a excluir: "); int idDel = Integer.parseInt(scanner.nextLine());
                    dao.excluir_clientes(idDel);
                    System.out.println("Cliente excluído!");
                    break;
            }
        }
    }

    private static void gerenciarUsuarios(Scanner scanner, UsuarioDAO dao) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Usuários ---");
            System.out.println("1. Listar todos");
            System.out.println("2. Adicionar novo");
            System.out.println("3. Atualizar existente");
            System.out.println("4. Excluir");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.println("\nListando usuários...");
                    dao.listar_usuarios().forEach(u -> System.out.printf(
                        "ID: %d | Usuário: %s | Email: %s | Franquia ID: %d\n",
                        u.getId(), u.getNomeUsuario(), u.getEmail(), u.getIdFranquia()
                    ));
                    break;
                case 2:
                    System.out.println("\nAdicionando novo usuário...");
                    System.out.print("Email: "); String email = scanner.nextLine();
                    System.out.print("Nome de usuário: "); String nomeUsuario = scanner.nextLine();
                    System.out.print("Senha: "); String senha = scanner.nextLine();
                    System.out.print("ID da Franquia: "); int idFranquia = Integer.parseInt(scanner.nextLine());
                    dao.adicionar_usuario(new Usuario(0, email, nomeUsuario, senha, idFranquia));
                    System.out.println("Usuário adicionado!");
                    break;
                case 3:
                    System.out.println("\nAtualizando usuário...");
                    System.out.print("ID do usuário a atualizar: "); int idUpd = Integer.parseInt(scanner.nextLine());
                    System.out.print("Novo Email: "); String emailUpd = scanner.nextLine();
                    System.out.print("Novo Nome de usuário: "); String nomeUsuarioUpd = scanner.nextLine();
                    System.out.print("Nova Senha: "); String senhaUpd = scanner.nextLine();
                    System.out.print("Novo ID da Franquia: "); int idFranquiaUpd = Integer.parseInt(scanner.nextLine());
                    dao.atualizar_usuario(new Usuario(idUpd, emailUpd, nomeUsuarioUpd, senhaUpd, idFranquiaUpd));
                    System.out.println("Usuário atualizado!");
                    break;
                case 4:
                    System.out.println("\nExcluindo usuário...");
                    System.out.print("ID do usuário a excluir: "); int idDel = Integer.parseInt(scanner.nextLine());
                    dao.excluir_usuario(idDel);
                    System.out.println("Usuário excluído!");
                    break;
            }
        }
    }

    private static void gerenciarLeads(Scanner scanner, LeadDAO dao) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Leads ---");
            System.out.println("1. Listar todos");
            System.out.println("2. Adicionar novo");
            System.out.println("3. Atualizar existente");
            System.out.println("4. Excluir");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.println("\nListando leads...");
                    dao.listar_leads().forEach(l -> System.out.printf(
                        "ID: %d | Nome: %s | Telefone: %s | Status: %s\n",
                        l.getId(), l.getNome(), l.getNumeroTelefone(), l.getStatus()
                    ));
                    break;
                case 2:
                    System.out.println("\nAdicionando novo lead...");
                    System.out.print("Nome: "); String nome = scanner.nextLine();
                    System.out.print("Telefone: "); String tel = scanner.nextLine();
                    System.out.print("Status: "); String status = scanner.nextLine();
                    dao.adicionar_lead(new Lead(0, nome, tel, status));
                    System.out.println("Lead adicionado!");
                    break;
                case 3:
                    System.out.println("\nAtualizando lead...");
                    System.out.print("ID do lead a atualizar: "); int idUpd = Integer.parseInt(scanner.nextLine());
                    System.out.print("Novo Nome: "); String nomeUpd = scanner.nextLine();
                    System.out.print("Novo Telefone: "); String telUpd = scanner.nextLine();
                    System.out.print("Novo Status: "); String statusUpd = scanner.nextLine();
                    dao.atualizar_lead(new Lead(idUpd, nomeUpd, telUpd, statusUpd));
                    System.out.println("Lead atualizado!");
                    break;
                case 4:
                    System.out.println("\nExcluindo lead...");
                    System.out.print("ID do lead a excluir: "); int idDel = Integer.parseInt(scanner.nextLine());
                    dao.excluir_lead(idDel);
                    System.out.println("Lead excluído!");
                    break;
            }
        }
    }

    private static void gerenciarVendas(Scanner scanner, VendaDAO dao) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Vendas ---");
            System.out.println("1. Listar todas");
            System.out.println("2. Adicionar nova");
            System.out.println("3. Atualizar existente");
            System.out.println("4. Excluir");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.println("\nListando vendas...");
                    dao.listar_vendas().forEach(v -> System.out.printf(
                        "ID: %d | Cliente ID: %d | Descrição: %s | Valor: %.2f | Data: %s\n",
                        v.getId(), v.getIdCliente(), v.getDescricao(), v.getValor(), v.getData()
                    ));
                    break;
                case 2:
                    System.out.println("\nAdicionando nova venda...");
                    System.out.print("ID do Cliente: "); int idCliente = Integer.parseInt(scanner.nextLine());
                    System.out.print("Descrição: "); String desc = scanner.nextLine();
                    System.out.print("Valor (ex: 199.99): "); double valor = Double.parseDouble(scanner.nextLine());
                    System.out.print("Data (AAAA-MM-DD): "); String data = scanner.nextLine();
                    dao.adicionar_venda(new Venda(0, idCliente, desc, valor, data));
                    System.out.println("Venda adicionada!");
                    break;
                case 3:
                     System.out.println("\nAtualizando venda...");
                    System.out.print("ID da venda a atualizar: "); int idUpd = Integer.parseInt(scanner.nextLine());
                    System.out.print("Novo ID do Cliente: "); int idClienteUpd = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nova Descrição: "); String descUpd = scanner.nextLine();
                    System.out.print("Novo Valor: "); double valorUpd = Double.parseDouble(scanner.nextLine());
                    System.out.print("Nova Data: "); String dataUpd = scanner.nextLine();
                    dao.atualizar_venda(new Venda(idUpd, idClienteUpd, descUpd, valorUpd, dataUpd));
                    System.out.println("Venda atualizada!");
                    break;
                case 4:
                    System.out.println("\nExcluindo venda...");
                    System.out.print("ID da venda a excluir: "); int idDel = Integer.parseInt(scanner.nextLine());
                    dao.excluir_venda(idDel);
                    System.out.println("Venda excluída!");
                    break;
            }
        }
    }

    private static void gerenciarCheckins(Scanner scanner, CheckinDAO dao) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Check-ins ---");
            System.out.println("1. Listar todos");
            System.out.println("2. Adicionar novo");
            System.out.println("3. Excluir");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.println("\nListando check-ins...");
                    dao.listar_checkins().forEach(ch -> System.out.printf(
                        "ID: %d | Cliente ID: %d | Usuário ID: %d | Data: %s | Hora: %s\n",
                        ch.getId(), ch.getClienteId(), ch.getUsuarioId(), ch.getData(), ch.getHora()
                    ));
                    break;
                case 2:
                    System.out.println("\nAdicionando novo check-in...");
                    System.out.print("ID do Cliente: "); int idCliente = Integer.parseInt(scanner.nextLine());
                    System.out.print("ID do Usuário (quem fez o checkin): "); int idUsuario = Integer.parseInt(scanner.nextLine());
                    System.out.print("Data (AAAA-MM-DD): "); String data = scanner.nextLine();
                    System.out.print("Hora (HH:MM): "); String hora = scanner.nextLine();
                    dao.adicionar_checkin(new Checkin(0, idCliente, idUsuario, data, hora));
                    System.out.println("Check-in adicionado!");
                    break;
                case 3:
                    System.out.println("\nExcluindo check-in...");
                    System.out.print("ID do check-in a excluir: "); int idDel = Integer.parseInt(scanner.nextLine());
                    dao.excluir_checkin(idDel);
                    System.out.println("Check-in excluído!");
                    break;
            }
        }
    }
}