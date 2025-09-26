package crm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

import crm.model.Cliente;


public class ClienteDAO{

    public void adicionar_clientes(Cliente cliente){
        String url = "jdbc:sqlite:meu_banco.db";

        try {
            var conexao = DriverManager.getConnection(url);
            var sql = "INSERT INTO Cliente(nome, franquia_id) VALUES (?, ?, ?, ?)";
            var ps = conexao.prepareStatement(sql);

            ps.setString(1, cliente.getNome());
            ps.setInt(2, cliente.getId_franquia());
            ps.setString(3, cliente.getNumero_telefone());
            ps.setString(4, cliente.getTipo_plano());
            
            ps.execute();

            ps.close();
            conexao.close();
                
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Cliente> listar_clientes(){
        var clientes = new ArrayList<Cliente>();
        String url = "jdbc:sqlite:meu_banco.db";

        try {
        var conexao = DriverManager.getConnection(url);
        var sql = "SELECT * FROM cliente";
        var ps = conexao.prepareStatement(sql);
        var rs = ps.executeQuery(); // execute Query é tipo uma escada pela tabela

        while (rs.next()){
            var id = rs.getInt("id");
            var nome = rs.getString("nome");
            var numero_telefone = rs.getString("numero_telefone");
            var tipo_plano = rs.getString("tipo_plano");
            var id_franquia = rs.getInt("id_franquia");

             var cliente = new Cliente(id, nome, numero_telefone, tipo_plano, id_franquia);

             clientes.add(cliente);
        }

        ps.close();
        conexao.close();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clientes;

    }

    public void exluir_clientes(Cliente cliente){

    }

    public void atualizar_clientes(Cliente cliente){

    }
    
}