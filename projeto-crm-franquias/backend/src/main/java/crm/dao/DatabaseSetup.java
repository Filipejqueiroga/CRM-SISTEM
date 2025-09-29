package crm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DatabaseSetup {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:meu_banco.db";
        
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                
                List<String> lines = Files.readAllLines(Paths.get("data/init.sql"));
                StringBuilder sql = new StringBuilder();
                for (String line : lines) {
                    if (line.trim().isEmpty()) {
                        continue;
                    }
                    sql.append(line);
                    if (line.trim().endsWith(";")) {
                        stmt.execute(sql.toString());
                        sql.setLength(0);
                    }
                }

                System.out.println("Banco criado e populado com sucesso!");
            }
        } catch (Exception e) {
            System.err.println("--- ERRO FATAL NA CONFIGURAÇÃO DO BANCO ---");
            e.printStackTrace();
        }
    }
}