package crm.model;

public class Franquia {
    private int id;
    private String nome;
    private String cidade;
    private String status;
    private String tipoNegocio;


    public Franquia(int id, String nome, String cidade, String status) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.status = status;
        this.tipoNegocio = "";
    }


    public Franquia(int id, String nome, String cidade, String status, String tipoNegocio) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.status = status;
        this.tipoNegocio = tipoNegocio;
    }


    public int getId() { return id; }

    public String getNome() { return nome; }

    public String getCidade() { return cidade; }

    public String getStatus() { return status; }

    public String getTipoNegocio() { return tipoNegocio; }


    public void setId(int id) { this.id = id; }

    public void setNome(String nome) { this.nome = nome; }

    public void setCidade(String cidade) { this.cidade = cidade; }

    public void setStatus(String status) { this.status = status; }

    public void setTipoNegocio(String tipoNegocio) { this.tipoNegocio = tipoNegocio; }


    @Override
    public String toString() {
        return "Franquia{id=" + id + ", nome='" + nome + "', cidade='" + cidade + "', status='" + status + "'}";
    }
}
}