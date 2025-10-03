package crm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por criar e fornecer conexões com o banco de dados.
 * Centraliza a URL de conexão em um único lugar, facilitando a manutenção.
 */
public class ConnectionFactory {

    // URL de conexão para o banco de dados SQLite.
    // O arquivo 'meu_banco.db' deve estar na raiz do seu projeto.
    private static final String URL = "jdbc:sqlite:meu_banco.db";

    /**
     * Obtém uma nova conexão com o banco de dados.
     * @return um objeto Connection pronto para uso.
     * @throws RuntimeException se a conexão falhar.
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            // Lançar uma RuntimeException é uma boa prática aqui,
            // pois a aplicação não pode funcionar sem o banco de dados.
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }
}