package crm.app;

import crm.dao.*;
import crm.model.*; // Certifique-se de que a classe Relatorio está neste pacote ou importe-a especificamente
import java.util.Scanner;

public class App {

    // Definindo os DAOs como static para que todos os métodos da classe possam usá-los.
    private static final FranquiaDAO franquiaDAO = new FranquiaDAO();
    private static final ClienteDAO clienteDAO = new ClienteDAO();
    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static final LeadDAO leadDAO = new LeadDAO();
    private static final VendaDAO vendaDAO = new VendaDAO();
    private static final CheckinDAO checkinDAO = new CheckinDAO();

    // <-- MODIFICAÇÃO 1: Instanciar o serviço de Relatório aqui.
    // Isso assume que você tem a classe Relatorio que recebe os DAOs no construtor, como discutimos.
    // Linha corrigida
    private static final Relatorio relatorioService = new Relatorio();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        // O loop principal agora é apenas para Login ou Cadastro.
        while (opcao != 0) {
            System.out.println("\n========= BEM-VINDO AO CRM =========");
            System.out.println("1. Login");
            System.out.println("2. Cadastro");
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
                    realizarLogin(scanner);
                    break;
                case 2:
                    realizarCadastro(scanner);
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

    /**
     * Lida com o processo de login do usuário.
     */
    private static void realizarLogin(Scanner scanner) {
        System.out.println("\n--- TELA DE LOGIN ---");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario usuarioLogado = usuarioDAO.buscarPorEmailESenha(email, senha);

        if (usuarioLogado != null) {
            System.out.println("\nLogin bem-sucedido! Bem-vindo(a), " + usuarioLogado.getNomeUsuario());
            exibirMenuLogado(usuarioLogado, scanner);
        } else {
            System.out.println("\nEmail ou senha incorretos. Tente novamente.");
        }
    }

    /**
     * Lida com o processo de cadastro de um novo usuário.
     */
    private static void realizarCadastro(Scanner scanner) {
        System.out.println("\n--- TELA DE CADASTRO ---");
        System.out.print("Qual o tipo de usuário? (1: Franqueado, 2: Franqueador): ");
        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("Nome de usuário: "); String nomeUsuario = scanner.nextLine();
        System.out.print("Senha: "); String senha = scanner.nextLine();
        
        Usuario novoUsuario = null;

        if (tipo == 1) { // Criando um Franqueado
            System.out.println("\nSelecione a sua franquia da lista abaixo:");
            var listaDeFranquias = franquiaDAO.listar_franquias();

            if (listaDeFranquias.isEmpty()) {
                System.out.println("Nenhuma franquia cadastrada no sistema. Contate o administrador (Franqueador).");
                return;
            }

            for (Franquia f : listaDeFranquias) {
                System.out.printf("%d - %s (%s)\n", f.getId(), f.getNome(), f.getCidade());
            }
            
            System.out.print("\nDigite o ID da franquia escolhida: ");
            int idFranquiaEscolhida = Integer.parseInt(scanner.nextLine());

            boolean idValido = listaDeFranquias.stream().anyMatch(f -> f.getId() == idFranquiaEscolhida);

            if (!idValido) {
                System.out.println("ID de franquia inválido.");
                return;
            }
            
            // Supondo que o construtor do Franqueado aceite (id, email, nomeUsuario, senha, nomeFranquia, idFranquiaLocal)
            // O nome da franquia aqui pode ser um placeholder, pois o que importa é o ID.
            String nomeFranquiaPlaceholder = listaDeFranquias.stream()
                .filter(f -> f.getId() == idFranquiaEscolhida).findFirst().get().getNome();
            
            novoUsuario = new Franqueado(0, email, nomeUsuario, senha, nomeFranquiaPlaceholder, idFranquiaEscolhida);
        
        } else if (tipo == 2) { // Criando um Franqueador
            System.out.print("Nome da Empresa: "); String nomeEmpresa = scanner.nextLine();
            // Linha Corrigida:
            novoUsuario = new Franqueador(0, email, nomeUsuario, senha, nomeEmpresa);
        }

        if (novoUsuario != null) {
            usuarioDAO.adicionar_usuario(novoUsuario);
            System.out.println("Usuário cadastrado com sucesso! Por favor, faça o login.");
        } else {
            System.out.println("Tipo de usuário inválido ou erro no cadastro.");
        }
    }

    /**
     * Exibe o menu correto (Franqueado ou Franqueador) após o login.
     */
    private static void exibirMenuLogado(Usuario usuario, Scanner scanner) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n------------------------------------");
            usuario.exibirMenu(); 
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido.");
                continue;
            }

