<<<<<<< HEAD
package src.main.java.crm.model;

=======
>>>>>>> develop
public class Lead {

    private String nome;
    private String numero_telefone;
    private String status;

    public Lead(String nome, String numero_telefone, String status) {
        this.nome = nome;
        this.numero_telefone = numero_telefone;
        this.status = status;
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