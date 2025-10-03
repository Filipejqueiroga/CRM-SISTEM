package crm.dao;

import crm.model.Franqueado;
import crm.model.Franqueador;
import crm.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de Acesso a Dados (DAO) para a entidade Usuario.
 * É responsável por toda a comunicação com o banco de dados referente a usuários.
 * Lida com a complexidade de instanciar as subclasses corretas (Franqueado ou Franqueador)
 * e buscar dados relacionados de outras tabelas (Franquia, Empresa).
 */
public class UsuarioDAO {

    /**
     * Adiciona um novo usuário (Franqueado ou Franqueador) ao banco de dados.
     */
    public void adicionar_usuario(Usuario usuario) {
        final String sql = "INSERT INTO Usuario(email, nome_usuario, senha, tipo_usuario, empresa_id, franquia_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getNomeUsuario());
            ps.setString(3, usuario.getSenha());
            
            if (usuario instanceof Franqueado) {
                Franqueado franqueado = (Franqueado) usuario;
                ps.setString(4, "franqueado");
                ps.setNull(5, Types.INTEGER); 
                ps.setInt(6, franqueado.getIdFranquiaLocal());
            
            } else if (usuario instanceof Franqueador) {
                // Para simplificar, estamos assumindo que o ID da empresa é 1.
                // Em um sistema real, haveria uma lógica para selecionar a empresa.
                ps.setString(4, "franqueador");
                ps.setInt(5, 1); 
                ps.setNull(6, Types.INTEGER);
            }
            
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar usuário.", e);
        }
    }

    /**
     * Lista todos os usuários do banco, instanciando o tipo de objeto correto.
     */
    public List<Usuario> listar_usuarios() {
        var usuarios = new ArrayList<Usuario>();
        final String sql = "SELECT * FROM Usuario"; 

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String tipoUsuario = rs.getString("tipo_usuario");
                
                if ("franqueado".equals(tipoUsuario)) {
                    usuarios.add(criarFranqueado(rs, conexao));
                } else if ("franqueador".equals(tipoUsuario)) {
                    usuarios.add(criarFranqueador(rs, conexao));
                }
                // Outros tipos como "funcionario" podem ser adicionados aqui
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários.", e);
        }
        return usuarios;
    }

    /**
     * Busca um usuário pelo email e senha para fins de login.
     */
    public Usuario buscarPorEmailESenha(String email, String senha) {
        final String sql = "SELECT * FROM Usuario WHERE email = ? AND senha = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            
            ps.setString(1, email);
            ps.setString(2, senha);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tipoUsuario = rs.getString("tipo_usuario");
                    
                    if ("franqueado".equals(tipoUsuario)) {
                        return criarFranqueado(rs, conexao);
                    } else if ("franqueador".equals(tipoUsuario)) {
                        return criarFranqueador(rs, conexao);
                    }
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário para login.", e);
        }
    }

    /**
     * Atualiza os dados de um usuário no banco.
     */
    public void atualizar_usuario(Usuario usuario) {
        final String sql = "UPDATE Usuario SET email = ?, nome_usuario = ?, senha = ?, tipo_usuario = ?, empresa_id = ?, franquia_id = ? WHERE id = ?";
        
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getNomeUsuario());
            ps.setString(3, usuario.getSenha());

            if (usuario instanceof Franqueado) {
                Franqueado franqueado = (Franqueado) usuario;
                ps.setString(4, "franqueado");
                ps.setNull(5, Types.INTEGER);
                ps.setInt(6, franqueado.getIdFranquiaLocal());
            } else if (usuario instanceof Franqueador) {
                ps.setString(4, "franqueador");
                ps.setInt(5, 1); // Assumindo ID 1 para a empresa
                ps.setNull(6, Types.INTEGER);
            }

            ps.setInt(7, usuario.getId()); // Cláusula WHERE
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário.", e);
        }
    }

    /**
     * Exclui um usuário do banco de dados pelo seu ID.
     */
    public void excluir_usuario(Integer id_usuario) {
        final String sql = "DELETE FROM Usuario WHERE id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, id_usuario);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir usuário.", e);
        }
    }

    // --- MÉTODOS AUXILIARES PARA EVITAR REPETIÇÃO DE CÓDIGO ---

    private Franqueado criarFranqueado(ResultSet rs, Connection conexao) throws SQLException {
        int id = rs.getInt("id");
        String email = rs.getString("email");
        String nomeUsuario = rs.getString("nome_usuario");
        String senha = rs.getString("senha");
        int franquiaId = rs.getInt("franquia_id");

        String nomeFranquia = buscarNomeFranquiaPorId(franquiaId, conexao);

        return new Franqueado(id, email, nomeUsuario, senha, nomeFranquia, franquiaId);
    }

    private Franqueador criarFranqueador(ResultSet rs, Connection conexao) throws SQLException {
        int id = rs.getInt("id");
        String email = rs.getString("email");
        String nomeUsuario = rs.getString("nome_usuario");
        String senha = rs.getString("senha");
        int empresaId = rs.getInt("empresa_id");
        int tipoUsuario = 2; // Tipo interno usado no construtor Java

        String nomeEmpresa = buscarNomeEmpresaPorId(empresaId, conexao);

        return new Franqueador(id, email, nomeUsuario, senha, tipoUsuario, nomeEmpresa);
    }
    
    private String buscarNomeFranquiaPorId(int id, Connection conexao) throws SQLException {
        final String sql = "SELECT nome FROM Franquia WHERE id = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nome");
                }
            }
        }
        return "Franquia não encontrada";
    }

    private String buscarNomeEmpresaPorId(int id, Connection conexao) throws SQLException {
        final String sql = "SELECT nome FROM Empresa WHERE id = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nome");
                }
            }
        }
        return "Empresa não encontrada";
    }
}