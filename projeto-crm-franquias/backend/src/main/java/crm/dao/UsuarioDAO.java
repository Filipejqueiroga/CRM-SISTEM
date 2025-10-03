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
 * Lida com a complexidade de instanciar as subclasses corretas (Franqueado ou Franqueador).
 */
public class UsuarioDAO {

    /**
     * Adiciona um novo usuário (Franqueado ou Franqueador) ao banco de dados.
     * Utiliza 'instanceof' para determinar o tipo de usuário e salvar os dados específicos.
     */
    public void adicionar_usuario(Usuario usuario) {
        final String sql = "INSERT INTO Usuario(email, nome_usuario, senha, tipo_usuario, id_franquia, nome_empresa, nome_franquia) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            // Dados comuns a todos os usuários
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getNomeUsuario());
            ps.setString(3, usuario.getSenha());
            ps.setInt(4, usuario.getTipoUsuario());
            
            // Dados específicos de cada subclasse
            if (usuario instanceof Franqueado) {
                Franqueado franqueado = (Franqueado) usuario;
                ps.setInt(5, franqueado.getIdFranquiaLocal());
                ps.setNull(6, Types.VARCHAR); // nome_empresa é nulo para Franqueado
                ps.setString(7, franqueado.getNomeFranquia());
            
            } else if (usuario instanceof Franqueador) {
                Franqueador franqueador = (Franqueador) usuario;
                ps.setNull(5, Types.INTEGER); // id_franquia é nulo para Franqueador
                ps.setString(6, franqueador.getNomeEmpresa());
                ps.setNull(7, Types.VARCHAR); // nome_franquia é nulo para Franqueador
            }
            
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar usuário.", e);
        }
    }

    /**
     * Lista todos os usuários do banco, instanciando o tipo de objeto correto.
     * Lê a coluna 'tipo_usuario' para decidir se cria um Franqueado ou um Franqueador.
     */
    public List<Usuario> listar_usuarios() {
        var usuarios = new ArrayList<Usuario>();
        final String sql = "SELECT * FROM Usuario"; 

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int tipoUsuario = rs.getInt("tipo_usuario");
                
                // Cria a instância correta com base no tipo
                if (tipoUsuario == 1) { // 1 = Franqueado
                    usuarios.add(criarFranqueado(rs));
                } else { // Outros tipos = Franqueador
                    usuarios.add(criarFranqueador(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários.", e);
        }
        return usuarios;
    }

    /**
     * Busca um usuário pelo email e senha para fins de login.
     * Retorna o objeto do tipo correto (Franqueado ou Franqueador).
     */
    public Usuario buscarPorEmailESenha(String email, String senha) {
        final String sql = "SELECT * FROM Usuario WHERE email = ? AND senha = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            
            ps.setString(1, email);
            ps.setString(2, senha);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int tipoUsuario = rs.getInt("tipo_usuario");
                    
                    // Retorna a instância correta com base no tipo
                    if (tipoUsuario == 1) { // 1 = Franqueado
                        return criarFranqueado(rs);
                    } else { // Outros tipos = Franqueador
                        return criarFranqueador(rs);
                    }
                }
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário para login.", e);
        }
    }

    /**
     * Exclui um usuário do banco de dados pelo seu ID.
     * Este método não precisa de alterações, pois a exclusão é baseada apenas no ID.
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

    // Você precisará de um método de atualização que também use 'instanceof'
    // public void atualizar_usuario(Usuario usuario) { ... }


    // --- MÉTODOS AUXILIARES PARA EVITAR REPETIÇÃO DE CÓDIGO ---

    /**
     * Método auxiliar privado para criar um objeto Franqueado a partir de um ResultSet.
     */
    private Franqueado criarFranqueado(ResultSet rs) throws SQLException {
        return new Franqueado(
            rs.getInt("id"),
            rs.getString("email"),
            rs.getString("nome_usuario"),
            rs.getString("senha"),
            rs.getString("nome_franquia"),
            rs.getInt("id_franquia")
        );
    }

    /**
     * Método auxiliar privado para criar um objeto Franqueador a partir de um ResultSet.
     */
    private Franqueador criarFranqueador(ResultSet rs) throws SQLException {
        return new Franqueador(
            rs.getInt("id"),
            rs.getString("email"),
            rs.getString("nome_usuario"),
            rs.getString("senha"),
            rs.getInt("tipo_usuario"),
            rs.getString("nome_empresa")
        );
    }
}