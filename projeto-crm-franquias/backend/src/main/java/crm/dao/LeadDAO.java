package crm.dao;

import crm.model.Lead;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeadDAO {
    
    private static final String URL = "jdbc:sqlite:meu_banco.db";

    public void adicionar_lead(Lead lead) {
        String sql = "INSERT INTO Lead(nome, numero_telefone, status) VALUES (?, ?, ?)";

        try {
            var conexao = DriverManager.getConnection(URL);
            var ps = conexao.prepareStatement(sql);

            ps.setString(1, lead.getNome());
            ps.setString(2, lead.getNumero_telefone());
            ps.setString(3, lead.getStatus());
            
            ps.execute();

            ps.close();
            conexao.close();
                
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar lead.", e);
        }
    }
    
    public List<Lead> listar_leads() {
        var leads = new ArrayList<Lead>();
        String sql = "SELECT id, nome, numero_telefone, status FROM Lead";

        try {
            var conexao = DriverManager.getConnection(URL);
            var ps = conexao.prepareStatement(sql);
            var rs = ps.executeQuery();

            while (rs.next()) {
                var id = rs.getInt("id");
                var nome = rs.getString("nome");
                var numero_telefone = rs.getString("numero_telefone");
                var status = rs.getString("status");

                var lead = new Lead(id, nome, numero_telefone, status);
                leads.add(lead);
            }

            rs.close(); 
            ps.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar leads.", e);
        }
        return leads;
    }
    
    public void excluir_lead(Integer id_lead) {
        String sql = "DELETE FROM Lead WHERE id = ?";

        try {
            var conexao = DriverManager.getConnection(URL);
            var ps = conexao.prepareStatement(sql);

            ps.setInt(1, id_lead);
            ps.execute();

            ps.close();
            conexao.close();
                
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir lead.", e);
        }
    }
    
    public void atualizar_lead(Lead lead) {
        String sql = "UPDATE Lead SET nome = ?, numero_telefone = ?, status = ? WHERE id = ?";

        try {
            var conexao = DriverManager.getConnection(URL);
            var ps = conexao.prepareStatement(sql);

            ps.setString(1, lead.getNome());
            ps.setString(2, lead.getNumero_telefone());
            ps.setString(3, lead.getStatus());
            ps.setInt(4, lead.getId());
            
            ps.execute();

            ps.close();
            conexao.close();
                
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar lead.", e);
        }
    }
}