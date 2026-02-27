# 🚀 CRM System — Gestão de Franquias (Java CLI)

Sistema de CRM desenvolvido em **Java 17** para gerenciamento de redes de franquias, executado via terminal, com persistência em **SQLite** e controle de acesso baseado em perfil.

---

## 📌 Sobre o Projeto

Este projeto simula a gestão de uma rede de franquias onde:

- O **Franqueador** possui visão global da rede.
- O **Franqueado** possui acesso restrito à sua própria unidade.

O sistema permite:

- 🔐 Controle de acesso por perfil
- 🏢 Gestão de franquias
- 👥 Cadastro e gerenciamento de clientes
- 📊 Gestão de leads
- 💰 Registro de vendas
- 🔄 Operações completas de CRUD
- 💾 Persistência de dados utilizando SQLite

A aplicação é executada totalmente via terminal (CLI).

---

## 🛠 Tecnologias Utilizadas

- Java 17
- SQLite
- JDBC
- Programação Orientada a Objetos
- Padrão DAO (Data Access Object)

---

## 🏗 Estrutura do Projeto

```
projeto-crm-franquias/
│
├── crm/
│   ├── app/        → Classe principal (App.java)
│   ├── model/      → Entidades do sistema
│   ├── dao/        → Camada de acesso a dados (DAO)
│
├── data/
│   └── init.sql    → Script de inicialização do banco
│
├── meu_banco.db    → Banco SQLite
└── Uml_FinalProject.png
```

---

## 🔐 Controle de Permissões

O sistema diferencia dois perfis:

### 👑 Franqueador
- Gerencia todas as franquias
- Visualiza dados globais
- Acessa relatórios consolidados

### 🏪 Franqueado
- Acesso restrito à própria franquia
- Gerencia clientes, leads e vendas locais

O isolamento de dados por franquia foi uma das principais preocupações técnicas do projeto.

---

## ⚙️ Como Executar

### 1️⃣ Clonar o repositório

```bash
git clone https://github.com/Filipejqueiroga/CRM-SISTEM.git
```

### 2️⃣ Abrir na IDE

Abra o projeto na sua IDE (VS Code, IntelliJ ou Eclipse) com Java 17 configurado.

### 3️⃣ Executar a classe principal

Execute:

```
crm.app.App
```

---

## 💾 Banco de Dados

O sistema utiliza **SQLite** para persistência.

- O arquivo `meu_banco.db` já está incluído no repositório.
- Caso necessário, o banco pode ser recriado utilizando o script `init.sql` disponível na pasta `data`.

---

## 🧠 Conceitos Aplicados

- Programação Orientada a Objetos (Herança, Polimorfismo, Encapsulamento)
- Separação de responsabilidades
- Padrão DAO
- Tratamento de exceções
- Controle de acesso por perfil
- Persistência de dados com banco relacional

---

## 🚧 Principais Desafios Técnicos

- Garantir que franqueados acessem apenas dados da própria unidade
- Organizar o fluxo de menus no terminal
- Tratar entradas inválidas para evitar falhas em execução
- Estruturar corretamente a camada de persistência

---

## 👥 Equipe

- Filipe Juscelino Queiroga Lacerda  
- Gabriela Borborema Ramos Formiga  
- Bruno Freire Ramos  
- Rebeca Nascimento Belmont  
- Pedro Henrique de Queiroz Cavalcanti  
