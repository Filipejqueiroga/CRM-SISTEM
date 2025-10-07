package crm.dao;

import crm.model.Lead;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para a entidade Lead.
 * <p>
 * Esta classe gerencia todas as operações de banco de dados (CRUD)
 * para os leads (clientes em potencial).
 *
 * @version 1.2
 * @since 2025-10-05
 */
public class LeadDAO {

    /**
     * URL de conexão com o banco de dados SQLite.
     */
    private static final String DATABASE_URL = "jdbc:sqlite:meu_banco.db";

    /**
     * Adiciona um novo lead ao banco de dados.
     *
     * @param lead O objeto {@link Lead} contendo os dados a serem inseridos.
     */
    public void adicionar_lead(Lead lead) {
        String sql = "INSERT INTO Lead(nome, numero_telefone, status, franquia_id) VALUES (?, ?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, lead.getNome());
            ps.setString(2, lead.getNumeroTelefone());
            ps.setString(3, lead.getStatus());
            ps.setInt(4, lead.getFranquiaId());
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar lead.", e);
        }
    }

    /**
     * Retorna uma lista com todos os leads cadastrados no banco de dados.
     *
     * @return Uma lista de objetos {@link Lead}. Se não houver registros, a lista estará vazia.
     */
    public List<Lead> listar_leads() {
        var leads = new ArrayList<Lead>();
        String sql = "SELECT * FROM Lead";

        try (Connection conexao = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String numero_telefone = rs.getString("numero_telefone");
                String status = rs.getString("status");
                int franquiaId = rs.getInt("franquia_id");

                // Lembre-se de ter um construtor em Lead.java que aceite todos estes campos.
                leads.add(new Lead(id, nome, numero_telefone, status, franquiaId));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar leads.", e);
        }
        return leads;
    }

    /**
     * Exclui um lead do banco de dados com base no seu ID.
     *
     * @param id_lead O ID do lead a ser excluído.
     */
    public void excluir_lead(Integer id_lead) {
        String sql = "DELETE FROM Lead WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, id_lead);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir lead.", e);
        }
    }

    /**
     * Atualiza os dados de um lead existente no banco de dados.
     *
     * @param lead O objeto {@link Lead} com os novos dados e o ID do lead a ser atualizado.
     */
    public void atualizar_lead(Lead lead) {
        String sql = "UPDATE Lead SET nome = ?, numero_telefone = ?, status = ?, franquia_id = ? WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, lead.getNome());
            ps.setString(2, lead.getNumeroTelefone());
            ps.setString(3, lead.getStatus());
            ps.setInt(4, lead.getFranquiaId());
            ps.setInt(5, lead.getId());
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar lead.", e);
        }
    }
}