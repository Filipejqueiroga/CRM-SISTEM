package crm.dao;

import crm.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    
    private static final String URL = "jdbc:sqlite:meu_banco.db";

    public void adicionar_usuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario(email, nome_usuario, senha, id_franquia) VALUES (?, ?, ?, ?)";

        try {
            var conexao = DriverManager.getConnection(URL);
            var ps = conexao.prepareStatement(sql);

            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getNomeUsuario());
            ps.setString(3, usuario.getSenha());
            ps.setInt(4, usuario.getIdFranquia()); 
            
            ps.execute();

            ps.close();
            conexao.close();
                
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar usuário.", e);
        }
    }
    
    public List<Usuario> listar_usuarios() {
        var usuarios = new ArrayList<Usuario>();
        String sql = "SELECT id, email, nome_usuario, senha, id_franquia FROM Usuario"; 

        try {
            var conexao = DriverManager.getConnection(URL);
            var ps = conexao.prepareStatement(sql);
            var rs = ps.executeQuery();

            while (rs.next()) {
                var id = rs.getInt("id");
                var email = rs.getString("email");
                var nomeUsuario = rs.getString("nome_usuario");
                var senha = rs.getString("senha");
                var idFranquia = rs.getInt("id_franquia");

                var usuario = new Usuario(id, email, nomeUsuario, senha, idFranquia);
                usuarios.add(usuario);
            }

            rs.close(); 
            ps.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários.", e);
        }
        return usuarios;
    }
    
    public void excluir_usuario(Integer id_usuario) {
        String sql = "DELETE FROM Usuario WHERE id = ?";

        try {
            var conexao = DriverManager.getConnection(URL);
            var ps = conexao.prepareStatement(sql);

            ps.setInt(1, id_usuario);
            ps.execute();

            ps.close();
            conexao.close();
                
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir usuário.", e);
        }
    }
    
    public void atualizar_usuario(Usuario usuario) {
        String sql = "UPDATE Usuario SET email = ?, nome_usuario = ?, senha = ?, id_franquia = ? WHERE id = ?";

        try {
            var conexao = DriverManager.getConnection(URL);
            var ps = conexao.prepareStatement(sql);

            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getNomeUsuario());
            ps.setString(3, usuario.getSenha());
            ps.setInt(4, usuario.getIdFranquia()); 
            ps.setInt(5, usuario.getId());         
            
            ps.execute();

            ps.close();
            conexao.close();
                
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário.", e);
        }
    }
    
    public Usuario buscarPorEmailESenha(String email, String senha) {
        String sql = "SELECT id, email, nome_usuario, senha, id_franquia FROM Usuario WHERE email = ? AND senha = ?";

        try {
            var conexao = DriverManager.getConnection(URL);
            var ps = conexao.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, senha);
            
            var rs = ps.executeQuery();
            
            if (rs.next()) {
                var id = rs.getInt("id");
                var nomeUsuario = rs.getString("nome_usuario");
                var idFranquia = rs.getInt("id_franquia");

                return new Usuario(id, email, nomeUsuario, senha, idFranquia);
            }

            rs.close(); 
            ps.close();
            conexao.close();

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário para login.", e);
        }
    }
}