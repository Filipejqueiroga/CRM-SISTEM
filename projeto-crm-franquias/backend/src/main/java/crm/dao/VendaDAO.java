package crm.dao;

import crm.model.Venda;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para a entidade Venda.
 * <p>
 * Esta classe gerencia todas as operações de banco de dados (CRUD)
 * relacionadas aos registros de vendas.
 *
 * @version 1.3
 * @since 2025-10-05
 */
public class VendaDAO {
    
    /**
     * URL de conexão com o banco de dados SQLite.
     */
    private static final String DATABASE_URL = "jdbc:sqlite:meu_banco.db";

    /**
     * Adiciona um novo registro de venda ao banco de dados.
     *
     * @param venda O objeto {@link Venda} contendo os dados a serem inseridos.
     */
    public void adicionar_venda(Venda venda) {
        String sql = "INSERT INTO Venda(id_cliente, franquia_id, descricao, valor, data) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, venda.getIdCliente());
            ps.setInt(2, venda.getFranquiaId());
            ps.setString(3, venda.getDescricao());
            ps.setDouble(4, venda.getValor());
            ps.setString(5, venda.getData());
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar venda.", e);
        }
    }
    
    /**
     * Retorna uma lista com todas as vendas cadastradas no banco de dados.
     *
     * @return Uma lista de objetos {@link Venda}. Se não houver registros, a lista estará vazia.
     */
    public List<Venda> listar_vendas() {
        var vendas = new ArrayList<Venda>();
        String sql = "SELECT * FROM Venda"; 

        try (Connection conexao = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int idCliente = rs.getInt("id_cliente");
                int franquiaId = rs.getInt("franquia_id");
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                String data = rs.getString("data");

                // Lembre-se de ter um construtor em Venda.java que aceite todos estes campos.
                vendas.add(new Venda(id, idCliente, franquiaId, descricao, valor, data));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vendas.", e);
        }
        return vendas;
    }
    
    /**
     * Lista as vendas de uma franquia específica em uma data específica.
     *
     * @param idFranquia O ID da franquia para a qual as vendas serão filtradas.
     * @param dataVenda A data específica da venda (ex: 'DD/MM/YYYY').
     * @return Uma lista de objetos {@link Venda} que correspondem aos critérios.
     */
    public List<Venda> listar_vendas_franquia(int idFranquia, String dataVenda) {
        var vendas = new ArrayList<Venda>();
        String sql = "SELECT * FROM Venda WHERE franquia_id = ? AND data = ?";

        try (Connection conexao = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            
            ps.setInt(1, idFranquia);
            ps.setString(2, dataVenda);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int idCliente = rs.getInt("id_cliente");
                    int franquiaId = rs.getInt("franquia_id");
                    String descricao = rs.getString("descricao");
                    double valor = rs.getDouble("valor");
                    String data = rs.getString("data");

                    vendas.add(new Venda(id, idCliente, franquiaId, descricao, valor, data));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vendas da franquia nesta data.", e);
        }
        return vendas;
    }

    /**
     * Exclui uma venda do banco de dados com base no seu ID.
     *
     * @param id_venda O ID da venda a ser excluída.
     */
    public void excluir_venda(Integer id_venda) {
        String sql = "DELETE FROM Venda WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, id_venda);
            ps.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir venda.", e);
        }
    }
    
    /**
     * Atualiza os dados de uma venda existente no banco de dados.
     *
     * @param venda O objeto {@link Venda} com os novos dados e o ID da venda a ser atualizada.
     */
    public void atualizar_venda(Venda venda) {
        String sql = "UPDATE Venda SET id_cliente = ?, franquia_id = ?, descricao = ?, valor = ?, data = ? WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, venda.getIdCliente());
            ps.setInt(2, venda.getFranquiaId());
            ps.setString(3, venda.getDescricao());
            ps.setDouble(4, venda.getValor());
            ps.setString(5, venda.getData());
            ps.setInt(6, venda.getId()); 
            ps.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar venda.", e);
        }
    }
}