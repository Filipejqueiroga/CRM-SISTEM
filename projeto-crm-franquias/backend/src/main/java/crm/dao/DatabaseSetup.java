package crm.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Utilitário para configurar e inicializar o banco de dados da aplicação.
 * <p>
 * Esta classe contém um método {@code main} que deve ser executado uma única vez
 * para criar o arquivo do banco de dados SQLite e popular suas tabelas
 * a partir de um script SQL externo.
 *
 * @version 1.0
 * @since 2025-10-05
 */
public class DatabaseSetup {

    /**
     * Ponto de entrada para a configuração do banco de dados.
     * <p>
     * O método realiza os seguintes passos:
     * 1. Conecta-se ao banco de dados SQLite (criando o arquivo se ele não existir).
     * 2. Lê o arquivo de script {@code data/init.sql}.
     * 3. Processa o script, executando cada comando SQL individualmente.
     * 4. Exibe uma mensagem de sucesso ou de erro fatal.
     *
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        String url = "jdbc:sqlite:meu_banco.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();

                // Lê todas as linhas do arquivo de script SQL.
                List<String> lines = Files.readAllLines(Paths.get("data/init.sql"));
                
                // StringBuilder é usado para montar comandos SQL que podem ocupar múltiplas linhas.
                StringBuilder sql = new StringBuilder();

                for (String line : lines) {
                    // Ignora linhas em branco para não interferir na montagem do comando.
                    if (line.trim().isEmpty()) {
                        continue;
                    }
                    
                    // Adiciona a linha atual ao comando que está sendo montado.
                    sql.append(line);

                    // Se a linha termina com ';', significa que o comando SQL está completo.
                    if (line.trim().endsWith(";")) {
                        // Executa o comando SQL completo.
                        stmt.execute(sql.toString());
                        
                        // Limpa o StringBuilder para começar a montar o próximo comando.
                        sql.setLength(0);
                    }
                }

                System.out.println("Banco de dados criado e populado com sucesso!");
            }
        } catch (SQLException | IOException e) {
            System.err.println("--- ERRO FATAL NA CONFIGURAÇÃO DO BANCO ---");
            e.printStackTrace();
        }
    }
}