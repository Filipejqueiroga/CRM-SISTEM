package crm.model;

public class Franquia {
    private int id;
    private String nome;
    private  String cidade;
    private String status;
    private String tipo_negocio;

    public Franquia(int id, String nome, String cidade, String status, String tipo_negocio) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.status = status;
        this.tipo_negocio = tipo_negocio;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCidade() {
        return this.cidade;
    }

    public String getStatus() {
        return this.status;
    }

    public String getTipo_negocio() {
        return tipo_negocio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTipo_negocio(String tipo_negocio) {
        this.tipo_negocio = tipo_negocio;
    }

}