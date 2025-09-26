package crm.dao;

import crm.model.Venda;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {
    
    private static final String URL = "jdbc:sqlite:meu_banco.db";

    public void adicionar_venda(Venda venda) {
        String sql = "INSERT INTO Venda(id_cliente, descricao, valor, data) VALUES (?, ?, ?, ?)";

        try {
            var conexao = DriverManager.getConnection(URL);
            var ps = conexao.prepareStatement(sql);

            ps.setInt(1, venda.getId_cliente());
            ps.setString(2, venda.getDescricao());
            ps.setDouble(3, venda.getValor());
            ps.setString(4, venda.getData());
            
            ps.execute();

            ps.close();
            conexao.close();
                
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar venda.", e);
        }
    }
    
    public List<Venda> listar_vendas() {
        var vendas = new ArrayList<Venda>();
        String sql = "SELECT id, id_cliente, descricao, valor, data FROM Venda"; 

        try {
            var conexao = DriverManager.getConnection(URL);
            var ps = conexao.prepareStatement(sql);
            var rs = ps.executeQuery();

            while (rs.next()) {
                var id = rs.getInt("id");
                var idCliente = rs.getInt("id_cliente");
                var descricao = rs.getString("descricao");
                var valor = rs.getDouble("valor");
                var data = rs.getString("data");

                var venda = new Venda(id, idCliente, descricao, valor, data);
                vendas.add(venda);
            }

            rs.close(); 
            ps.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vendas.", e);
        }
        return vendas;
    }
    
    public void excluir_venda(Integer id_venda) {
        String sql = "DELETE FROM Venda WHERE id = ?";

        try {
            var conexao = DriverManager.getConnection(URL);
            var ps = conexao.prepareStatement(sql);

            ps.setInt(1, id_venda);
            ps.execute();

            ps.close();
            conexao.close();
                
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir venda.", e);
        }
    }
    
    public void atualizar_venda(Venda venda) {
        String sql = "UPDATE Venda SET id_cliente = ?, descricao = ?, valor = ?, data = ? WHERE id = ?";

        try {
            var conexao = DriverManager.getConnection(URL);
            var ps = conexao.prepareStatement(sql);

            ps.setInt(1, venda.getId_cliente());
            ps.setString(2, venda.getDescricao());
            ps.setDouble(3, venda.getValor());
            ps.setString(4, venda.getData());
            ps.setInt(5, venda.getId()); 
            
            ps.execute();

            ps.close();
            conexao.close();
                
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar venda.", e);
        }
    }
}