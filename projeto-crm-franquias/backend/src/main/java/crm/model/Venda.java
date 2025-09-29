package crm.model;

public class Venda {

    private int id;
    private int id_cliente;
    private String descricao;
    private double valor;
    private String data;

    public Venda(int id, int id_cliente, String descricao, double valor, String data) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data){
        this.data = data;
    }
}