<<<<<<< HEAD
package src.main.java.crm.model;

=======
>>>>>>> develop
public class Venda {

    private int id;
    private int id_cliente;
    private String descricao;
    private double valor;

    public Venda(int id, int id_cliente, String descricao, double valor) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.descricao = descricao;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}