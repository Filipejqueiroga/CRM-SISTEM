package src.main.java.crm.model;

public class Cliente {

    private int id;
    private String nome;
    private String numero_telefone;
    private String tipo_plano;
    private int id_franquia;

    public Cliente(int id, String nome, String numero_telefone, String tipo_plano, int id_franquia) {
        this.id = id;
        this.nome = nome;
        this.numero_telefone = numero_telefone;
        this.tipo_plano = tipo_plano;
        this.id_franquia = id_franquia;
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

    public String getTipo_plano() {
        return tipo_plano;
    }

    public void setTipo_plano(String tipo_plano) {
        this.tipo_plano = tipo_plano;
    }

    public int getId_franquia() {
        return id_franquia;
    }

    public void setId_franquia(int id_franquia) {
        this.id_franquia = id_franquia;
    }
}