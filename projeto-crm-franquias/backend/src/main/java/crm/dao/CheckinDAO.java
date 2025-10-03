package crm.dao;

import crm.model.Checkin;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckinDAO {

    public void adicionar_checkin(Checkin checkin) {
        String url = "jdbc:sqlite:meu_banco.db";
        String sql = "INSERT INTO Checkin (cliente_id, usuario_id, data, hora) VALUES (?, ?, ?, ?)";

        try {
            var conexao = DriverManager.getConnection(url);
            var ps = conexao.prepareStatement(sql);

            ps.setInt(1, checkin.getClienteId());
            ps.setInt(2, checkin.getUsuarioId());
            ps.setString(3, checkin.getData());
            ps.setString(4, checkin.getHora());
            ps.execute();

            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Checkin> listar_checkins() {
        var checkins = new ArrayList<Checkin>();
        String url = "jdbc:sqlite:meu_banco.db";

        try {
            var conexao = DriverManager.getConnection(url);
            var sql = "SELECT * FROM Checkin";
            var ps = conexao.prepareStatement(sql);
            var rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int cliente_id = rs.getInt("cliente_id");
                int usuario_id = rs.getInt("usuario_id");
                int franquia_id = rs.getInt("franquia_id");
                String data = rs.getString("data");
                String hora = rs.getString("hora");

                checkins.add(new Checkin(id, cliente_id, usuario_id, franquia_id, data, hora));
            }

            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return checkins;
    }

    public List<Checkin> listar_checkins_franquia(int idFranquia){
        var checkins = new ArrayList<Checkin>();
        String url = "jdbc:sqlite:meu_banco.db";

        try {
            var conexao = DriverManager.getConnection(url);
            var sql = "SELECT * FROM Checkin WHERE id_franquia = ?";
            var ps = conexao.prepareStatement(sql);
            ps.setInt(1, idFranquia);
            var rs = ps.executeQuery();

            while (rs.next()) {
                var id = rs.getInt("id");
                var cliente_id = rs.getInt("cliente_id");
                var usuario_id = rs.getInt("usuario_id");
                int franquia_id = rs.getInt("franquia_id");
                var data = rs.getString("data");
                var hora = rs.getString("hora");

                checkins.add(new Checkin(id, cliente_id, usuario_id, franquia_id, data, hora));
            }
            rs.close();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return checkins;
    }

    public void atualizar_checkin(Checkin checkin) {
        String url = "jdbc:sqlite:meu_banco.db";
        String sql = "UPDATE Checkin SET cliente_id = ?, usuario_id = ?, data = ?, hora = ? WHERE id = ?";

        try {
            var conexao = DriverManager.getConnection(url);
            var ps = conexao.prepareStatement(sql);

            ps.setInt(1, checkin.getClienteId());
            ps.setInt(2, checkin.getUsuarioId());
            ps.setString(3, checkin.getData());
            ps.setString(4, checkin.getHora());
            ps.setInt(5, checkin.getId());
            ps.execute();

            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir_checkin(Integer id_checkin) {
        String url = "jdbc:sqlite:meu_banco.db";

        try {
            var conexao = DriverManager.getConnection(url);
            var sql = "DELETE FROM Checkin WHERE id = ?";
            var ps = conexao.prepareStatement(sql);

            ps.setInt(1, id_checkin);
            ps.execute();

            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}