package crm.model;

public class Venda {
    private int id;
    private int idCliente;
    private String descricao;
    private double valor;
    private String data;

    public Venda(int id, int idCliente, String descricao, double valor, String data) {
        this.id = id;
        this.idCliente = idCliente;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    public Venda(int idCliente, String descricao, double valor) {
        this(-1, idCliente, descricao, valor, "");
    }

    public int getId() { return id; }

    public int getIdCliente() { return idCliente; }

    public String getDescricao() { return descricao; }

    public double getValor() { return valor; }

    public String getData() { return data; }

    public void setId(int id) { this.id = id; }

    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public void setValor(double valor) { this.valor = valor; }

    public void setData(String data) { this.data = data; }


    @Override
    public String toString() {
        return "Venda{id=" + id + ", idCliente=" + idCliente + ", descricao='" + descricao + "', valor=" + valor + ", data='" + data + "'}";
    }
}
