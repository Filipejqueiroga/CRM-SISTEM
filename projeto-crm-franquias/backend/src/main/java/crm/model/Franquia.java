<<<<<<< HEAD
package src.main.java.crm.model;

=======
>>>>>>> develop
public class Franquia {
    private int id;
    private String nome;
    private  String cidade;
    private String status;

    public Franquia(int id, String nome, String cidade, String status) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.status = status;
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
}