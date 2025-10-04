package crm.dao;

import crm.model.Checkin;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckinDAO {

    public void adicionar_checkin(Checkin checkin) {
        String url = "jdbc:sqlite:meu_banco.db";
        // <-- CORREÇÃO: Adicionado 'franquia_id' ao SQL
        String sql = "INSERT INTO Checkin (cliente_id, usuario_id, franquia_id, data, hora) VALUES (?, ?, ?, ?, ?)";

        try (var conexao = DriverManager.getConnection(url);
             var ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, checkin.getClienteId());
            ps.setInt(2, checkin.getUsuarioId());
            ps.setInt(3, checkin.getFranquiaId()); // <-- CORREÇÃO: Adicionado o parâmetro 'franquia_id'
            ps.setString(4, checkin.getData());
            ps.setString(5, checkin.getHora());
            ps.execute();

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar check-in: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<Checkin> listar_checkins() {
        var checkins = new ArrayList<Checkin>();
        String url = "jdbc:sqlite:meu_banco.db";
        String sql = "SELECT * FROM Checkin";

        try (var conexao = DriverManager.getConnection(url);
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

    public List<Checkin> listar_checkins_franquia(int idFranquia) {
        var checkins = new ArrayList<Checkin>();
        String url = "jdbc:sqlite:meu_banco.db";
        String sql = "SELECT * FROM Checkin WHERE franquia_id = ?";

        try (var conexao = DriverManager.getConnection(url);
             var ps = conexao.prepareStatement(sql)) {
            
            ps.setInt(1, idFranquia);
            try (var rs = ps.executeQuery()) {
                while (rs.next()) {
                    var id = rs.getInt("id");
                    var cliente_id = rs.getInt("cliente_id");
                    var usuario_id = rs.getInt("usuario_id");
                    int franquia_id = rs.getInt("franquia_id");
                    var data = rs.getString("data");
                    var hora = rs.getString("hora");

                    checkins.add(new Checkin(id, cliente_id, usuario_id, franquia_id, data, hora));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar check-ins por franquia: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return checkins;
    }

    public void atualizar_checkin(Checkin checkin) {
        String url = "jdbc:sqlite:meu_banco.db";
        // <-- CORREÇÃO: Adicionado 'franquia_id = ?' ao SQL
        String sql = "UPDATE Checkin SET cliente_id = ?, usuario_id = ?, franquia_id = ?, data = ?, hora = ? WHERE id = ?";

        try (var conexao = DriverManager.getConnection(url);
             var ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, checkin.getClienteId());
            ps.setInt(2, checkin.getUsuarioId());
            ps.setInt(3, checkin.getFranquiaId()); // <-- CORREÇÃO: Adicionado o parâmetro 'franquia_id'
            ps.setString(4, checkin.getData());
            ps.setString(5, checkin.getHora());
            ps.setInt(6, checkin.getId());
            ps.execute();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar check-in: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void excluir_checkin(Integer id_checkin) {
        String url = "jdbc:sqlite:meu_banco.db";
        String sql = "DELETE FROM Checkin WHERE id = ?";

        try (var conexao = DriverManager.getConnection(url);
             var ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, id_checkin);
            ps.execute();

        } catch (SQLException e) {
            System.err.println("Erro ao excluir check-in: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}