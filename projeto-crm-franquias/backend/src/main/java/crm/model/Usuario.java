package crm.model;

public class Usuario {
    private int id;
    private String email;
    private String nome_usuario;
    private String senha;
    private int id_franquia;

    public Usuario(int id, String email, String nome_usuario, String senha, int id_franquia) {
        this.id = id;
        this.email = email;
        this.nome_usuario = nome_usuario;
        this.senha = senha;
        this.id_franquia = id_franquia;
    }

    // Métodos Get
    public int getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNome_usuario() {
        return this.nome_usuario;
    }

    public String getSenha() {
        return this.senha;
    }
    public int getId_Franquia(){
        return this.id_franquia;
    }

    // Métodos Set
    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setId_Franquia(int id_franquia){
        this.id_franquia = id_franquia;
    }

    // Método toString
    public String toString() {
        return "Nome do usuário: " + nome_usuario + "\n" + "Email do usuário: " + email;
    }
}