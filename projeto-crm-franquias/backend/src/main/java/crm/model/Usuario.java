<<<<<<< HEAD
package src.main.java.crm.model;

=======
>>>>>>> develop
public class Usuario {
    private int id;
    private String email;
    private String nome_usuario;
    private String senha;

    public Usuario(int id, String email, String nome_usuario, String senha) {
        this.id = id;
        this.email = email;
        this.nome_usuario = nome_usuario;
        this.senha = senha;
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

    // Método toString
    public String toString() {
        return "Nome do usuário: " + nome_usuario + "\n" + "Email do usuário: " + email;
    }
}