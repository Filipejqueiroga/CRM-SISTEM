package crm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import crm.model.Cliente;


public class ClienteDAO{

    public void adicionar_clientes(Cliente cliente){
        String url = "jdbc:sqlite:meu_banco.db";

        try {
            var conexao = DriverManager.getConnection(url);
            var sql = "INSERT INTO Cliente(nome, numero_telefone, tipo_plano, franquia_id) VALUES (? , ?, ?, ?)";
            var ps = conexao.prepareStatement(sql);

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getNumeroTelefone());
            ps.setString(3, cliente.getTipoPlano());
            ps.setInt(4, cliente.getIdFranquia());            
            ps.execute();

            ps.close();
            conexao.close();
                
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar cliente.", e);
        }
    }
    
    public List<Cliente> listar_clientes(){
        var clientes = new ArrayList<Cliente>();
        String url = "jdbc:sqlite:meu_banco.db";

        try {
        var conexao = DriverManager.getConnection(url);
        var sql = "SELECT * FROM Cliente";
        var ps = conexao.prepareStatement(sql);
        var rs = ps.executeQuery(); // execute Query é tipo uma escada pela tabela

        while (rs.next()){
            var id = rs.getInt("id");
            var nome = rs.getString("nome");
            var numero_telefone = rs.getString("numero_telefone");
            var tipo_plano = rs.getString("tipo_plano");
            var id_franquia = rs.getInt("franquia_id");

             var cliente = new Cliente(id, nome, numero_telefone, tipo_plano, id_franquia);

             clientes.add(cliente);
        }

        ps.close();
        conexao.close();

        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar leads.", e);
        }

        return clientes;

    }

    public List<Cliente> listar_clientes_franquia(int idFranquia){
        var clientes = new ArrayList<Cliente>();
        String url = "jdbc:sqlite:meu_banco.db";

        try {
            var conexao = DriverManager.getConnection(url);
            var sql = "SELECT * FROM Cliente WHERE franquia_id = ?";
            var ps = conexao.prepareStatement(sql);
            ps.setInt(1, idFranquia);
            var rs = ps.executeQuery();

            while (rs.next()) {
                var id = rs.getInt("id");
                var nome = rs.getString("nome");
                var numero_telefone = rs.getString("numero_telefone");
                var tipo_plano = rs.getString("tipo_plano");
                var id_franquia = rs.getInt("franquia_id");

                var cliente = new Cliente(id, nome, numero_telefone, tipo_plano, id_franquia);
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes.", e);
        }
        return clientes;
    }

    public void excluir_clientes(Integer id_cliente){
        String url = "jdbc:sqlite:meu_banco.db";

        try {
            var conexao = DriverManager.getConnection(url);
            var sql = "DELETE FROM Cliente WHERE id = ?";
            var ps = conexao.prepareStatement(sql);

            ps.setInt(1, id_cliente);
            
            ps.execute();

            ps.close();
            conexao.close();
                
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir lead.", e);
        }
    }

    public void atualizar_clientes(Cliente cliente){
        String url = "jdbc:sqlite:meu_banco.db";

        try {
            var conexao = DriverManager.getConnection(url);
            var sql = "UPDATE Cliente SET nome = ?, numero_telefone = ?, tipo_plano = ?, franquia_id = ? WHERE id = ?";
            var ps = conexao.prepareStatement(sql);

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getNumeroTelefone());
            ps.setString(3, cliente.getTipoPlano());
            ps.setInt(4, cliente.getIdFranquia());
            ps.setInt(5, cliente.getId());
            
            ps.execute();

            ps.close();
            conexao.close();
                
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar lead.", e);
        }
    }
    
}