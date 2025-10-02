package crm.dao;

import crm.model.Franquia;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FranquiaDAO {

    public void adicionar_franquias(Franquia franquia) {
        String url = "jdbc:sqlite:meu_banco.db";
        String sql = "INSERT INTO Franquia(nome, cidade, status, tipo_negocio) VALUES (?, ?, ?, ?)";

        try {
            var conexao = DriverManager.getConnection(url);
            var ps = conexao.prepareStatement(sql);

            ps.setString(1, franquia.getNome());
            ps.setString(2, franquia.getCidade());
            ps.setString(3, franquia.getStatus());
            ps.setString(4, franquia.getTipoNegocio()); // NOVO CAMPO
            ps.execute();

            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Franquia> listar_franquias() {
        var franquias = new ArrayList<Franquia>();
        String url = "jdbc:sqlite:meu_banco.db";

        try {
            var conexao = DriverManager.getConnection(url);
            var sql = "SELECT * FROM Franquia";
            var ps = conexao.prepareStatement(sql);
            var rs = ps.executeQuery();

            while (rs.next()) {
                var id = rs.getInt("id");
                var nome = rs.getString("nome");
                var cidade = rs.getString("cidade");
                var status = rs.getString("status");
                var tipo_negocio = rs.getString("tipo_negocio"); 

                var franquia = new Franquia(id, nome, cidade, status, tipo_negocio);
                franquias.add(franquia);
            }

            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return franquias;
    }

    public void excluir_franquias(Integer id_franquia) {
        String url = "jdbc:sqlite:meu_banco.db";

        try {
            var conexao = DriverManager.getConnection(url);
            var sql = "DELETE FROM Franquia WHERE id = ?";
            var ps = conexao.prepareStatement(sql);

            ps.setInt(1, id_franquia);
            ps.execute();

            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar_franquias(Franquia franquia) {
        String url = "jdbc:sqlite:meu_banco.db";
        String sql = "UPDATE Franquia SET nome = ?, cidade = ?, status = ?, tipo_negocio = ? WHERE id = ?";

        try {
            var conexao = DriverManager.getConnection(url);
            var ps = conexao.prepareStatement(sql);

            ps.setString(1, franquia.getNome());
            ps.setString(2, franquia.getCidade());
            ps.setString(3, franquia.getStatus());
            ps.setString(4, franquia.getTipoNegocio()); 
            ps.setInt(5, franquia.getId());           
            ps.execute();

            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}