            if (usuario instanceof Franqueado) {
                tratarOpcaoFranqueado(opcao, (Franqueado) usuario, scanner);
            } else if (usuario instanceof Franqueador) {
                tratarOpcaoFranqueador(opcao, (Franqueador) usuario, scanner);
            }
            
            if (opcao == 0) {
                System.out.println("Fazendo logout...");
            }
        }
    }

    private static void tratarOpcaoFranqueado(int opcao, Franqueado franqueado, Scanner scanner) {
        switch (opcao) {
            case 1: franqueado.exibirPerfil(); break;
            case 2: gerenciarClientes(scanner, clienteDAO); break;
            case 3: gerenciarVendas(scanner, vendaDAO); break;
            case 4: gerenciarLeads(scanner, leadDAO); break;
            case 5: gerenciarCheckins(scanner, checkinDAO); break;
            case 6: exibirMenuRelatorios(franqueado, scanner); break;
            case 0: break;
            default: System.out.println("Opção inválida!"); break;
        }
    }

    // Dentro da classe App.java

    private static void tratarOpcaoFranqueador(int opcao, Franqueador franqueador, Scanner scanner) {
        switch (opcao) {
            case 1: gerenciarUsuarios(scanner, usuarioDAO); break;
            case 2: gerenciarClientes(scanner, clienteDAO); break;
            case 3: gerenciarLeads(scanner, leadDAO); break;
            case 4: exibirMenuRelatorios(franqueador, scanner); break;
            case 5: gerenciarFranquias(scanner, franquiaDAO); break; // <-- ADICIONE ESTA LINHA
            case 0: break;
            default: System.out.println("Opção inválida!"); break;
        }
    }

