package crm.dao;

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
                        nome TEXT NOT NULL
                    );
                """);

                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Cliente (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nome TEXT NOT NULL,
                        franquia_id INTEGER,
                        FOREIGN KEY(franquia_id) REFERENCES Franquia(id)
                    );
                """);

                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Usuario (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nome TEXT NOT NULL,
                        franquia_id INTEGER,
                        FOREIGN KEY(franquia_id) REFERENCES Franquia(id)
                    );
                """);

                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Lead (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nome TEXT NOT NULL,
                        cliente_id INTEGER,
                        FOREIGN KEY(cliente_id) REFERENCES Cliente(id)
                    );
                """);

                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Venda (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        cliente_id INTEGER,
                        valor REAL,
                        FOREIGN KEY(cliente_id) REFERENCES Cliente(id)
                    );
                """);

                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Checkin (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        cliente_id INTEGER,
                        usuario_id INTEGER,
                        data TEXT,
                        FOREIGN KEY(cliente_id) REFERENCES Cliente(id),
                        FOREIGN KEY(usuario_id) REFERENCES Usuario(id)
                    );
                """);

                stmt.execute("INSERT INTO Franquia (nome) VALUES ('Franquia A'), ('Franquia B');");

                stmt.execute("""
                    INSERT INTO Cliente (nome, franquia_id) VALUES
                    ('Cliente 1', 1),
                    ('Cliente 2', 1),
                    ('Cliente 3', 2),
                    ('Cliente 4', 2),
                    ('Cliente 5', 1);
                """);

                stmt.execute("""
                    INSERT INTO Usuario (nome, franquia_id) VALUES
                    ('Usuario 1', 1),
                    ('Usuario 2', 2);
                """);

                stmt.execute("""
                    INSERT INTO Venda (cliente_id, valor) VALUES
                    (1, 100.0),
                    (2, 200.5),
                    (3, 150.75);
                """);

                stmt.execute("""
                    INSERT INTO Lead (nome, cliente_id) VALUES
                    ('Lead X', 1),
                    ('Lead Y', 2),
                    ('Lead Z', 3);
                """);

                stmt.execute("""
                    INSERT INTO Checkin (cliente_id, usuario_id, data) VALUES
                    (1, 1, '2025-09-19'),
                    (2, 1, '2025-09-18'),
                    (3, 2, '2025-09-17');
                """);

                System.out.println("Banco criado e populado com sucesso!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
