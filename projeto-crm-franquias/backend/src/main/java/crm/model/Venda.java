package crm.model;

import jakarta.persistence.*;

@Entity 
@Table(name = "Venda") 
public class Venda {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id_cliente")
    private int idCliente;
    @Column(name = "franquia_id")
    private int idFranquia;
    private String descricao;
    private double valor;
    private String data;

    public Venda() {}

    public Venda(Integer id, int idCliente, int idFranquia, String descricao, double valor, String data) {
        this.id = id;
        this.idCliente = idCliente;
        this.idFranquia = idFranquia;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    public Venda(int idCliente, int idFranquia, String descricao, double valor, String data) {
        this.idCliente = idCliente;
        this.idFranquia = idFranquia;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
  }

    public Venda(int idCliente, String descricao, double valor) {
        this(null, idCliente, 0, descricao, valor, null); 
    }

    public Integer getId() { return id; }

    public int getIdCliente() { return idCliente; }

    public String getDescricao() { return descricao; }

    public double getValor() { return valor; }

    public String getData() { return data; }

    public int getIdFranquia() {
        return idFranquia;
    }

    public void setIdFranquia(int idFranquia) {
        this.idFranquia = idFranquia;
    }

    public void setId(Integer id) { this.id = id; }

    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public void setValor(double valor) { this.valor = valor; }

    public void setData(String data) { this.data = data; }


    @Override
    public String toString() {
        return "Venda{id=" + id + ", idCliente=" + idCliente + ", idFranquia="+idFranquia+ "descricao='" + descricao + "', valor=" + valor + ", data='" + data + "'}";
    }
}