// SUBSTITUA O MÉTODO ANTIGO POR ESTE CORRIGIDO
    private static void exibirMenuRelatorios(Usuario usuarioLogado, Scanner scanner) {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- Menu de Relatórios ---");

            if (usuarioLogado instanceof Franqueador) {
                System.out.println("1. Relatório Geral de Todas as Franquias");
                System.out.println("2. Relatório de uma Franquia Específica");
            } else if (usuarioLogado instanceof Franqueado) {
                System.out.println("1. Gerar Relatório da Minha Franquia");
            }
            System.out.println("0. Voltar ao Menu Anterior");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido.");
                continue;
            }

            if (usuarioLogado instanceof Franqueador) {
                switch (opcao) {
                    case 1:
                        System.out.print("Digite a data para o faturamento (ex: 06/09/2023): ");
                        String dataGeral = scanner.nextLine();
                        relatorioService.relatorioGeral(dataGeral);
                        break;
                    case 2:
                        System.out.println("\nLista de Franquias Disponíveis:");
                        franquiaDAO.listar_franquias().forEach(f ->
                                System.out.printf("ID: %d - %s\n", f.getId(), f.getNome())
                        );
                        System.out.print("Digite o ID da franquia para o relatório: ");
                        int idFranquia = Integer.parseInt(scanner.nextLine());
                        System.out.print("Digite a data para o faturamento (ex: 06/09/2023): ");
                        String dataEspecifica = scanner.nextLine();
                        relatorioService.relatorioFranquia(idFranquia, dataEspecifica);
                        break;
                    case 0:
                        // Apenas sai do loop, voltando ao menu anterior
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } else if (usuarioLogado instanceof Franqueado) {
                Franqueado franqueado = (Franqueado) usuarioLogado;
                switch (opcao) {
                    case 1:
                        System.out.print("Digite a data para o faturamento (ex: 06/09/2023): ");
                        String dataFranquia = scanner.nextLine();
                        relatorioService.relatorioFranquia(franqueado.getIdFranquiaLocal(), dataFranquia);
                        break;
                    case 0:
                        // Apenas sai do loop, voltando ao menu anterior
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        }
    }


    // ===================================================================================
    //  MÉTODOS DE GERENCIAMENTO (Nenhuma alteração necessária aqui)
    // ===================================================================================

    private static void gerenciarFranquias(Scanner scanner, FranquiaDAO dao) {
            int opcao = -1;
            while (opcao != 0) {
                System.out.println("\n--- Gerenciar Franquias ---");
                System.out.println("1. Listar todas");
                System.out.println("2. Adicionar nova");
                System.out.println("3. Atualizar existente");
                System.out.println("4. Excluir");
                System.out.println("0. Voltar ao Menu Anterior");
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
                        System.out.print("Nome: "); String nome = scanner.nextLine();
                        System.out.print("Cidade: "); String cidade = scanner.nextLine();
                        System.out.print("Status (Ativa/Inativa): "); String status = scanner.nextLine();
                        System.out.print("Tipo de Negócio (ex: Academia): "); String tipoNegocio = scanner.nextLine();
                        Franquia novaFranquia = new Franquia(0, nome, cidade, status, tipoNegocio);
                        dao.adicionar_franquias(novaFranquia);
                        System.out.println("Franquia adicionada com sucesso!");
                        break;

                    case 3:
                        System.out.println("\nAtualizando franquia...");
                        System.out.print("Digite o ID da franquia para atualizar: "); int idAtualizar = Integer.parseInt(scanner.nextLine());
                        System.out.print("Novo Nome: "); String novoNome = scanner.nextLine();
                        System.out.print("Nova Cidade: "); String novaCidade = scanner.nextLine();
                        System.out.print("Novo Status: "); String novoStatus = scanner.nextLine();
                        System.out.print("Novo Tipo de Negócio: "); String novoTipoNegocio = scanner.nextLine();
                        Franquia franquiaAtualizada = new Franquia(idAtualizar, novoNome, novaCidade, novoStatus, novoTipoNegocio);
                        dao.atualizar_franquias(franquiaAtualizada);
                        System.out.println("Franquia atualizada com sucesso!");
                        break;

                    case 4:
                        System.out.println("\nExcluindo franquia...");
                        System.out.print("Digite o ID da franquia para excluir: "); int idExcluir = Integer.parseInt(scanner.nextLine());
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
            System.out.println("0. Voltar ao Menu Anterior");
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
            System.out.println("\n--- Gerenciar Usuários (Franqueados) ---");
            System.out.println("1. Listar todos");
            System.out.println("2. Adicionar novo");
            System.out.println("3. Atualizar existente");
            System.out.println("4. Excluir");
            System.out.println("0. Voltar ao Menu Anterior");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\nListando usuários...");
                    dao.listar_usuarios().forEach(System.out::println);
                    break;
                case 2:
                    // Reutiliza a função de cadastro geral
                    realizarCadastro(scanner);
                    break;
                case 3:
                    System.out.println("\nFuncionalidade de atualização precisa ser adaptada para os diferentes tipos de usuário.");
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
            System.out.println("0. Voltar ao Menu Anterior");
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
            System.out.println("0. Voltar ao Menu Anterior");
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
            System.out.println("0. Voltar ao Menu Anterior");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.println("\nListando check-ins...");
                    dao.listar_checkins().forEach(ch -> System.out.printf(
                            // A linha abaixo foi corrigida para incluir ch.getFranquiaId()
                            "ID: %d | Cliente ID: %d | Usuário ID: %d | Franquia ID: %d | Data: %s | Hora: %s\n",
                            ch.getId(), ch.getClienteId(), ch.getUsuarioId(), ch.getFranquiaId(), ch.getData(), ch.getHora()
                    ));
                    break;
                case 2:
                    System.out.println("\nAdicionando novo check-in...");
                    System.out.print("ID do Cliente: "); int idCliente = Integer.parseInt(scanner.nextLine());
                    System.out.print("ID do Usuário (quem fez o checkin): "); int idUsuario = Integer.parseInt(scanner.nextLine());
                    System.out.print("ID da Franquia (onde foi feito o checkin): "); int idFranquia = Integer.parseInt(scanner.nextLine());
                    System.out.print("Data (AAAA-MM-DD): "); String data = scanner.nextLine();
                    System.out.print("Hora (HH:MM): "); String hora = scanner.nextLine();
                    dao.adicionar_checkin(new Checkin(0, idCliente, idUsuario, idFranquia, data, hora));
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