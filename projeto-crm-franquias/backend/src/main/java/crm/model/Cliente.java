package crm.model;

public class Cliente {

    private int id;
    private String nome;
    private String numeroTelefone;
    private String tipoPlano;
    private int idFranquia;

    public Cliente(int id, String nome, String numeroTelefone, String tipoPlano, int idFranquia) {
        this.id = id;
        this.nome = nome;
        this.numeroTelefone = numeroTelefone;
        this.tipoPlano = tipoPlano;
        this.idFranquia = idFranquia;
    }

    public Cliente(String nome, String numeroTelefone, String tipoPlano, int idFranquia) {
        this(-1, nome, numeroTelefone, tipoPlano, idFranquia);
    }

    public int getId() { return id; }

    public String getNome() { return nome; }

    public String getNumeroTelefone() { return numeroTelefone; }

    public String getTipoPlano() { return tipoPlano; }

    public int getIdFranquia() { return idFranquia; }

    public void setId(int id) { this.id = id; }

    public void setNome(String nome) { this.nome = nome; }

    public void setNumeroTelefone(String numeroTelefone) { this.numeroTelefone = numeroTelefone; }

    public void setTipoPlano(String tipoPlano) { this.tipoPlano = tipoPlano; }

    public void setIdFranquia(int idFranquia) { this.idFranquia = idFranquia; }


    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nome='" + nome + "', telefone='" + numeroTelefone + "'}";
    }
}
