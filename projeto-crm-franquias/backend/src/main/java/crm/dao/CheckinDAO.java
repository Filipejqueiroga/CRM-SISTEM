package crm.dao;

import crm.model.Checkin;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para a entidade Checkin.
 * Esta classe é responsável por todas as operações de banco de dados (CRUD)
 * relacionadas aos registros de check-in.
 *
 * @version 1.0
 * @since 2025-10-05
 */
public class CheckinDAO {

    /**
     * URL de conexão com o banco de dados SQLite.
     * Definida como uma constante para evitar repetição e facilitar a manutenção.
     */
    private static final String DATABASE_URL = "jdbc:sqlite:meu_banco.db";

    /**
     * Insere um novo registro de check-in no banco de dados.
     *
     * @param checkin O objeto {@link Checkin} contendo os dados a serem salvos.
     */
    public void adicionar_checkin(Checkin checkin) {
        String sql = "INSERT INTO Checkin (cliente_id, usuario_id, franquia_id, data, hora) VALUES (?, ?, ?, ?, ?)";

        try (var conexao = DriverManager.getConnection(DATABASE_URL);
             var ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, checkin.getClienteId());
            ps.setInt(2, checkin.getUsuarioId());
            ps.setInt(3, checkin.getFranquiaId());
            ps.setString(4, checkin.getData());
            ps.setString(5, checkin.getHora());
            ps.execute();

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar check-in: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Retorna uma lista com todos os check-ins cadastrados no banco de dados.
     *
     * @return Uma lista de objetos {@link Checkin}. A lista estará vazia se não houver registros.
     */
    public List<Checkin> listar_checkins() {
        var checkins = new ArrayList<Checkin>();
        String sql = "SELECT * FROM Checkin";

        try (var conexao = DriverManager.getConnection(DATABASE_URL);
             var ps = conexao.prepareStatement(sql);
             var rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int cliente_id = rs.getInt("cliente_id");
                int usuario_id = rs.getInt("usuario_id");
                int franquia_id = rs.getInt("franquia_id");
                String data = rs.getString("data");
                String hora = rs.getString("hora");

                checkins.add(new Checkin(id, cliente_id, usuario_id, franquia_id, data, hora));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar check-ins: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return checkins;
    }

    /**
     * Retorna uma lista de check-ins filtrada por uma franquia específica.
     *
     * @param idFranquia O ID da franquia cujos check-ins devem ser retornados.
     * @return Uma lista de objetos {@link Checkin} pertencentes à franquia especificada.
     */
    public List<Checkin> listar_checkins_franquia(int idFranquia) {
        var checkins = new ArrayList<Checkin>();
        String sql = "SELECT * FROM Checkin WHERE franquia_id = ?";

        try (var conexao = DriverManager.getConnection(DATABASE_URL);
             var ps = conexao.prepareStatement(sql)) {
            
            ps.setInt(1, idFranquia);
            try (var rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int cliente_id = rs.getInt("cliente_id");
                    int usuario_id = rs.getInt("usuario_id");
                    int franquia_id = rs.getInt("franquia_id");
                    String data = rs.getString("data");
                    String hora = rs.getString("hora");

                    checkins.add(new Checkin(id, cliente_id, usuario_id, franquia_id, data, hora));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar check-ins por franquia: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return checkins;
    }

    /**
     * Atualiza os dados de um registro de check-in existente no banco de dados.
     * O ID do check-in a ser atualizado é obtido do próprio objeto.
     *
     * @param checkin O objeto {@link Checkin} com os dados atualizados.
     */
    public void atualizar_checkin(Checkin checkin) {
        String sql = "UPDATE Checkin SET cliente_id = ?, usuario_id = ?, franquia_id = ?, data = ?, hora = ? WHERE id = ?";

        try (var conexao = DriverManager.getConnection(DATABASE_URL);
             var ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, checkin.getClienteId());
            ps.setInt(2, checkin.getUsuarioId());
            ps.setInt(3, checkin.getFranquiaId());
            ps.setString(4, checkin.getData());
            ps.setString(5, checkin.getHora());
            ps.setInt(6, checkin.getId());
            ps.execute();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar check-in: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Exclui um registro de check-in do banco de dados com base no seu ID.
     *
     * @param id_checkin O ID do check-in a ser excluído.
     */
    public void excluir_checkin(Integer id_checkin) {
        String sql = "DELETE FROM Checkin WHERE id = ?";

        try (var conexao = DriverManager.getConnection(DATABASE_URL);
             var ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, id_checkin);
            ps.execute();

        } catch (SQLException e) {
            System.err.println("Erro ao excluir check-in: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}