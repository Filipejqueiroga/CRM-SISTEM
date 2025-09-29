package crm.model;

public class Lead {
    private int id;
    private String nome;
    private String numero_telefone;
    private String status;

    public Lead(int id, String nome, String numero_telefone, String status) {
        this.id =id;
        this.nome = nome;
        this.numero_telefone = numero_telefone;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero_telefone() {
        return numero_telefone;
    }

    public void setNumero_telefone(String numero_telefone) {
        this.numero_telefone = numero_telefone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}