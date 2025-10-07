package crm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String numeroTelefone;
    private String tipoPlano;
    @Column(name = "franquia_id")
    private Integer idFranquia;

    public Cliente(){}

    public Cliente(Integer id, String nome, String numeroTelefone, String tipoPlano, Integer idFranquia) {
        this.id = id;
        this.nome = nome;
        this.numeroTelefone = numeroTelefone;
        this.tipoPlano = tipoPlano;
        this.idFranquia = idFranquia;
    }

    public Cliente(String nome, String numeroTelefone, String tipoPlano, Integer idFranquia) {
        this(-1, nome, numeroTelefone, tipoPlano, idFranquia);
    }

    public Integer getId() { return id; }

    public String getNome() { return nome; }

    public String getNumeroTelefone() { return numeroTelefone; }

    public String getTipoPlano() { return tipoPlano; }

    public Integer getIdFranquia() { return idFranquia; }

    public void setId(Integer id) { this.id = id; }

    public void setNome(String nome) { this.nome = nome; }

    public void setNumeroTelefone(String numeroTelefone) { this.numeroTelefone = numeroTelefone; }

    public void setTipoPlano(String tipoPlano) { this.tipoPlano = tipoPlano; }

    public void setIdFranquia(Integer idFranquia) { this.idFranquia = idFranquia; }


    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nome='" + nome + "', telefone='" + numeroTelefone + "'}";
    }
}
