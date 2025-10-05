package crm.model;

/**
 * Representa uma entidade de Lead no sistema.
 * Um Lead é um cliente em potencial que demonstrou interesse, mas ainda não
 * realizou uma compra.
 *
 * @version 1.1
 * @since 2025-10-05
 */
public class Lead {

    private int id;
    private String nome;
    private String numeroTelefone;
    private String status;
    private int franquiaId; // Campo adicionado para alinhar com o banco de dados

    /**
     * Construtor padrão.
     */
    public Lead() {
    }

    /**
     * Construtor completo para criar uma instância de Lead com todos os dados.
     *
     * @param id O ID único do lead.
     * @param nome O nome do lead.
     * @param numeroTelefone O número de telefone para contato.
     * @param status O status atual do lead (ex: 'em negociação').
     * @param franquiaId O ID da franquia à qual este lead está associado.
     */
    public Lead(int id, String nome, String numeroTelefone, String status, int franquiaId) {
        this.id = id;
        this.nome = nome;
        this.numeroTelefone = numeroTelefone;
        this.status = status;
        this.franquiaId = franquiaId;
    }

    // --- Getters e Setters ---

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

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFranquiaId() {
        return franquiaId;
    }

    public void setFranquiaId(int franquiaId) {
        this.franquiaId = franquiaId;
    }

    @Override
    public String toString() {
        return "Lead{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", numeroTelefone='" + numeroTelefone + '\'' +
                ", status='" + status + '\'' +
                ", franquiaId=" + franquiaId +
                '}';
    }
}