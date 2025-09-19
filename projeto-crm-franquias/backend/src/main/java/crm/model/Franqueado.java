<<<<<<< HEAD
package src.main.java.crm.model;

=======
>>>>>>> develop
public class Franqueado extends Usuario {
    private String nome_franquia;

    public Franqueado(int id, String email, String nome_usuario, String senha, String nome_franquia) {
        super(id, email, nome_usuario, senha);
        this.nome_franquia = nome_franquia;
    }

    public String getNome_franquia() {
        return this.nome_franquia;
    }

    public void setNome_franquia(String nome_franquia) {
        this.nome_franquia = nome_franquia;
    }

    @Override
    public String toString() {
        return "Nome do Franqueado: " + getNome_usuario() + "\n" + "Franquia: " + nome_franquia;
    }
}