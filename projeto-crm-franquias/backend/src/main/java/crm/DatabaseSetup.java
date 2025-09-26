package crm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseSetup {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:meu_banco.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();

                stmt.execute("PRAGMA foreign_keys = ON;");

                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Franquia (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nome TEXT NOT NULL,
                        cidade TEXT NOT NULL,
                        status TEXT NOT NULL
                    );
                """);

                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Cliente (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nome TEXT NOT NULL,
                        numero_telefone TEXT NOT NULL,
                        tipo_plano TEXT NOT NULL,
                        id_franquia INTEGER,
                        FOREIGN KEY(id_franquia) REFERENCES Franquia(id)
                    );
                """);

                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Usuario (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        email TEXT NOT NULL,
                        nome_usuario TEXT NOT NULL,
                        senha TEXT NOT NULL,
                        id_franquia INTEGER,
                        FOREIGN KEY(id_franquia) REFERENCES Franquia(id)
                    );
                """);

                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Lead (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nome TEXT NOT NULL,
                        numero_telefone TEXT NOT NULL,
                        status TEXT NOT NULL
                    );
                """);

                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Venda (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        id_cliente INTEGER,
                        descricao TEXT NOT NULL,
                        valor REAL,
                        FOREIGN KEY(id_cliente) REFERENCES Cliente(id)
                    );
                """);

                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Checkin (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        cliente_id INTEGER,
                        usuario_id INTEGER,
                        data TEXT,
                        hora TEXT,
                        FOREIGN KEY(cliente_id) REFERENCES Cliente(id),
                        FOREIGN KEY(usuario_id) REFERENCES Usuario(id)
                    );
                """);

                stmt.execute("INSERT INTO Franquia (nome, cidade, status) VALUES ('Franquia A', 'Cidade A', 'Ativa'), ('Franquia B', 'Cidade B', 'Ativa');");

                stmt.execute("""
                INSERT INTO Cliente (nome, numero_telefone, tipo_plano, id_franquia) VALUES
                    ('Filipe', '83 8768-3922', 'anual', 1),
                    ('Pedro', '83 8768-3922', 'anual', 1),
                    ('Gabriela', '83 8768-3922', 'anual', 1),
                    ('Bruno', '83 8768-3922', 'semestral', 1),
                    ('Rebeca', '83 8768-3922', 'semestral', 1);
                """);

                stmt.execute("""
                INSERT INTO Usuario (email, nome_usuario, senha) VALUES
                    ('filipe.colgate@gmail.com', 'filipe_ju', 'senha_filipe123'),
                    ('pedro.lindo@gmail.com', 'pedro_h', 'senha_pedro456'),
                    ('gabriela.faraonica@gmail.com', 'gabi_b', 'senha_gabi789'),
                    ('bruno.aloprado@gmail.com', 'bruno_f', 'senha_bruno101'),
                    ('rebeca.dyva@gmail.com', 'rebeca_b', 'senha_rebeca212');
                """);

                stmt.execute("""
              INSERT INTO Venda (id_cliente, descricao, valor) VALUES
                (1, 'Pagamento do plano anual - 2025', 1200.00),
                (2, 'Compra de suplemento (Whey Protein)', 189.90),
                (4, 'Pagamento do plano semestral - 2º Sem/2025', 650.00),
                (3, 'Taxa de avaliação física', 80.00),
                (5, 'Compra de luvas e coqueteleira', 75.50);
                """);

                stmt.execute("""
                    INSERT INTO Lead (nome, numero_telefone, status) VALUES
                    ('belarmino', '83 8768-3922','satisfeito'),
                    ('thais', '83 8768-3922','satisfeito'),
                    ('lincoln', '83 8768-3922','satisfeito');
                """);

                stmt.execute("""
                    INSERT INTO Checkin (cliente_id, usuario_id, data, hora) VALUES
                    (1, 1, '19/09/2025', '15:22'),
                    (2, 1, '19/09/2025', '09:56'),
                    (3, 2, '19/09/2025', '14:29');
                """);

                System.out.println("Banco criado e populado com sucesso!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
