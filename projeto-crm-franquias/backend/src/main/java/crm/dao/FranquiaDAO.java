package crm.dao;

import crm.model.Franquia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para a entidade Franquia.
 * Esta classe é responsável por todas as operações de banco de dados (CRUD)
 * relacionadas às franquias do sistema.
 *
 * @version 1.2
 * @since 2025-10-05
 */
public class FranquiaDAO {

    /**
     * URL de conexão com o banco de dados SQLite.
     */
    private static final String DATABASE_URL = "jdbc:sqlite:meu_banco.db";

    /**
     * Insere uma nova franquia no banco de dados.
     *
     * @param franquia O objeto {@link Franquia} contendo os dados a serem salvos.
     */
    public void adicionar_franquias(Franquia franquia) {
        String sql = "INSERT INTO Franquia(nome, cidade, status, tipo_negocio) VALUES (?, ?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, franquia.getNome());
            ps.setString(2, franquia.getCidade());
            ps.setString(3, franquia.getStatus());
            ps.setString(4, franquia.getTipoNegocio());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar franquia.", e);
        }
    }

    /**
     * Retorna uma lista com todas as franquias cadastradas.
     *
     * @return Uma lista de objetos {@link Franquia}. Se não houver registros, a lista estará vazia.
     */
    public List<Franquia> listar_franquias() {
        var franquias = new ArrayList<Franquia>();
        String sql = "SELECT * FROM Franquia";

        try (Connection conexao = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cidade = rs.getString("cidade");
                String status = rs.getString("status");
                String tipo_negocio = rs.getString("tipo_negocio");

                franquias.add(new Franquia(id, nome, cidade, status, tipo_negocio));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar franquias.", e);
        }
        return franquias;
    }

    /**
     * Exclui uma franquia do banco de dados com base no seu ID.
     *
     * @param id_franquia O ID da franquia a ser excluída.
     */
    public void excluir_franquias(Integer id_franquia) {
        String sql = "DELETE FROM Franquia WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, id_franquia);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir franquia.", e);
        }
    }

    /**
     * Atualiza os dados de uma franquia existente.
     *
     * @param franquia O objeto {@link Franquia} com os novos dados e o ID da franquia a ser atualizada.
     */
    public void atualizar_franquias(Franquia franquia) {
        String sql = "UPDATE Franquia SET nome = ?, cidade = ?, status = ?, tipo_negocio = ? WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, franquia.getNome());
            ps.setString(2, franquia.getCidade());
            ps.setString(3, franquia.getStatus());
            ps.setString(4, franquia.getTipoNegocio());
            ps.setInt(5, franquia.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar franquia.", e);
        }
    }
    
    /**
     * Busca e retorna uma franquia específica pelo seu ID.
     *
     * @param idFranquia O ID da franquia a ser buscada.
     * @return Um objeto {@link Franquia} se encontrado, caso contrário, {@code null}.
     */
    public Franquia buscar_franquia_por_id(int idFranquia) {
        Franquia franquia = null;
        String sql = "SELECT * FROM Franquia WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            
            ps.setInt(1, idFranquia);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String cidade = rs.getString("cidade");
                    String status = rs.getString("status");
                    String tipo_negocio = rs.getString("tipo_negocio"); // Correção: Campo faltante
            
                    franquia = new Franquia(id, nome, cidade, status, tipo_negocio); // Correção: Construtor
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar franquia por ID.", e);
        }
        return franquia;
    }
}