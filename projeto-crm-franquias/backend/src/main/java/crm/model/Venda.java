package crm.model;

/**
 * Representa uma entidade de Venda no sistema.
 * Esta classe é um modelo de dados (POJO) que armazena as informações
 * de uma transação de venda.
 *
 * @version 1.1
 * @since 2025-10-05
 */
public class Venda {

    private int id;
    private int idCliente;
    private int franquiaId; 
    private String descricao;
    private double valor;
    private String data;

    public Venda(int id, int idCliente, int franquiaId, String descricao, double valor, String data) {
        this.id = id;
        this.idCliente = idCliente;
        this.franquiaId = franquiaId;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    // --- Getters e Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getFranquiaId() {
        return franquiaId;
    }

    public void setFranquiaId(int franquiaId) {
        this.franquiaId = franquiaId;
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

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", idCliente=" + idCliente +
                ", franquiaId=" + franquiaId +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", data='" + data + '\'' +
                '}';
    }
}