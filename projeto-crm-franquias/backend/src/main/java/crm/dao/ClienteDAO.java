package crm.dao;

import java.sql.Connection;
import java.sql.DriverManager;


public class ClienteDAO{
    public static void main(String[] args) throws Exception{
        String url = "jdbc:sqlite:meu_banco.db";

        Connection conexao = DriverManager.getConnection(url);

        conexao.close();

        
    }
}