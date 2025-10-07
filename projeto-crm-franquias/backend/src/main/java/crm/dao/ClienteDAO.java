package crm.dao;

import crm.model.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para a entidade Cliente.
 * Esta classe centraliza todas as operações de banco de dados (CRUD)
 * relacionadas aos clientes do sistema.
 *
 * @version 1.1
 * @since 2025-10-05
 */
public class ClienteDAO {

    /**
     * URL de conexão com o banco de dados SQLite.
     * Definida como uma constante para facilitar a manutenção.
     */
    private static final String DATABASE_URL = "jdbc:sqlite:meu_banco.db";

    /**
     * Insere um novo cliente no banco de dados.
     *
     * @param cliente O objeto {@link Cliente} com os dados a serem persistidos.
     */
    public void adicionar_clientes(Cliente cliente) {
        String sql = "INSERT INTO Cliente(nome, numero_telefone, tipo_plano, franquia_id) VALUES (?, ?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getNumeroTelefone());
            ps.setString(3, cliente.getTipoPlano());
            ps.setInt(4, cliente.getIdFranquia());
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar cliente.", e);
        }
    }

    /**
     * Retorna uma lista com todos os clientes cadastrados no banco de dados.
     *
     * @return Uma lista de objetos {@link Cliente}. Se não houver clientes, a lista estará vazia.
     */
    public List<Cliente> listar_clientes() {
        var clientes = new ArrayList<Cliente>();
        String sql = "SELECT * FROM Cliente";

        try (Connection conexao = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String numero_telefone = rs.getString("numero_telefone");
                String tipo_plano = rs.getString("tipo_plano");
                int id_franquia = rs.getInt("franquia_id");

                clientes.add(new Cliente(id, nome, numero_telefone, tipo_plano, id_franquia));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes.", e);
        }
        return clientes;
    }

    /**
     * Retorna uma lista de clientes pertencentes a uma franquia específica.
     *
     * @param idFranquia O ID da franquia para filtrar os clientes.
     * @return Uma lista de objetos {@link Cliente} da franquia especificada.
     */
    public List<Cliente> listar_clientes_franquia(int idFranquia) {
        var clientes = new ArrayList<Cliente>();
        String sql = "SELECT * FROM Cliente WHERE franquia_id = ?";

        try (Connection conexao = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            
            ps.setInt(1, idFranquia);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String numero_telefone = rs.getString("numero_telefone");
                    String tipo_plano = rs.getString("tipo_plano");
                    int id_franquia = rs.getInt("franquia_id");

                    clientes.add(new Cliente(id, nome, numero_telefone, tipo_plano, id_franquia));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes por franquia.", e);
        }
        return clientes;
    }

    /**
     * Exclui um cliente do banco de dados com base no seu ID.
     *
     * @param id_cliente O ID do cliente a ser excluído.
     */
    public void excluir_clientes(Integer id_cliente) {
        String sql = "DELETE FROM Cliente WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, id_cliente);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir cliente.", e);
        }
    }

    /**
     * Atualiza os dados de um cliente existente no banco de dados.
     * O ID do cliente a ser atualizado é obtido do próprio objeto.
     *
     * @param cliente O objeto {@link Cliente} com os dados atualizados.
     */
    public void atualizar_clientes(Cliente cliente) {
        String sql = "UPDATE Cliente SET nome = ?, numero_telefone = ?, tipo_plano = ?, franquia_id = ? WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getNumeroTelefone());
            ps.setString(3, cliente.getTipoPlano());
            ps.setInt(4, cliente.getIdFranquia());
            ps.setInt(5, cliente.getId());
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente.", e);
        }
    }
}