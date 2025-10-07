# Relatório do Projeto - Sistema de CRM para Gestão de Franquias

## 1\. Capa e Identificação

  * **Título do Projeto:** Sistema de CRM para Gestão de Franquias.
  * **Nomes dos Integrantes da Equipe:**
      * Filipe Juscelino Queiroga Lacerda
      * Gabriela Borborema
      * Bruno Freire Ramos
      * Rebeca Nascimento Belmont
      * Pedro Henrique de Queiroz Cavalcanti
  * **Local de Armazenamento do Código Fonte:**
      * https://github.com/Filipejqueiroga/CRM-SISTEM.git

## 2\. Introdução

O presente projeto aborda o desafio de gerenciar uma rede de franquias de forma centralizada e eficiente. Em um modelo de negócio como este, o franqueador precisa ter uma visão macro de todas as unidades, enquanto cada franqueado necessita de autonomia para gerenciar suas operações locais, como clientes, vendas e leads. A ausência de um sistema integrado gera dificuldades no acompanhamento de desempenho, na padronização de processos e na consolidação de dados para tomada de decisão estratégica.

Para solucionar esse problema, foi desenvolvido um sistema de **Gestão de Relacionamento com o Cliente (CRM)** em formato de aplicação de console. O software permite a criação de dois tipos de perfis de usuário, **Franqueador** e **Franqueado**, cada um com menus e permissões específicas, garantindo que o franqueador possa administrar a rede e que o franqueado possa focar na gestão da sua própria unidade.

## 3\. UML do projeto: C:\Users\slash\OneDrive\Documentos\Projects\CRM-SISTEM\projeto-crm-franquias\Uml_FinalProject.png

## 4\. Ferramentas Utilizadas

  * **Linguagem de Programação:** Java JDK 17.
  * **IDE (Ambiente de Desenvolvimento Integrado):** Visual Studio Code
  * **Bibliotecas:** sqlite-jdbc, Scanner, 
  * **Estrutura de Pacotes:** O projeto foi organizado na seguinte estrutura para separar as responsabilidades:
      * `crm.app`: Contém a classe principal da aplicação (App.java).
      * `crm.dao`: Contém as classes de acesso a dados (Data Access Objects) para interagir com o banco de dados. Exemplos: CheckinDAO.java, ClienteDAO.java, FranquiaDAO.java, LeadDAO.java, UsuarioDAO.java, VendaDAO.java, ConnectionFactory.java, DatabaseSetup.java.
      * `crm.model`: Contém as classes de modelo que representam as entidades do sistema. Exemplos: Academia.java, Checkin.java, Cliente.java, Franqueado.java, Franqueador.java, Franquia.java, Lead.java, Main.java, Relatorio.java, Usuario.java, Venda.java.
      * `data`: contém init.sql, que serve para inicializar o banco de dados
      * `frontend`: O frontend do projeto com os arquivos principais em src/, incluindo App.tsx, main.tsx e index.css.

## 5\. Resultados e Considerações Finais

O projeto resultou em uma aplicação de console plenamente funcional que atende aos requisitos propostos, permitindo a gestão completa de uma rede de franquias de forma simulada. O sistema diferencia corretamente as permissões entre Franqueador e Franqueado e permite a realização de operações de CRUD (Criar, Ler, Atualizar, Excluir) para todas as entidades principais, como franquias, clientes, leads e vendas.

**Dificuldades Encontradas:**
*(Sugestão: descreva aqui os desafios que seu grupo enfrentou. Exemplo abaixo)*

> Uma das principais dificuldades foi garantir que os franqueados tivessem acesso apenas aos dados de suas respectivas franquias, o que exigiu um controle cuidadoso na lógica dos métodos DAO e na passagem de parâmetros. Além disso, a gestão do fluxo de menus no console e a validação das entradas do usuário para evitar quebras de execução (como `NumberFormatException`) foram desafios constantes.
Também um grande desafio foi conectar o front com o back por meio de API.


- O desenvolvimento deste projeto foi essencial para solidificar os conceitos de Programação Orientada a Objetos. A aplicação prática de herança e polimorfismo na modelagem dos usuários tornou a teoria muito mais concreta. A utilização do padrão DAO também foi um grande aprendizado, demonstrando a importância de separar responsabilidades para criar um código mais limpo, organizado e escalável. O trabalho em equipe nos ensinou a importância da comunicação e do versionamento de código para o sucesso de um projeto de software.

- Sugerimos a inclusão de uma aula ou material de apoio sobre a conexão de aplicações Java com um banco de dados simples, como o SQLite. Isso permitiria que os dados persistissem entre as execuções do programa, tornando o projeto final ainda mais completo e próximo de uma aplicação real. Também achamos que uma aula sobre a integração de uma API em um projeto (front e back) pode ser um grande diferencial na disciplina.
