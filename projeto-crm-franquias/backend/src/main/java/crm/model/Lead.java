package crm.model;

import jakarta.persistence.*;

@Entity 
@Table(name = "Lead")
public class Lead {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @Column(name = "numero_telefone")
    private String numeroTelefone;
    private String status;

    public Lead(){}

    public Lead(Integer id, String nome, String numeroTelefone, String status) {
        this.id = id;
        this.nome = nome;
        this.numeroTelefone = numeroTelefone;
        this.status = status;
    }

    public Lead(String nome, String numeroTelefone, String status) {
        this(-1, nome, numeroTelefone, status);
    }

    public Integer getId() { return id; }

    public String getNome() { return nome; }

    public String getNumeroTelefone() { return numeroTelefone; }

    public String getStatus() { return status; }

    public void setId(Integer id) { this.id = id; }

    public void setNome(String nome) { this.nome = nome; }

    public void setNumeroTelefone(String numeroTelefone) { this.numeroTelefone = numeroTelefone; }

    public void setStatus(String status) { this.status = status; }


    @Override
    public String toString() {
        return "Lead{id=" + id + ", nome='" + nome + "', telefone='" + numeroTelefone + "', status='" + status + "'}";
    }
}
