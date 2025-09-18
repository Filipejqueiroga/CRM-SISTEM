public class Academia extends Franquia {
    String registro_entrada;

    public Academia(int id, String nome, String cidade, String status, String registro_entrada) {
        super(id, nome, cidade, status);
        this.registro_entrada = registro_entrada;
    }

    public String getRegistro_entrada() {
        return this.registro_entrada;
    }

    public void setRegistro_entrada(String registro_entrada) {
        this.registro_entrada = registro_entrada;
    }
}